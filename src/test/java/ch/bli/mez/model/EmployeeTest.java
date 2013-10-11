package ch.bli.mez.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmployeeTest {

	String id;
	String lastName;
	String firstname;
	String email;
	String street;
	String city;
	Integer plz;
	String mobileNumber;
	String homeNumber;
	
	Employee employee;

	@SuppressWarnings("unused")
	@Before
	public void setUp() throws Exception {
		employee = new Employee();

		String id = "1";
		String lastName = "Power";
		String firstname = "Max";
		String street = "inceptionstreet";
		String email = "max.power@powerranger.com";
		String city = "New York";
		String mobileNumber = "099 555 66 88";
		Integer plz = 9999;
		String homeNumber = "055 777 99 77";

	}

	@After
	public void tearDown() throws Exception {
		employee = null;
	}

	@SuppressWarnings("unused")
	@Test
	public void CreateEmployeeClasstest() {
		Employee employee = new Employee();
	}

	// City, PLZ und Street hinzufügen und abfragen
	@Test
	public void CityEmployeetest() {
		employee.setCity(city);
		employee.setPlz(plz);
		employee.setStreet(street);
		
		assertEquals(city, employee.getCity());
		assertEquals(plz, employee.getPlz());
		assertEquals(street, employee.getStreet());
	}
	
	// E-Mail hinzufügen und abfragen
	@Test
	public void EmailEmployeetest() {
		employee.setEmail(email);
		assertEquals(email, employee.getCity());
	}

	// Lastname & Firstname hinzufügen und abfragen
	@Test
	public void FirstLastNameEmployeetest() {
		employee.setLastName(lastName);
		employee.setFirstName(firstname);
		assertEquals(email, employee.getLastName());
		assertEquals(email, employee.getFirstName());
	}

	
	// Phone Numbers testen
	@Test
	public void NumbersEmployeetest() {
		employee.setHomeNumber(homeNumber);
		employee.setMobileNumber(mobileNumber);
		
		assertEquals(homeNumber, employee.getHomeNumber());
		assertEquals(mobileNumber, employee.getMobileNumber());
	}


}
