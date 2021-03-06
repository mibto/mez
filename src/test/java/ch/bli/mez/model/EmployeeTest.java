package ch.bli.mez.model;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    Employee newemployee = new Employee("Vorname", "Nachname");
    assertEquals("Vorname", newemployee.getFirstName());
    assertEquals("Nachname", newemployee.getLastName());
  }

  // Get Methoden testen
  @Test
  public void GetEmployeetest() {
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
