package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.bli.mez.model.Employee;
import ch.bli.mez.model.Mission;
import ch.bli.mez.model.Position;
import ch.bli.mez.model.TimeEntry;
import ch.bli.mez.model.dao.EmployeeDAO;
import ch.bli.mez.model.dao.MissionDAO;
import ch.bli.mez.model.dao.PositionDAO;
import ch.bli.mez.model.dao.TimeEntryDAO;
import ch.bli.mez.view.time.TimeListEntry;
import ch.bli.mez.view.time.TimePanel;
import ch.bli.mez.view.time.TimeView;

public class TimeController {

  private EmployeeDAO modelemployee;
  private TimeView view;
  private TimeEntryDAO model;
  private MissionDAO missionModel;
  private PositionDAO positionModel;
  private final SearchController searchController;
  private List<Position> positions;
  private List<Mission> missions;

  public TimeController() {
    this.model = new TimeEntryDAO();
    this.missionModel = new MissionDAO();
    this.positionModel = new PositionDAO();
    this.modelemployee = new EmployeeDAO();
    this.searchController = new SearchController();
    this.positions = positionModel.findAll();
    this.missions = missionModel.findAll();

    this.view = new TimeView(searchController.getSearchPanel());
    setListener();
    addTabs();
  }

  public TimeView getView() {
    return view;
  }

  private void addTabs() {
    view.removeAllTab();
    for (Employee employee : modelemployee.findAll()) {
      addAdditionalTab(employee);
    }
  }

  public void addAdditionalTab(Employee employee) {
    view.addTab(employee.getFirstName() + " " + employee.getLastName(),
        createTimePanel(employee));
  }

  public void update() {
    this.positions = positionModel.findAll();
    this.missions = missionModel.findAll();

    addTabs();
  }

  private TimePanel createTimePanel(Employee employee) {
    TimePanel form = new TimePanel();
    setFormActionListeners(form, employee);

    addTimeEntrys(employee, form);
    preFillTimeEntry(employee, form);
    return form;
  }

  private void addTimeEntrys(Employee employee, TimePanel timePanel) {
    for (TimeEntry timeEntry : model.findAll(employee)) {
      timePanel.addTimeListEntry(createTimeListEntry(timeEntry));
    }
  }

  private TimeListEntry createTimeListEntry(final TimeEntry timeEntry) {
    final TimeListEntry timeListEntry = new TimeListEntry();

    timeListEntry.setMission(timeEntry.getMission().getMissionName());
    Calendar calendar = timeEntry.getDate();
    Date date = calendar.getTime();
    SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
    String date1 = format1.format(date);
    timeListEntry.setDate(date1);
    timeListEntry.setPosition(timeEntry.getPosition()
        .getCode());
    timeListEntry.setWorktime(parseMinutesToWorkTime(timeEntry.getWorktime()));

    setTimeListEntryActionListeners(timeListEntry, timeEntry);

    return timeListEntry;
  }

  /**
   * Füllt die Eingabefelder für einen Mitarbeiter mit dem entsprechenden
   * Zeiteintrag aus (meistens der aktuellste bzw. letzte)
   * 
   * @param timeEntry
   */
  private void preFillTimeEntry(Employee employee, TimePanel form) {

    // TODO
    // Wie am besten letzter Eintrag im TimeEntry finden??
    List<TimeEntry> timeEntries = model.findAll(employee);

    if (timeEntries.size() != 0) {
      form.setMission(timeEntries.get(0).getMission().getMissionName());
      Calendar calendar = timeEntries.get(0).getDate();
      Date date = calendar.getTime();
      SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
      String date1 = format1.format(date);
      form.setDate(date1);
      form.setPosition(timeEntries.get(0).getPosition()
          .getCode());
    }
  }

  public void setFormActionListeners(final TimePanel form,
      final Employee employee) {
    form.setSaveTimeListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {

        if (!validateFields(form)) {
          return;
        }
        TimeEntry timeEntry = new TimeEntry();
        timeEntry.setEmployee(employee);

        if (updateTimeEntry(timeEntry, form) != null) {
          model.addTimeEntry(timeEntry);
          form.addTimeListEntry(createTimeListEntry(timeEntry));
          form.showConfirmation("Der Zeiteintrag wurde gespeichert");
          form.cleanFields();
        }
      }
    });
  }

  public void setTimeListEntryActionListeners(
      final TimeListEntry timeListEntry, final TimeEntry timeEntry) {

    timeListEntry.setSaveTimeEntryListListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {

        if (!validateFields(timeListEntry)) {
          return;
        }
        TimeEntry timeEntry = new TimeEntry();

        timeEntry.setWorktime(parseWorkTimeToMinutes(timeListEntry
            .getWorktime()));
        if (updateTimeEntry(timeEntry, timeListEntry) != null) {
          model.updateTimeEntry(timeEntry);
          timeListEntry.showSuccess();
        }

      }
    });

    timeListEntry.setDeleteTimeEntryListListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        // TODO
        model.deleteTimeEntry(timeEntry.getId());
      }
    });
  }

  private Calendar createDate(String date) {
    String splittedDate[] = date.split("\\.");
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, Integer.parseInt(splittedDate[2]));
    calendar.set(Calendar.MONTH, Integer.parseInt(splittedDate[1]) - 1);
    calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(splittedDate[0]));
    return calendar;
  }

  private Mission findMissionByName(String missionName) {
    for (Mission mission : missions) {
      if (mission.getMissionName().equals(missionName)) {
        return mission;
      }
    }
    return null;
  }

  private Position findPositionByCode(String code) {
    for (Position position : positions) {
      if (position.getCode().equals(code)) {
        return position;
      }
    }
    return null;
  }

  public TimeEntry updateTimeEntry(TimeEntry timeEntry, TimeInterface form) {
    timeEntry.setWorktime(parseWorkTimeToMinutes(form.getWorktime()));
    timeEntry.setDate(createDate(form.getDate()));
    Position position = findPositionByCode(form.getPosition());
    Mission mission = findMissionByName(form.getMission());

    if (position != null) {
      timeEntry.setPosition(position);
    } else {
      return null;
    }
    if (mission != null) {
      timeEntry.setMission(mission);
    } else {
      return null;
    }
    return timeEntry;
  }

  public boolean validateFields(TimeInterface panel) {
    boolean valid = true;
    String errortext = "Die Felder ";

    if (panel.getDate().equals("")) {
      panel.showDateError();
      errortext = errortext + "Datum ";
      valid = false;
    }
    if (panel.getMission().equals("")
        || findMissionByName(panel.getMission()) == null) {
      panel.showMissionError();
      errortext = errortext + "Auftrag ";
      valid = false;
    }
    if (panel.getPosition().equals("")
        || findPositionByCode(panel.getPosition()) == null) {
      panel.showPositionError();
      errortext = errortext + "Position ";
      valid = false;
    }

    if (panel.getWorktime().equals("")
        || (panel.getWorktime().matches("[0-9]*[:,.]{1}[0-9]{2}") || panel
            .getWorktime().matches("[0-9]*")) == false) {
      panel.showWorktimeError();
      errortext = errortext + "Zeit ";
      valid = false;
    }

    if (valid == false) {
      errortext = errortext + "müssen gültig ausgefüllt sein ";
      panel.showErrorMessage(errortext);
    }

    return valid;
  }

  /**
   * Nimmt die Zeit im Format 1:30, 0:00 oder auch nur Minuten entgegen. Es wird
   * hier mit RegEx gearbeitet.
   * 
   * @param worktime
   *          , Eingabefeld des Formulars
   * @return Worktime in Minuten
   */
  private Integer parseWorkTimeToMinutes(String worktime) {
    Integer workminutes = 0;

    if (worktime != null) {
      if (worktime.matches("[0-9]*")) {
        workminutes = Integer.parseInt(worktime);
      } else if (worktime.matches("[0-9]*[:,.]{1}[0-9]{2}")) {
        String workhours[] = worktime.split("[:,.]");
        workminutes = Integer.parseInt(workhours[0]) * 60
            + Integer.parseInt(workhours[1]);
      }
    }

    return workminutes;

  }

  private String parseMinutesToWorkTime(Integer workminutes) {
    String worktime = "";

    if ((workminutes / 60) > 0) {
      worktime = worktime + (workminutes / 60);
    } else {
      worktime = "0";
    }

    worktime = worktime + ":";

    if (workminutes % 60 < 10) {
      worktime = (workminutes / 60) + ":0" + workminutes % 60;
    } else {
      worktime = (workminutes / 60) + ":" + workminutes % 60;
    }

    return worktime;
  }

  private void setListener() {
    view.setTabListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        if (((JTabbedPane) e.getSource()).getSelectedIndex() == 1) {
          // TODO not used so far..
        }
      }
    });
  }
}
