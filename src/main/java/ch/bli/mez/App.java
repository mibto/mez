package ch.bli.mez;

import ch.bli.mez.controller.MainController;
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
	}
}