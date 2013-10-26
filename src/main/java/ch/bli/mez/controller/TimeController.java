package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import ch.bli.mez.model.Employee;
import ch.bli.mez.model.Mission;
import ch.bli.mez.model.Position;
import ch.bli.mez.model.TimeEntry;
import ch.bli.mez.model.dao.EmployeeDAO;
import ch.bli.mez.model.dao.TimeEntryDAO;
import ch.bli.mez.view.time.TimeListEntry;
import ch.bli.mez.view.time.TimePanel;
import ch.bli.mez.view.time.TimeView;

public class TimeController {

  private EmployeeDAO modelemployee;
  private TimeView view;
  private TimeEntryDAO model;
  private final SearchController searchController;

  public TimeController() {
    this.model = new TimeEntryDAO();
    this.modelemployee = new EmployeeDAO();
    this.searchController = new SearchController();

    this.view = new TimeView(searchController.getSearchPanel());
    addTabs();
  }

  public TimeView getView() {
    return view;
  }

  private void addTabs() {
    for (Employee employee : modelemployee.findAll()) {
      view.addTab(employee.getFirstName() + " " + employee.getLastName(),
          createTimePanel(employee));
    }
  }

  private TimePanel createTimePanel(Employee employee) {
    TimePanel form = new TimePanel();
    TimeEntry timeEntry = new TimeEntry();
    timeEntry.setEmployee(employee);
    setFormActionListeners(timeEntry, form);

    addTimeEntrys(employee, form);

    return form;
  }

  private void addTimeEntrys(Employee employee, TimePanel timePanel) {
    // TODO
    for (TimeEntry timeEntry : model.findAll()) {
      timePanel.addTimeListEntry(createTimeListEntry(timeEntry));
    }
  }

  private TimeListEntry createTimeListEntry(final TimeEntry timeEntry) {
    final TimeListEntry timeListEntry = new TimeListEntry();

    timeListEntry.setMission(timeEntry.getMission().getMissionName());
    timeListEntry.setDate(timeEntry.getDate().toString());
    timeListEntry.setPosition(timeEntry.getPosition().getPositionName());
    timeListEntry.setWorktime(timeEntry.getWorktime().toString());

    setTimeListEntryActionListeners(timeListEntry, timeEntry);

    return timeListEntry;
  }

  public void setFormActionListeners(final TimeEntry timeEntry,
      final TimePanel form) {
    form.setSaveTimeListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {

        if (!validateFields(form)) {
          return;
        }

        model.updateTimeEntry(updateTimeEntry(timeEntry, form));
        form.showConfirmation("Der Zeiteintrag vom" + timeEntry.getDate()
            + " wurde gespeichert");

        form.cleanFields();
      }
    });
  }

  public void setTimeListEntryActionListeners(
      final TimeListEntry timeListEntry, final TimeEntry timeEntry) {

    timeListEntry.setSaveTimeEntryListListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {

        if (!validateFields(timeListEntry)) {
          return;
        }

        timeEntry.setDate(timeEntry.getDate());
        timeEntry.setMission(timeEntry.getMission());
        timeEntry.setPosition(timeEntry.getPosition());
        timeEntry.setWorktime(timeEntry.getWorktime());
        timeListEntry.showSuccess();
        model.updateTimeEntry(timeEntry);
      }
    });
  }

  public TimeEntry updateTimeEntry(TimeEntry timeEntry, TimePanel form) {
    timeEntry.setWorktime(Integer.parseInt(form.getWorktime()));
    // TODO
    // timeEntry.setDate(form.getDate());
    timeEntry.setDate(new Date());
    // TODO
    timeEntry.setMission(new Mission());
    // TODO
    timeEntry.setPosition(new Position());
    return timeEntry;
  }

  // TODO
  public boolean validateFields(TimeListEntry panel) {
    boolean valid = true;

    if (panel.getDate().equals("")) {
      panel.showDateError();
      valid = false;
    }
    if (panel.getMission().equals("")) {
      panel.showMissionError();
      valid = false;
    }
    if (panel.getPosition().equals("")) {
      panel.showPositionError();
      valid = false;
    }

    try {
      if (!panel.getWorktime().equals(""))
        Integer.parseInt(panel.getWorktime());
    } catch (NumberFormatException e) {
      panel.showWorktimeError();
      valid = false;
    }

    return valid;
  }

  public boolean validateFields(TimePanel panel) {
    boolean valid = true;
    String errortext = "Die Felder ";

    if (panel.getDate().equals("")) {
      panel.showDateError();
      errortext = errortext + "Datum ";
      valid = false;
    }
    if (panel.getMission().equals("")) {
      panel.showMissionError();
      errortext = errortext + "Auftrag ";
      valid = false;
    }
    if (panel.getPosition().equals("")) {
      panel.showPositionError();
      errortext = errortext + "Position ";
      valid = false;
    }

    try {
      if (!panel.getWorktime().equals("")) {
        Integer.parseInt(panel.getWorktime());
      } else {
        panel.showWorktimeError();
        errortext = errortext + "Zeit ";
        valid = false;
      }
    } catch (NumberFormatException e) {
      panel.showWorktimeError();
      errortext = errortext + "Zeit ";
      valid = false;
    }

    if (valid == false) {
      errortext = errortext + "müssen gültig ausgefüllt sein ";
      panel.showErrorMessage(errortext);
    }

    return valid;
  }
}
