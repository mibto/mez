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
  private TimeEntryController timeEntryController;
  private final MainView view;

  public MainController() {
    view = new MainView();
    managementController = null;
    employeeController = null;
    timeEntryController = null;
    setListener();
    view.setTimeEntryPanel(new TimeEntryController().getView());
  }

  public void showView() {
    view.pack();
    view.setVisible(true);
  }

  private void setListener() {
    view.setTabChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        if (((JTabbedPane) e.getSource()).getSelectedIndex() == 0 && timeEntryController == null) {
          timeEntryController = new TimeEntryController();
          view.setTimeEntryPanel(timeEntryController.getView());
        } else if (((JTabbedPane) e.getSource()).getSelectedIndex() == 0) {
          timeEntryController.updateTimeView();
        }
        if (((JTabbedPane) e.getSource()).getSelectedIndex() == 1 && employeeController == null) {
          employeeController = new EmployeeController();
          view.setEmployeePanel(employeeController.getView());
        }
        if (((JTabbedPane) e.getSource()).getSelectedIndex() == 3 && managementController == null) {
          managementController = new ManagementController();
          view.setManagementPanel(managementController.getView());
        }
      }
    });
  }
}
