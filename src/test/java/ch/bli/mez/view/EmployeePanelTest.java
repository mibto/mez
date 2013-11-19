package ch.bli.mez.view;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.bli.mez.view.employee.EmployeePanel;

public class EmployeePanelTest {

  static EmployeePanel employeePanel;

  @Before
  public void setUp() throws Exception {
    employeePanel = new EmployeePanel();
  }

  @After
  public void tearDown() throws Exception {
    employeePanel = null;
  }

  @Test
  public void validateFieldsTest() {

    // Case1: Required fields empty, PLZ correct
    employeePanel.setPlz("8880");
    assertFalse(employeePanel.validateFields());

    // Case2: Everything correct
    employeePanel.setFirstname("Vorname");
    employeePanel.setLastname("Nachname");
    assertTrue(employeePanel.validateFields());

    // Case3: Required fields correct, PLZ alphanumeric (fail)
    employeePanel.setPlz("88u0");
    assertFalse(employeePanel.validateFields());

    // Case4: Required fields correct, PLZ empty (correct)
    employeePanel.setPlz("");
    assertTrue(employeePanel.validateFields());
  }

}
