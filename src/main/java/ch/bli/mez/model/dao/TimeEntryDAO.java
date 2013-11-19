package ch.bli.mez.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.bli.mez.model.Employee;
import ch.bli.mez.model.SessionManager;
import ch.bli.mez.model.TimeEntry;

@SuppressWarnings("unchecked")
public class TimeEntryDAO {

  public TimeEntryDAO() {
  }

  public List<TimeEntry> findAll(Employee employee) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    List<TimeEntry> timeEntries = session.createQuery(
        "FROM TimeEntry WHERE employee_id = " + employee.getId() + " order by date").list();
    tx.commit();
    return timeEntries;
  }

  public void addTimeEntry(TimeEntry timeEntry) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    try {
      session.save(timeEntry);
      tx.commit();
    } catch (IllegalArgumentException ex) {
      tx.rollback();
      throw ex;
    }
  }

  public TimeEntry getTimeEntry(Integer id) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    TimeEntry timeEntry = (TimeEntry) session.load(TimeEntry.class, id);
    tx.commit();
    return timeEntry;
  }

  public void updateTimeEntry(TimeEntry timeEntry) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    try {
      session.update(timeEntry);
      tx.commit();
    } catch (Exception ex) {
      tx.rollback();
    }
  }

  public void deleteTimeEntry(Integer id) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    TimeEntry timeEntry = (TimeEntry) session.load(TimeEntry.class, id);
    if (null != timeEntry) {
      session.delete(timeEntry);
    }
    tx.commit();
  }
}
