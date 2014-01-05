package ch.bli.mez.view.report;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ch.bli.mez.view.DefaultForm;

public class EmployeeForm extends DefaultForm {
  private static final long serialVersionUID = 4307548389043639607L;

  private JTextField singelEmployee;
  private ButtonGroup radioButtonGroup;
  private JRadioButton allEmployeeRadioButton;
  private JRadioButton singelEmployeeRadioButton;

  public EmployeeForm() {
    setLayout(new BorderLayout(0, 0));

    JPanel projectLabelPanel = new JPanel();
    FlowLayout flowLayout = (FlowLayout) projectLabelPanel.getLayout();
    flowLayout.setAlignment(FlowLayout.LEFT);
    add(projectLabelPanel, BorderLayout.NORTH);

    JLabel chooseProjectLabel = new JLabel("Mitarbeiter Auswählen:");
    chooseProjectLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
    projectLabelPanel.add(chooseProjectLabel);

    JPanel inputPanel = new JPanel();
    add(inputPanel, BorderLayout.CENTER);
    inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

    JPanel selectProjectPanel = new JPanel();
    inputPanel.add(selectProjectPanel);
    selectProjectPanel.setLayout(new BoxLayout(selectProjectPanel, BoxLayout.Y_AXIS));

    JPanel allOrganPanel = new JPanel();
    selectProjectPanel.add(allOrganPanel);
    allOrganPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

    allEmployeeRadioButton = new JRadioButton("Alle Mitarbeiter");
    allEmployeeRadioButton.setEnabled(true);
    allEmployeeRadioButton.setSelected(false);
    allOrganPanel.add(allEmployeeRadioButton);

    JPanel singelOrganPAnel = new JPanel();
    selectProjectPanel.add(singelOrganPAnel);
    singelOrganPAnel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

    singelEmployeeRadioButton = new JRadioButton("Einzelne Mitarbeiter");
    singelEmployeeRadioButton.setSelected(false);
    singelOrganPAnel.add(singelEmployeeRadioButton);

    singelEmployee = new JTextField();
    singelEmployee.setToolTipText("Mehrere Mitarbeiter mit einem Komma trennen. z.B \r\nMax, Hans");
    singelOrganPAnel.add(singelEmployee);
    singelEmployee.setColumns(15);

    // Group the radio buttons.
    radioButtonGroup = new ButtonGroup();
    radioButtonGroup.add(allEmployeeRadioButton);
    radioButtonGroup.add(singelEmployeeRadioButton);
    allEmployeeRadioButton.setSelected(true);
  }

  // 0: Alle, 1: singleOrgan
  public Integer getSelectedEmployee() {
    if (allEmployeeRadioButton.isSelected()) {
      return 0;
    }
    if (singelEmployeeRadioButton.isSelected()) {
      return 1;
    }
    return null;
  }

  public List<String> getSingelEmployee() {
    String[] employee = singelEmployee.getText().split(",");
    List<String> employeeTrimed = new ArrayList<String>();
    for (String emp : employee) {
      if (!"".equals(emp)) {
        employeeTrimed.add(emp.trim());
      }
    }
    return employeeTrimed;
  }

  @Override
  public Boolean validateFields() {

    if (getSelectedEmployee() == 1) {
      if ("".equals(singelEmployee.getText())) {
        getParentPanel()
            .showError(
                "Es muss mind. ein Mitarbeiter angegeben werden. Mehrere Mitarbeiter können mit einem Komma , separiert werden.");
        return false;
      }

      if (getSingelEmployee().size() == 1) {
        getParentPanel().showError("Es gab ein Problem beim lesen der Eingabe. Bitte den Mitarbeiter neu eingeben.");
        return false;
      }

    }

    return true;
  }

  @Override
  public void showAsCreateNew() {
    // not used

  }

  @Override
  public void cleanFields() {
    singelEmployee.setText("");
    allEmployeeRadioButton.setSelected(true);

  }

}
