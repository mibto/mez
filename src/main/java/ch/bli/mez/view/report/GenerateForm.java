package ch.bli.mez.view.report;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ch.bli.mez.view.DefaultForm;

public class GenerateForm extends DefaultForm {

  private static final long serialVersionUID = 8673072243248002376L;
  private JButton generateProjectReportButton;

  public GenerateForm() {
    setLayout(new BorderLayout(0, 0));

    JPanel generateButtonForm = new JPanel();
    add(generateButtonForm, BorderLayout.CENTER);

    generateProjectReportButton = new JButton("Projekt Report erstellen!");
    generateButtonForm.add(generateProjectReportButton);

  }

  public void setGenerateProjectReportListener(ActionListener actionListener) {
    generateProjectReportButton.addActionListener(actionListener);
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
    // not used
  }
}
