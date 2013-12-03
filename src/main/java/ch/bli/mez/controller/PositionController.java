package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import ch.bli.mez.model.Mission;
import ch.bli.mez.model.Position;
import ch.bli.mez.model.dao.MissionDAO;
import ch.bli.mez.model.dao.PositionDAO;
import ch.bli.mez.view.management.PositionForm;
import ch.bli.mez.view.management.PositionPanel;
import ch.bli.mez.view.management.PositionTitlePanel;

public class PositionController {

  private PositionPanel view;
  private PositionDAO model;
  private MissionDAO missionModel;

  public PositionController() {
    this.view = new PositionPanel();
    view.setListTitlePanel(new PositionTitlePanel());
    this.model = new PositionDAO();
    this.missionModel = new MissionDAO();
    addPositionEntrys();
  }

  public PositionPanel getView() {
    return view;
  }
  
  public void setView(PositionPanel view){
    this.view = view;
  }
  
  public void setMissionModel(MissionDAO missionModel) {
    this.missionModel = missionModel;
  }

  private void addPositionEntrys() {
    view.setCreateNewForm(createPositionForm(null));
    for (Position position : model.findAll()) {
      view.addForm(createPositionForm(position));
    }
  }

  protected PositionForm createPositionForm(final Position position) {
    final PositionForm form;
    if (position == null){
      form = new PositionForm(true);
    }
    else {
      form = new PositionForm(position.getIsActive());
      form.setPositionName(position.getPositionName());
      form.setComment(position.getComment());
      form.setPositionCode(position.getCode());
      if (position.isOrganDefault()) {
        form.setMissionName("Orgeln");
      } else {
        Mission mission = position.firstMission();
        form.setMissionName(mission.getMissionName());
      }      
    }

    setPositionFormActionListeners(form, position);

    return form;
  }

  private void setPositionFormActionListeners(final PositionForm form, final Position position) {

    form.setSaveListener((new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        updatePosition(position, form);
      }
    }));

    form.setStatusButtonListener((new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (position.getIsActive()) {
          position.setIsActive(false);
          model.updatePosition(position);
          form.setActive(false);
        } else {
          position.setIsActive(true);
          model.updatePosition(position);
          form.setActive(true);
        }
      }
    }));
  }

  public void setComboBoxItems() {
    HashMap<Integer, String> items = new HashMap<Integer, String>();
    items.put(0, "Orgeln");
    List<Mission> missionsNotOrgan = missionModel.getNotOrganMissions();
    for (Mission mission : missionsNotOrgan) {
      items.put(mission.getId(), mission.getMissionName());
    }
    view.setComboBoxItems(items);
  }

  public void setModel(PositionDAO positionModel) {
    this.model = positionModel;
  }

  public void updatePosition(Position position, PositionForm form) {
    boolean newPosition = false;
    if (validateFields(form, position)){
      if (position == null){
        newPosition = true;
        position = makePosition();
      }
      if (newPosition){
        Boolean isOrganDefault = view.getSelectedMission() == 0;
        position.setOrganDefault(isOrganDefault);
        if (isOrganDefault) {
          List<Mission> organMissions = missionModel.getOrganMissions();
          position.addMissions(organMissions);
        } else {
          Mission mission = missionModel.getMission(view
              .getSelectedMission());
          position.addMission(mission);
        }
      }
      position.setPositionName(form.getPositionName());
      position.setComment(form.getComment());
      position.setCode(form.getPositionCode());
      if (newPosition) {
        position.setIsActive(true);
        model.addPosition(position);
        view.addForm(createPositionForm(position));
        form.cleanFields();
      }
      else {
        model.updatePosition(position);              
      }
      view.showConfirmation("Die Position " + position.getPositionName() + " wurde gespeichert.");
    }
  }
  
  public Boolean validateFields(PositionForm form, Position position){
    if (!form.validateFields()){
      return false;
    }
    Position savedPosition = model.findByCode(form.getPositionCode());
    if (savedPosition != null && (position == null || !position.getCode().equals(savedPosition.getCode()))) {
      form.getParentPanel().showError("Eine Position mit diesem Code existiert schon.");
      return false;
    }
    savedPosition = model.findByName(form.getPositionName());
    if (savedPosition != null && (position == null || !position.getPositionName().equals(savedPosition.getPositionName()))) {
      form.getParentPanel().showError("Eine Position mit diesem Name existiert schon.");
      return false;
    }
    return true;
  }

  public Position makePosition() {
    return new Position();
  }
}
