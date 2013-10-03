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
    return session.createQuery("from Employee")
        .list();
  }

  public void addEmployee(Employee employee) {
    session.save(employee);
    session.flush();
  }

  public void deleteEmployee(Integer id) {
    Employee employee = (Employee) session.load(
        Employee.class, id);
    if (null != employee) {
      session.delete(employee);
    }
  }
}
