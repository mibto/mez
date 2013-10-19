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
	private ScrollPanel scrollpanemissions;
	private MissionDAO model;

	public MissionController() {

		this.model = new MissionDAO();
		
		// Mission Panel mit ActionListeners etc. erstellen
		createMissionPanel();
		this.scrollpanemissions  = new ScrollPanel();
		this.view.addAnotherPanel(this.scrollpanemissions, new Rectangle(20, 160, 815, 350));

		
		//TODO
		scrollpanemissions.add(new MissionListEntry());
		scrollpanemissions.add(new MissionListEntry());

		// Vorhandene Missions in der Liste anzeigen
		addMissionEntrys();
	}

	public MissionPanel getView() {
		return view;
	}

	public void createMissionPanel() {
		this.view = new MissionPanel();

		final Mission newmission = new Mission();

		// BUTTON HINZUFÜGEN definition
		view.setSaveMissionListener((new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				view.hideConfirmation();
				view.hideNameError();

				if (!validateFields(view)) {
					return;
				}

				newmission.setName(view.getNameMission());
				newmission.setComment(view.getComment());
				model.updateMission(newmission);
				view.cleanFields();
				addMissionListEntry(newmission);
				view.showConfirmation(newmission.getName());
			}
		}));

		// BUTTON LEEREN definition
		view.setClearMissionListener((new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				view.hideConfirmation();
				view.hideNameError();
				view.cleanFields();
			}
		}));

	}


	/**
	 * Iteriert über alle Mission's und ruft für jeden die Methode
	 * addMissionListEntry auf.
	 */
	private void addMissionEntrys() {
		for (Mission mission : model.findAll()) {
			addMissionListEntry(mission);
		}
	}

	/**
	 * Generiert pro Mission ein neues JPanel (wir erzeugen eine JPanel Liste,
	 * die JPanel werden untereinander angezeigt)
	 * 
	 * @param missionListEntry
	 */
	public void addMissionListEntry(Mission missionentry) {

		final MissionListEntry listentry = new MissionListEntry();
		final Mission mission = new Mission();
		
		listentry.setName(missionentry.getName());
		listentry.setComment(missionentry.getComment());

		// BUTTON SPEICHERN
		// TODO: Hier muss noch ein Validator rein
		listentry.setSaveMissionEntryListListener((new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				mission.setName(listentry.getNameMission());
				mission.setComment(listentry.getComment());
				model.updateMission(mission);
			}
		}));

		// BUTTON LÖSCHEN
		// TODO: Hier muss noch ein Validator rein, damit kein Projekt gelöscht
		// wird an dem bereits etwas hinzugefügt wurde
		listentry.setDeleteMissionEntryListListener((new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// model.deleteMission(model.find(listentry.getName()));
			}
		}));

		scrollpanemissions.addPanelToList(listentry);
	}

	/*
	 * MUSS Felder
	 */
	public boolean validateFields(MissionPanel panel) {
		boolean validator = true;

		if (panel.getNameMission().equals("")) {
			panel.showNameError();
			validator = false;
		}
		return validator;
	}

}
