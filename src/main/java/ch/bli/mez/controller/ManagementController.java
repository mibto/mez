package ch.bli.mez.controller;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import ch.bli.mez.view.management.ManagementView;

public class ManagementController {
  private ManagementView view;
  private MissionController missionController;
  private PositionController positionController;
  private HolidayController holidayController;
  
  public ManagementController(){
    this.view = new ManagementView();
    this.missionController = new MissionController();
    this.positionController = new PositionController();
    this.holidayController = new HolidayController();
    // weitere Controller hier instanzieren
    setTabs();
    setListener();
  }
  
  public ManagementView getView(){
    return view;
  }
  
  public MissionController getMissionController(){
    return missionController;
  }
  
  public PositionController getPositionController(){
	  return positionController;
  }
  
  private void setTabs() {
    view.addTab("Aufträge verwalten", missionController.getView());
    view.addTab("Positionen verwalten", positionController.getView());
    view.addTab("Ferien verwalten", holidayController.getView());
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
