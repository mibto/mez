package ch.bli.mez.view.report;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.bli.mez.view.DefaultForm;

public class OptionEmployeeForm extends DefaultForm {
  private static final long serialVersionUID = 1224786297787640298L;
  private JPanel optionsLabelPanel;

  private JCheckBox reportMissionPerEmployee;
  private JCheckBox reportKWSeparation;

  public OptionEmployeeForm() {
    setLayout(new BorderLayout(0, 0));

    optionsLabelPanel = new JPanel();
    FlowLayout flowLayout = (FlowLayout) optionsLabelPanel.getLayout();
    flowLayout.setAlignment(FlowLayout.LEFT);
    add(optionsLabelPanel, BorderLayout.NORTH);

    JLabel optionsLabel = new JLabel("Unterteilung / weitere Optionen:");
    optionsLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
    optionsLabelPanel.add(optionsLabel);

    JPanel inputPanel = new JPanel();
    add(inputPanel, BorderLayout.CENTER);
    inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

    JPanel employeeOptionPanelMPE = new JPanel();
    inputPanel.add(employeeOptionPanelMPE);
    employeeOptionPanelMPE.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

    reportMissionPerEmployee = new JCheckBox("Auftrag pro Mitarbeiter");
    employeeOptionPanelMPE.add(reportMissionPerEmployee);

    JPanel employeeOptionPanelKWS = new JPanel();
    inputPanel.add(employeeOptionPanelKWS);
    employeeOptionPanelKWS.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

    reportKWSeparation = new JCheckBox("In Kalenderwochen unterteilen");
    employeeOptionPanelKWS.add(reportKWSeparation);

  }

  public boolean getReportMissionPerEmployee() {
    return reportMissionPerEmployee.isSelected();
  }

  public boolean getReportKWSeparation() {
    return reportKWSeparation.isSelected();
  }

  @Override
  public Boolean validateFields() {
    return true;
  }

  @Override
  public void showAsCreateNew() {
    // not used
  }

  @Override
  public void cleanFields() {
    reportMissionPerEmployee.setEnabled(false);
    reportKWSeparation.setEnabled(false);
  }

}
