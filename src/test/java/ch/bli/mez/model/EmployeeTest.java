package ch.bli.mez.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.bli.mez.model.Employee;

public class EmployeeTest {

	Employee employee;

	@Before
	public void setUp() throws Exception {
		
		employee = new Employee();
		
		employee.setFirstName("Max");
		employee.setLastName("Power");
		employee.setCity("New York");
		employee.setPlz(9999);
		employee.setStreet("inceptionstreet");
		employee.setEmail("max.power@powerranger.com");
		employee.setHomeNumber("099 555 66 88");
		employee.setMobileNumber("055 777 99 77");
	}

	@After
	public void tearDown() throws Exception {
		employee = null;
	}

	@Test
	public void CreateEmployeeClasstest() {
		Employee employee = new Employee();
	}

	// City, PLZ und Street hinzufügen und abfragen
	@Test
	public void CityEmployeetest() {
		assertEquals("New York", employee.getCity());
		assertEquals("9999", employee.getPlz().toString());
		assertEquals("inceptionstreet", employee.getStreet());
		assertEquals("max.power@powerranger.com", employee.getEmail());
		assertEquals("Power", employee.getLastName());
		assertEquals("Max", employee.getFirstName());
		assertEquals("099 555 66 88", employee.getHomeNumber());
		assertEquals("055 777 99 77", employee.getMobileNumber());
	}


}
