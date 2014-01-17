package ch.bli.mez.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ch.bli.mez.model.Employee;
import ch.bli.mez.model.Mission;
import ch.bli.mez.model.dao.TimeEntryDAO;

public class TimeEntriesPerWeek {

  private Calendar week;
  private List<TimeEntriesPerMission> timeEntriesPerMission;
  private Integer totalTime = 0;
  private TimeEntryDAO model;

  public TimeEntriesPerWeek(Employee employee, Boolean showMissions, Calendar week) {
    this.week = week;
    model = new TimeEntryDAO();
    timeEntriesPerMission = new ArrayList<TimeEntriesPerMission>();
    totalTime = model.getWeekSummaryAmount(employee, week);
    if (showMissions) {
      Calendar endDate = getWeekEndDate(week);
      createMissions(employee, week, endDate);
    }
  }
  
  public TimeEntriesPerWeek(Employee employee, Boolean showMissions, Calendar startDate, Calendar endDate){
    this.week = Parser.getWeekBegin((Calendar) startDate.clone());
    model = new TimeEntryDAO();
    timeEntriesPerMission = new ArrayList<TimeEntriesPerMission>();
    totalTime = model.getWorktimeForReport(employee, endDate, startDate, null, null);
    if (showMissions){
      createMissions(employee, startDate, endDate);
    }
  }
  
  private void createMissions(Employee employee, Calendar startDate, Calendar endDate){
    for (Mission mission : model.getMissionsForReport(employee, endDate, startDate)) {
      timeEntriesPerMission.add(new TimeEntriesPerMission(mission, employee, endDate, startDate));
    }
  }
  
  private Calendar getWeekEndDate(Calendar weekBegin){
    Calendar endDate = (Calendar) weekBegin.clone();
    endDate.add(Calendar.WEEK_OF_YEAR, 1);
    endDate.setTimeInMillis(endDate.getTimeInMillis() - 1);
    return endDate;
  }

  public List<TimeEntriesPerMission> getTimeEntriesPerMission() {
    return timeEntriesPerMission;
  }

  public Integer getTotalTime() {
    return totalTime;
  }

  public Calendar getWeek() {
    return week;
  }

}
