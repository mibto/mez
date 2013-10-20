package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ch.bli.mez.model.Mission;
import ch.bli.mez.model.dao.MissionDAO;
import ch.bli.mez.view.MissionListEntry;
import ch.bli.mez.view.MissionPanel;

/**
 * @author Michael Brodmann
 * @version 1.0
 */

public class MissionController {

  private MissionPanel view;
  private MissionDAO model;

  public MissionController() {
    this.view = new MissionPanel();
    this.model = new MissionDAO();
    addMissionEntrys();
    setActionListeners();
  }

  public MissionPanel getView() {
    return view;
  }

  private void addMissionEntrys() {
    for (Mission mission : model.findAll()) {
      view.addMissionListEntry(createMissionListEntry(mission));
    }
  }

  private void setActionListeners(){
    view.setSaveMissionListener(new ActionListener() {
      
      public void actionPerformed(ActionEvent arg0) {
        System.out.print(view.getMissionName());
        Mission mission = new Mission(view.getMissionName(), view.getComment(), view.getIsOrgan());
        model.addMission(mission);
        view.addMissionListEntry(createMissionListEntry(mission));
      }
    });
  }
  
  private MissionListEntry createMissionListEntry(final Mission mission){
    final MissionListEntry missionListEntry = new MissionListEntry();
    
    missionListEntry.setMissionName(mission.getMissionName());
    missionListEntry.setComment(mission.getComment());
    
    missionListEntry.setSaveMissionEntryListListener((new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        mission.setMissionName(missionListEntry.getMissionName());
        mission.setComment(missionListEntry.getComment());
        model.updateMission(mission);
        missionListEntry.hideNameError();
      }
    }));

    missionListEntry.setDeleteMissionEntryListListener((new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        view.removeMissionListEntry(missionListEntry);
      }
    }));
    
    return missionListEntry;
  }
}