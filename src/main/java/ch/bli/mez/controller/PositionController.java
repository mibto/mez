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

public class PositionController {

  private PositionPanel view;
  private PositionDAO model;
  private MissionDAO missionModel;

  public PositionController() {
    this.view = new PositionPanel();
    this.model = new PositionDAO();
    this.missionModel = new MissionDAO();
    addPositionEntrys();
  }

  public PositionPanel getView() {
    return view;
  }

  private void addPositionEntrys() {
    view.setNewPositionForm(new PositionForm(true));
    for (Position position : model.findAll()) {
      view.addPositionForm(createPositionForm(position));
    }
  }

  private PositionForm createPositionForm(final Position position) {
    final PositionForm form = new PositionForm(position.getIsActive());
    form.setPositionName(position.getPositionName());
    form.setComment(position.getComment());
    if (position.isOrganDefault()) {
      form.setMissionName("Orgeln");
    } else {
      Mission mission = (Mission) position.getMissions().toArray()[0];
      form.setMissionName(mission.getMissionName());
    }

    setPositionFormActionListeners(form, position);

    return form;
  }

  private void setPositionFormActionListeners(final PositionForm form, final Position position) {

    form.setSaveListener((new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        try {
          //position.setCode(form.getCode());
        } catch (NumberFormatException e) {
        } finally {
          if (!form.getPositionName().equals("")) {
            position.setPositionName(form.getPositionName());
          } else {
            form.setPositionName(position.getPositionName());
          }
          position.setComment(form.getComment());
          model.updatePosition(position);
        }
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

  public void updatePosition(Position position, PositionForm positionForm, boolean b) {
    // TODO Auto-generated method stub
  }

  public Object makePosition() {
    // TODO Auto-generated method stub
    return null;
  }
}
