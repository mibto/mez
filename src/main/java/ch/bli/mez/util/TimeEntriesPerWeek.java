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
      for (Mission mission : model.getMissionsForReport(employee, endDate, week)) {
        timeEntriesPerMission.add(new TimeEntriesPerMission(mission, employee, endDate, week));
      }
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
