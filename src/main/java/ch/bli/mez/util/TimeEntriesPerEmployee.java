package ch.bli.mez.util;

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

  public TimeEntriesPerEmployee(Employee employee, Mission mission, Position position, Calendar endDate, Calendar dateStart) {
    timeEntries = model.getEntriesForReport(mission, position, endDate, dateStart, employee);
    for (TimeEntry timeEntry : timeEntries){
      totalTime += timeEntry.getWorktime();
    }
    this.employee = employee;
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

  
}
