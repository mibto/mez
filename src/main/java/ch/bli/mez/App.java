package ch.bli.mez;

import ch.bli.mez.controller.MainController;

public class App {
	/*
	 * Starten vom MainController und somit vom Mainframe
	 */
	public static void main(String[] args) {
		MainController mainController = new MainController();
		mainController.showView();
	}
}