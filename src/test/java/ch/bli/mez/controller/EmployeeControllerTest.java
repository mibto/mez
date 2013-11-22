package ch.bli.mez.controller;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.InvalidObjectException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import ch.bli.mez.model.Employee;
import ch.bli.mez.view.EmployeeTabbedView;
import ch.bli.mez.view.employee.EmployeeForm;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

  static EmployeeController employeeController;

  @Mock
  static Employee employee;

  @Mock
  static EmployeeTabbedView employeeView;

  @Before
  public void setUp() throws Exception {
    employeeController = new EmployeeController();
    MockitoAnnotations.initMocks(this);
    employeeController.setView(employeeView);
  }

  @After
  public void tearDown() throws Exception {
    employee = null;
    employeeView = null;
    employeeController = null;
  }

  @Test
  public void updateEmployeeTest() throws InvalidObjectException {
    EmployeeForm employeeForm = Mockito.mock(EmployeeForm.class);

    // Form is valid
    // Define return values for getters (View)
    when(employeeForm.getPlz()).thenReturn("8880");
    when(employeeForm.getFirstname()).thenReturn("Vorname");
    when(employeeForm.getLastname()).thenReturn("Nachname");
    when(employeeForm.validateFields()).thenReturn(true);

    EmployeeController.updateEmployee(employee, employeeForm);

    InOrder inOrder = inOrder(employeeForm, employee);

    // Check if setters are called with right return values in right order.
    inOrder.verify(employeeForm).validateFields();
    inOrder.verify(employeeForm, atLeastOnce()).getPlz();
    inOrder.verify(employee).setPlz(8880);
    inOrder.verify(employeeForm).getFirstname();
    inOrder.verify(employee).setFirstName("Vorname");
    inOrder.verify(employeeForm).getLastname();
    inOrder.verify(employee).setLastName("Nachname");
    inOrder.verify(employeeView).updateTabName(employee.getFirstName() + " " + employee.getLastName());
  }

  @Test(expected = InvalidObjectException.class)
  public void updateInvalidEmployeeTest() throws InvalidObjectException {
    EmployeeForm employeeForm = Mockito.mock(EmployeeForm.class);

    when(employeeForm.validateFields()).thenReturn(false);
    employeeController.updateEmployee(employee, employeeForm);
    verify(employeeForm).validate();
  }

  /*@Test
  public void updateHolidayTest() throws InvalidObjectException {
    EmployeeHolidayForm holidayPanel = Mockito.mock(EmployeeHolidayForm.class);
    Holiday holiday = Mockito.mock(Holiday.class);
    HolidayDAO holidayModel = Mockito.mock(HolidayDAO.class);

    // Form is valid
    // Define return values for getters (View)
    when(holidayPanel.getPublicHolidays()).thenReturn("20");
    when(holidayPanel.getPreWorkdays()).thenReturn("10");
    when(holidayPanel.getHolidays()).thenReturn("30");
    when(holidayPanel.validateFields(holiday)).thenReturn(true);

    // Case1: HolidayObject for this employee is existing.

    employeeController.updateHoliday(holidayPanel, employee, holiday);

    InOrder inOrder = inOrder(holidayPanel, holiday);

    // Check if setters are called with right return values in right order.
    // Creating new Holiday.
    inOrder.verify(holidayPanel).validateFields(holiday);
    inOrder.verify(holiday).getEmployee();
    inOrder.verify(holidayModel).addHoliday(holiday);
    inOrder.verify(holidayPanel).getPublicHolidays();
    inOrder.verify(holiday).setPublicHolidays(20);
    inOrder.verify(holidayPanel).getPreWorkdays();
    inOrder.verify(holiday).setPreworkdays(10);
    inOrder.verify(holidayPanel).getHolidays();
    inOrder.verify(holiday).setHolidays(30);
    inOrder.verify(holidayModel).updateHoliday(holiday);

    // Case2: HolidayObject for this employee isn't existing.
    holiday.setEmployee(employee);
    when(holiday.getEmployee()).thenReturn(employee);

    employeeController.updateHoliday(holidayPanel, employee, holiday);

    // Check if setters are called with right return values in right order.
    // Existing Object.
    inOrder.verify(holidayPanel).validateFields(holiday);
    inOrder.verify(holiday).getEmployee();
    inOrder.verify(holidayModel, never()).addHoliday(holiday);
    inOrder.verify(holidayPanel).getPublicHolidays();
    inOrder.verify(holiday).setPublicHolidays(20);
    inOrder.verify(holidayPanel).getPreWorkdays();
    inOrder.verify(holiday).setPreworkdays(10);
    inOrder.verify(holidayPanel).getHolidays();
    inOrder.verify(holiday).setHolidays(30);
    inOrder.verify(holidayModel).updateHoliday(holiday);
  }*/

  /*@Test(expected = WantedButNotInvoked.class)
  public void updateInvalidHolidayTest() throws InvalidObjectException {
    EmployeeHolidayForm holidayPanel = Mockito.mock(EmployeeHolidayForm.class);
    Holiday holiday = Mockito.mock(Holiday.class);

    when(holidayPanel.validateFields()).thenReturn(false);
    employeeController.updateHoliday(holidayPanel, employee, holiday);
    verify(holidayPanel).validate();
  }*/
}