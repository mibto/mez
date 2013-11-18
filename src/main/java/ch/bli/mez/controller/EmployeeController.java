package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InvalidObjectException;
import java.util.Calendar;
import java.util.List;

import ch.bli.mez.model.Employee;
import ch.bli.mez.model.Holiday;
import ch.bli.mez.model.dao.ContractDAO;
import ch.bli.mez.model.dao.EmployeeDAO;
import ch.bli.mez.model.dao.HolidayDAO;
import ch.bli.mez.view.employee.ContractPanel;
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

  public EmployeeController() {
    this.model = new EmployeeDAO();
    this.holidayModel = new HolidayDAO();
    this.view = new EmployeeView();
    addTabs();
    test();
  }
  
  private void test(){
    List<Employee> results = model.findByKeywords("name=bla");
    for (Employee employee : results) {
      System.out.println(employee.getLastName());
    }
  }

  public EmployeeView getView() {
    return view;
  }

  public void setView(EmployeeView employeeView) {
    this.view = employeeView;
  }

  private void addTabs() {
    view.addTab("Neuer Mitarbeiter", createNewEmployeeTab());
    for (Employee employee : model.findAll()) {
      addEmployeeTab(employee);
    }
  }

  private void addEmployeeTab(Employee employee) {
    view.addTab(employee.getFirstName() + " " + employee.getLastName(), createEmployeePanel(employee, false));
  }

  private EmployeePanel createNewEmployeeTab() {
    Employee employee = new Employee();
    EmployeePanel form = createEmployeePanel(employee, true);
    form.hideStatusButton();
    return form;
  }

  private EmployeePanel createEmployeePanel(Employee employee, Boolean isNewEmployee) {
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
    	form.addContractPanel(createHolidayContract(form, employee));
    }
    return form;
  }

  public Employee updateEmployee(Employee employee, EmployeePanel form, Boolean newEmployee)
      throws InvalidObjectException {
    if (form.validateFields()) {
      if (!form.getPlz().equals("")) {
        employee.setPlz(Integer.parseInt(form.getPlz()));
      }
      employee.setFirstName(form.getFirstname());
      employee.setLastName(form.getLastname());
      employee.setStreet(form.getStreet());
      employee.setCity(form.getCity());
      employee.setMobileNumber(form.getMobileNumber());
      employee.setHomeNumber(form.getHomeNumber());
      employee.setEmail(form.getEmail());
      if (!newEmployee) {
        form.updateTabName();
      }
      return employee;
    }
    throw new InvalidObjectException("Employee invalid");
  }
  
  private ContractPanel createHolidayContract(EmployeePanel panel, Employee employee){
	  ContractController controller = new ContractController(employee, createHolidayRefreshListener(panel, employee));
	  if (controller.contractsExists()){
		  createEmployeeHolidayListEntries(panel, employee, controller.getStartYear());
	  }
	  return controller.getView();
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

  public void setFormActionListeners(final Employee employee, final EmployeePanel form, final Boolean newEmployee) {

    form.setSaveEmployeeListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {

        if (!newEmployee) {
          try {
            model.updateEmployee(updateEmployee(employee, form, false));
          } catch (InvalidObjectException e) {
            return;
          }
          form.showConfirmation(employee.getFirstName() + " " + employee.getLastName());
        } else {
          Employee safeEmployee = new Employee();
          try {
            safeEmployee = updateEmployee(safeEmployee, form, true);
          } catch (InvalidObjectException e) {
            return;
          }
          model.addEmployee(safeEmployee);
          view.addTab(safeEmployee.getFirstName() + " " + safeEmployee.getLastName(),
              createEmployeePanel(safeEmployee, false));
          form.cleanFields();
          form.showConfirmation(safeEmployee.getFirstName() + " " + safeEmployee.getLastName());
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

  private void setEmployeeHolidayListEntryListener(final EmployeeHolidayListEntry employeeHolidayListEntry,
      final Employee employee, final Holiday globalHoliday) {
    employeeHolidayListEntry.setSaveListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Holiday holiday = globalHoliday;
        Holiday employeeHoliday = holidayModel.getEmployeeHolidayByYear(globalHoliday.getYear(), employee);
        if (employeeHoliday != null) {
          holiday = employeeHoliday;
        }
        // TODO: public Holiday updateHoliday(employeeHolidayListEntry,
        // employee, holiday) in eigene Methode
        // holidayModel.updateHoliday(updateHoliday)
        // Test existiert
        int holidays = -1;
        int publicHolidays;
        int preWorkdays;
        try {
          // TODO: employeeHolidayListEntry.validateFields(holiday), muss true
          // oder false zurück geben, felder nur auslesen
          // falls valid, sonst return!
          if (!(employeeHolidayListEntry.getHolidays().equals("") && holiday.getHolidays() == null)) {
            holidays = Integer.valueOf(employeeHolidayListEntry.getHolidays());
          }
          publicHolidays = Integer.valueOf(employeeHolidayListEntry.getPublicHolidays());
          preWorkdays = Integer.valueOf(employeeHolidayListEntry.getPreWorkdays());
          if ((holidays < -1 || publicHolidays < 0 || preWorkdays < 0)
              || ((publicHolidays == holiday.getPublicHolidays() && preWorkdays == holiday.getPreworkdays()) && ((holiday
                  .getHolidays() == null && holidays == -1) || (holiday.getHolidays() != null && holiday.getHolidays() == holidays)))) {
            throw new NumberFormatException(
                "keine Zahl darf negativ sein UND es muss mindestens ein Feld geändert worden sein");
          }
        } catch (NumberFormatException exeption) {
          employeeHolidayListEntry.setPublicHolidays(String.valueOf(holiday.getPublicHolidays()));
          employeeHolidayListEntry.setPreWorkdays(String.valueOf(holiday.getPreworkdays()));
          if (holiday.getHolidays() != null) {
            employeeHolidayListEntry.setHolidays(String.valueOf(holiday.getHolidays()));
          }
          employeeHolidayListEntry.showError();
          return;
        }
        // TODO: if(newHoliday){holiday = new Holiday(),
        // holidayModel.addHoliday(holiday)} danach get und set (Reihenfolge:
        // PublicHolidays, PreWorkdays, Holidays)
        // Test existiert
        if (holiday.getEmployee() != null) {
          if (holidays != -1) {
            holiday.setHolidays(holidays);
          }
          holiday.setPublicHolidays(publicHolidays);
          holiday.setPreworkdays(preWorkdays);
          holidayModel.updateHoliday(holiday);
        } else {
          holiday = new Holiday(holiday.getYear(), publicHolidays, preWorkdays);
          if (holidays != -1) {
            holiday.setHolidays(holidays);
          }
          holiday.setEmployee(employee);
          holidayModel.addHoliday(holiday);
        }
        employeeHolidayListEntry.showSuccess();
      }
    });
  }
  
  private ActionListener createHolidayRefreshListener(final EmployeePanel panel, final Employee employee){
	  ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			panel.setVisible(false);
			panel.removeEmployeeHolidayListEntries();
			ContractDAO contractDAO = new ContractDAO();
			try {
				createEmployeeHolidayListEntries(panel, employee, contractDAO.getEmployeeContracts(employee).get(0).getStartDate().get(Calendar.YEAR));				
			} catch (IndexOutOfBoundsException exception) {				
				panel.setVisible(true);
			}
			panel.setVisible(true);
		}
	};
	return listener;
  }
}