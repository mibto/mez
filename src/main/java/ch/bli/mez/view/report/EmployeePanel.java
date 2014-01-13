package ch.bli.mez.view.report;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import ch.bli.mez.view.DefaultForm;
import ch.bli.mez.view.DefaultPanel;

public class EmployeePanel extends DefaultPanel {
  private static final long serialVersionUID = 9218067589902931609L;

  private DefaultForm dateForm;
  private DefaultForm employeeForm;
  private DefaultForm optionForm;
  private DefaultForm generateForm;

  JPanel datePanel;
  JPanel employeePanel;
  JPanel optionPanel;
  JPanel generateReportPanel;
  private JPanel reportPanel;

  public EmployeePanel() {
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

    employeePanel = new JPanel();
    reportPanel.add(employeePanel);
    employeePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

    optionPanel = new JPanel();
    reportPanel.add(optionPanel);
    optionPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

    generateReportPanel = new JPanel();
    reportPanel.add(generateReportPanel);
    generateReportPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
  }

  public void setDatePanel(DefaultForm form) {
    datePanel.setVisible(true);
    datePanel.add(form);
    dateForm = form;
  }

  public void setEmployeePanel(DefaultForm form) {
    employeePanel.setVisible(true);
    employeePanel.add(form);
    employeeForm = form;
  }

  public void setOptionPanel(DefaultForm form) {
    optionPanel.setVisible(true);
    optionPanel.add(form);
    optionForm = form;
  }

  public void setGenerateReportPanel(DefaultForm form) {
    generateReportPanel.setVisible(true);
    generateReportPanel.add(form);
    generateForm = form;
  }

  public DateForm getDatePanelForm() {
    return (DateForm) dateForm;
  }

  public EmployeeForm getEmployeePanelForm() {
    return (EmployeeForm) employeeForm;
  }

  public OptionEmployeeForm getOptionPanelForm() {
    return (OptionEmployeeForm) optionForm;
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
