package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

import ch.bli.mez.model.Employee;
import ch.bli.mez.model.Holiday;
import ch.bli.mez.model.dao.EmployeeDAO;
import ch.bli.mez.model.dao.HolidayDAO;
import ch.bli.mez.view.employee.EmployeeHolidayListEntry;
import ch.bli.mez.view.employee.EmployeePanel;
import ch.bli.mez.view.employee.EmployeeView;

/**
 * @author leandrafinger
 * @author mbrodmann
 * @version 2.0
 */
public class EmployeeController {
  private EmployeeView view;
  private EmployeeDAO model;
  private HolidayDAO holidayModel;
  private final SearchController searchController;

  public EmployeeController() {
    this.model = new EmployeeDAO();
    this.holidayModel = new HolidayDAO();
    this.searchController = new SearchController();
    this.view = new EmployeeView(searchController.getSearchPanel());
    addTabs();
  }

  public EmployeeView getView() {
    return view;
  }

  private void addTabs() {
    view.addTab("Neuer Mitarbeiter", createNewEmployeeTab());
    for (Employee employee : model.findAll()) {
      addEmployeeTab(employee);
    }
  }

  private void addEmployeeTab(Employee employee) {
    view.addTab(employee.getFirstName() + " " + employee.getLastName(),
        createEmployeePanel(employee, false));
  }

  private EmployeePanel createNewEmployeeTab() {
    Employee employee = new Employee();
    EmployeePanel form = createEmployeePanel(employee, true);
    form.hideStatusButton();
    return form;
  }

  private EmployeePanel createEmployeePanel(Employee employee,
      Boolean isNewEmployee) {
    EmployeePanel form = new EmployeePanel();
    setFormActionListeners(employee, form, isNewEmployee);

    form.setFirstname(employee.getFirstName());
    form.setLastname(employee.getLastName());
    form.setCity(employee.getCity());
    if (employee.getPlz() != 0)
      form.setPlz(employee.getPlz().toString());
    form.setEmail(employee.getEmail());
    form.setHomeNumber(employee.getHomeNumber());
    form.setMobileNumber(employee.getMobileNumber());
    form.setStreet(employee.getStreet());
    if (! isNewEmployee){
//TODO create a year query from the contract
    	createEmployeeHolidayListEntries(form, employee, 2000);
    }
    return form;
  }

  public Employee updateEmployee(Employee employee, EmployeePanel form) {
    if (!form.getPlz().equals(""))
      employee.setPlz(Integer.parseInt(form.getPlz()));
    employee.setFirstName(form.getFirstname());
    employee.setLastName(form.getLastname());
    employee.setStreet(form.getStreet());
    employee.setCity(form.getCity());
    employee.setMobileNumber(form.getMobileNumber());
    employee.setHomeNumber(form.getHomeNumber());
    employee.setEmail(form.getEmail());
    int index = ((JTabbedPane) form.getParent()).indexOfComponent(form);
    ((JTabbedPane) form.getParent()).setTitleAt(index, employee.getFirstName() + " " + employee.getLastName());
    return employee;
  }
  
  private void createEmployeeHolidayListEntries(EmployeePanel panel, Employee employee, Integer year){
	  for (Holiday holiday : holidayModel.getEmployeeHolidays(employee, year)){
		  EmployeeHolidayListEntry employeeHolidaylistEntry = new EmployeeHolidayListEntry();
		  employeeHolidaylistEntry.setYear(String.valueOf(holiday.getYear()));
		  if (holiday.getHolidays() != null){
			  employeeHolidaylistEntry.setHolidays(String.valueOf(holiday.getHolidays()));
		  }
		  employeeHolidaylistEntry.setPublicHolidays(String.valueOf(holiday.getPublicHolidays()));
		  employeeHolidaylistEntry.setPreWorkdays(String.valueOf(holiday.getPreworkdays()));
		  panel.addEmployeeHolidayListEntry(employeeHolidaylistEntry);
		  setEmployeeHolidayListEntryListener(employeeHolidaylistEntry, employee, holiday);
	  }
  }

  public void setFormActionListeners(final Employee employee,
      final EmployeePanel form, final Boolean newEmployee) {
    form.setSaveEmployeeListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (!validateFields(form)) {
          return;
        }
        if (!newEmployee) {
          model.updateEmployee(updateEmployee(employee, form));
          form.showConfirmation(employee.getFirstName() + " "
              + employee.getLastName());
        } else {
          Employee safeEmployee = new Employee();
          safeEmployee = updateEmployee(safeEmployee, form);
          model.addEmployee(safeEmployee);
          view.addTab(
              safeEmployee.getFirstName() + " " + safeEmployee.getLastName(),
              createEmployeePanel(safeEmployee, false));
          form.cleanFields();
          form.showConfirmation(safeEmployee.getFirstName() + " "
              + safeEmployee.getLastName());
        }
      }
    });
    form.setStatusButtonListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (employee.getIsActive()) {
          employee.setIsActive(false);
          form.setStatusButtonName("Aktivieren");
        } else {
          employee.setIsActive(true);
          form.setStatusButtonName("Deaktivieren");
          view.removeTab(view.getSelectedIndex());
        }
        model.updateEmployee(employee);
      }
    });
  }
  
  private void setEmployeeHolidayListEntryListener(final EmployeeHolidayListEntry employeeHolidayListEntry, final Employee employee, final Holiday globalHoliday){
	  employeeHolidayListEntry.setSaveListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Holiday holiday = holidayModel.getEmployeeHolidayByYear(globalHoliday.getYear(), employee);
			int holidays = -1;
			int publicHolidays;
			int preWorkdays;
			try{
				if (!(employeeHolidayListEntry.getHolidays().equals("") && holiday.getHolidays() == null)){
					holidays = Integer.valueOf(employeeHolidayListEntry.getHolidays());
				}
				publicHolidays = Integer.valueOf(employeeHolidayListEntry.getPublicHolidays());
				preWorkdays = Integer.valueOf(employeeHolidayListEntry.getPreWorkdays());
				if ((holidays < -1 || publicHolidays < 0 || preWorkdays < 0) || ((publicHolidays == holiday.getPublicHolidays() && preWorkdays == holiday.getPreworkdays()) && 
						((holiday.getHolidays() == null && holidays == -1) || (holiday.getHolidays() != null && holiday.getHolidays() == holidays)))){
					throw new NumberFormatException("keine Zahl darf negativ sein UND es muss mindestens ein Feld geändert worden sein");
				}
			} catch (NumberFormatException exeption){
				employeeHolidayListEntry.setPublicHolidays(String.valueOf(holiday.getPublicHolidays()));
				employeeHolidayListEntry.setPreWorkdays(String.valueOf(holiday.getPreworkdays()));
				if (holiday.getHolidays() != null){
					employeeHolidayListEntry.setHolidays(String.valueOf(holiday.getHolidays()));
				}
				employeeHolidayListEntry.showError();
				return;
			}
			if (holiday.getEmployee() != null){
				if (holidays != -1){
					holiday.setHolidays(holidays);
				}
				holiday.setPublicHolidays(publicHolidays);
				holiday.setPreworkdays(preWorkdays);
				holidayModel.updateHoliday(holiday);
			} else {
				holiday = new Holiday(holiday.getYear(), publicHolidays, preWorkdays);
				if (holidays != -1){
					holiday.setHolidays(holidays);
				}
				holiday.setEmployee(employee);
				holidayModel.addHoliday(holiday);
			}
			employeeHolidayListEntry.showSuccess();
		}
	});
  }

  public boolean validateFields(EmployeePanel panel) {
    boolean valid = true;
    if (panel.getFirstname().equals("")) {
      panel.showError("Vorname");
      valid = false;
    }
    if (panel.getLastname().equals("")) {
      panel.showError("Nachname");
      valid = false;
    }
    try {
      if (!panel.getPlz().equals(""))
        Integer.parseInt(panel.getPlz());
    } catch (NumberFormatException e) {
      panel.showError("PLZ");
      valid = false;
    }
    return valid;
  }
}