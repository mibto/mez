package ch.bli.mez.view.report;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.bli.mez.view.DefaultForm;

public class OptionMissionForm extends DefaultForm {
  private static final long serialVersionUID = 1224786297787640298L;
  private JPanel optionsLabelPanel;

  private JCheckBox reportWithEmployee;

  public OptionMissionForm() {
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

    JPanel employeeOptionPanel = new JPanel();
    FlowLayout flowLayout_1 = (FlowLayout) employeeOptionPanel.getLayout();
    flowLayout_1.setAlignment(FlowLayout.LEFT);
    inputPanel.add(employeeOptionPanel);

    reportWithEmployee = new JCheckBox("Mitarbeiter anzeigen im Report");
    employeeOptionPanel.add(reportWithEmployee);

  }

  public boolean getReportWithEmployee() {
    return reportWithEmployee.isSelected();
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
    reportWithEmployee.setEnabled(false);
  }

}
