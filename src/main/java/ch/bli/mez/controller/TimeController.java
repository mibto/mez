package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    form.addHeadInput(createTimeListEntry(null, employee, true));
    form.addTimeListEntrys(getExistingTimeEntrys(employee));

    return form;
  }

  /*
   * Sucht alle bestehenden Zeiteinträge eines Employees raus Pro Zeiteintrag
   * wird ein Panel dafür erstellt Das ganze wird als Liste an die Form gegeben,
   * welche diese selbstständig bei sich am auflistet
   */
  private List<TimeListEntry> getExistingTimeEntrys(Employee employee) {
    List<TimeListEntry> timeListEntrys = new ArrayList<TimeListEntry>();
    for (TimeEntry timeEntry : model.findAll(employee)) {
      timeListEntrys.add(createTimeListEntry(timeEntry, employee, false));
    }
    return timeListEntrys;
  }

  /*
   * Fügt dem aktiven employee-Panel ein neues Listenelement hinzu
   */
  private void addAdditionalTimeEntryInList(TimeEntry timeEntry,
      Employee employee) {
    view.getSelectedTabComponent().addAdditionalTimeListEntry(
        createTimeListEntry(timeEntry, employee, false));
  }

  /*
   * Erstellt ein Panel mit einem Zetieintrag isNewTimeEntry: false =
   * Listenelement, true = für Kopf
   */
  private TimeListEntry createTimeListEntry(TimeEntry timeEntry,
      Employee employee, Boolean isNewTimeEntry) {

    TimeListEntry timeListEntry = new TimeListEntry();

    if (!isNewTimeEntry) {
      timeListEntry.setId(timeEntry.getId());
      timeListEntry.setMission(timeEntry.getMission().getMissionName());
      Calendar calendar = timeEntry.getDate();
      Date date = calendar.getTime();
      SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
      String date1 = format1.format(date);
      timeListEntry.setDate(date1);
      timeListEntry.setPosition(timeEntry.getPosition().getCode());
      timeListEntry
          .setWorktime(parseMinutesToWorkTime(timeEntry.getWorktime()));
    } else {
      preFillTimeEntry(employee, timeListEntry);
    }

    setTimeListEntryActionListeners(timeListEntry, employee, isNewTimeEntry);

    return timeListEntry;
  }

  /*
   * Füllt den Head aus (anhand des letzten Zeiteintrags) Nur die "Zeit" bleibt
   * leer.
   */
  private void preFillTimeEntry(Employee employee, TimeListEntry form) {
    // TODO setzt die letzte Eingabe beim Eintragen oben
    // Wie am besten letzter Eintrag im TimeEntry finden??
    List<TimeEntry> timeEntries = model.findAll(employee);
    if (timeEntries.size() != 0) {
      form.setMission(timeEntries.get(0).getMission().getMissionName());
      Calendar calendar = timeEntries.get(0).getDate();
      Date date = calendar.getTime();
      SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
      String date1 = format1.format(date);
      form.setDate(date1);
      form.setPosition(timeEntries.get(0).getPosition().getCode());
    }
  }

  /*
   * Setzt alle Listeners vom Panel "TimeListEntry" (Speicher- und
   * Delete-Button)
   */
  private void setTimeListEntryActionListeners(
      final TimeListEntry timeListEntry, final Employee employee,
      final Boolean isNewTimeEntry) {

    timeListEntry.setSaveTimeEntryListListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {

        if (!timeListEntry.validateFields()) {
          return;
        }

        if (isNewTimeEntry) {
          TimeEntry timeEntry = new TimeEntry();

          timeEntry.setEmployee(employee);

          if (updateTimeEntry(timeEntry, timeListEntry) != null) {
            model.addTimeEntry(timeEntry);
            timeListEntry.showSuccess();
            timeListEntry.cleanFields();
            addAdditionalTimeEntryInList(timeEntry, employee);
          }

        } else {
          if (!timeListEntry.validateFields()) {
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
      }
    });

    timeListEntry.setDeleteTimeEntryListListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        model.deleteTimeEntry(timeListEntry.getId());
        timeListEntry.deletePanel();
      }
    });
  }

  /*
   * Kalender Datum
   */
  // TODO Gehört das jetzt nicht direkt ins GUI?! Wenn wir schon validate machen
  // dort...
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

  /*
   * Erstellt den Zeiteintrag anhand Eingaben der Form aus. Inkl. überprüfung ob
   * Mission und Position vorkommen.
   * 
   * Bezüglich Position gültigkeit hängt von der Mission ab.
   * 
   * Mit GUI Error ausgabe (das erste Validate passiert im Gui)
   */
  public TimeEntry updateTimeEntry(TimeEntry timeEntry, TimeListEntry form) {
    timeEntry.setWorktime(parseWorkTimeToMinutes(form.getWorktime()));
    timeEntry.setDate(createDate(form.getDate()));
    Mission mission = findMissionByName(form.getMission());
    // TODO
    // Die Position muss von der Mission abhängig sein
    Position position = findPositionByCode(form.getPosition());
    if (mission != null) {
      timeEntry.setMission(mission);
    } else {
      form.showMissionError();
      form.showErrorOnPanel();
      return null;
    }
    if (position != null) {
      timeEntry.setPosition(position);
    } else {
      form.showPositionError();
      form.showErrorOnPanel();
      return null;
    }
    return timeEntry;
  }

  /*
   * Nimmt die Zeit im Format 1:30, 0:00 oder auch nur Minuten entgegen. Es wird
   * hier mit RegEx gearbeitet.
   * 
   * @param worktime , Eingabefeld des Formulars
   * 
   * @return Worktime in Minuten
   */
  // TODO Gehört das jetzt nicht direkt ins GUI?! Wenn wir schon validate machen
  // dort...
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

  /*
   * Übersetzt Minuten ins stundenformat xx:xx
   */
  // TODO Gehört das jetzt nicht direkt ins GUI?! Wenn wir schon validate machen
  // dort...
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

}
