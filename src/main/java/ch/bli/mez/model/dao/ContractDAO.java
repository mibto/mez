package ch.bli.mez.model.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.bli.mez.model.Contract;
import ch.bli.mez.model.Employee;
import ch.bli.mez.model.SessionManager;

@SuppressWarnings("unchecked")
public class ContractDAO {

  public ContractDAO() {
  }

  public List<Contract> findAll() {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    List<Contract> contracts = session.createQuery("from Contract c order by c.employee").list();
    tx.commit();
    return contracts;
  }

  public List<Contract> getEmployeeContracts(Employee employee) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    List<Contract> contracts = session.createQuery(
        "from Contract c WHERE c.employee=" + employee.getId() + " order by c.startDate ASC").list();
    tx.commit();
    return contracts;
  }

  /**
   * Gibt den Vertrag zurück, welches das gesuchte Datum beinhaltet
   * 
   * @param employee
   *          Mitarbeiter für welchen der Vertrag gesucht wird
   * @param date
   *          Das Datum für welches eine Überschneidung gefunden werden soll
   * @return der gesuchte Vertrag, null wenn kein Vertrag existiert
   */
  public List<Contract> getEmployeeContracts(Employee employee, Calendar date) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    List<Contract> contracts = session.createQuery(
        "from Contract c WHERE c.employee=" + employee.getId() + " AND c.startDate<=" + date.getTimeInMillis()
            + " AND c.endDate>=" + date.getTimeInMillis()).list();
    tx.commit();
    return contracts;
  }

  /**
   * Sucht nach einem Vertrag welcher zwischen gesuchtem Start- und Enddatum
   * liegt
   * 
   * @param employee
   *          Mitarbeiter für welchen der Vertrag gesucht wird
   * @param startDate
   *          Startdatum für welches dazwischenliegende Verträge gesucht werden
   *          sollen
   * @param endDate
   *          Enddatum für welches dazwischenliegende Verträge gesucht werden
   *          sollen
   * @return
   */
  public List<Contract> getEmployeeContracts(Employee employee, Calendar startDate, Calendar endDate) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    List<Contract> contracts = session.createQuery(
        "from Contract c WHERE c.employee=" + employee.getId() + " AND c.startDate>" + startDate.getTimeInMillis()
            + " AND c.endDate<" + endDate.getTimeInMillis()).list();
    tx.commit();
    return contracts;
  }

  public List<Contract> getEmployeeContractsWithoutEndDate(Employee employee) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    List<Contract> contracts = session.createQuery(
        "from Contract c WHERE c.employee=" + employee.getId() + " AND c.endDate IS null").list();
    tx.commit();
    return contracts;
  }

  public void addContract(Contract contract) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    try {
      session.save(contract);
      tx.commit();
    } catch (IllegalArgumentException ex) {
      tx.rollback();
      throw ex;
    }
  }

  public Contract getContract(Integer id) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    Contract contract = (Contract) session.load(Contract.class, id);
    tx.commit();
    return contract;
  }

  public void updateContract(Contract contract) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    try {
      session.update(contract);
      tx.commit();
    } catch (Exception ex) {
      tx.rollback();
    }
  }

  public void deleteContract(Integer id) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    Contract contract = (Contract) session.load(Contract.class, id);
    if (null != contract) {
      session.delete(contract);
    }
    tx.commit();
  }
}
