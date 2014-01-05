package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;

import ch.bli.mez.model.Contract;
import ch.bli.mez.model.Employee;
import ch.bli.mez.model.Holiday;
import ch.bli.mez.model.dao.ContractDAO;
import ch.bli.mez.model.dao.HolidayDAO;
import ch.bli.mez.view.DefaultPanel;
import ch.bli.mez.view.employee.EmployeeHolidayForm;
import ch.bli.mez.view.employee.EmployeeHolidayTitlePanel;

public class EmployeeHolidayController {

  private DefaultPanel view;
  private HolidayDAO model;
  private ContractDAO contractModel;
  private Employee employee;

  public EmployeeHolidayController(Employee employee) {
    this.employee = employee;
    this.model = new HolidayDAO();
    this.contractModel = new ContractDAO();
    createView();
  }

  public DefaultPanel getView() {
    return this.view;
  }

  protected void createView() {
    this.view = new DefaultPanel();
    view.setListTitlePanel(new EmployeeHolidayTitlePanel());
    for (Holiday holiday : model.getEmployeeHolidays(employee, getStartYear(), getEndYear())) {
      view.addForm(createHolidayForm(holiday));
    }
  }

  private EmployeeHolidayForm createHolidayForm(Holiday holiday) {
    EmployeeHolidayForm form = new EmployeeHolidayForm();
    setEmployeeHolidayFormActionListeners(form);
    form.setYear(String.valueOf(holiday.getYear()));
    form.setPreWorkdays(String.valueOf(holiday.getPreworkdays()));
    form.setPublicHolidays(String.valueOf(holiday.getPublicHolidays()));
    if (holiday.getHolidays() != null) {
      form.setHolidays(String.valueOf(holiday.getHolidays()));
    }
    return form;
  }

  private void updateEmployeeHoliday(EmployeeHolidayForm form) {
    if (!validateFields(form)) {
      return;
    }
    Holiday holiday = createHolidayObject(Integer.valueOf(form.getYear()));
    holiday.setHolidays(Integer.valueOf(form.getHolidays()));
    holiday.setPreworkdays(Integer.valueOf(form.getPreWorkdays()));
    holiday.setPublicHolidays(Integer.valueOf(form.getPublicHolidays()));
    if (holiday.getId() == null){
      model.addHoliday(holiday);
    } else {
      model.updateHoliday(holiday);
    }
    form.getParentPanel().showConfirmation("Die Ferien für das Jahr " + holiday.getYear() + " wurden gespeichert");
  }
  
  private Holiday createHolidayObject(Integer year){
    Holiday holiday = model.getEmployeeHolidayByYear(year, employee);
    if (holiday == null){
      holiday = new Holiday();
      holiday.setEmployee(employee);
      holiday.setYear(year);
    }
    return holiday;
  }

  private Boolean validateFields(EmployeeHolidayForm form) {
    if (!form.validateFields()) {
      return false;
    }
    if (Integer.valueOf(form.getHolidays()) < 0) {
      form.getParentPanel().showError("Das Format der eingegebenen Ferien ist nicht gültig");
    }
    if (Integer.valueOf(form.getPreWorkdays()) < 0) {
      form.getParentPanel().showError("Das Format der eingegebenen Vorholtage ist nicht gültig");
    }
    if (Integer.valueOf(form.getPublicHolidays()) < 0) {
      form.getParentPanel().showError("Das Format der eingegebenen Feiertage ist nicht gültig");
    }
    return true;
  }
  
  private void setEmployeeHolidayFormActionListeners(final EmployeeHolidayForm form){
    form.setSaveListener(createHolidayFormSaveListener(form));    
  }

  private ActionListener createHolidayFormSaveListener(final EmployeeHolidayForm form) {
    return new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        updateEmployeeHoliday(form);
      }
    };
  }
  
  private Integer getEndYear(){
    List<Contract> contracts = contractModel.getEmployeeContracts(employee);
    if (contracts.size() < 1) {
      return null;
    }
    int index = contracts.size() - 1;
    while (contracts.get(index).getEndDate() == null){
      if (index == 0){
        return null;
      }
      index --;
    }
    return contracts.get(index).getEndDate().get(Calendar.YEAR);
  }

  private Integer getStartYear() {
    List<Contract> contracts = contractModel.getEmployeeContracts(employee);
    if (contracts.size() < 1) {
      return null;
    }
    return contracts.get(0).getStartDate().get(Calendar.YEAR);
  }
}
