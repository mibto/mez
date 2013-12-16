package ch.bli.mez.controller;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import ch.bli.mez.util.TimeEntriesPerEmployee;
import ch.bli.mez.util.TimeEntriesPerMission;
import ch.bli.mez.util.TimeEntriesPerPosition;

public class FormatMissionReportController {

  public void formatTimeEntries(List<TimeEntriesPerMission> timeEntriesPerMissions) {
    showReport(createReport(timeEntriesPerMissions));
  }

  private String createReport(List<TimeEntriesPerMission> timeEntriesPerMissions) {
    String report = "<!Doctype html>";
    report += "<ul>";
    for (TimeEntriesPerMission timeEntryPerMission : timeEntriesPerMissions) {
      report += "<li>";
      report += "<span class='missionName'>" + timeEntryPerMission.getMission().getMissionName() + "</span>";
      report += "<span class='missionTotal'>" + timeEntryPerMission.getTotalTime() + "</span>";
      report += "<ul>";

      for (TimeEntriesPerPosition timeEntryPerPosition : timeEntryPerMission.getTimeEntriesPerPositions()) {
        report += "<li>";
        report += "<span>" + timeEntryPerPosition.getPosition().getPositionName() + "</span>";
        report += "<span>" + timeEntryPerPosition.getTotalTime() + "</span>";
        if (timeEntryPerPosition.getShowEmployees()) {
          report += "<ul>";
          for (TimeEntriesPerEmployee timeEntriesPerEmployee : timeEntryPerPosition.getTimeEntriesPerEmployees()) {
            report += "<li>";
            report += "<span>" + timeEntriesPerEmployee.getEmployee().getLastName() + "</span>";
            report += "<span>" + timeEntriesPerEmployee.getTotalTime() + "</span>";
            report += "</li>";
          }
          report += "</ul>";
        } else {
          report += "</li>";
        }
        report += "</li>";
      }
      report += "</ul>";
      report += "</li>";
    }
    report += "</ul>";
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
