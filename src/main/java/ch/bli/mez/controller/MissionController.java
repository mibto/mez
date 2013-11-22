package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import ch.bli.mez.model.Mission;
import ch.bli.mez.model.Position;
import ch.bli.mez.model.dao.MissionDAO;
import ch.bli.mez.model.dao.PositionDAO;
import ch.bli.mez.view.management.MissionForm;
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
    addMissionForms();
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

  private void addMissionForms() {
    view.setNewMissionForm(new MissionForm(true));
    for (Mission mission : model.findAll()) {
      view.addMissionForm(createMissionForm(mission));
    }
  }

  private MissionForm createMissionForm(final Mission mission) {
    final MissionForm missionForm = new MissionForm(mission.getIsActive());

    missionForm.setMissionName(mission.getMissionName());
    missionForm.setComment(mission.getComment());
    missionForm.setIsOrgan(mission.getIsOrgan());

    setMissionFormActionListeners(missionForm, mission);

    return missionForm;
  }

  public void safeNewMission(Mission mission) {
    model.addMission(mission);
  }

  public void updateMission(Mission mission, MissionForm form, boolean isNewMission) {
    if (form.validateFields()) {
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

  private void setMissionFormActionListeners(final MissionForm missionForm, final Mission mission) {

    missionForm.setSaveListener((new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        updateMission(mission, missionForm, false);
      }
    }));

    missionForm.setStatusButtonListener((new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (mission.getIsActive()) {
          mission.setIsActive(false);
          model.updateMission(mission);
          missionForm.setActive(false);
        } else {
          mission.setIsActive(true);
          model.updateMission(mission);
          missionForm.setActive(true);
        }
      }
    }));
  }

}