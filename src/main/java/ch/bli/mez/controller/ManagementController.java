package ch.bli.mez.controller;

import ch.bli.mez.view.management.ManagementView;

public class ManagementController {
  private ManagementView view;
  private MissionController missionController;
  private PositionController positionController;
  
  public ManagementController(){
    this.view = new ManagementView();
    this.missionController = new MissionController();
    this.positionController = new PositionController();
    // weitere Controller hier instanzieren
    setTabs();
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
    view.addTab("Aufträge Verwalten", missionController.getView());
    view.addTab("Positionen Verwalten", positionController.getView());
    // Weitere views für die Tabs hier setzen.
   }
}
