package ch.bli.mez.view.report;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import ch.bli.mez.view.DefaultForm;
import ch.bli.mez.view.DefaultPanel;

public class ProjectPanel extends DefaultPanel {
  private static final long serialVersionUID = 9218067562902931609L;

  private DefaultForm dateForm;
  private DefaultForm missionForm;
  private DefaultForm optionForm;
  private DefaultForm positionForm;
  private DefaultForm generateForm;

  JPanel datePanel;
  JPanel missionPanel;
  JPanel optionPanel;
  JPanel positionPanel;
  JPanel generateReportPanel;
  private JPanel reportPanel;

  public ProjectPanel() {
    createPanel();
  }

  public void createPanel() {
    JPanel centerPanel = new JPanel(new GridLayout(1, 0));
    add(centerPanel, BorderLayout.CENTER);
    
    reportPanel = new JPanel();
    reportPanel.setLayout(new BoxLayout(reportPanel, BoxLayout.Y_AXIS));
    getTopPanel().add(reportPanel, BorderLayout.CENTER);

    datePanel = new JPanel();
    reportPanel.add(datePanel);
    datePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

    missionPanel = new JPanel();
    reportPanel.add(missionPanel);
    missionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

    optionPanel = new JPanel();
    reportPanel.add(optionPanel);
    optionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

    positionPanel = new JPanel();
    reportPanel.add(positionPanel);
    positionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

    generateReportPanel = new JPanel();
    reportPanel.add(generateReportPanel);
    generateReportPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
  }

  public void setDatePanel(DefaultForm form) {
    datePanel.setVisible(true);
    datePanel.add(form);
    dateForm = form;
  }

  public void setMissionPanel(DefaultForm form) {
    missionPanel.setVisible(true);
    missionPanel.add(form);
    missionForm = form;
  }

  public void setOptionPanel(DefaultForm form) {
    optionPanel.setVisible(true);
    optionPanel.add(form);
    optionForm = form;
  }

  public void setPositionPanel(DefaultForm form) {
    positionPanel.setVisible(true);
    positionPanel.add(form);
    positionForm = form;
  }

  public void setGenerateReportPanel(DefaultForm form) {
    generateReportPanel.setVisible(true);
    generateReportPanel.add(form);
    generateForm = form;
  }

  public DateForm getDatePanelForm() {
    return (DateForm) dateForm;
  }

  public MissionForm getMissionPanelForm() {
    return (MissionForm) missionForm;
  }

  public OptionMissionForm getOptionPanelForm() {
    return (OptionMissionForm) optionForm;
  }

  public PositionForm getPositionPanelForm() {
    return (PositionForm) positionForm;
  }

  public GenerateForm getGeneratePanelForm() {
    return (GenerateForm) generateForm;
  }

  @Override
  public void addForm(DefaultForm form) {
    reportPanel.add(form, BorderLayout.CENTER);
    form.setParentPanel(this);
  }

}
