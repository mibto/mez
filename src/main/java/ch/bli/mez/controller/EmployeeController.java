package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
    addEmployeeTabs(model.findActive());
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
    EmployeePanel panel = createEmployeePanel(employee, false);
    view.addTab(employee.getLastName() + " " + employee.getFirstName(), panel);
    view.setTabListener(createChangeListener(view.getTabCount(), employee, panel));
  }

  private EmployeePanel createEmployeePanel(Employee employee, Boolean isNewEmployee) {
    employeePanel = new EmployeePanel();
    if (isNewEmployee) {
      employeePanel.setCreateNewForm(createEmployeeForm(employee, isNewEmployee));
    } else {
      employeePanel.addForm(createEmployeeForm(employee, isNewEmployee));
      employeePanel.setHolidayPanel(new EmployeeHolidayController(employee).getView());
      employeePanel.setContractPanel(new ContractController(employee, createHolidayUpdateListener(employeePanel,
          employee)).getView());
    }
    return employeePanel;
  }
  
  private ChangeListener createChangeListener(final int tabIndex, final Employee employee, final EmployeePanel panel){
    return new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        if (((JTabbedPane) e.getSource()).getSelectedIndex() == tabIndex - 1){
          updateHolidayPanel(panel, employee);
        }
      }
    };
  }

  private ActionListener createHolidayUpdateListener(final EmployeePanel employeePanel, final Employee employee) {
    return new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        updateHolidayPanel(employeePanel, employee);
      }
    };
  }
  
  private void updateHolidayPanel(EmployeePanel employeePanel, Employee employee){
    employeePanel.removeHolidayPanel();
    employeePanel.setHolidayPanel(new EmployeeHolidayController(employee).getView());
    employeePanel.revalidate();
  }

  private EmployeeForm createEmployeeForm(Employee employee, Boolean isNewEmployee) {
    EmployeeForm form = new EmployeeForm();
    setEmployeeFormActionListeners(employee, form, isNewEmployee);
    if (!isNewEmployee) {
      form.setFirstname(employee.getFirstName());
      form.setLastname(employee.getLastName());
      form.setCity(employee.getCity());
      if (employee.getPlz() != 0) {
        form.setPlz(employee.getPlz().toString());
      }
      form.setEmail(employee.getEmail());
      form.setHomeNumber(employee.getHomeNumber());
      form.setMobileNumber(employee.getMobileNumber());
      form.setStreet(employee.getStreet());
      form.setAhv(employee.getAhv());
      form.setBirthday(employee.getBirthday());
      form.setStatusButton(employee.getIsActive());
    }
    return form;
  }

  protected Employee updateEmployee(Employee employee, EmployeeForm form, Boolean isNewEmployee) {
    if (validateFields(employee, form, isNewEmployee)) {
      if (!"".equals(form.getPlz())) {
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
      form.getParentPanel().showConfirmation(
          "Der Mitarbeiter " + employee.getFirstName() + " " + employee.getLastName() + " wurde gespeichert");
      return employee;
    }
    return null;
  }
  
  private boolean validateFields(Employee employee, EmployeeForm form, Boolean isNewEmployee){
    if (!form.validateFields()){
      return false;
    }
    Employee duplicate = model.findByEmployeeName(form.getLastname(), form.getFirstname());
    if (isNewEmployee && duplicate != null || duplicate!= null && !isNewEmployee && duplicate.getId() != employee.getId()){
      form.getParentPanel().showError("Einen Mitarbeiter mit gleichem Vor- und Nachname existiert bereits");
      return false;
    }
    return true;
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
          Employee updatedEmployee = updateEmployee(employee, form, isNewEmployee);
          if (updatedEmployee != null){
            model.updateEmployee(updatedEmployee);
            view.updateTabName(updatedEmployee.getLastName() + " " + updatedEmployee.getFirstName());
          }
        } else {
          Employee safeEmployee = new Employee();
          safeEmployee = updateEmployee(safeEmployee, form, isNewEmployee);
          if (safeEmployee != null){
            model.addEmployee(safeEmployee);
            view.addTab(safeEmployee.getLastName() + " " + safeEmployee.getFirstName(),
                createEmployeePanel(safeEmployee, false));
            form.cleanFields();
          }
        }
      }
    };
  }
}