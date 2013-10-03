package ch.bli.mez.model;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class SessionManager {

  private static SessionFactory sessionFactory = null;
  private static ServiceRegistry serviceRegistry = null;

  public static SessionFactory getSessionFactory() throws HibernateException {
    Configuration configuration = new Configuration();
    configuration.configure();

    Properties properties = configuration.getProperties();

    serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
    sessionFactory = configuration.buildSessionFactory(serviceRegistry);

    return sessionFactory;
  }

}
