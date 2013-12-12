package ch.bli.mez.controller;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.bli.mez.view.DefaultTabbedView;

public class ManagementController {
  private DefaultTabbedView view;
  private MissionController missionController;
  private PositionController positionController;
  private HolidayController holidayController;

  public ManagementController() {
    view = new DefaultTabbedView();
    missionController = new MissionController();
    positionController = new PositionController();
    holidayController = new HolidayController();
    // weitere Controller hier instanzieren
    setTabs();
    setListener();
  }

  public DefaultTabbedView getView() {
    return view;
  }

  public MissionController getMissionController() {
    return missionController;
  }

  public PositionController getPositionController() {
    return positionController;
  }

  private void setTabs() {
    view.addTab("Aufträge verwalten", missionController.getView());
    view.addTab("Positionen verwalten", positionController.getView());
    view.addTab("Feiertage verwalten", holidayController.getView());
    // Weitere views für die Tabs hier setzen.
  }

  private void setListener() {
    view.setTabListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        // Für andere Panels das selbe. Zeit erfassen noch besprechen wann die
        // Liste geladen werden soll.
        if (((JTabbedPane) e.getSource()).getSelectedIndex() == 1) {
          positionController.setComboBoxItems();
        }
      }
    });
  }
}
