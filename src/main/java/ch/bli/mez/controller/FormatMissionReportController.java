package ch.bli.mez.controller;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import ch.bli.mez.util.Parser;
import ch.bli.mez.util.TimeEntriesPerEmployee;
import ch.bli.mez.util.TimeEntriesPerMission;
import ch.bli.mez.util.TimeEntriesPerPosition;

public class FormatMissionReportController {

  public void formatTimeEntries(List<TimeEntriesPerMission> timeEntriesPerMissions, List<TimeEntriesPerPosition> summarizedTimeEntries) {
    showReport(createReport(timeEntriesPerMissions, summarizedTimeEntries));
  }

  private String createReport(List<TimeEntriesPerMission> timeEntriesPerMissions, List<TimeEntriesPerPosition> summarizedTimeEntries) {
    String report = "<!Doctype html>";
    report += "<div style='margin: 0 auto; width: 1250px;'>";
    report += "<div style='font-weight: bold; width: 300px; text-align: left; display: inline-block;'>Auftrag</div>";
    report += "<div style='font-weight: bold; width: 450px; text-align: left; display: inline-block;'>Position</div>";
    report += "<div style='font-weight: bold; width: 450px; text-align: left; display: inline-block;'>Person</div>";
    report += "<br>";
    
    for (TimeEntriesPerMission timeEntryPerMission : timeEntriesPerMissions) {
      report += "<div style='display: inline-block; width: 300px;'>";
      report += "<span style='padding-right: 25px;'>" + timeEntryPerMission.getMission().getMissionName() + "</span>";
      report += "<div style='display: inline-block; font-weight: bold; text-align: right'>" + Parser.parseMinutesIntegerToString(timeEntryPerMission.getTotalTime()) + "</div>";
      report += "</div>";
      report += "<br>";

      for (TimeEntriesPerPosition timeEntryPerPosition : timeEntryPerMission.getTimeEntriesPerPositions()) {
        report += "<div style='display: inline-block; width: 450px; margin-left: 300px;'>";
        report += "<span style='padding-right: 25px;'>" + timeEntryPerPosition.getPosition().getCode() + "</span>";
        report += "<span style='padding-right: 25px;'>" + timeEntryPerPosition.getPosition().getPositionName() + "</span>";
        report += "<div style='display: inline-block; float: right; margin-right: 150px; text-align: right; font-weight: bold;'>" + Parser.parseMinutesIntegerToString(timeEntryPerPosition.getTotalTime()) + "</div>";
        report += "</div>";
        report += "<br>";
        
        if (timeEntryPerPosition.getShowEmployees()) {
          for (TimeEntriesPerEmployee timeEntriesPerEmployee : timeEntryPerPosition.getTimeEntriesPerEmployees()) {
            report += "<div style='display: inline-block; width: 450px; margin-left: 750px;'>";
            report += "<span style='padding-right: 10px;'>" + timeEntriesPerEmployee.getEmployee().getFirstName() + "</span>";
            report += "<span style='padding-right: 25px;'>" + timeEntriesPerEmployee.getEmployee().getLastName() + "</span>";
            report += "<div style='display: inline-block; text-align: right; float: right; margin-right: 100px; font-weight: bold;'>" + Parser.parseMinutesIntegerToString(timeEntriesPerEmployee.getTotalTime()) + "</div>";
            report += "</div>";
            report += "<br>";
          }
        } else {
          report += "<br>";
        }
      }
      report += "<hr>";
    }
    for (TimeEntriesPerPosition timeEntryPerPosition : summarizedTimeEntries){
      report += timeEntryPerPosition.getPosition().getPositionName() + " " + Parser.parseMinutesIntegerToString(timeEntryPerPosition.getTotalTime());
      if (timeEntryPerPosition.getShowEmployees()) {
        for (TimeEntriesPerEmployee timeEntriesPerEmployee : timeEntryPerPosition.getTimeEntriesPerEmployees()) {
          report += "<div style='display: inline-block; width: 450px; margin-left: 750px;'>";
          report += "<span style='padding-right: 10px;'>" + timeEntriesPerEmployee.getEmployee().getFirstName() + "</span>";
          report += "<span style='padding-right: 25px;'>" + timeEntriesPerEmployee.getEmployee().getLastName() + "</span>";
          report += "<div style='display: inline-block; text-align: right; float: right; margin-right: 100px; font-weight: bold;'>" + Parser.parseMinutesIntegerToString(timeEntriesPerEmployee.getTotalTime()) + "</div>";
          report += "</div>";
          report += "<br>";
        }
      } else {
        report += "<br>";
      }
    }
    report += "</div></html>";
    return report;
  }

  private void showReport(String report) {
    File reportFile;
    try {
      reportFile = File.createTempFile("report", ".html");
      BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(reportFile),"UTF-8"));
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
