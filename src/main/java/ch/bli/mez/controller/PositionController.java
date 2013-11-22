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
    setActionListeners();
    addPositionEntrys();
  }

  public PositionPanel getView() {
    return view;
  }

  private void addPositionEntrys() {
    for (Position position : model.findAll()) {
      view.addPositionListEntry(createPositionListEntry(position));
    }
  }

  private PositionForm createPositionListEntry(final Position position) {
    final PositionForm positionForm = new PositionForm(position.getIsActive());
    if (position.getCode() != null) {
      positionForm.setCode(position.getCode());
    }
    positionForm.setPositionName(position.getPositionName());
    positionForm.setComment(position.getComment());
    if (position.isOrganDefault()) {
      positionForm.setMission("Orgeln");
    } else {
      Mission mission = (Mission) position.getMissions().toArray()[0];
      positionForm.setMission(mission.getMissionName());
    }

    setPositionListEntryActionListeners(positionForm, position);

    return positionForm;
  }

  private void setPositionListEntryActionListeners(final PositionForm positionForm, final Position position) {

    positionForm.setSaveButtonListener((new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        try {
          position.setCode(positionForm.getCode());
        } catch (NumberFormatException e) {
        } finally {
          if (!positionForm.getPositionName().equals("")) {
            position.setPositionName(positionForm.getPositionName());
            positionForm.showSuccess();
          } else {
            positionForm.showError();
            positionForm.setPositionName(position.getPositionName());
          }
          position.setComment(positionForm.getComment());
          model.updatePosition(position);
        }
      }
    }));

    positionForm.setStatusButtonListener((new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (position.getIsActive()) {
          position.setIsActive(false);
          model.updatePosition(position);
          positionForm.setActive(false);
        } else {
          position.setIsActive(true);
          model.updatePosition(position);
          positionForm.setActive(true);
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

  private void setActionListeners() {
    view.setSaveButtonListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        if (!view.getPositionName().equals("")) {
          Boolean isOrganDefault = view.getSelectedMission() == 0;
          Position position = null;
          try {
            position = new Position(view.getCode(), view.getPositionName(), view.getComment(), isOrganDefault);
          } catch (NumberFormatException e) {
            position = new Position(null, view.getPositionName(), view.getComment(), isOrganDefault);
          } finally {
            if (isOrganDefault) {
              List<Mission> organMissions = missionModel.getOrganMissions();
              position.addMissions(organMissions);
            } else {
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
