package ch.bli.mez.controller;

import ch.bli.mez.model.EmployeModel;
import ch.bli.mez.view.EmployeView;

public class EmployeController {
  private EmployeView view;
  private EmployeModel model;

  public EmployeController() {
    this.model = new EmployeModel();
    this.view = new EmployeView();
    addListener();
  }

  public void showView() {
    this.view.setVisible(true);
  }

  private void addListener() {

  }

}
