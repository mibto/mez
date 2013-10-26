package ch.bli.mez.controller;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.bli.mez.view.employee.EmployeePanel;

public class EmployeeControllerTest {

	EmployeePanel employeePanel;
	static EmployeeController employeeController;
	
	@BeforeClass
	public static void beforeEverything(){
		employeeController = new EmployeeController();
	}
	
	@AfterClass
	public static void afterEverything(){
		employeeController = null;
	}

	@Before
	public void setUp() throws Exception {
		employeePanel = new EmployeePanel();
	}

	@After
	public void tearDown() throws Exception {
		employeePanel = null;
	}
	
	//PLZ muss int sein
	@Test
	public void validatePlzTest() {
		employeePanel.setFirstname("Vorname");
		employeePanel.setLastname("Nachname");
		employeePanel.setPlz("keine Zahl");
		assertEquals(false, employeeController.validateFields(employeePanel));
		
		employeePanel.setPlz("");
		assertEquals(true, employeeController.validateFields(employeePanel));
		
		employeePanel.setPlz("9999");
		assertEquals(true, employeeController.validateFields(employeePanel));
	}
	
	//Vor- und Nachname müssen eingegeben werden.
		@Test
		public void validateRequiredTest() {
			assertEquals(false, employeeController.validateFields(employeePanel));

			employeePanel.setLastname("Nachname");
			assertEquals(false, employeeController.validateFields(employeePanel));
			
			employeePanel.setFirstname("Vorname");
			assertEquals(true, employeeController.validateFields(employeePanel));
			
			employeePanel.setLastname("");
			assertEquals(false, employeeController.validateFields(employeePanel));
		}
}