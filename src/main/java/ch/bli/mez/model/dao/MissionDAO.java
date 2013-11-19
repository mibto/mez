package ch.bli.mez.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ch.bli.mez.model.Mission;
import ch.bli.mez.model.SessionManager;

@SuppressWarnings("unchecked")
public class MissionDAO {

  public MissionDAO() {
  }

  public List<Mission> findAll() {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    List<Mission> missions = session.createQuery("FROM " + Mission.class.getName() + " AS m ORDER BY m.isActive DESC")
        .list();
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

  public Mission getMissionByMissionName(String missionName) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    // Integer id = (Integer)
    // session.createSQLQuery("select mission_id from mission where missionName = "
    // + missionName).list();
    // System.out.print(id);
    // Mission mission = (Mission) session.get(Mission.class, id);
    tx.commit();
    return new Mission();
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
    List<Mission> organMissions = session.createQuery("from " + Mission.class.getName() + " m where m.isOrgan = true")
        .list();
    tx.commit();
    return organMissions;
  }

  public List<Mission> getNotOrganMissions() {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    List<Mission> notOrganMissions = session.createQuery(
        "from " + Mission.class.getName() + " m where m.isOrgan = false").list();
    tx.commit();
    return notOrganMissions;
  }

  public List<Mission> getActiveMissions() {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    List<Mission> missions = session.createQuery("from " + Mission.class.getName() + " where isActive=true ").list();
    tx.commit();
    return missions;
  }

  public List<Mission> getInactiveMissions() {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    List<Mission> missions = session.createQuery("from " + Mission.class.getName() + " where isActive=false ").list();
    tx.commit();
    return missions;
  }

  public Mission findByMissionName(String missionName) {
    Session session = SessionManager.getSessionManager().getSession();
    Criteria criteria = session.createCriteria(Mission.class);
    criteria.add(Restrictions.eq("missionName", missionName));
    Mission mission = (Mission) criteria.list().get(0);
    if (mission != null) {
      return mission;
    } else {
      return null;
    }
  }
}
