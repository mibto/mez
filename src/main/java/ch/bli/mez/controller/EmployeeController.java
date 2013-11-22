package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.InvalidObjectException;
import java.util.List;

import ch.bli.mez.model.Employee;
import ch.bli.mez.model.Holiday;
import ch.bli.mez.model.dao.EmployeeDAO;
import ch.bli.mez.view.employee.EmployeeHolidayListEntry;
import ch.bli.mez.view.employee.EmployeePanel;
import ch.bli.mez.view.employee.EmployeeSearchPanel;
import ch.bli.mez.view.employee.EmployeeView;

/**
 * @author leandrafinger
 * @author mbrodmann
 * @version 2.0
 */
public class EmployeeController {
  private EmployeeView view;
  private EmployeeDAO model;
  private EmployeeSearchPanel searchPanel;

  public EmployeeController() {
    this.model = new EmployeeDAO();
    setView(new EmployeeView());
    addTabs();
  }

  public EmployeeView getView() {
    return view;
  }

  public void setView(EmployeeView employeeView) {
    this.view = employeeView;
    this.searchPanel = new EmployeeSearchPanel();
    this.view.setSearchPanel(searchPanel);
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
    view.removeAllTabs();
    addNewEmployeeTab();
    addEmployeeTabs(model.findByKeywords("name=" + employeeName));
  }

  private void addTabs() {
    addNewEmployeeTab();
    addEmployeeTabs(model.findAll());
  }

  private void addEmployeeTabs(List<Employee> employeeList) {
    for (Employee employee : employeeList) {
      addEmployeeTab(employee);
    }
  }

  private void addNewEmployeeTab() {
    view.addTab("Neuer Mitarbeiter", createNewEmployeePanel());
  }

  private void addEmployeeTab(Employee employee) {
    view.addTab(employee.getFirstName() + " " + employee.getLastName(), createEmployeePanel(employee, false));
  }

  private EmployeePanel createNewEmployeePanel() {
    Employee employee = new Employee();
    EmployeePanel employeePanel = createEmployeePanel(employee, true);
    employeePanel.hideStatusButton();
    return employeePanel;
  }

  private EmployeePanel createEmployeePanel(Employee employee, Boolean isNewEmployee) {
    EmployeePanel employeePanel = new EmployeePanel();
    setEmployeePanelActionListeners(employee, employeePanel, isNewEmployee);

    employeePanel.setFirstname(employee.getFirstName());
    employeePanel.setLastname(employee.getLastName());
    employeePanel.setCity(employee.getCity());
    if (employee.getPlz() != 0)
      employeePanel.setPlz(employee.getPlz().toString());
    employeePanel.setEmail(employee.getEmail());
    employeePanel.setHomeNumber(employee.getHomeNumber());
    employeePanel.setMobileNumber(employee.getMobileNumber());
    employeePanel.setStreet(employee.getStreet());
    if (!isNewEmployee) {
      // employeePanel.addContractPanel(createHolidayContract(employeePanel,
      // employee));
    }
    return employeePanel;
  }

  public Employee updateEmployee(Employee employee, EmployeePanel employeePanel) throws InvalidObjectException {
    if (employeePanel.validateFields()) {
      if (!employeePanel.getPlz().equals("")) {
        employee.setPlz(Integer.parseInt(employeePanel.getPlz()));
      }
      employee.setFirstName(employeePanel.getFirstname());
      employee.setLastName(employeePanel.getLastname());
      employee.setStreet(employeePanel.getStreet());
      employee.setCity(employeePanel.getCity());
      employee.setMobileNumber(employeePanel.getMobileNumber());
      employee.setHomeNumber(employeePanel.getHomeNumber());
      employee.setEmail(employeePanel.getEmail());
      return employee;
    }
    throw new InvalidObjectException("Employee invalid");
  }

  public void setEmployeePanelActionListeners(final Employee employee, final EmployeePanel employeePanel,
      final Boolean newEmployee) {
    employeePanel.setSaveEmployeeListener(createEmployeeSaveListener(employee, employeePanel, newEmployee));
    employeePanel.setStatusButtonListener(createStatusButtonListener(employee, employeePanel, newEmployee));
  }
  
  private ActionListener createStatusButtonListener(final Employee employee, final EmployeePanel employeePanel, final Boolean newEmployee){
    return new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (employee.getIsActive()) {
          employee.setIsActive(false);
          employeePanel.setStatusButtonName("Aktivieren");
        } else {
          employee.setIsActive(true);
          employeePanel.setStatusButtonName("Deaktivieren");
          view.removeTab(view.getSelectedIndex());
        }
        model.updateEmployee(employee);
      }
    };
  }

  private ActionListener createEmployeeSaveListener(final Employee employee, final EmployeePanel employeePanel, final Boolean newEmployee){
    return new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (!newEmployee) {
          try {
            model.updateEmployee(updateEmployee(employee, employeePanel));
            employeePanel.updateTabName();
          } catch (InvalidObjectException e) {
            return;
          }
          employeePanel.showConfirmation(employee.getFirstName() + " " + employee.getLastName());
        } else {
          Employee safeEmployee = new Employee();
          try {
            safeEmployee = updateEmployee(safeEmployee, employeePanel);
          } catch (InvalidObjectException e) {
            return;
          }
          model.addEmployee(safeEmployee);
          view.addTab(safeEmployee.getFirstName() + " " + safeEmployee.getLastName(),
              createEmployeePanel(safeEmployee, false));
          employeePanel.cleanFields();
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

  public void updateHoliday(EmployeeHolidayListEntry holidayPanel, Employee employee, Holiday holiday) {
    // TODO Auto-generated method stub

  }

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