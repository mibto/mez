package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import ch.bli.mez.model.Mission;
import ch.bli.mez.model.dao.MissionDAO;
import ch.bli.mez.model.dao.PositionDAO;
import ch.bli.mez.view.DefaultPanel;
import ch.bli.mez.view.report.MissionReportForm;
import ch.bli.mez.view.report.ProjectPanel;

public class MissionReportController {

  private DefaultPanel view;
  private MissionDAO missionModel;
  private PositionDAO positionModel;

  public MissionReportController() {
    this.view = new ProjectPanel();
    this.missionModel = new MissionDAO();
    this.positionModel = new PositionDAO();
    view.setCreateNewForm(createProjectForm());
  }

  private MissionReportForm createProjectForm() {
    MissionReportForm form = new MissionReportForm();
    setProjectFormActionListeners(form);
    return form;
  }

  public boolean validateFields(MissionReportForm form) {
    if (!form.validateFields()) {
      return false;
    }
    if (form.getSelectedMission() == 2){
      for (String mission : form.getSingelMission()){
        if (missionModel.findByMissionName(mission) == null){
          view.showError("Es gibt kein Auftrag mit dem Name " + mission);
          return false;
        }
      }
    }
    for (String position : form.getPositions()){
      if (positionModel.findByCode(position) == null){
        view.showError("Es gibt keine Position mit dem Code " + position);
        return false;
      }
    }
    return true;
  }
  
  private List<Mission> getSelectedMissions(MissionReportForm form){
    return new ArrayList<Mission>();
  }

  private void setProjectFormActionListeners(final MissionReportForm form) {

    form.setGenerateProjectReportListener((new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (validateFields(form)){
          getSelectedMissions(form);
        }
      }
    }));

  }

  public void setView(DefaultPanel view) {
    this.view = view;
  }

  public DefaultPanel getView() {
    return view;
  }
}
