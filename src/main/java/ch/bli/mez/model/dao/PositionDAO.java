package ch.bli.mez.model.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import ch.bli.mez.model.Position;
import ch.bli.mez.model.SessionManager;

@SuppressWarnings("unchecked")
public class PositionDAO {

  public PositionDAO() {
  }

  public List<Position> findAll() {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    List<Position> positions = session.createQuery(
        "FROM " + Position.class.getName() + " AS p ORDER BY p.isActive DESC")
        .list();
    tx.commit();
    return positions;
  }

  public void addPosition(Position position) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    try {
      session.save(position);
      tx.commit();
    } catch (IllegalArgumentException ex) {
      tx.rollback();
      throw ex;
    }
  }

  public Position getPosition(Integer id) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    Position position = (Position) session.get(Position.class, id);
    tx.commit();
    return position;
  }

  public Position getPositionByPositionName(String positionName) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    Position position = (Position) session.createQuery("FROM "
        + Position.class.getName() + " WHERE positionName = " + positionName);
    tx.commit();
    return position;
  }

  public void updatePosition(Position position) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    try {
      session.update(position);
      tx.commit();
    } catch (Exception ex) {
      tx.rollback();
    }
  }

  public void deletePosition(Integer id) {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    Position position = (Position) session.load(Position.class, id);
    if (null != position) {
      session.delete(position);
    }
    tx.commit();
  }

  public List<Position> getOrganPositions() {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    List<Position> organPositions = session.createQuery(
        "from " + Position.class.getName() + " p where p.organDefault = true")
        .list();
    tx.commit();
    return organPositions;
  }

  public List<Position> getActivePositions() {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    List<Position> positions = session.createQuery(
        "from " + Position.class.getName() + " where isActive=true ").list();
    tx.commit();
    return positions;
  }

  public List<Position> getInactivePositions() {
    Session session = SessionManager.getSessionManager().getSession();
    Transaction tx = session.beginTransaction();
    List<Position> positions = session.createQuery(
        "from " + Position.class.getName() + " where isActive=false ").list();
    tx.commit();
    return positions;
  }

  public Position findByCode(String code) {
    Session session = SessionManager.getSessionManager().getSession();
    Criteria criteria = session.createCriteria(Position.class);
    criteria.add(Restrictions.eq("code", code));
    Position position = (Position) criteria.list().get(0);
    if (position != null) {
      return position;
    } else {
      return null;
    }
  }
}
