package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    view.setNewButtonActionListener(new ActionListener() {

      public void actionPerformed(ActionEvent arg0) {
        model.getEmployeById(1);
      }
    });
  }

}
