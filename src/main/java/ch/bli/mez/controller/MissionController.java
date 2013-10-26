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

	private void setActionListeners() {
		view.setSaveMissionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!view.getMissionName().equals("")) {
					Mission mission = new Mission(view.getMissionName(), view
							.getComment(), view.getIsOrgan());
					model.addMission(mission);
					view.addMissionListEntry(createMissionListEntry(mission));
					view.showConfirmation(mission.getMissionName());
				} else {
					view.showNameError();
				}
			}
		});

		view.setClearMissionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view.cleanFields();
			}
		});
	}

	private MissionListEntry createMissionListEntry(final Mission mission) {
		final MissionListEntry missionListEntry = new MissionListEntry();

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
				mission.setIsOrgan(missionListEntry.getIsOrgan());
				model.updateMission(mission);
			}
		}));

		missionListEntry
				.setDeleteMissionEntryListListener((new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						model.deleteMission(mission.getId());
						view.removeMissionListEntry(missionListEntry);
					}
				}));
	}
}