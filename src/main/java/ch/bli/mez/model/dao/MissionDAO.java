package ch.bli.mez.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch.bli.mez.model.Mission;
import ch.bli.mez.model.SessionManager;

@SuppressWarnings("unchecked")
public class MissionDAO {

  private SessionFactory sessionFactory;
  private Session session;

  public MissionDAO() {
    sessionFactory = SessionManager.getSessionFactory();
  }

  public List<Mission> findAll() {
	  List<Mission> missions = getActiveMissions();
	  missions.addAll(getInactiveMissions());
    return missions;
  }

  public void addMission(Mission mission) {
    session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      session.save(mission);
      tx.commit();
    } catch (IllegalArgumentException ex) {
      tx.rollback();
      throw ex;
    } finally {
      session.close();
    }
  }

  public Mission getMission(Integer id) {
    session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    Mission mission = (Mission) session.load(Mission.class, id);
    tx.commit();
    session.close();
    return mission;
  }

  public void updateMission(Mission mission) {
    session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      session.update(mission);
      tx.commit();
    } catch (Exception ex) {
      tx.rollback();
    } finally {
      session.close();
    }
  }

  public void deleteMission(Integer id) {
    session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    Mission mission = (Mission) session.load(Mission.class, id);
    if (null != mission) {
      session.delete(mission);
    }
    tx.commit();
    session.close();
  }

  public List<Mission> getOrganMissions() {
    session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    List<Mission> organMissions = session.createQuery(
        "from " + Mission.class.getName() + " m where m.isOrgan = true").list();
    tx.commit();
    session.close();
    return organMissions;
  }

  public List<Mission> getNotOrganMissions() {
    session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    List<Mission> notOrganMissions = session.createQuery(
        "from " + Mission.class.getName() + " m where m.isOrgan = false")
        .list();
    tx.commit();
    session.close();
    return notOrganMissions;
  }
  
  public List<Mission> getActiveMissions() {
	    session = sessionFactory.openSession();
	    Transaction tx = session.beginTransaction();
	    List<Mission> missions = session.createQuery(
	        "from " + Mission.class.getName() + " where isActive=true ").list();
	    session.flush();
	    tx.commit();
	    session.close();
	    return missions;
	  }
  
  public List<Mission> getInactiveMissions() {
	    session = sessionFactory.openSession();
	    Transaction tx = session.beginTransaction();
	    List<Mission> missions = session.createQuery(
	        "from " + Mission.class.getName() + " where isActive=false ").list();
	    session.flush();
	    tx.commit();
	    session.close();
	    return missions;
	  }
}
