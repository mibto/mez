package ch.bli.mez.controller;

import java.util.List;

import ch.bli.mez.model.TimeEntry;
import ch.bli.mez.util.TimeEntriesPerEmployee;
import ch.bli.mez.util.TimeEntriesPerMission;
import ch.bli.mez.util.TimeEntriesPerPosition;

public class FormatMissionReportController {

  public void formatTimeEntries(List<TimeEntriesPerMission> timeEntriesPerMissions) {
    // TODO Auto-generated method stub
    for (TimeEntriesPerMission timeEntriesPerMission: timeEntriesPerMissions){
      System.out.println(timeEntriesPerMission.getMission().getMissionName() + " " + timeEntriesPerMission.getTotalTime());      
      for (TimeEntriesPerPosition timeEntriesPerPosition: timeEntriesPerMission.getTimeEntriesPerPositions()){
        System.out.println("    " + timeEntriesPerPosition.getPosition().getPositionName() + " " + timeEntriesPerPosition.getTotalTime());      
        if (timeEntriesPerPosition.getShowEmployees()){
          for (TimeEntriesPerEmployee timeEntriesPerEmployee: timeEntriesPerPosition.getTimeEntriesPerEmployees()){
            System.out.println("        " + timeEntriesPerEmployee.getEmployee().getLastName() + " " + timeEntriesPerEmployee.getTotalTime());      
          }          
        }
      }
    }
  }

}
