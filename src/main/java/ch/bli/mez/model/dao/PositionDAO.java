package ch.bli.mez.model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ch.bli.mez.model.Position;
import ch.bli.mez.model.SessionManager;

@SuppressWarnings("unchecked")
public class PositionDAO {

  private SessionFactory sessionFactory;
  private Session session;

  public PositionDAO() {
    sessionFactory = SessionManager.getSessionFactory();
  }

  public List<Position> findAll() {
	  session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    List<Position> positions = session.createQuery(
        "from " + Position.class.getName() + " where isActive=true ").list();
    tx.commit();
    session.close();
    return positions;
  }

  public void addPosition(Position position) {
		 session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      session.save(position);
      tx.commit();
    } catch (IllegalArgumentException ex) {
      tx.rollback();
      throw ex;
    } finally {
    	session.close();
    }
  }

  public Position getPosition(Integer id) {
		 session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    Position position = (Position) session.load(Position.class, id);
    tx.commit();
    session.close();
    return position;
  }

  public void updatePosition(Position position) {
	 session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    try {
      session.update(position);
      tx.commit();
    } catch (Exception ex) {
      tx.rollback();
    } finally {
    	session.close();
    }
  }

  public void deletePosition(Integer id) {
	session = sessionFactory.openSession();
    Transaction tx = session.beginTransaction();
    Position position = (Position) session.load(Position.class, id);
    if (null != position) {
      session.delete(position);
    }
    tx.commit();
    session.close();
  }
  
  public List<Position> getOrganPositions() {
	    session = sessionFactory.openSession();
	    Transaction tx = session.beginTransaction();
	    List<Position> organPositions = session.createQuery(
	        "from " + Position.class.getName() + " p where p.organDefault = true").list();
	    tx.commit();
	    session.close();
	    return organPositions;
	  }
}
