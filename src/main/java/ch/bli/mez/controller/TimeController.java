package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

  public TimeController() {
    this.model = new TimeEntryDAO();
    this.missionModel = new MissionDAO();
    this.positionModel = new PositionDAO();
    this.modelemployee = new EmployeeDAO();

    this.view = new TimeView();
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
    addTabs();
  }

  private TimePanel createTimePanel(Employee employee) {
    TimePanel form = new TimePanel();

    form.addHeadInput(createTimeListEntry(null, employee, true));
    form.addTimeListEntrys(getExistingTimeEntrys(employee));

    return form;
  }

  /*
   * Sucht alle bestehenden Zeiteinträge eines Employees raus. Pro Zeiteintrag
   * wird ein Panel dafür erstellt. Das ganze wird als Liste an die Form
   * gegeben, welche diese selbstständig bei sich am auflistet
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
   * Löscht aus aktiven employee-Panel das Listenelement
   */
  private void removeTimeEntryInList(TimeListEntry timeListEntry) {
    view.getSelectedTabComponent().removeTimeListEntry(timeListEntry);
  }

  /*
   * Erstellt ein Panel mit einem Zeiteintrag. false = Listenelement, true = für
   * Kopf
   */
  private TimeListEntry createTimeListEntry(TimeEntry timeEntry,
      Employee employee, Boolean isNewTimeEntry) {

    TimeListEntry timeListEntry = new TimeListEntry();

    if (!isNewTimeEntry) {
      timeListEntry.setId(timeEntry.getId());
      timeListEntry.setMission(timeEntry.getMission().getMissionName());
      timeListEntry.setDate(timeEntry.getDate());
      timeListEntry.setPosition(timeEntry.getPosition().getCode());
      timeListEntry.setWorktime(timeEntry.getWorktime());
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
      form.setDate(timeEntries.get(0).getDate());
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
        TimeEntry timeEntry = new TimeEntry();
        timeEntry.setEmployee(employee);

        if (!timeListEntry.validateFields()) {
          return;
        }

        if (isNewTimeEntry) {
          if (updateTimeEntry(timeEntry, timeListEntry) != null) {
            model.addTimeEntry(timeEntry);

            timeListEntry.showSuccess();
            timeListEntry.cleanFields();
            addAdditionalTimeEntryInList(timeEntry, employee);
          }
        } else {
          if (updateTimeEntry(timeEntry, timeListEntry) != null) {
            model.updateTimeEntry(timeEntry);

            timeListEntry.showSuccess();
            timeListEntry.setWorktime(timeEntry.getWorktime());
          }
        }
      }
    });

    timeListEntry.setDeleteTimeEntryListListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (timeListEntry.showMessageWarning()) {
          model.deleteTimeEntry(timeListEntry.getId());
          removeTimeEntryInList(timeListEntry);
        }
      }
    });
  }

  private Mission findMissionByName(String missionName) {
    return missionModel.findByMissionName(missionName);
  }

  private Position findPositionByCode(String code) {
    return positionModel.findByCode(code);
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
    timeEntry.setDate(form.getDate());
    timeEntry.setWorktime(form.getWorktime());

    // TODO
    // Die Position muss von der Mission abhängig sein
    Mission mission = findMissionByName(form.getMission());
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

}
