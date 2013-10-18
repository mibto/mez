package ch.bli.mez.controller;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ch.bli.mez.model.Mission;
import ch.bli.mez.model.dao.MissionDAO;
import ch.bli.mez.view.MissionListEntry;
import ch.bli.mez.view.MissionPanel;
import ch.bli.mez.view.ScrollPanel;

/**
 * @author Michael Brodmann
 * @version 1.0
 */


public class MissionController {
	
  private MissionPanel view;
  private ScrollPanel scrollpanemissions = new ScrollPanel();
  private MissionDAO model;

  public MissionController(){
    this.view = new MissionPanel();
    this.view.addAnotherPanel(this.scrollpanemissions, new Rectangle(21, 160, 600, 100));
    
    this.model = new MissionDAO();
    
    addActionListeners();
    addMissionEntrys();
  }
  
  public MissionPanel getView(){
    return view;
  }
  
  private void addActionListeners(){
    
  }
  
  
	/**
	 * Iteriert über alle Mission's und ruft für jeden
	 * die Methode addMissionListEntry auf.
	 */
  private void addMissionEntrys(){
    for (Mission mission : model.findAll()) {
      addMissionListEntry(mission);
     }
  }
  
  /**
   * Generiert pro Mission ein neues JPanel (wir erzeugen eine JPanel Liste, die JPanel werden untereinander angezeigt)
   * @param missionListEntry
   */
  public void addMissionListEntry(Mission missionentry) {

		MissionListEntry listentry = new MissionListEntry();
		listentry.setName(missionentry.getName());
		listentry.setComment(missionentry.getComment());
		
		scrollpanemissions.add(listentry);
  }
  
  
  public void setSaveAction() {
	  final Mission newmission = new Mission();

		view.setSaveMissionListener((new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				view.hideConfirmation();

				if (! validateFields(view)){
					return;
				}
				
				newmission.setName(view.getName());
				newmission.setComment(view.getComment());
				model.updateMission(newmission);
				view.cleanFields();
			}
		}));
		addMissionListEntry(newmission);
  }
  
  /*
   * MUSS Felder
   * 
   */
  public boolean validateFields(MissionPanel panel){
		boolean valid = true;
		
		if (panel.getTextField_Name().equals("")){
			panel.showNameError();
			valid = false;
		}
		return valid;
	}
  
  
}
