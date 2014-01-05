package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ch.bli.mez.view.DefaultPanel;
import ch.bli.mez.view.report.DateForm;
import ch.bli.mez.view.report.EmployeeForm;
import ch.bli.mez.view.report.EmployeePanel;
import ch.bli.mez.view.report.GenerateForm;
import ch.bli.mez.view.report.OptionEmployeeForm;

public class EmployeeReportController {

  private EmployeePanel view;

  public EmployeeReportController() {
    this.view = new EmployeePanel();
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

    // TODO
    // Weitere überprüfugen durchführen

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
