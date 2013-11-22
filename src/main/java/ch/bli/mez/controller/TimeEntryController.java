package ch.bli.mez.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import ch.bli.mez.model.Employee;
import ch.bli.mez.model.dao.EmployeeDAO;
import ch.bli.mez.model.dao.MissionDAO;
import ch.bli.mez.model.dao.PositionDAO;
import ch.bli.mez.model.dao.TimeEntryDAO;
import ch.bli.mez.view.EmployeeTabbedView;
import ch.bli.mez.view.employee.EmployeeSearchPanel;
import ch.bli.mez.view.time.TimeEntryPanel;

public class TimeEntryController {

  private EmployeeDAO employeeModel;
  private EmployeeTabbedView view;
  private TimeEntryDAO model;
  private MissionDAO missionModel;
  private PositionDAO positionModel;
  private EmployeeSearchPanel searchPanel;

  public TimeEntryController() {
    this.model = new TimeEntryDAO();
    this.missionModel = new MissionDAO();
    this.positionModel = new PositionDAO();
    this.employeeModel = new EmployeeDAO();
    setView();
    addTabs();
  }

  public EmployeeTabbedView getView() {
    return view;
  }

  private void setView() {
    this.view = new EmployeeTabbedView();
    this.searchPanel = new EmployeeSearchPanel();
    this.view.setEmployeeSearchPanel(searchPanel);
    searchPanel.setKeyListener(createSearchKeyListener());
  }

  private KeyListener createSearchKeyListener() {
    return new KeyListener() {
      public void keyTyped(KeyEvent e) {
      }

      public void keyReleased(KeyEvent e) {
        addTabs(searchPanel.getSearchText());
      }

      public void keyPressed(KeyEvent e) {
      }
    };
  }

  private void addTabs(String employeeName) {
    addEmployeeTabs(employeeModel.findByKeywords("name=" + employeeName));
  }

  private void addTabs() {
    addEmployeeTabs(employeeModel.findAll());
  }
  
  private void addEmployeeTabs(List<Employee> employeeList){
    view.removeAllTabs();
    for (Employee employee : employeeList) {
      addEmployeeTab(employee);
    }
  }

  private void addEmployeeTab(Employee employee) {
    view.addTab(employee.getFirstName() + " " + employee.getLastName(), createTimePanel(employee));
  }

  public void updateTimeView() {
    addTabs();
  }
  
  //-----------------------------------------------------------------------------

  private TimeEntryPanel createTimePanel(Employee employee) {
    TimeEntryPanel timeEntryPanel = new TimeEntryPanel();
    timeEntryPanel.addInputTimeListEntry();
   // addExistingTimeEntries(employee, timePanel);
    return timeEntryPanel;
  }
  
  /*private void addExistingTimeEntries(final Employee employee, final TimePanel timePanel){
    for (TimeEntry timeEntry : model.findAll(employee)) {
      timePanel.addTimeListEntry(new TimeListEntry(timeEntry));
    }
    
  }*/



  /*
   * Fügt dem aktiven employee-Panel ein neues Listenelement hinzu
   */
  /*private void addAdditionalTimeEntryInList(TimeEntry timeEntry, Employee employee) {
    view.getSelectedTabComponent().addAdditionalTimeListEntry(createTimeListEntry(timeEntry, employee, false));
  }*/

  /*
   * Löscht aus aktiven employee-Panel das Listenelement
   *//*
  private void removeTimeEntryInList(TimeListEntry timeListEntry) {
    view.getSelectedTabComponent().removeTimeListEntry(timeListEntry);
  }*/

  /*
   * Erstellt ein Panel mit einem Zeiteintrag. false = Listenelement, true = für
   * Kopf
   */
  /*private TimeListEntry createTimeListEntry(final TimeEntry timeEntry, final Employee employee, Boolean isNewTimeEntry) {
    TimeListEntry timeListEntry = new TimeListEntry();
    if (!isNewTimeEntry) {
      updateTimeListEntry(timeListEntry, timeEntry, employee);
    } else {
      preFillTimeListEntry(employee, timeListEntry);
    }

    setTimeListEntryActionListeners(timeListEntry, employee, isNewTimeEntry);

    return timeListEntry;
  }
  
  private void updateTimeListEntry(final TimeListEntry timeListEntry, final TimeEntry timeEntry, final Employee employee){
    timeListEntry.setId(timeEntry.getId());
    timeListEntry.setMission(timeEntry.getMission().getMissionName());
    timeListEntry.setDate(timeEntry.getDate());
    timeListEntry.setPosition(timeEntry.getPosition().getCode());
    timeListEntry.setWorktime(timeEntry.getWorktime());
  }

  /*
   * Füllt den Head aus (anhand des letzten Zeiteintrags) Nur die "Zeit" bleibt
   * leer.
   *//*
  private void preFillTimeEntry(Employee employee, TimeListEntry form) {
    // TODO setzt die letzte Eingabe beim Eintragen oben
    // Wie am besten letzter Eintrag im TimeEntry finden??
    List<TimeEntry> timeEntries = model.findAll(employee);
    if (timeEntries.size() != 0) {
      form.setMission(timeEntries.get(0).getMission().getMissionName());
      form.setDate(timeEntries.get(0).getDate());
      form.setPosition(timeEntries.get(0).getPosition().getCode());
    }
  }*/

  /*
   * Setzt alle Listeners vom Panel "TimeListEntry" (Speicher- und
   * Delete-Button)
   */
  /*private void setTimeListEntryActionListeners(final TimeListEntry timeListEntry, final Employee employee,
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
  /*public TimeEntry updateTimeEntry(TimeEntry timeEntry, TimeListEntry form) {
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
  }*/

}
