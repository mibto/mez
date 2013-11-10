package ch.bli.mez.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.bli.mez.model.Holiday;
import ch.bli.mez.model.SessionManager;

/**
 * 
 * @author dave
 * @version 1.0
 */

@SuppressWarnings("unchecked")
public class HolidayDAO {

	  public HolidayDAO() {}

	  public List<Holiday> findAll() {
	    Session session = SessionManager.getSessionManager().getSession();
	    Transaction tx = session.beginTransaction();
	    List<Holiday> holidays = session.createQuery(
	        "FROM " + Holiday.class.getName() + " AS h ORDER BY h.year DESC").list();
	    tx.commit();
	    return holidays;
	  }

	  public void addHoliday(Holiday holiday) {
	    Session session = SessionManager.getSessionManager().getSession();
	    Transaction tx = session.beginTransaction();
	    try {
	      session.save(holiday);
	      tx.commit();
	    } catch (IllegalArgumentException ex) {
	      tx.rollback();
	      throw ex;
	    }
	  }

	  public Holiday getHoliday(Integer id) {
	    Session session = SessionManager.getSessionManager().getSession();
	    Transaction tx = session.beginTransaction();
	    Holiday holiday = (Holiday) session.load(Holiday.class, id);
	    tx.commit();
	    return holiday;
	  }
	  
	  /**
	   * 
	   * @return alle globale Holiday Objekte welche keinem Mitarbeiter zugewiesen sind
	   */
	  public List<Holiday> getGlobalHoliday(){
	    Session session = SessionManager.getSessionManager().getSession();
	    Transaction tx = session.beginTransaction();
	    List<Holiday> holidays = session.createQuery(
	        "FROM " + Holiday.class.getName() + " AS h WHERE h.employee IS null ORDER BY h.year DESC").list();
	    tx.commit();
	    return holidays;
	  }
	  
	  /**
	   * @param year das gesuchte Jahr
	   * @return das globale Holiday Objekt (jene mit zugewiesene Mitarbeiter werden ignoriert)
	   */
	  public Holiday getGlobalHolidayByYear(Integer year) {
		    Session session = SessionManager.getSessionManager().getSession();
		    Transaction tx = session.beginTransaction();
		    Holiday holiday = (Holiday) session.createQuery(
		        "from " + Holiday.class.getName() + " h where h.year=" + year + " AND h.employee IS null").uniqueResult();
		    tx.commit();
		    return holiday;
		  }

	  public void updateHoliday(Holiday holiday) {
	    Session session = SessionManager.getSessionManager().getSession();
	    Transaction tx = session.beginTransaction();
	    try {
	      session.update(holiday);
	      tx.commit();
	    } catch (Exception ex) {
	      tx.rollback();
	    }
	  }

	  public void deleteHoliday(Integer id) {
	    Session session = SessionManager.getSessionManager().getSession();
	    Transaction tx = session.beginTransaction();
	    Holiday holiday = (Holiday) session.load(Holiday.class, id);
	    if (null != holiday) {
	      session.delete(holiday);
	    }
	    tx.commit();
	  }
}
