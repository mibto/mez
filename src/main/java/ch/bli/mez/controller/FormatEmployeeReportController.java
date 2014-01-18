package ch.bli.mez.controller;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ch.bli.mez.util.Parser;
import ch.bli.mez.util.TimeEntriesPerEmployee;
import ch.bli.mez.util.TimeEntriesPerMission;
import ch.bli.mez.util.TimeEntriesPerPosition;
import ch.bli.mez.util.TimeEntriesPerWeek;

public class FormatEmployeeReportController {
  
  public void formatTimeEntries(List<TimeEntriesPerEmployee> timeEntriesPerEmployees, Calendar endDate, Calendar startDate) {
    showReport(createReport(timeEntriesPerEmployees, endDate, startDate));
  }

  private String createReport(List<TimeEntriesPerEmployee> timeEntriesPerEmployees, Calendar endDate, Calendar startDate) {

    Date date = new Date();
    SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    DecimalFormat df = new DecimalFormat("00");

    String report = "<!Doctype html>";
    report += "<div style='margin: 0 auto; width: 1000px;'>";
    report += "<div style='height: 100px; border-bottom: 1px dotted black;'>" + "<h2>Metzler Orgelbau AG - Mitarbeiter Report</h2>"
        + "<span>Zeitperiode: " + Parser.parseDateCalendarToString(startDate) + " - "
        + Parser.parseDateCalendarToString(endDate) + "</span>" + "<span style='float: right'>"
        + f.format(date) + "</span>" + "</div><br>";
    report += "<div style='font-weight: bold; width: 350px; text-align: left; display: inline-block;'>Person</div>";
    report += "<div style='font-weight: bold; width: 300px; text-align: left; display: inline-block;'>Auftrag</div>";
    report += "<div style='font-weight: bold; width: 350px; text-align: left; display: inline-block;'>Position</div>";
    report += "<br>";
    
    for (TimeEntriesPerEmployee timeEntryPerEmployee : timeEntriesPerEmployees) {
      report += "<div style='display: inline-block; width: 350px;'>";
      report += "<span style='padding-right: 10px;'>" + Parser.encodeHTML(timeEntryPerEmployee.getEmployee().getFirstName()) + "</span>";
      report += "<span>" + Parser.encodeHTML(timeEntryPerEmployee.getEmployee().getLastName()) + "</span>";
      report += "<div style='display: inline-block; float: right; margin-right: 50px; text-align: right; font-weight: bold;'>" + Parser.parseMinutesIntegerToString(timeEntryPerEmployee.getTotalTime()) + "</div>";
      report += "</div>";
      report += "<br>";
      
      if (timeEntryPerEmployee.getShowWeeks()){
        for (TimeEntriesPerWeek timeEntryPerWeek : timeEntryPerEmployee.getTimeEntriesPerWeek()){
          report += "<div style='display: inline-block; width: 300px; margin-left: 350px;'>";
          report += "<span style='padding-right: 25px;'>" + "KW " + df.format(timeEntryPerWeek.getWeek().get(Calendar.WEEK_OF_YEAR)) + "</span>";
          report += "<span>" + "(" + Parser.parseDateCalendarToString(timeEntryPerWeek.getWeek()) + ")" + "</span>";
          report += "<div style='display: inline-block; text-align: right; float: right; margin-right: 50px; font-weight: bold;'>" + Parser.parseMinutesIntegerToString(timeEntryPerWeek.getTotalTime()) + "</div>";
          report += "</div><br>";
          
          if (timeEntryPerEmployee.getShowMissions()){
            report += createMissionReport(timeEntryPerWeek.getTimeEntriesPerMission());
          }
        }
      } else if (timeEntryPerEmployee.getShowMissions() && !timeEntryPerEmployee.getShowWeeks()) {
        report += createMissionReport(timeEntryPerEmployee.getTimeEntriesPerMission());
      }
      report += "<hr>";
    }
    return report;
  }
  
  private String createMissionReport(List<TimeEntriesPerMission> timeEntriesPerMission){
    String report = "";
    for (TimeEntriesPerMission timeEntryPerMission : timeEntriesPerMission) {
      report += "<div style='display: inline-block; width: 300px; margin-left: 350px;'>";
      report += "<span class='missionName'>" + Parser.encodeHTML(timeEntryPerMission.getMission().getMissionName()) + "</span>";
      report += "<div style='display: inline-block; text-align: right; float: right; margin-right: 50px; font-weight: bold;'>" + Parser.parseMinutesIntegerToString(timeEntryPerMission.getTotalTime()) + "</div>";
      report += "</div><br>";
      for (TimeEntriesPerPosition timeEntryPerPosition : timeEntryPerMission.getTimeEntriesPerPositions()) {
        report += "<div style='display: inline-block; width: 350px; margin-left: 650px;'>";
        report += "<span style='padding-right: 25px;'>" + timeEntryPerPosition.getPosition().getCode() + "</span>";
        report += "<span>" + Parser.encodeHTML(timeEntryPerPosition.getPosition().getPositionName()) + "</span>";
        report += "<div style='display: inline-block; text-align: right; float: right; font-weight: bold;'>" + Parser.parseMinutesIntegerToString(timeEntryPerPosition.getTotalTime()) + "</div>";
        report += "</div><br>";
      }
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
