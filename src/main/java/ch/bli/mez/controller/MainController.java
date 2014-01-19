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
  private AnalysisController analysisController;

  private final MainView view;

  public MainController() {
    view = new MainView();
    managementController = null;
    employeeController = null;
    timeEntryController = new TimeEntryController();
    analysisController = null;
    setListener();
    view.setTimeEntryPanel(timeEntryController.getView());
  }

  public void showView() {
    view.pack();
    view.setVisible(true);
  }

  private void setListener() {
    view.setTabChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        if (((JTabbedPane) e.getSource()).getSelectedIndex() == 0) {
          timeEntryController.updateTimeView();
        }

        if (((JTabbedPane) e.getSource()).getSelectedIndex() == 1 && employeeController == null) {
          employeeController = new EmployeeController();
          view.setEmployeePanel(employeeController.getView());
        } else if (((JTabbedPane) e.getSource()).getSelectedIndex() == 1){
          employeeController.getView().setSelectedIndex(0);
        }

        if (((JTabbedPane) e.getSource()).getSelectedIndex() == 2 && analysisController == null) {
          analysisController = new AnalysisController();
          view.setAnalysisPanel(analysisController.getView());
        }

        if (((JTabbedPane) e.getSource()).getSelectedIndex() == 3 && managementController == null) {
          managementController = new ManagementController();
          view.setManagementPanel(managementController.getView());
        }

      }
    });
  }
}
