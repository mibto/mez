package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.InvalidObjectException;
import java.util.List;

import ch.bli.mez.model.Employee;
import ch.bli.mez.model.dao.EmployeeDAO;
import ch.bli.mez.view.EmployeeTabbedView;
import ch.bli.mez.view.employee.EmployeeForm;
import ch.bli.mez.view.employee.EmployeePanel;
import ch.bli.mez.view.employee.EmployeeSearchPanel;

/**
 * @author leandrafinger
 * @author mbrodmann
 * @version 2.0
 */
public class EmployeeController {
  private EmployeeTabbedView view;
  private EmployeeDAO model;
  private EmployeeSearchPanel searchPanel;
  private EmployeePanel employeePanel;

  public EmployeeController() {
    model = new EmployeeDAO();
    setView(new EmployeeTabbedView());
    addTabs();
  }

  public EmployeeTabbedView getView() {
    return view;
  }

  public void setView(EmployeeTabbedView employeeView) {
    view = employeeView;
    searchPanel = new EmployeeSearchPanel();
    searchPanel.setKeyListener(createSearchKeyListener());
    view.setEmployeeSearchPanel(searchPanel);
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
    view.removeAllTabs();
    addNewEmployeeTab();
    addEmployeeTabs(model.findByKeywords(employeeName));
  }

  private void addTabs() {
    view.removeAllTabs();
    addNewEmployeeTab();
    addEmployeeTabs(model.findAll());
  }

  private void addEmployeeTabs(List<Employee> employeeList) {
    for (Employee employee : employeeList) {
      addEmployeeTab(employee);
    }
  }

  private void addNewEmployeeTab() {
    view.addTab("Neuer Mitarbeiter", createEmployeePanel(new Employee(), true));
  }

  private void addEmployeeTab(Employee employee) {
    view.addTab(employee.getFirstName() + " " + employee.getLastName(), createEmployeePanel(employee, false));
  }

  private EmployeePanel createEmployeePanel(Employee employee, Boolean isNewEmployee) {
    employeePanel = new EmployeePanel();
    employeePanel.setEmployeeForm(createEmployeeForm(employee, isNewEmployee));
    return employeePanel;
  }

  private EmployeeForm createEmployeeForm(Employee employee, Boolean isNewEmployee) {
    EmployeeForm form = new EmployeeForm();
    setEmployeeFormActionListeners(employee, form, isNewEmployee);

    form.setFirstname(employee.getFirstName());
    form.setLastname(employee.getLastName());
    form.setCity(employee.getCity());
    if (employee.getPlz() != 0)
      form.setPlz(employee.getPlz().toString());
    form.setEmail(employee.getEmail());
    form.setHomeNumber(employee.getHomeNumber());
    form.setMobileNumber(employee.getMobileNumber());
    form.setStreet(employee.getStreet());
    form.setAhv(employee.getAhv());
    form.setBirthday(employee.getBirthday());
    form.setStatusButton(employee.getIsActive());
    return form;
  }

  static Employee updateEmployee(Employee employee, EmployeeForm form) throws InvalidObjectException {
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
      employee.setAhv(form.getAhv());
      employee.setBirthday(form.getBirthday());
      return employee;
    }
    throw new InvalidObjectException("Employee invalid");
  }

  public void setEmployeeFormActionListeners(final Employee employee, final EmployeeForm form, Boolean isNewEmployee) {
    form.setSaveListener(createEmployeeSaveListener(employee, form, isNewEmployee));
    form.setStatusButtonListener(createStatusButtonListener(employee, form));
  }

  private ActionListener createStatusButtonListener(final Employee employee, final EmployeeForm form) {
    return new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (employee.getIsActive()) {
          employee.setIsActive(false);
          model.updateEmployee(employee);
          addTabs();
        } else {
          employee.setIsActive(true);
          form.setStatusButton(employee.getIsActive());
          model.updateEmployee(employee);
        }
      }
    };
  }

  private ActionListener createEmployeeSaveListener(final Employee employee, final EmployeeForm form,
      final Boolean isNewEmployee) {
    return new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (!isNewEmployee) {
          try {
            model.updateEmployee(updateEmployee(employee, form));
            view.updateTabName(employee.getFirstName() + " " + employee.getLastName());
          } catch (InvalidObjectException e) {
            return;
          }
          employeePanel.showConfirmation(employee.getFirstName() + " " + employee.getLastName());
        } else {
          Employee safeEmployee = new Employee();
          try {
            safeEmployee = updateEmployee(safeEmployee, form);
          } catch (InvalidObjectException e) {
            return;
          }
          model.addEmployee(safeEmployee);
          view.addTab(safeEmployee.getFirstName() + " " + safeEmployee.getLastName(),
              createEmployeePanel(safeEmployee, false));
          form.cleanFields();
          employeePanel.showConfirmation(safeEmployee.getFirstName() + " " + safeEmployee.getLastName());
        }
      }
    };
  }

  /*
   * private void setEmployeeHolidayListEntryListener(final
   * EmployeeHolidayListEntry employeeHolidayListEntry, final Employee employee,
   * final Holiday globalHoliday) { employeeHolidayListEntry.setSaveListener(new
   * ActionListener() { public void actionPerformed(ActionEvent e) { Holiday
   * holiday = globalHoliday; Holiday employeeHoliday =
   * holidayModel.getEmployeeHolidayByYear(globalHoliday.getYear(), employee);
   * if (employeeHoliday != null) { holiday = employeeHoliday; } // TODO: public
   * Holiday updateHoliday(employeeHolidayListEntry, // employee, holiday) in
   * eigene Methode // holidayModel.updateHoliday(updateHoliday) // Test
   * existiert int holidays = -1; int publicHolidays; int preWorkdays; try { //
   * TODO: employeeHolidayListEntry.validateFields(holiday), muss true // oder
   * false zurück geben, felder nur auslesen // falls valid, sonst return! if
   * (!(employeeHolidayListEntry.getHolidays().equals("") &&
   * holiday.getHolidays() == null)) { holidays =
   * Integer.valueOf(employeeHolidayListEntry.getHolidays()); } publicHolidays =
   * Integer.valueOf(employeeHolidayListEntry.getPublicHolidays()); preWorkdays
   * = Integer.valueOf(employeeHolidayListEntry.getPreWorkdays()); if ((holidays
   * < -1 || publicHolidays < 0 || preWorkdays < 0) || ((publicHolidays ==
   * holiday.getPublicHolidays() && preWorkdays == holiday.getPreworkdays()) &&
   * ((holiday .getHolidays() == null && holidays == -1) ||
   * (holiday.getHolidays() != null && holiday.getHolidays() == holidays)))) {
   * throw new NumberFormatException(
   * "keine Zahl darf negativ sein UND es muss mindestens ein Feld geändert worden sein"
   * ); } } catch (NumberFormatException exeption) {
   * employeeHolidayListEntry.setPublicHolidays
   * (String.valueOf(holiday.getPublicHolidays()));
   * employeeHolidayListEntry.setPreWorkdays
   * (String.valueOf(holiday.getPreworkdays())); if (holiday.getHolidays() !=
   * null) {
   * employeeHolidayListEntry.setHolidays(String.valueOf(holiday.getHolidays
   * ())); } employeeHolidayListEntry.showError(); return; } // TODO:
   * if(newHoliday){holiday = new Holiday(), //
   * holidayModel.addHoliday(holiday)} danach get und set (Reihenfolge: //
   * PublicHolidays, PreWorkdays, Holidays) // Test existiert if
   * (holiday.getEmployee() != null) { if (holidays != -1) {
   * holiday.setHolidays(holidays); } holiday.setPublicHolidays(publicHolidays);
   * holiday.setPreworkdays(preWorkdays); holidayModel.updateHoliday(holiday); }
   * else { holiday = new Holiday(holiday.getYear(), publicHolidays,
   * preWorkdays); if (holidays != -1) { holiday.setHolidays(holidays); }
   * holiday.setEmployee(employee); holidayModel.addHoliday(holiday); }
   * employeeHolidayListEntry.showSuccess(); } }); }
   */

  /*
   * private ActionListener createHolidayRefreshListener(final EmployeePanel
   * panel, final Employee employee) { ActionListener listener = new
   * ActionListener() { public void actionPerformed(ActionEvent e) {
   * panel.setVisible(false); panel.removeEmployeeHolidayListEntries();
   * ContractDAO contractDAO = new ContractDAO(); try {
   * createEmployeeHolidayListEntries(panel, employee,
   * contractDAO.getEmployeeContracts(employee).get(0)
   * .getStartDate().get(Calendar.YEAR)); } catch (IndexOutOfBoundsException
   * exception) { panel.setVisible(true); } panel.setVisible(true); } }; return
   * listener; }
   */

  /*
   * public void updateHoliday(EmployeeHolidayForm holidayPanel, Employee
   * employee, Holiday holiday) { // TODO Auto-generated method stub
   * 
   * }
   */

  /*
   * private ContractPanel createHolidayContract(EmployeePanel panel, Employee
   * employee) { ContractController controller = new
   * ContractController(employee, createHolidayRefreshListener(panel,
   * employee)); if (controller.contractsExists()) {
   * createEmployeeHolidayListEntries(panel, employee,
   * controller.getStartYear()); } return controller.getView(); }
   */

  /*
   * private void createEmployeeHolidayListEntries(EmployeePanel panel, Employee
   * employee, Integer year) { for (Holiday holiday :
   * holidayModel.getEmployeeHolidays(employee, year)) {
   * EmployeeHolidayListEntry employeeHolidaylistEntry = new
   * EmployeeHolidayListEntry();
   * employeeHolidaylistEntry.setYear(String.valueOf(holiday.getYear())); if
   * (holiday.getHolidays() != null) {
   * employeeHolidaylistEntry.setHolidays(String
   * .valueOf(holiday.getHolidays())); }
   * employeeHolidaylistEntry.setPublicHolidays
   * (String.valueOf(holiday.getPublicHolidays()));
   * employeeHolidaylistEntry.setPreWorkdays
   * (String.valueOf(holiday.getPreworkdays()));
   * panel.addEmployeeHolidayListEntry(employeeHolidaylistEntry);
   * setEmployeeHolidayListEntryListener(employeeHolidaylistEntry, employee,
   * holiday); } }
   */
}