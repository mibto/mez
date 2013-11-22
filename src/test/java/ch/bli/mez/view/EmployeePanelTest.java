package ch.bli.mez.view;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.bli.mez.view.employee.EmployeeForm;

public class EmployeePanelTest {

  static EmployeeForm employeeForm;

  @Before
  public void setUp() throws Exception {
    employeeForm = new EmployeeForm();
  }

  @After
  public void tearDown() throws Exception {
    employeeForm = null;
  }

  @Test
  public void validateFieldsTest() {

    // Case1: Required fields empty, PLZ correct
    employeeForm.setPlz("8880");
    assertFalse(employeeForm.validateFields());

    // Case2: Everything correct
    employeeForm.setFirstname("Vorname");
    employeeForm.setLastname("Nachname");
    assertTrue(employeeForm.validateFields());

    // Case3: Required fields correct, PLZ alphanumeric (fail)
    employeeForm.setPlz("88u0");
    assertFalse(employeeForm.validateFields());

    // Case4: Required fields correct, PLZ empty (correct)
    employeeForm.setPlz("");
    assertTrue(employeeForm.validateFields());
  }

}
