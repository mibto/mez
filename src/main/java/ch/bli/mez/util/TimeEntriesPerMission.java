package ch.bli.mez.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import ch.bli.mez.model.Mission;
import ch.bli.mez.model.Position;

public class TimeEntriesPerMission {
  
  private Mission mission;
  private Integer totalTime = 0;
  private List<TimeEntriesPerPosition> timeEntriesPerPositions = new ArrayList<TimeEntriesPerPosition>();
  
  public TimeEntriesPerMission(Mission mission, List<Position> positions, Boolean showEmployee, Calendar endDate, Calendar startDate){
    this.mission = mission;
    Set<Position> visiblePositions = getVisiblePositions(positions);
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

  private Set<Position> getVisiblePositions(List<Position> positions) {
    Set<Position> missionPositions = mission.getPositions();
    missionPositions.removeAll(positions);
    return missionPositions;
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
