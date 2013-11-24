package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import ch.bli.mez.model.Employee;
import ch.bli.mez.model.Mission;
import ch.bli.mez.model.Position;
import ch.bli.mez.model.TimeEntry;
import ch.bli.mez.model.dao.EmployeeDAO;
import ch.bli.mez.model.dao.MissionDAO;
import ch.bli.mez.model.dao.PositionDAO;
import ch.bli.mez.model.dao.TimeEntryDAO;
import ch.bli.mez.view.EmployeeTabbedView;
import ch.bli.mez.view.employee.EmployeeSearchPanel;
import ch.bli.mez.view.time.TimeEntryForm;
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

  private TimeEntryPanel createTimePanel(Employee employee) {
    TimeEntryPanel timeEntryPanel = new TimeEntryPanel(employee.getId());
    timeEntryPanel.setNewTimeEntryForm(createTimeEntryForm(null, true, employee.getId()));
    for (TimeEntry timeEntry : model.findAll(employee)){
      timeEntryPanel.addTimeEntryForm(createTimeEntryForm(timeEntry, false, employee.getId()));
    }
    return timeEntryPanel;
  }
  
  private TimeEntryForm createTimeEntryForm(TimeEntry timeEntry, boolean isNew, Integer employeeId){
    TimeEntryForm timeEntryForm = new TimeEntryForm(employeeId);
    if (!isNew){
      timeEntryForm.setMission(timeEntry.getMission().getMissionName());
      timeEntryForm.setDate(timeEntry.getDate());
      timeEntryForm.setPosition(timeEntry.getPosition().getCode());
      timeEntryForm.setWorktime(timeEntry.getWorktime());      
    }
    setTimeEntryFormActionListeners(timeEntryForm, timeEntry, isNew);
    return timeEntryForm;
  }
  
  private void setTimeEntryFormActionListeners(final TimeEntryForm form, final TimeEntry timeEntry, final boolean isNew){
    form.setSaveListener((new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        updateTimeEntry(timeEntry, form, isNew);
      }
    }));
    
    form.setDeleteListener((new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if ((TimeEntryPanel.showDeleteWarning(form))) {
          model.deleteTimeEntry(timeEntry.getId());
          form.getParent().remove(form);
        }
      }
    }));
  }
  
  public void updateTimeEntry(TimeEntry timeEntry, TimeEntryForm form, boolean isNew){
    if (validateFields(form)) {
      if (isNew) {
        timeEntry = makeTimeEntry();
      }
      timeEntry.setDate(form.getDate());
      timeEntry.setMission(findMissionByName(form.getMissionName()));
      timeEntry.setPosition(findPositionByCode(form.getPositionCode()));
      timeEntry.setWorktime(form.getWorktime());
      timeEntry.setEmployee(employeeModel.getEmployee(form.getEmployeeId()));
      if (isNew) {
        model.addTimeEntry(timeEntry);
        form.getTimeEntryPanel().addTimeEntryForm(createTimeEntryForm(timeEntry, false, form.getEmployeeId()));
      } else {
        model.updateTimeEntry(timeEntry);
      }
      form.cleanFields();
    }
  }
  
  public TimeEntry makeTimeEntry(){
    return new TimeEntry();
  }

  private Mission findMissionByName(String missionName) {
    return missionModel.findByMissionName(missionName);
  }

  private Position findPositionByCode(String code) {
    return positionModel.findByCode(code);
  }
  
  public boolean validateFields(TimeEntryForm form){
    if (form.validateFields()){
      Position position = findPositionByCode(form.getPositionCode());
      Mission mission = findMissionByName(form.getMissionName());
      if (mission == null){
        form.getTimeEntryPanel().showError("Der eingegebene Auftrag existiert nicht.");
        return false;
      }
      if (position == null){
        form.getTimeEntryPanel().showError("Die eingegebene Position existiert nicht.");
        return false;
      }
      if (!mission.getPositions().contains(position)){
        form.getTimeEntryPanel().showError("Der Auftrag " + mission.getMissionName() +
            " hat keine Position mit Code " + position.getCode() + ".");
        return false;
      }
      form.getTimeEntryPanel().showSuccess("Der Eintrag wurde gespeichert.");
      return true;
    }
    return false;
  }
}
