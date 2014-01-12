package ch.bli.mez.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ch.bli.mez.model.Employee;
import ch.bli.mez.model.Mission;
import ch.bli.mez.model.Position;
import ch.bli.mez.model.dao.TimeEntryDAO;

public class TimeEntriesPerMission {
  
  private Mission mission;
  private Integer totalTime = 0;
  private List<TimeEntriesPerPosition> timeEntriesPerPositions = new ArrayList<TimeEntriesPerPosition>();
  private TimeEntryDAO model;
  
  public TimeEntriesPerMission(Mission mission, List<Position> hiddenPositions, Boolean showEmployee, Calendar endDate, Calendar startDate){
    this.mission = mission;
    Set<Position> visiblePositions = getVisiblePositions(hiddenPositions);
    for (Position position : visiblePositions){
      TimeEntriesPerPosition timeEntriesPerPosition = new TimeEntriesPerPosition(mission, position, showEmployee, endDate, startDate);
      timeEntriesPerPositions.add(timeEntriesPerPosition);
      if (timeEntriesPerPosition.getTotalTime()==0){
        timeEntriesPerPositions.remove(timeEntriesPerPosition);
      }
      else{
        totalTime += timeEntriesPerPosition.getTotalTime();          
      }
    }
  }

  public TimeEntriesPerMission(Mission mission, Employee employee, Calendar endDate, Calendar startDate) {
    this.mission = mission;
    this.model = new TimeEntryDAO();
    totalTime = model.getWorktimeForReport(employee, endDate, startDate, mission, null);
    for (Position position : model.getPositionsForReport(employee, endDate, startDate, mission)){
      timeEntriesPerPositions.add(new TimeEntriesPerPosition(mission, position, false, endDate, startDate));
    }
  }

  private Set<Position> getVisiblePositions(List<Position> positions) {
    Set<Position> visibleMissionPositions = new HashSet<Position>();
    Set<Position> missionPositions = mission.getPositions();
    visibleMissionPositions.addAll(missionPositions);
    visibleMissionPositions.removeAll(positions);
    return visibleMissionPositions;
  }

  public Mission getMission() {
    return mission;
  }

  public Integer getTotalTime() {
    return totalTime;
  }

  public List<TimeEntriesPerPosition> getTimeEntriesPerPositions() {
    return timeEntriesPerPositions;
  }
}
