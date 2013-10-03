package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    this.view.setVisible(true);
  }

  private void addListener() {
    view.setBlubbctionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {
        for (Employee employee : model.findAll()) {
          //jetzt was der view übergeben.
          System.out.println(employee.getName() + employee.getEmail());
        }
      } 
    });
    
    view.setBlaaActionListener(new ActionListener() {
      
      public void actionPerformed(ActionEvent arg0) {
        model.addEmployee(new Employee(3, "franzl max2", "max2@blaaa.ch"));
      }
    });
  }
}
