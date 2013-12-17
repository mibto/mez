package ch.bli.mez.controller;

import java.util.Calendar;

import ch.bli.mez.model.Employee;
import ch.bli.mez.model.dao.EmployeeReportDAO;
import ch.bli.mez.view.time.WeekSummaryPanel;

public class TimeEntryWeekSummaryController {

  private Employee employee;
  private WeekSummaryPanel view;
  private EmployeeReportDAO model;

  public TimeEntryWeekSummaryController(Employee employee) {
    this.model = new EmployeeReportDAO();
    this.view = new WeekSummaryPanel();
    this.employee = employee;
  }

  public WeekSummaryPanel getView() {
    return this.view;
  }

  public void updateWeekSummary() {
    Calendar currentWeek = model.getNewestDateOfWork(employee);
    if (currentWeek == null) {
      return;
    }
    Calendar lastWeek = (Calendar) currentWeek.clone();
    lastWeek.set(Calendar.WEEK_OF_YEAR, currentWeek.get(Calendar.WEEK_OF_YEAR) - 1);
    view.setCurrentWeekNumber(currentWeek.get(Calendar.WEEK_OF_YEAR), currentWeek.get(Calendar.YEAR));
    view.setLastWeekNumber(lastWeek.get(Calendar.WEEK_OF_YEAR), lastWeek.get(Calendar.YEAR));
    view.setCurrentWeekAmount(model.getWeekSummaryAmount(employee, getWeekBegin(currentWeek)));
    view.setLastWeekAmount(model.getWeekSummaryAmount(employee, getWeekBegin(lastWeek)));
  }

  private Calendar getWeekBegin(Calendar calendar) {
    Calendar weekBegin = Calendar.getInstance();
    weekBegin.clear();
    weekBegin.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
    weekBegin.set(Calendar.WEEK_OF_YEAR, calendar.get(Calendar.WEEK_OF_YEAR));
    return weekBegin;
  }
}