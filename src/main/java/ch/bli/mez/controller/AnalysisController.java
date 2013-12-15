package ch.bli.mez.controller;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.bli.mez.view.DefaultTabbedView;

public class AnalysisController {

  private DefaultTabbedView view;
  private MissionReportController reportController;

  public AnalysisController() {
    view = new DefaultTabbedView();
    reportController = new MissionReportController();
    setTabs();
    setListener();
  }

  public DefaultTabbedView getView() {
    return view;
  }

  public MissionReportController getReportController() {
    return reportController;
  }

  private void setTabs() {
    view.addTab("Auftrag Report", reportController.getView());
    // Weitere views für die Tabs hier setzen.
  }

  private void setListener() {
    view.setTabListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        if (((JTabbedPane) e.getSource()).getSelectedIndex() == 1) {

        }
      }
    });
  }

}
