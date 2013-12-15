package ch.bli.mez.controller;

import java.util.List;

import ch.bli.mez.model.TimeEntry;

public class FormatMissionReportController {

  public void formatTimeEntries(List<TimeEntry> timeEntries) {
    // TODO Auto-generated method stub
    for (TimeEntry timeEntry: timeEntries){
      System.out.println(timeEntry.getMission().getMissionName() + " " + timeEntry.getPosition().getPositionName() + " " + timeEntry.getEmployee().getFirstName());      
    }
  }

}
