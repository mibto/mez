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

  public void setView(MissionPanel view) {
    this.view = view;
  }

  public void setModel(MissionDAO model) {
    this.model = model;
  }

  public void setPositionModel(PositionDAO positionModel) {
    this.positionModel = positionModel;
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
          if (view.getIsOrgan()) {
            List<Position> organPositions = positionModel.getOrganPositions();
            mission.addPositions(organPositions);
          }
          safeNewMission(mission);
          view.addMissionListEntry(createMissionListEntry(mission));
          view.showConfirmation(mission.getMissionName());
        } else {
          view.showNameError();
        }
      }
    });
  }

  public void safeNewMission(Mission mission) {
    model.addMission(mission);
  }

  public void updateMission(Mission mission, MissionListEntry form,
      boolean isNewMission) {
    if (form.validateFields(mission.getMissionName())) {
      if (isNewMission) {
        mission = makeMission();
      }
      mission.setMissionName(form.getMissionName());
      mission.setComment(form.getComment());
      boolean formIsOrgan = form.getIsOrgan();
      if (formIsOrgan ^ mission.getIsOrgan()) {
        mission.setIsOrgan(formIsOrgan);
        updatePositions(mission, formIsOrgan);
      }
      if (isNewMission) {
        model.addMission(mission);
      } else {
        model.updateMission(mission);
      }
    }
  }

  public Mission makeMission() {
    return new Mission();
  }

  public void updatePositions(Mission mission, boolean formIsOrgan) {
    mission.clearPositions();
    if (formIsOrgan) {
      List<Position> organPositions = positionModel.getOrganPositions();
      mission.addPositions(organPositions);
    }
  }

  private MissionListEntry createMissionListEntry(final Mission mission) {
    final MissionListEntry missionListEntry = new MissionListEntry(
        mission.getIsActive());

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
        updateMission(mission, missionListEntry, false);
      }
    }));

    missionListEntry.setStatusMissionEntryListListener((new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (mission.getIsActive()) {
          mission.setIsActive(false);
          model.updateMission(mission);
          missionListEntry.setActive(false);
        } else {
          mission.setIsActive(true);
          model.updateMission(mission);
          missionListEntry.setActive(true);
        }

      }
    }));
  }
}