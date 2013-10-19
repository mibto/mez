package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ch.bli.mez.model.Employee;
import ch.bli.mez.model.dao.EmployeeDAO;
import ch.bli.mez.view.EmployeePanel;
import ch.bli.mez.view.EmployeeView;

/**
 * @author leandrafinger
 * @version 1.0
 */
public class EmployeeController {
	private EmployeeView view;
	private EmployeeDAO model;
	private final SearchController searchController;

	/**
	 * Initialisiert einen SearchController, EmployeeDAO, EmployeeView. Bestimmt
	 * die Formfelder und speichert Sie in der ArrayList formfields. Die
	 * Methoden addListener(), addTabsForEmployees() werden aufgerufen.
	 */
	public EmployeeController() {
		this.model = new EmployeeDAO();
		this.searchController = new SearchController();
		this.view = new EmployeeView(searchController.getSearchPanel());
		addInitalTab();
		addTabsForEmployees();
	}

	public EmployeeView getView() {
		return view;
	}

	private void addInitalTab() {
		final EmployeePanel panel = new EmployeePanel();
		panel.hideDeleteButton(); // Muss bei neuen Mitarbeiter nicht angezeigt werden
		
		
		panel.setSaveEmployeeListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				panel.hideConfirmation();
				panel.hideErrors();
				if (!validateFields(panel)) {
					return;
				}
				Employee employee = new Employee(panel.getFirstname(), panel.getLastname());
				employee.setStreet(panel.getStreet());
				if (!panel.getPlz().equals("")) employee.setPlz(Integer.parseInt(panel.getPlz()));
				employee.setCity(panel.getCity());
				employee.setMobileNumber(panel.getMobileNumber());
				employee.setHomeNumber(panel.getHomeNumber());
				employee.setEmail(panel.getEmail());
				addTabForEmployee(employee);
				model.addEmployee(employee);
				panel.cleanFields();
				panel.showConfirmation(employee.getFirstName() + " " + employee.getLastName());
			}
		});
		view.addTab("Neuer Mitarbeiter", panel);
	}

	/**
	 * Iteriert über alle Employees und ruft für jeden die Methode
	 * addTabForEmployee auf.
	 */
	private void addTabsForEmployees() {
		for (Employee employee : model.findAll()) {
			addTabForEmployee(employee);
		}
	}

	/**
	 * Nimmt einen Employee entgegen, übergibt Name und Id der view Methode
	 * addEmployeeTab
	 * 
	 * @param emp
	 *            Employee Objekt
	 */
	public void addTabForEmployee(Employee emp) {
		final Employee employee = emp;
		final EmployeePanel panel = new EmployeePanel();

		// TODO
		// Ein Employee muss bereits beim speichern eine ID erhalten. Oder er muss zuerst in die DB gespeichert und dann wieder herausgelesen werden.
		// panel.setDeleteButtonName(employee.getId().toString()); // ID auf DeleteButton
		panel.setFirstname(employee.getFirstName());
		panel.setLastname(employee.getLastName());
		panel.setCity(employee.getCity());
		if (employee.getPlz() != 0)
			panel.setPlz(employee.getPlz().toString());
		panel.setEmail(employee.getEmail());
		panel.setHomeNumber(employee.getHomeNumber());
		panel.setMobileNumber(employee.getMobileNumber());
		panel.setStreet(employee.getStreet());

		panel.setSaveEmployeeListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				panel.hideErrors();
				panel.hideConfirmation();
				if (!validateFields(panel)) {
					return;
				}
				if (!panel.getPlz().equals(""))
					employee.setPlz(Integer.parseInt(panel.getPlz()));
				employee.setFirstName(panel.getFirstname());
				employee.setLastName(panel.getLastname());
				employee.setStreet(panel.getStreet());
				employee.setCity(panel.getCity());
				employee.setMobileNumber(panel.getMobileNumber());
				employee.setHomeNumber(panel.getHomeNumber());
				employee.setEmail(panel.getEmail());
				model.updateEmployee(employee);
				panel.showConfirmation(employee.getFirstName() + " " + employee.getLastName());
			}
		});

		panel.setDeleteEmployeeListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				// TODO Sobald die ID-Problematik gelöst ist kann das hier benutzt werden.
				//model.deleteEmployee(Integer.parseInt(panel.getDeleteButtonName())); 
				view.removeTab(view.getSelectedIndex());
			}
		});

		view.addTab(employee.getFirstName() + ' ' + employee.getLastName(), panel);
	}

	public boolean validateFields(EmployeePanel panel) {
		boolean valid = true;
		if (panel.getFirstname().equals("")) {
			panel.showFirstNameError();
			valid = false;
		}
		if (panel.getLastname().equals("")) {
			panel.showLastNameError();
			valid = false;
		}
		try {
			if (!panel.getPlz().equals(""))
				Integer.parseInt(panel.getPlz());
		} catch (NumberFormatException e) {
			panel.showPlzError();
			valid = false;
		}
		return valid;
	}
}
