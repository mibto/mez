package ch.bli.mez.model.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import ch.bli.mez.model.Employee;
import ch.bli.mez.model.SessionManager;
import ch.bli.mez.util.Keyword;

@SuppressWarnings("unchecked")
public class EmployeeDAO implements Searchable {

  public EmployeeDAO() {
  }

  public List<Employee> findAll() {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    List<Employee> employees = session.createQuery("from Employee e where isActive=true order by e.firstName").list();
    tx.commit();
    return employees;
  }

  public void addEmployee(Employee employee) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    try {
      session.save(employee);
      tx.commit();
    } catch (IllegalArgumentException ex) {
      tx.rollback();
      throw ex;
    }
  }

  public Employee getEmployee(Integer id) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    Employee employee = (Employee) session.load(Employee.class, id);
    tx.commit();
    return employee;
  }

  public void updateEmployee(Employee employee) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    try {
      session.update(employee);
      tx.commit();
    } catch (Exception ex) {
      tx.rollback();
    }
  }

  public void deleteEmployee(Integer id) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    Employee employee = (Employee) session.load(Employee.class, id);
    if (null != employee) {
      session.delete(employee);
    }
    tx.commit();
  }

  public List<Employee> findByKeywords(String url) {
    Criteria criteria = createCriteria(Keyword.getKeywords(url));
    return criteria.list();
  }

  private Criteria createCriteria(Map<String, String> keywords) {
    Session session = SessionManager.getSessionManager().getSession();
    Criteria criteria = session.createCriteria(Employee.class);
    Criterion firstName = Restrictions.like("firstName", keywords.get("name") + "%");
    Criterion lastName = Restrictions.like("lastName", keywords.get("name") + "%");
    criteria.add(Restrictions.or(firstName, lastName));
    return criteria;
  }

}
