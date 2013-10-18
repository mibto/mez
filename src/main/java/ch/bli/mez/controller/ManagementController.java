package ch.bli.mez.controller;

import ch.bli.mez.view.ManagementView;

public class ManagementController {
  private ManagementView view;
  private MissionController missionController;
  
  public ManagementController(){
    this.view = new ManagementView();
    this.missionController = new MissionController();
    setTabs();
  }
  
  public ManagementView getView(){
    return view;
  }
  
  public MissionController getMissionController(){
    return missionController;
  }
  
  private void setTabs() {
    view.addTab("Mission Verwaltung", missionController.getView());
    // Weitere views für die Tabs hier setzen.
   }
}
