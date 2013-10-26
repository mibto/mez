package ch.bli.mez;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ch.bli.mez.controller.MainController;
import ch.bli.mez.model.SessionManager;
/**
 * Started die App
 * @author mike
 * @version 1.0
 */
public class App {
	/*
	 * Starten vom MainController und somit vom Mainframe
	 */
	public static void main(String[] args) {
		MainController mainController = new MainController();
		mainController.showView();
		SessionFactory sessionFactory = SessionManager.getSessionFactory();
	    Session session = sessionFactory.openSession();
	    session.close();
	}
}