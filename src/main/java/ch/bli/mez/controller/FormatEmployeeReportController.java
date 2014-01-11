package ch.bli.mez.controller;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import ch.bli.mez.util.TimeEntriesPerEmployee;
import ch.bli.mez.util.TimeEntriesPerMission;
import ch.bli.mez.util.TimeEntriesPerPosition;
import ch.bli.mez.util.TimeEntriesPerWeek;

public class FormatEmployeeReportController {
  
  public void formatTimeEntries(List<TimeEntriesPerEmployee> timeEntriesPerEmployees) {
    showReport(createReport(timeEntriesPerEmployees));
  }

  private String createReport(List<TimeEntriesPerEmployee> timeEntriesPerEmployees) {
    String report = "<!Doctype html>";
    report += "<ul style='list-style-type: none;'>";
    for (TimeEntriesPerEmployee timeEntryPerEmployee : timeEntriesPerEmployees) {
      report += "<li>";
      report += "<span class='employeeName'>" + timeEntryPerEmployee.getEmployee().getLastName() + "</span>";
      report += "<span style='margin-left: 25px;'>" + timeEntryPerEmployee.getEmployee().getFirstName() + "</span>";
      report += "<span class='employeeTotal' style='margin-left: 50px; font-weight: bold;'>" + timeEntryPerEmployee.getTotalTime() + "</span>";
      report += "<ul style='list-style-type: none;'>";
      if (timeEntryPerEmployee.getShowWeeks()){
        for (TimeEntriesPerWeek timeEntryPerWeek : timeEntryPerEmployee.getTimeEntriesPerWeek()){
          report += "<li style='padding-left: 250px;'>";
          report += "<span>" + "KW" + timeEntryPerWeek.getWeek().get(Calendar.WEEK_OF_YEAR) + "</span>";
          report += "<span style='margin-left: 25px;'>" + "(" + timeEntryPerWeek.getWeek().get(Calendar.YEAR) + ")" + "</span>";
          report += "<span style='margin-left: 50px; font-weight: bold;'>" + timeEntryPerWeek.getTotalTime() + "</span>";
          if (timeEntryPerEmployee.getShowMissions()){
            report += createMissionReport(timeEntryPerWeek.getTimeEntriesPerMission());
          }
          report += "</li>";
        }
      } else if (timeEntryPerEmployee.getShowMissions()) {
        report += createMissionReport(timeEntryPerEmployee.getTimeEntriesPerMission());
      }
      report += "</ul>";
      report += "</li>";
    }
    report += "</ul>";
    return report;
  }
  
  private String createMissionReport(List<TimeEntriesPerMission> timeEntriesPerMission){
    String report = "";
    for (TimeEntriesPerMission timeEntryPerMission : timeEntriesPerMission) {
      report += "<li style='padding-left: 250px;'>";
      report += "<span class='missionName'>" + timeEntryPerMission.getMission().getMissionName() + "</span>";
      report += "<span class='missionTotal' style='margin-left: 50px; font-weight: bold;'>" + timeEntryPerMission.getTotalTime() + "</span>";
      report += "<ul style='list-style-type: none;'>";
      for (TimeEntriesPerPosition timeEntryPerPosition : timeEntryPerMission.getTimeEntriesPerPositions()) {
        report += "<li style='padding-left: 250px;'>";
        report += "<span>" + timeEntryPerPosition.getPosition().getPositionName() + "</span>";
        report += "<span style='margin-left: 25px;'>" + timeEntryPerPosition.getPosition().getCode() + "</span>";
        report += "<span style='margin-left: 50px; font-weight: bold;'>" + timeEntryPerPosition.getTotalTime() + "</span>";
        report += "</li>";
      }
      report += "</ul>";
      report += "</li>";
    }
    return report;
  }

  private void showReport(String report) {
    File reportFile;
    try {
      reportFile = File.createTempFile("report", ".html");
      BufferedWriter out = new BufferedWriter(new FileWriter(reportFile));
      out.write(report);
      out.close();
      if (Desktop.isDesktopSupported()) {
        Desktop desktop = Desktop.getDesktop();
        try {
          desktop.browse(reportFile.toURI());
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {
        Runtime runtime = Runtime.getRuntime();
        try {
          runtime.exec("xdg-open " + reportFile.toURI());
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
