package ch.bli.mez.controller;

import static org.mockito.Mockito.*;

import java.io.InvalidObjectException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.verification.WantedButNotInvoked;
import org.mockito.runners.*;

import ch.bli.mez.model.Employee;
import ch.bli.mez.model.Holiday;
import ch.bli.mez.model.dao.HolidayDAO;
import ch.bli.mez.view.employee.EmployeeHolidayListEntry;
import ch.bli.mez.view.employee.EmployeePanel;
import ch.bli.mez.view.employee.EmployeeView;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

	static EmployeeController employeeController;
	
	@Mock
  static Employee employee;
	
	@Mock
	static EmployeeView employeeView;

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
	public void updateEmployeeTest() throws InvalidObjectException{
	  EmployeePanel employeePanel = Mockito.mock(EmployeePanel.class);
	  
	  // Form is valid
	  // Define return values for getters (View)
	  when(employeePanel.getPlz()).thenReturn("8880");
	  when(employeePanel.getFirstname()).thenReturn("Vorname");
	  when(employeePanel.getLastname()).thenReturn("Nachname");
	  when(employeePanel.validateFields()).thenReturn(true);
	  
	  employeeController.updateEmployee(employee, employeePanel, false);
	  
	  InOrder inOrder = inOrder(employeePanel, employee);
	  
	  // Check if setters are called with right return values in right order.
	  inOrder.verify(employeePanel).validateFields();
	  inOrder.verify(employeePanel, atLeastOnce()).getPlz();
	  inOrder.verify(employee).setPlz(8880);
	  inOrder.verify(employeePanel).getFirstname();
	  inOrder.verify(employee).setFirstName("Vorname");
	  inOrder.verify(employeePanel).getLastname();
	  inOrder.verify(employee).setLastName("Nachname");
	  inOrder.verify(employeePanel).updateTabName();
	}
	
	@Test (expected=InvalidObjectException.class)
  public void updateInvalidEmployeeTest() throws InvalidObjectException{
	  EmployeePanel employeePanel = Mockito.mock(EmployeePanel.class);
	  
	  when(employeePanel.validateFields()).thenReturn(false);
	  employeeController.updateEmployee(employee, employeePanel, false);
	  verify(employeePanel).validate();
	}
	
	@Test
  public void updateHolidayTest() throws InvalidObjectException{
    EmployeeHolidayListEntry holidayPanel = Mockito.mock(EmployeeHolidayListEntry.class);
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
    
    // Check if setters are called with right return values in right order. Creating new Holiday.
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
    
    // Check if setters are called with right return values in right order. Existing Object.
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
  }
	
	@Test (expected=WantedButNotInvoked.class)
  public void updateInvalidHolidayTest() throws InvalidObjectException{
	  EmployeeHolidayListEntry holidayPanel = Mockito.mock(EmployeeHolidayListEntry.class);
	  Holiday holiday = Mockito.mock(Holiday.class);
    
    when(holidayPanel.validateFields()).thenReturn(false);
    employeeController.updateHoliday(holidayPanel, employee, holiday);
    verify(holidayPanel).validate();
  }
}