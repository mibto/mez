package ch.bli.mez.model.dao;

import java.util.List;

import org.hibernate.*;

import ch.bli.mez.model.Employee;
import ch.bli.mez.model.SessionManager;

@SuppressWarnings("unchecked")
public class EmployeeDAO {

  private SessionFactory sessionFactory;
  private Session session;

  public EmployeeDAO() {
    sessionFactory = SessionManager.getSessionFactory();
    session = sessionFactory.openSession();
  }

  public List<Employee> findAll() {
    List<Employee> employees = session.createQuery("from Employee").list();
    session.flush();
    return employees;
  }

  public void addEmployee(Employee employee) {
    Transaction tx = session.beginTransaction();
    try {
      session.save(employee);
      session.flush();
      tx.commit();
    } catch (Exception ex) {
      tx.rollback();
    }
  }

  public Employee getEmployee(Integer id) {
    Employee employee = (Employee) session.load(Employee.class, id);
    session.flush();
    return employee;
  }

  public void updateEmployee(Employee employee) {
    session.update(employee);
    session.flush();
  }

  public void deleteEmployee(Integer id) {
    Employee employee = (Employee) session.load(Employee.class, id);
    if (null != employee) {
      session.delete(employee);
    }
  }
}
