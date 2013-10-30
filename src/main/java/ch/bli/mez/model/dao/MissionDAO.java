package ch.bli.mez.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ch.bli.mez.model.Mission;
import ch.bli.mez.model.SessionManager;

@SuppressWarnings("unchecked")
public class MissionDAO {

  public MissionDAO() {}

  public List<Mission> findAll() {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    List<Mission> missions = session.createQuery(
        "FROM " + Mission.class.getName() + " AS m ORDER BY m.isActive DESC").list();
    System.out.println(missions);
    tx.commit();
    return missions;
  }

  public void addMission(Mission mission) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    try {
      session.save(mission);
      tx.commit();
    } catch (IllegalArgumentException ex) {
      tx.rollback();
      throw ex;
    }
  }

  public Mission getMission(Integer id) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    Mission mission = (Mission) session.load(Mission.class, id);
    tx.commit();
    return mission;
  }

  public void updateMission(Mission mission) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    try {
      session.update(mission);
      tx.commit();
    } catch (Exception ex) {
      tx.rollback();
    }
  }

  public void deleteMission(Integer id) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    Mission mission = (Mission) session.load(Mission.class, id);
    if (null != mission) {
      session.delete(mission);
    }
    tx.commit();
  }

  public List<Mission> getOrganMissions() {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    List<Mission> organMissions = session.createQuery(
        "from " + Mission.class.getName() + " m where m.isOrgan = true").list();
    tx.commit();
    return organMissions;
  }

  public List<Mission> getNotOrganMissions() {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    List<Mission> notOrganMissions = session.createQuery(
        "from " + Mission.class.getName() + " m where m.isOrgan = false")
        .list();
    tx.commit();
    return notOrganMissions;
  }
  
  public List<Mission> getActiveMissions() {
    Session session = SessionManager.getSessionManager().getSession();
	    Transaction tx = session.beginTransaction();
	    List<Mission> missions = session.createQuery(
	        "from " + Mission.class.getName() + " where isActive=true ").list();
	    tx.commit();
	    return missions;
	  }
  
  public List<Mission> getInactiveMissions() {
      Session session = SessionManager.getSessionManager().getSession();
	    Transaction tx = session.beginTransaction();
	    List<Mission> missions = session.createQuery(
	        "from " + Mission.class.getName() + " where isActive=false ").list();
	    tx.commit();
	    return missions;
	  }
}
