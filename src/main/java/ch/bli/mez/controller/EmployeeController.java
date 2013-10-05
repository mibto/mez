package ch.bli.mez.controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;

import ch.bli.mez.model.Employee;
import ch.bli.mez.model.dao.EmployeeDAO;
import ch.bli.mez.view.EmployeeView;

public class EmployeeController {
  private EmployeeView view;
  private EmployeeDAO model;

  public EmployeeController() {
    this.model = new EmployeeDAO();
    this.view = new EmployeeView();
    addListener();
  }

  public void showView() {
	this.view.setSize(1300, 800);
	addTabsForEmployees();
    this.view.setVisible(true);
  }
  
  private void addTabsForEmployees(){
	  for (Employee employee : model.findAll()) {
		  addTabForEmployee(employee);
	  }
  }
  
  private void addTabForEmployee(Employee employee){
	  view.addEmployeeTab(employee.getFirstName() + " " + employee.getLastName(), employee.getId());
  }
  
  private ArrayList getFormData() {
		Collection formdata = new ArrayList();
		// TODO: forloop?
		formdata.add(view.getFirstName());
		formdata.add(view.getLastName());
		formdata.add(view.getStreet());
		formdata.add(view.getPlz());
		formdata.add(view.getCity());
		return (ArrayList)formdata;
  }

  private void addListener() {
    view.setBlubbctionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {
        for (Employee employee : model.findAll()) {
          //jetzt was der view übergeben.
          System.out.println(employee.getFirstName() + " " +  employee.getLastName());
        }
      } 
    });
    
    view.setBlaaActionListener(new ActionListener() {
      
      public void actionPerformed(ActionEvent arg0) {
    	Employee employee = new Employee("michael", "brodmann", "gattikonerstrasse 117", 8136, "gattikon");
        model.addEmployee(employee);
        addTabForEmployee(employee);
      }
    });
    
    view.setSaveNewEmployeeListener(new ActionListener() {
        
        public void actionPerformed(ActionEvent event) {
        	ArrayList formdata = getFormData();
        	Employee employee = new Employee((String)formdata.get(0), (String)formdata.get(1), (String)formdata.get(2), (Integer)formdata.get(3), (String)formdata.get(4));
        	model.addEmployee(employee);
        	addTabForEmployee(employee);
        }
    });
    
    view.setSaveChangedEmployeeListener(new ActionListener() {
        
        public void actionPerformed(ActionEvent event) {
        	ArrayList formdata = getFormData();
        	Integer id = Integer.parseInt(((Component) event.getSource()).getName());
        	Employee employee = model.getEmployee(id);
        	employee.setFirstName((String)formdata.get(0));
        	employee.setLastName((String)formdata.get(1));
        	employee.setStreet((String)formdata.get(2));
        	employee.setPlz((Integer)formdata.get(3));
        	employee.setCity((String)formdata.get(4));
        	model.updateEmployee(employee);
        }
    });
  }
}
