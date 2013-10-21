package ch.bli.mez.controller;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.bli.mez.view.MainView;

/**
 * @author dave
 * @version 1.0
 */

public class MainController {

  private EmployeeController employeeController;
  private ManagementController managementController;
  private final MainView mainView;

  public MainController() {
    this.mainView = new MainView();
    this.managementController = null;
    this.employeeController = null;
    setListener();
  }

  public void showView() {
    mainView.pack();
    mainView.setVisible(true);
  }

  private void setListener() {
    mainView.setTabChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        // Für andere Panels das selbe. Zeit erfassen noch besprechen wann die
        // Liste geladen werden soll.
        if (((JTabbedPane) e.getSource()).getSelectedIndex() == 1
            && employeeController == null) {
          employeeController = new EmployeeController();
          mainView.setEmployeePanel(employeeController.getView());
        }
        if (((JTabbedPane) e.getSource()).getSelectedIndex() == 3
            && managementController == null) {
          managementController = new ManagementController();
          mainView.setManagementPanel(managementController.getView());
        }
      }
    });
  }
}
