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
import org.mockito.runners.*;


import ch.bli.mez.model.Employee;
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
}