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
    List<Mission> missions = session.createQuery("from "  + Mission.class.getName()).list();
    session.flush();
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
    Mission mission = (Mission) session.load(Mission.class, id);
    session.flush();
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
    Mission mission = (Mission) session.load(Mission.class, id);
    if (null != mission) {
      session.delete(mission);
    }
  }
}
