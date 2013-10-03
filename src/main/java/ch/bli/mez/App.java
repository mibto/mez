package ch.bli.mez;

import ch.bli.mez.controller.EmployeeController;



public class App {
  public static void main(String[] args) {
    EmployeeController employeeController = new EmployeeController();
    employeeController.showView();
  }
}