package ch.bli.mez.model.doa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.bli.mez.model.Employee;
import ch.bli.mez.model.dao.EmployeeDAO;

/**
 * Prüft ob ein Employee gespeichert, updated, oder gelöscht werden kann
 * 
 * @author dave
 * @version 1.0
 */
public class EmployeeDAOTest {

  private static EmployeeDAO instance;
  private Employee employee;

  @BeforeClass
  public static void beforeEverything() {
    instance = new EmployeeDAO();
  }

  @AfterClass
  public static void cleanUp() {
    instance = null;
  }

  @Before
  public void setUp() {
    this.employee = new Employee("Hans", "Müller");
  }

  @After
  public void tearDown() {
    this.employee = null;
  }

  /*
   * Prüft ob die Instanzen erstellt wurden
   */
  @Test
  public void checkInstance() {
    assertNotNull(instance);
    assertNotNull(employee);
  }

  /*
   * Prüft ob ein Employee korrekt abgespeichert und wieder gelöscht wird
   */
  @Test(expected = ObjectNotFoundException.class)
  public void saveEmployee() {
    instance.addEmployee(employee);
    assertEquals(employee.getFirstName(), instance.getEmployee(employee.getId()).getFirstName());
    instance.deleteEmployee(employee.getId());
    instance.getEmployee(employee.getId()).getFirstName();
  }

  /*
   * Prüft, dass ein Employee nicht doppelt gespeichert werden kann
   */
  @Test
  public void duplicateEmployee() {
    instance.addEmployee(employee);
    int begin = instance.findActive().size();
    instance.addEmployee(employee);
    assertEquals(begin, instance.findActive().size());
    instance.deleteEmployee(employee.getId());
  }

  /*
   * Prüft ob einen als null gespeicherten Employee nicht gespeichert wird
   */
  @Test
  public void addNullEmployee() {
    List<Employee> allEmployees = instance.findAll();
    instance.addEmployee(null);
    instance.findAll().equals(allEmployees);
  }

  /*
   * Prüft ob der Employee updated werden kann
   */
  @Test
  public void updateEmployee() {
    instance.addEmployee(employee);
    employee.setFirstName("Peter");
    employee.setPlz(9876);
    employee.setCity("Basel");
    instance.updateEmployee(employee);
    assertFalse("Hans".equals(instance.getEmployee(employee.getId()).getFirstName()));
    assertEquals(employee, instance.getEmployee(employee.getId()));
    instance.deleteEmployee(employee.getId());
  }

  /*
   * Prüft ob der Employee ohne Änderungen korrekt updated wird
   */
  @Test
  public void updateWithoutModificationEmployee() {
    instance.addEmployee(employee);
    instance.updateEmployee(employee);
    assertTrue("Hans".equals(instance.getEmployee(employee.getId()).getFirstName()));
    assertEquals(employee, instance.getEmployee(employee.getId()));
    instance.deleteEmployee(employee.getId());
  }

}
