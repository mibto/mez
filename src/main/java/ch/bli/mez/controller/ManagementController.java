package ch.bli.mez.controller;

import ch.bli.mez.view.ManagementView;

public class ManagementController {
  private ManagementView view;
  private MissionController missionController;
  
  public ManagementController(){
    this.view = new ManagementView();
    this.missionController = new MissionController();
    setPanels();
  }
  
  public ManagementView getView(){
    return view;
  }
  
  public MissionController getMissionController(){
    return missionController;
  }
  
  private void setPanels() {
    view.setMissionPanel(missionController.getView());
    // Weitere views für die Tabs hier setzen.
   }
}
