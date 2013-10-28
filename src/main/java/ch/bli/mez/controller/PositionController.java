package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import ch.bli.mez.model.Mission;
import ch.bli.mez.model.Position;
import ch.bli.mez.model.dao.MissionDAO;
import ch.bli.mez.model.dao.PositionDAO;
import ch.bli.mez.view.management.PositionListEntry;
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
		addPositionEntrys();
	}
	
	public PositionPanel getView(){
		return view;
	}
	
	private void addPositionEntrys() {
		for (Position position : model.findAll()) {
			view.addPositionListEntry(createPositionListEntry(position));
		}
	}
	
	private PositionListEntry createPositionListEntry(final Position position) {
		final PositionListEntry positionListEntry = new PositionListEntry(position.getIsActive());
		if(position.getNumber() != null){
			positionListEntry.setNumber(String.valueOf(position.getNumber()));
		}
		positionListEntry.setPositionName(position.getPositionName());
		positionListEntry.setComment(position.getComment());
		if (position.isOrganDefault()){
			positionListEntry.setMission("Orgeln");
		}
		else {
			Mission mission = (Mission) position.getMissions().toArray()[0];
			positionListEntry.setMission(mission.getMissionName());
		}

		setPositionListEntryActionListeners(positionListEntry, position);

		return positionListEntry;
	}
	
	private void setPositionListEntryActionListeners(
			final PositionListEntry positionListEntry, final Position position) {

		positionListEntry.setSaveButtonListener((new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try{
					position.setNumber(Integer.valueOf(positionListEntry.getNumber()));
				}
				catch (NumberFormatException e){
				}
				finally{
				if(!positionListEntry.getPositionName().equals("")){
				position.setPositionName(positionListEntry.getPositionName());
				positionListEntry.showSuccess();
			} else {
				positionListEntry.showError();
				positionListEntry.setPositionName(position.getPositionName());
			}
				position.setComment(positionListEntry.getComment());
				model.updatePosition(position);
			}
			}
		}));

		positionListEntry.setStatusButtonListener((new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (position.getIsActive()){
					position.setIsActive(false);
					model.updatePosition(position);
					positionListEntry.setActive(false);
				}
				else {
					position.setIsActive(true);
					model.updatePosition(position);
					positionListEntry.setActive(true);
				}
			}
		}));
	}
	
	public void setComboBoxItems(){
		HashMap<Integer, String> items = new HashMap<Integer, String>();
		items.put(0, "Orgeln");
		List<Mission> missionsNotOrgan = missionModel.getNotOrganMissions();
		for (Mission mission: missionsNotOrgan){
			items.put(mission.getId(), mission.getMissionName());
		}
		view.setComboBoxItems(items);
	}
	
	private void setActionListeners() {
		view.setSaveButtonListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!view.getPositionName().equals("")) {
					Boolean isOrganDefault = view.getSelectedMission() == 0;
					Position position = null;
					try{
						position = new Position(Integer.parseInt(view.getNumber()), view.getPositionName(), view
								.getComment(), isOrganDefault);
					} catch (NumberFormatException e) {
						position = new Position(null, view.getPositionName(), view
								.getComment(), isOrganDefault);
					} finally {
					if (isOrganDefault){
						List<Mission> organMissions = missionModel.getOrganMissions();
						position.addMissions(organMissions);
					}
					else {
						Mission mission = missionModel.getMission(view.getSelectedMission());
						position.addMission(mission);
					}
					model.addPosition(position);
					 view.addPositionListEntry(createPositionListEntry(position));
					view.showConfirmation(position.getPositionName());
					}
				} else {
					view.showNameError();
				}
			}
		});
	}
}
