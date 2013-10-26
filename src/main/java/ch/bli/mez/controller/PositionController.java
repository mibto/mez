package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import ch.bli.mez.model.Employee;
import ch.bli.mez.model.Mission;
import ch.bli.mez.model.Position;
import ch.bli.mez.model.dao.MissionDAO;
import ch.bli.mez.model.dao.PositionDAO;
import ch.bli.mez.view.management.PositionPanel;


public class PositionController {
	
	private PositionPanel view;
	private PositionDAO model;
	private MissionDAO missionModel;
	
	public PositionController(){
		this.view = new PositionPanel();
		this.model = new PositionDAO();
		this.missionModel = new MissionDAO();
		setActionListeners();
		// addPositionEntrys();
		setComboBoxItems();
	}
	
	public PositionPanel getView(){
		return view;
	}
	
	private void setComboBoxItems(){
		HashMap<Integer, String> items = new HashMap<Integer, String>();
		items.put(0, "Orgeln");
		List<Mission> missionsNotOrgan = missionModel.getNotOrganMissions();
		for (Mission mission: missionsNotOrgan){
			items.put(mission.getId(), mission.getMissionName());
		}
		System.out.println(items);
		view.setComboBoxItems(items);
	}
	
	private void setActionListeners() {
		view.setSaveButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!view.getPositionName().equals("")) {
					Boolean isOrganDefault = view.getSelectedMission() == 0;
					Position position = new Position(Integer.parseInt(view.getNumber()), view.getPositionName(), view
							.getComment(), isOrganDefault);
					if (isOrganDefault){
						List<Mission> organMissions = missionModel.getOrganMissions();
						position.addMissions(organMissions);
					}
					else {
						Mission mission = missionModel.getMission(view.getSelectedMission());
						position.addMission(mission);
					}
					model.addPosition(position);
					// view.addPositionListEntry(createPositionListEntry(position, isOrganDefault));
					view.showConfirmation(position.getPositionName());
				} else {
					view.showNameError();
				}
			}
		});
	}

}
