package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ch.bli.mez.view.DefaultPanel;
import ch.bli.mez.view.report.ProjectForm;
import ch.bli.mez.view.report.ProjectPanel;

public class MissionReportController {

  private DefaultPanel view;

  public MissionReportController() {
    this.view = new ProjectPanel();
    view.setCreateNewForm(createProjectForm());
  }

  private ProjectForm createProjectForm() {
    ProjectForm form = new ProjectForm();
    setProjectFormActionListeners(form);
    return form;
  }

  public boolean validateFields(ProjectForm form) {
    if (!form.validateFields()) {
      return false;
    }
    if (form.getSelectedMission() == 1){
      
    }
    return true;
  }

  private void setProjectFormActionListeners(final ProjectForm form) {

    form.setGenerateProjectReportListener((new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        if (validateFields(form)){
          
        }
      }
    }));

  }

  public void setView(DefaultPanel view) {
    this.view = view;
  }

  public DefaultPanel getView() {
    return view;
  }
}
