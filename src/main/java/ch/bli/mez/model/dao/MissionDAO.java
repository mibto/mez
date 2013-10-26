package ch.bli.mez.model.dao;

import java.util.List;

import org.hibernate.*;

import ch.bli.mez.model.Mission;
import ch.bli.mez.model.SessionManager;

@SuppressWarnings("unchecked")
public class MissionDAO {

  private SessionFactory sessionFactory;
  private Session session;

  public MissionDAO() {
    sessionFactory = SessionManager.getSessionFactory();
    session = sessionFactory.openSession();
  }

  public List<Mission> findAll() {
	Transaction tx = session.beginTransaction();
    List<Mission> missions = session.createQuery("from "  + Mission.class.getName()).list();
    session.flush();
    tx.commit();
    return missions;
  }

  public void addMission(Mission mission){
    Transaction tx = session.beginTransaction();
    try {
      session.save(mission);
      session.flush();
      tx.commit();
    } catch (IllegalArgumentException ex) {
      tx.rollback();
      throw ex;
    }
  }

  public Mission getMission(Integer id) {
	Transaction tx = session.beginTransaction();
    Mission mission = (Mission) session.load(Mission.class, id);
    session.flush();
    tx.commit();
    return mission;
  }

  public void updateMission(Mission mission) {
    Transaction tx = session.beginTransaction();
    try {
      session.update(mission);
      session.flush();
      tx.commit();
    } catch (Exception ex) {
      tx.rollback();
    }
  }

  public void deleteMission(Integer id) {
    Transaction tx = session.beginTransaction();
    Mission mission = (Mission) session.load(Mission.class, id);
    if (null != mission) {
      session.delete(mission);
    }
    tx.commit();
  }
  
  public List<Mission> getOrganMissions(){
	  Transaction tx = session.beginTransaction();
	  List<Mission> organMissions = session.createQuery("from " +Mission.class.getName()+ " m where m.isOrgan = true").list();
	    tx.commit();
	    session.flush();
	    return organMissions;
  }
  
  public List<Mission> getNotOrganMissions(){
	  Transaction tx = session.beginTransaction();
	  List<Mission> notOrganMissions = session.createQuery("from " +Mission.class.getName()+ " m where m.isOrgan = false").list();
	    tx.commit();
	    session.flush();
	    return notOrganMissions;
  }
}
