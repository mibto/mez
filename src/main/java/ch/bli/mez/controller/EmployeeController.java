package ch.bli.mez.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import ch.bli.mez.model.Employee;
import ch.bli.mez.model.dao.EmployeeDAO;
import ch.bli.mez.view.EmployeeView;

public class EmployeeController {
	private EmployeeView view;
	private EmployeeDAO model;
	private Collection<String> formfields;
	private final SearchController searchController;

	public EmployeeController() {
		this.model = new EmployeeDAO();
		this.searchController = new SearchController();
		this.view = new EmployeeView(searchController.getSearchPanel());
		this.formfields = new ArrayList<String>();
		formfields.add("firstName");
		formfields.add("lastName");
		formfields.add("street");
		formfields.add("plz");
		formfields.add("city");
		addListener();
		addTabsForEmployees();
	}
	
	public EmployeeView getView() {
		return view;
	}

	private void addTabsForEmployees() {
		for (Employee employee : model.findAll()) {
			addTabForEmployee(employee);
		}
	}

	private void addTabForEmployee(Employee employee) {
		view.addEmployeeTab(
				employee.getFirstName() + " " + employee.getLastName(),
				employee.getId());
	}

	private HashMap<String, Object> getFormData(Integer id) {
		HashMap<String, Object> formdata = new HashMap<String, Object>();
		for (String field : formfields) {
			formdata.put(field, view.getFieldValue(id, field));
		}
		return formdata;
	}

	private void addListener() {
		view.setBlubbctionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				for (Employee employee : model.findAll()) {
					// jetzt was der view übergeben.
					System.out.println(employee.getFirstName() + " "
							+ employee.getLastName());
				}
			}
		});

		view.setBlaaActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Employee employee = new Employee("Michael", "Brodmann",
						"gattikonerstrasse 117", 8136, "gattikon");
				model.addEmployee(employee);
				addTabForEmployee(employee);
				
				employee = new Employee("Max", "Power",
						"superstrasse 1541", 9999, "Entenhausen");
				model.addEmployee(employee);
				addTabForEmployee(employee);
			}
		});

		view.setSaveNewEmployeeListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				HashMap<String, Object> formdata = getFormData(null);
				Employee employee = new Employee((String) formdata
						.get("firstName"), (String) formdata.get("lastName"),
						(String) formdata.get("street"), (Integer) formdata
								.get("plz"), (String) formdata.get("city"));
				model.addEmployee(employee);
				addTabForEmployee(employee);
			}
		});

		view.setSaveChangedEmployeeListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				Integer id = Integer.parseInt(((Component) event.getSource())
						.getName());
				HashMap<String, Object> formdata = getFormData(id);
				Employee employee = model.getEmployee(id);
				employee.setFirstName((String) formdata.get("firstName"));
				employee.setLastName((String) formdata.get("lastName"));
				employee.setStreet((String) formdata.get("street"));
				employee.setPlz((Integer) formdata.get("plz"));
				employee.setCity((String) formdata.get("city"));
				model.updateEmployee(employee);
			}
		});
	}

}
