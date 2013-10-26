package ch.bli.mez.model.dao;

import java.util.List;

import org.hibernate.*;

import ch.bli.mez.model.Position;
import ch.bli.mez.model.SessionManager;

@SuppressWarnings("unchecked")
public class PositionDAO {

  private SessionFactory sessionFactory;
  private Session session;

  public PositionDAO() {
    sessionFactory = SessionManager.getSessionFactory();
    session = sessionFactory.openSession();
  }

  public List<Position> findAll() {
	Transaction tx = session.beginTransaction();
    List<Position> positions = session.createQuery("from "  + Position.class.getName()).list();
    session.flush();
    tx.commit();
    return positions;
  }

  public void addPosition(Position position){
    Transaction tx = session.beginTransaction();
    try {
      session.save(position);
      session.flush();
      tx.commit();
    } catch (IllegalArgumentException ex) {
      tx.rollback();
      throw ex;
    }
  }

  public Position getPosition(Integer id) {
	Transaction tx = session.beginTransaction();
    Position position = (Position) session.load(Position.class, id);
    session.flush();
    tx.commit();
    return position;
  }

  public void updatePosition(Position position) {
    Transaction tx = session.beginTransaction();
    try {
      session.update(position);
      session.flush();
      tx.commit();
    } catch (Exception ex) {
      tx.rollback();
    }
  }

  public void deletePosition(Integer id) {
    Transaction tx = session.beginTransaction();
    Position position = (Position) session.load(Position.class, id);
    if (null != position) {
      session.delete(position);
    }
    tx.commit();
  }
}
