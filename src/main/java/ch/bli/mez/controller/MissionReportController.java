package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ch.bli.mez.model.Mission;
import ch.bli.mez.model.Position;
import ch.bli.mez.model.dao.MissionDAO;
import ch.bli.mez.model.dao.PositionDAO;
import ch.bli.mez.model.dao.TimeEntryDAO;
import ch.bli.mez.util.TimeEntriesPerMission;
import ch.bli.mez.view.DefaultPanel;
import ch.bli.mez.view.report.DateForm;
import ch.bli.mez.view.report.GenerateForm;
import ch.bli.mez.view.report.MissionForm;
import ch.bli.mez.view.report.OptionForm;
import ch.bli.mez.view.report.PositionForm;
import ch.bli.mez.view.report.ProjectPanel;

public class MissionReportController {

  private ProjectPanel view;
  private MissionDAO missionModel;
  private PositionDAO positionModel;
  private TimeEntryDAO timeEntryModel;
  private FormatMissionReportController formatController;

  public MissionReportController() {
    this.view = new ProjectPanel();
    this.missionModel = new MissionDAO();
    this.positionModel = new PositionDAO();
    this.timeEntryModel = new TimeEntryDAO();
    this.formatController = new FormatMissionReportController();
    view.setDatePanel(createDateForm());
    view.setMissionPanel(createProjectForm());
    view.setOptionPanel(createOptionForm());
    view.setPositionPanel(createPositionForm());
    view.setGenerateReportPanel(createGenerateForm());
    setProjectFormActionListeners(view);
  }

  private DateForm createDateForm() {
    DateForm form = new DateForm();
    form.setParentPanel(view);
    return form;
  }

  private MissionForm createProjectForm() {
    MissionForm form = new MissionForm();
    form.setParentPanel(view);
    return form;
  }

  private OptionForm createOptionForm() {
    OptionForm form = new OptionForm();
    form.setParentPanel(view);
    return form;
  }

  private PositionForm createPositionForm() {
    PositionForm form = new PositionForm();
    form.setParentPanel(view);
    return form;
  }

  private GenerateForm createGenerateForm() {
    GenerateForm form = new GenerateForm();
    form.setParentPanel(view);
    return form;
  }

  public boolean validateFields(ProjectPanel view) {
    if (!view.getDatePanelForm().validateFields()) {
      return false;
    }
    if (!view.getMissionPanelForm().validateFields()) {
      return false;
    }
    if (!view.getOptionPanelForm().validateFields()) {
      return false;
    }
    if (!view.getPositionPanelForm().validateFields()) {
      return false;
    }
    if (!view.getGeneratePanelForm().validateFields()) {
      return false;
    }

    if (view.getMissionPanelForm().getSelectedMission() == 2) {
      for (String mission : view.getMissionPanelForm().getSingelMission()) {
        if (missionModel.findByMissionName(mission) == null) {
          view.showError("Es gibt kein Auftrag (Orgel) mit dem Namen " + mission);
          return false;
        }
      }
    }
    for (String position : view.getPositionPanelForm().getPositions()) {
      if (positionModel.findByCode(position) == null) {
        view.showError("Es gibt keine Position mit dem Code " + position);
        return false;
      }
    }
    return true;
  }

  private List<TimeEntriesPerMission> getTimeEntries(ProjectPanel view) {
    List<Mission> missions = getSelectedMissions(view);
    Calendar startDate = view.getDatePanelForm().getDateFrom();
    Calendar endDate = view.getDatePanelForm().getDateUntil();
    Boolean showEmployees = view.getOptionPanelForm().getReportWithEmployee();
    List<Position> positions = getSelectedPositions(view.getPositionPanelForm().getPositions());
    List<TimeEntriesPerMission> timeEntriesPerMission = new ArrayList<TimeEntriesPerMission>();
    if (endDate == null) {
      endDate = Calendar.getInstance();
    }
    if (startDate == null && view.getMissionPanelForm().getSelectedMission() != 2) {
      startDate = Calendar.getInstance();
      startDate.set(startDate.get(Calendar.YEAR), 0, 1);
    }
    for (Mission mission : missions) {
      timeEntriesPerMission.add(new TimeEntriesPerMission(mission, positions, showEmployees, endDate, startDate));
    }
    return timeEntriesPerMission;
  }

  private List<Mission> getSelectedMissions(ProjectPanel view) {
    List<Mission> missions = new ArrayList<Mission>();
    switch (view.getMissionPanelForm().getSelectedMission()) {
    case 0:
      missions = missionModel.getOrganMissions();
      break;
    case 1:
      missions = missionModel.findAll();
      break;
    case 2:
      for (String mission : view.getMissionPanelForm().getSingelMission()) {
        missions.add(missionModel.findByMissionName(mission));
      }
      break;
    }
    return missions;
  }

  private List<Position> getSelectedPositions(List<String> positionList) {
    List<Position> positions = new ArrayList<Position>();
    for (String position : positionList) {
      positions.add(positionModel.findByCode(position));
    }
    return positions;
  }

  private void setProjectFormActionListeners(final ProjectPanel view) {

    view.getGeneratePanelForm().setGenerateProjectReportListener((new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (validateFields(view)) {
          formatController.formatTimeEntries(getTimeEntries(view));
        }
      }
    }));

  }

  public void setView(ProjectPanel view) {
    this.view = view;
  }

  public DefaultPanel getView() {
    return view;
  }
}
