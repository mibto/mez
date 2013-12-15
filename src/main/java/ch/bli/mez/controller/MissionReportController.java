package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ch.bli.mez.model.Mission;
import ch.bli.mez.model.Position;
import ch.bli.mez.model.TimeEntry;
import ch.bli.mez.model.dao.MissionDAO;
import ch.bli.mez.model.dao.PositionDAO;
import ch.bli.mez.model.dao.TimeEntryDAO;
import ch.bli.mez.view.DefaultPanel;
import ch.bli.mez.view.report.MissionReportForm;
import ch.bli.mez.view.report.ProjectPanel;

public class MissionReportController {

  private DefaultPanel view;
  private MissionDAO missionModel;
  private PositionDAO positionModel;
  private TimeEntryDAO timeEntryModel;

  public MissionReportController() {
    this.view = new ProjectPanel();
    this.missionModel = new MissionDAO();
    this.positionModel = new PositionDAO();
    this.timeEntryModel = new TimeEntryDAO();
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
  
  private List<TimeEntry> getTimeEntries(MissionReportForm form){
    List<Mission> missions = getSelectedMissions(form);
    Calendar startDate = form.getDateFrom();
    Calendar endDate = form.getDateUntil();
    List<Position> positions = getSelectedPositions(form.getPositions());
    if (endDate == null){
      endDate = Calendar.getInstance();
    }
    if (startDate == null){
      startDate = Calendar.getInstance();
      if (form.getSelectedMission() == 2){
        return timeEntryModel.getEntriesForReport(missions, positions, endDate); 
      }
      else {
        startDate.set(startDate.YEAR, 0, 1);  
      }
    }
    return timeEntryModel.getEntriesForReport(missions, positions, endDate, startDate);
  }
  
  private List<Mission> getSelectedMissions(MissionReportForm form){
    List<Mission> missions = new ArrayList<Mission>();
    switch (form.getSelectedMission()){
      case 0: missions = missionModel.getOrganMissions();
      case 1: missions = missionModel.findAll();
      case 2:
        for (String mission : form.getSingelMission()){
          missions.add(missionModel.findByMissionName(mission));
        }
    }
    return missions;
  }
  
  private List<Position> getSelectedPositions(List<String> positionList){
    List<Position> positions = new ArrayList<Position>();
    for (String position : positionList){
      positions.add(positionModel.findByCode(position));
    }
    return positions;
  }

  private void setProjectFormActionListeners(final MissionReportForm form) {

    form.setGenerateProjectReportListener((new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (validateFields(form)){
          getTimeEntries(form);
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
