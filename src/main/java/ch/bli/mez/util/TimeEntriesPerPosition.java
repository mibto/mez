package ch.bli.mez.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ch.bli.mez.model.Employee;
import ch.bli.mez.model.Mission;
import ch.bli.mez.model.Position;
import ch.bli.mez.model.TimeEntry;
import ch.bli.mez.model.dao.EmployeeDAO;
import ch.bli.mez.model.dao.TimeEntryDAO;

public class TimeEntriesPerPosition {
  
  private Position position;
  private Integer totalTime = 0;
  private List<TimeEntriesPerEmployee> timeEntriesPerEmployees = new ArrayList<TimeEntriesPerEmployee>();
  private List<TimeEntry> timeEntries;
  private Boolean showEmployees;
  private static TimeEntryDAO model = new TimeEntryDAO();
  private static EmployeeDAO employeeModel = new EmployeeDAO();
  
  public TimeEntriesPerPosition(Mission mission, Position position, Boolean showEmployees, Calendar endDate, Calendar dateStart){
    this.position = position;
    this.showEmployees = showEmployees;
    if (!showEmployees){
      timeEntries = model.getEntriesForReport(mission, position, endDate, dateStart);
      for (TimeEntry timeEntry : timeEntries){
        totalTime += timeEntry.getWorktime();
      }
    }
    else {
      List<Employee> employees = employeeModel.findAll();
      for (Employee employee : employees){
        TimeEntriesPerEmployee timeEntriesPerEmployee = new TimeEntriesPerEmployee(employee, mission, position, endDate, dateStart);
        timeEntriesPerEmployees.add(timeEntriesPerEmployee);
        if (timeEntriesPerEmployee.getTotalTime()==0){
          timeEntriesPerEmployees.remove(timeEntriesPerEmployee);
        }
        else{
          totalTime += timeEntriesPerEmployee.getTotalTime();          
        }
      }
    }
  }

  public List<TimeEntry> getTimeEntries() {
    return timeEntries;
  }

  public Boolean getShowEmployees() {
    return showEmployees;
  }

  public Position getPosition() {
    return position;
  }

  public Integer getTotalTime() {
    return totalTime;
  }

  public List<TimeEntriesPerEmployee> getTimeEntriesPerEmployees() {
    return timeEntriesPerEmployees;
  }
}
