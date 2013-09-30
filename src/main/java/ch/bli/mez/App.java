package ch.bli.mez;

import java.util.List;
import java.util.Properties;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.service.*;

import ch.bli.mez.controller.MainController;
import ch.bli.mez.model.classes.Employe;


public class App {
  private static SessionFactory sessionFactory = null; 
  private static ServiceRegistry serviceRegistry = null; 
     
  private static SessionFactory configureSessionFactory() throws HibernateException { 
      Configuration configuration = new Configuration(); 
      configuration.configure(); 
       
      Properties properties = configuration.getProperties();
       
      serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();         
      sessionFactory = configuration.buildSessionFactory(serviceRegistry); 
       
      return sessionFactory; 
  }
   
  public static void main(String[] args) {
      // Configure the session factory
      configureSessionFactory();
       
      Session session = null;
      Transaction tx=null;
       
      try {
          session = sessionFactory.openSession();
          tx = session.beginTransaction();
           
          // Creating Employe entity that will be save to the sqlite database
          Employe myEmploye = new Employe(4, "My Name", "my_email@email.com");
          Employe yourEmploye = new Employe(25, "Your Name", "your_email@email.com");
           
          // Saving to the database
          session.save(myEmploye);
          session.save(yourEmploye);
           
          // Committing the change in the database.
          //42!
          session.flush();
          tx.commit();
           
          // Fetching saved data
          List<Employe> EmployeList = session.createQuery("from Employe").list();
           
          for (Employe Employe : EmployeList) {
              System.out.println("Id: " + Employe.getId() + " | Name:"  + Employe.getName() + " | Email:" + Employe.getEmail());
          }
           
      } catch (Exception ex) {
          ex.printStackTrace();
           
          // Rolling back the changes to make the data consistent in case of any failure
          // in between multiple database write operations.
          tx.rollback();
      } finally{
          if(session != null) {
              session.close();
          }
      }
  }
}
 
 
/*public class App 
{
  static MainController mainController;
  
    public static void main( String[] args )
    {
       mainController = new MainController();
       mainController.showView();
    }
}*/
