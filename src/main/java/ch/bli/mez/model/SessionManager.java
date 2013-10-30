package ch.bli.mez.model;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class SessionManager {

  private Configuration configuration;
  private Properties properties;
  private ServiceRegistry serviceRegistry;
  private SessionFactory sessionFactory;

  private static SessionManager sessionManager;

  private Session session = null;

  private SessionManager(){
    configuration = new Configuration().configure();
    properties = configuration.getProperties();
    serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    session = sessionFactory.openSession();
  }

  public static SessionManager getSessionManager(){
    if (sessionManager == null){
      sessionManager = new SessionManager();
    }
    return sessionManager;       
  }

  public Session getSession(){
    if(!session.isOpen()){
      session = sessionFactory.openSession();
    }
    return session;
  }

}
