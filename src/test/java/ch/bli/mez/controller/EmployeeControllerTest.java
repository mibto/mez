package ch.bli.mez.controller;

import static org.mockito.Mockito.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
	public void updateEmployeeTest(){
	  EmployeePanel employeePanel = Mockito.mock(EmployeePanel.class);
	  
	  // Define return values for getters (View)
	  when(employeePanel.getPlz()).thenReturn("8880");
	  when(employeePanel.getFirstname()).thenReturn("Vorname");
	  
	  employeeController.updateEmployee(employee, employeePanel);
	  
	  // Check if setters are called with right return values.
	  verify(employee).setFirstName("Vorname");
	  verify(employee).setPlz(8880);
	}
	
	@Test
	public void validateFieldsTest(){
	  EmployeePanel employeePanel = Mockito.mock(EmployeePanel.class);
	  
	  // Case1: Required fields empty, PLZ correct
	  when(employeePanel.getPlz()).thenReturn("8880");
    when(employeePanel.getFirstname()).thenReturn("");
    when(employeePanel.getLastname()).thenReturn("");
	  
	  employeeController.validateFields(employeePanel);
	  
	  verify(employeePanel).showError("Vorname");
	  verify(employeePanel).showError("Nachname");
	  verify(employeePanel, never()).showError("PLZ");
	  
	  // Case2: Required fields correct, PLZ with chars (fail)
	  when(employeePanel.getPlz()).thenReturn("88ef");
    when(employeePanel.getFirstname()).thenReturn("Vorname");
    when(employeePanel.getLastname()).thenReturn("Nachname");
    
    employeeController.validateFields(employeePanel);
    
    verify(employeePanel).showError("PLZ");
    
    reset(employeePanel);
    
    // Case3: Required fields correct, PLZ empty (correct)
    when(employeePanel.getPlz()).thenReturn("");
    when(employeePanel.getFirstname()).thenReturn("Vorname");
    when(employeePanel.getLastname()).thenReturn("Nachname");
    
    employeeController.validateFields(employeePanel);
    
    verify(employeePanel, never()).showError("PLZ");
    verify(employeePanel, never()).showError("Vorname");
    verify(employeePanel, never()).showError("Nachname");
	}
	
//	//PLZ muss int sein
//	@Test
//	public void validatePlzTest() {
//		employeePanel.setFirstname("Vorname");
//		employeePanel.setLastname("Nachname");
//		employeePanel.setPlz("keine Zahl");
//		assertEquals(false, employeeController.validateFields(employeePanel));
//		
//		employeePanel.setPlz("");
//		assertEquals(true, employeeController.validateFields(employeePanel));
//		
//		employeePanel.setPlz("9999");
//		assertEquals(true, employeeController.validateFields(employeePanel));
//	}
//	
//	//Vor- und Nachname müssen eingegeben werden.
//		@Test
//		public void validateRequiredTest() {
//			assertEquals(false, employeeController.validateFields(employeePanel));
//
//			employeePanel.setLastname("Nachname");
//			assertEquals(false, employeeController.validateFields(employeePanel));
//			
//			employeePanel.setFirstname("Vorname");
//			assertEquals(true, employeeController.validateFields(employeePanel));
//			
//			employeePanel.setLastname("");
//			assertEquals(false, employeeController.validateFields(employeePanel));
//		}
}