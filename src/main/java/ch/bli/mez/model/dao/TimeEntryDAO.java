package ch.bli.mez.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch.bli.mez.model.SessionManager;
import ch.bli.mez.model.TimeEntry;

@SuppressWarnings("unchecked")
public class TimeEntryDAO {

  private SessionFactory sessionFactory;
  private Session session;

  public TimeEntryDAO() {
    sessionFactory = SessionManager.getSessionFactory();
    session = sessionFactory.openSession();
  }

  public List<TimeEntry> findAll() {
    Transaction tx = session.beginTransaction();
    List<TimeEntry> timeEntries = session.createQuery("from TimeEntry").list();
    session.flush();
    tx.commit();
    return timeEntries;
  }

  public void addTimeEntry(TimeEntry timeEntry) {
    Transaction tx = session.beginTransaction();
    try {
      session.save(timeEntry);
      session.flush();
      tx.commit();
    } catch (IllegalArgumentException ex) {
      tx.rollback();
      throw ex;
    }
  }

  public TimeEntry getTimeEntry(Integer id) {
    Transaction tx = session.beginTransaction();
    TimeEntry timeEntry = (TimeEntry) session.load(TimeEntry.class, id);
    session.flush();
    tx.commit();
    return timeEntry;
  }

  public void updateTimeEntry(TimeEntry timeEntry) {
    Transaction tx = session.beginTransaction();
    try {
      session.update(timeEntry);
      session.flush();
      tx.commit();
    } catch (Exception ex) {
      tx.rollback();
    }
  }

  public void deleteTimeEntry(Integer id) {
    Transaction tx = session.beginTransaction();
    TimeEntry timeEntry = (TimeEntry) session.load(TimeEntry.class, id);
    if (null != timeEntry) {
      session.delete(timeEntry);
    }
    tx.commit();
  }
}
