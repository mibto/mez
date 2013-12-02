package ch.bli.mez.controller;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.inOrder;
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
import ch.bli.mez.view.DefaultPanel;
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
    when(employeeForm.getParentPanel()).thenReturn(new DefaultPanel());

    employeeController.updateEmployee(employee, employeeForm);

    InOrder inOrder = inOrder(employeeForm, employee);

    // Check if setters are called with right return values in right order.
    inOrder.verify(employeeForm).validateFields();
    inOrder.verify(employeeForm, atLeastOnce()).getPlz();
    inOrder.verify(employee).setPlz(8880);
    inOrder.verify(employeeForm).getFirstname();
    inOrder.verify(employee).setFirstName("Vorname");
    inOrder.verify(employeeForm).getLastname();
    inOrder.verify(employee).setLastName("Nachname");
  }
}