package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ch.bli.mez.model.dao.EmployeeDAO;
import ch.bli.mez.view.DefaultPanel;
import ch.bli.mez.view.report.DateForm;
import ch.bli.mez.view.report.EmployeeForm;
import ch.bli.mez.view.report.EmployeePanel;
import ch.bli.mez.view.report.GenerateForm;
import ch.bli.mez.view.report.OptionEmployeeForm;

public class EmployeeReportController {

  private EmployeePanel view;
  private EmployeeDAO model;

  public EmployeeReportController() {
    this.view = new EmployeePanel();
    this.model = new EmployeeDAO();
    view.setDatePanel(createDateForm());
    view.setEmployeePanel(createEmployeeForm());
    view.setOptionPanel(createOptionForm());
    view.setGenerateReportPanel(createGenerateForm());
    setProjectFormActionListeners(view);
  }

  private DateForm createDateForm() {
    DateForm form = new DateForm();
    form.setParentPanel(view);
    return form;
  }

  private EmployeeForm createEmployeeForm() {
    EmployeeForm form = new EmployeeForm();
    form.setParentPanel(view);
    return form;
  }

  private OptionEmployeeForm createOptionForm() {
    OptionEmployeeForm form = new OptionEmployeeForm();
    form.setParentPanel(view);
    return form;
  }

  private GenerateForm createGenerateForm() {
    GenerateForm form = new GenerateForm();
    form.setParentPanel(view);
    return form;
  }

  public boolean validateFields(EmployeePanel view) {
    if (!view.getDatePanelForm().validateFields()) {
      return false;
    }
    if (!view.getEmployeePanelForm().validateFields()) {
      return false;
    }
    if (!view.getOptionPanelForm().validateFields()) {
      return false;
    }
    if (!view.getGeneratePanelForm().validateFields()) {
      return false;
    }
    if (view.getEmployeePanelForm().getSelectedEmployee() == 1) {
      for (String employeeString : view.getEmployeePanelForm().getSingelEmployees()) {
        String[] employeeNames = employeeString.split(" ");
        if (employeeNames.length != 2) {
          view.showError("Einzelne Mitarbeiter müssen mit Nachname, dann Vorname, Kommagetrennt aufgelistet sein");
          return false;
        }
        if (model.findByEmployeeName(employeeNames[0], employeeNames[1]) == null) {
          view.showError("Es existiert keinen Mitarbeiter mit dem Nachname " + employeeNames[0] + " und dem Vorname "
              + employeeNames[1]);
          return false;
        }
      }
    }
    return true;
  }

  private void setProjectFormActionListeners(final EmployeePanel view) {

    view.getGeneratePanelForm().setGenerateProjectReportListener((new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (validateFields(view)) {
          // TODO
          // Action Listener setzen
        }
      }
    }));

  }

  public void setView(EmployeePanel view) {
    this.view = view;
  }

  public DefaultPanel getView() {
    return view;
  }
}
