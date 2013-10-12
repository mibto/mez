package ch.bli.mez.controller;

import ch.bli.mez.view.MainView;

public class MainController {

	private final EmployeeController employeeController;
	// private final TimeMgmtController timeMgmtController;
	// private final ReportingController reportingController;
	// private final ManagementController managementController;
	private final MainView mainView;

	public MainController() {
		this.mainView = new MainView();
		this.employeeController = new EmployeeController();
		setPanels();
	}

	public void showView() {
		mainView.pack();
		mainView.setVisible(true);
	}

	private void setPanels() {
		this.mainView.setEmployeePanel(this.employeeController.getView());
		// hier sollen die "Zeiten erfassen", "Auswertungen" und "Verwaltung"
		// Panels dem Mainframe hinzugefügt werden (internerKommentar)
	}
}
