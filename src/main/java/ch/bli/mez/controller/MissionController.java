package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import ch.bli.mez.model.Mission;
import ch.bli.mez.model.Position;
import ch.bli.mez.model.dao.MissionDAO;
import ch.bli.mez.model.dao.PositionDAO;
import ch.bli.mez.view.management.MissionListEntry;
import ch.bli.mez.view.management.MissionPanel;

/**
 * @author Michael Brodmann
 * @version 1.0
 */

public class MissionController {

	private MissionPanel view;
	private MissionDAO model;
	private PositionDAO positionModel;

	public MissionController() {
		this.view = new MissionPanel();
		this.model = new MissionDAO();
		this.positionModel = new PositionDAO();
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

	private void setActionListeners() {
		view.setSaveMissionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!view.getMissionName().equals("")) {
					Mission mission = new Mission(view.getMissionName(), view
							.getComment(), view.getIsOrgan());
					if(view.getIsOrgan()){
						List<Position> organPositions = positionModel.getOrganPositions();
						mission.addPositions(organPositions);
						for (Position position : organPositions){
							positionModel.updatePosition(position);
						}
					}
					model.addMission(mission);
					view.addMissionListEntry(createMissionListEntry(mission));
					view.showConfirmation(mission.getMissionName());
				} else {
					view.showNameError();
				}
			}
		});
	}

	private MissionListEntry createMissionListEntry(final Mission mission) {
		final MissionListEntry missionListEntry = new MissionListEntry(mission.getIsActive());

		missionListEntry.setMissionName(mission.getMissionName());
		missionListEntry.setComment(mission.getComment());
		missionListEntry.setIsOrgan(mission.getIsOrgan());

		setMissionListEntryActionListeners(missionListEntry, mission);

		return missionListEntry;
	}

	private void setMissionListEntryActionListeners(
			final MissionListEntry missionListEntry, final Mission mission) {

		missionListEntry.setSaveMissionEntryListListener((new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!missionListEntry.getMissionName().equals("")){
				mission.setMissionName(missionListEntry.getMissionName());
				missionListEntry.showSuccess();
			} else {
				missionListEntry.showError();
				missionListEntry.setMissionName(mission.getMissionName());
			}
				mission.setComment(missionListEntry.getComment());
				System.out.println(missionListEntry.getIsOrgan());
				if (missionListEntry.getIsOrgan() ^ mission.getIsOrgan()){
					mission.setIsOrgan(missionListEntry.getIsOrgan());
					// clear not working yet
					System.out.println(missionListEntry.getIsOrgan());
					mission.clearPositions();
					for (Position position : mission.getPositions()){
						positionModel.updatePosition(position);
					}
					if (missionListEntry.getIsOrgan()){
						List<Position> organPositions = positionModel.getOrganPositions();
						mission.addPositions(organPositions);
						for (Position position : organPositions){
							positionModel.updatePosition(position);
						}
					}
				}
				model.updateMission(mission);
			}
		}));

		missionListEntry
				.setStatusMissionEntryListListener((new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if (mission.getIsActive()){
							mission.setIsActive(false);
							model.updateMission(mission);
							missionListEntry.setActive(false);
						}
						else {
							mission.setIsActive(true);
							model.updateMission(mission);
							missionListEntry.setActive(true);
						}
						
					}
				}));
	}
}