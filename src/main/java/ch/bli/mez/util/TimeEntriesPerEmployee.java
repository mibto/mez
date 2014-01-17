package ch.bli.mez.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ch.bli.mez.model.Employee;
import ch.bli.mez.model.Mission;
import ch.bli.mez.model.Position;
import ch.bli.mez.model.TimeEntry;
import ch.bli.mez.model.dao.TimeEntryDAO;

public class TimeEntriesPerEmployee {

  private Integer totalTime = 0;
  private List<TimeEntry> timeEntries;
  private Employee employee;
  private static TimeEntryDAO model = new TimeEntryDAO();
  private boolean showWeeks;
  private boolean showMissions;
  private List<TimeEntriesPerWeek> timeEntriesPerWeek;
  private List<TimeEntriesPerMission> timeEntriesPerMission;

  public TimeEntriesPerEmployee(Employee employee, Mission mission, Position position, Calendar endDate,
      Calendar dateStart) {
    timeEntries = model.getEntriesForReport(mission, position, endDate, dateStart, employee);
    for (TimeEntry timeEntry : timeEntries) {
      totalTime += timeEntry.getWorktime();
    }
    this.employee = employee;
  }

  public TimeEntriesPerEmployee(Employee employee, List<Mission> missions, Position position, Calendar endDate,
      Calendar startDate) {
    timeEntries = model.getEntriesForReport(missions, position, endDate, startDate, employee);
    for (TimeEntry timeEntry : timeEntries) {
      totalTime += timeEntry.getWorktime();
    }
    this.employee = employee;
  }

  public TimeEntriesPerEmployee(Employee employee, Boolean showWeeks, Boolean showMissions, Calendar endDate,
      Calendar startDate) {
    this.employee = employee;
    this.showWeeks = showWeeks;
    this.showMissions = showMissions;
    endDate.add(Calendar.DAY_OF_MONTH, 1);
    endDate.setTimeInMillis(endDate.getTimeInMillis() - 1);
    totalTime = model.getWorktimeForReport(employee, endDate, startDate, null, null);
    if (showWeeks) {
      timeEntriesPerWeek = new ArrayList<TimeEntriesPerWeek>();
      TimeEntriesPerWeek timeEntries;
      for (Calendar week : getWeekNumbers(endDate, startDate)) {
        if (!startDate.before(week)){
          Calendar endOfWeek = (Calendar) week.clone();
          endOfWeek.add(Calendar.WEEK_OF_YEAR, 1);
          endOfWeek.setTimeInMillis(endOfWeek.getTimeInMillis() - 1);
          timeEntries = new TimeEntriesPerWeek(employee, showMissions, startDate, endOfWeek);
        } else if (Parser.getWeekBegin(endDate).equals(week)){
          timeEntries = new TimeEntriesPerWeek(employee, showMissions, week, endDate);
        } else {
          timeEntries = new TimeEntriesPerWeek(employee, showMissions, week);
        }
        if (timeEntries.getTotalTime() > 0){
          timeEntriesPerWeek.add(timeEntries);
        }
      }
    } else {
      timeEntriesPerMission = new ArrayList<TimeEntriesPerMission>();
      if (showMissions){
        for (Mission mission : model.getMissionsForReport(employee, endDate, startDate)) {
          timeEntriesPerMission.add(new TimeEntriesPerMission(mission, employee, endDate, startDate));
        }
      }
    }
  }

  private List<Calendar> getWeekNumbers(Calendar endDate, Calendar startDate) {
    List<Calendar> list = new ArrayList<Calendar>();
    Calendar week = Parser.getWeekBegin(startDate);
    while (!week.after(endDate)) {
        list.add((Calendar) week.clone());
        week.add(Calendar.WEEK_OF_YEAR, 1);
    }
    return list;
  }

  public Employee getEmployee() {
    return employee;
  }

  public Integer getTotalTime() {
    return totalTime;
  }

  public List<TimeEntry> getTimeEntries() {
    return timeEntries;
  }

  public static TimeEntryDAO getModel() {
    return model;
  }

  public List<TimeEntriesPerWeek> getTimeEntriesPerWeek() {
    return timeEntriesPerWeek;
  }

  public boolean getShowWeeks() {
    return showWeeks;
  }

  public List<TimeEntriesPerMission> getTimeEntriesPerMission() {
    return timeEntriesPerMission;
  }

  public boolean getShowMissions() {
    return showMissions;
  }

}
