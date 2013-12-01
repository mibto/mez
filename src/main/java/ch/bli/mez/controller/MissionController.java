package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import ch.bli.mez.model.Mission;
import ch.bli.mez.model.Position;
import ch.bli.mez.model.dao.MissionDAO;
import ch.bli.mez.model.dao.PositionDAO;
import ch.bli.mez.view.DefaultPanel;
import ch.bli.mez.view.management.MissionForm;

/**
 * @author Leandra Finger
 * @version 1.0
 */

public class MissionController {

  private DefaultPanel view;
  private MissionDAO model;
  private PositionDAO positionModel;

  public MissionController() {
    this.view = new DefaultPanel();
    this.model = new MissionDAO();
    this.positionModel = new PositionDAO();
    addMissionForms();
  }

  public void setView(DefaultPanel view) {
    this.view = view;
  }

  public void setModel(MissionDAO model) {
    this.model = model;
  }

  public void setPositionModel(PositionDAO positionModel) {
    this.positionModel = positionModel;
  }

  public DefaultPanel getView() {
    return view;
  }

  private void addMissionForms() {
    view.setCreateNewForm(new MissionForm(true));
    for (Mission mission : model.findAll()) {
      view.addForm(createMissionForm(mission));
    }
  }

  private MissionForm createMissionForm(final Mission mission) {
    final MissionForm form = new MissionForm(mission.getIsActive());

    form.setMissionName(mission.getMissionName());
    form.setComment(mission.getComment());
    form.setIsOrgan(mission.getIsOrgan());

    setMissionFormActionListeners(form, mission);

    return form;
  }

  public void safeNewMission(Mission mission) {
    model.addMission(mission);
  }

  public void updateMission(Mission mission, MissionForm form) {
    if (validateFields(mission, form)) {
      boolean isNewMission = false;
      if (mission == null){
        isNewMission = true;
      }
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
  
  public boolean validateFields(Mission mission, MissionForm form){
    if (!form.validateFields()){
      return false;
    }
    Mission savedMission = model.findByMissionName(form.getMissionName());
    if (savedMission != null && !mission.getMissionName().equals(savedMission.getMissionName())){
      form.getParentPanel().showError("Ein Auftrag mit diesem Name existiert schon.");
      return false;
    }
    return true;
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

  private void setMissionFormActionListeners(final MissionForm form, final Mission mission) {

    form.setSaveListener((new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        updateMission(mission, form);
      }
    }));

    form.setStatusButtonListener((new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (mission.getIsActive()) {
          mission.setIsActive(false);
          model.updateMission(mission);
          form.setActive(false);
        } else {
          mission.setIsActive(true);
          model.updateMission(mission);
          form.setActive(true);
        }
      }
    }));
  }

}