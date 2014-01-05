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

public class MissionForm extends DefaultForm {
  private static final long serialVersionUID = 4307548389043639607L;

  private JTextField singelMission;
  private ButtonGroup radioButtonGroup;
  private JRadioButton allOrganRadioButton;
  private JRadioButton everythingRadioButton;
  private JRadioButton singelOrganRadioButton;

  public MissionForm() {
    setLayout(new BorderLayout(0, 0));

    JPanel projectLabelPanel = new JPanel();
    FlowLayout flowLayout = (FlowLayout) projectLabelPanel.getLayout();
    flowLayout.setAlignment(FlowLayout.LEFT);
    add(projectLabelPanel, BorderLayout.NORTH);

    JLabel chooseProjectLabel = new JLabel("Projekte Auswählen:");
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

    allOrganRadioButton = new JRadioButton("Alle Orgeln");
    allOrganRadioButton.setEnabled(true);
    allOrganRadioButton.setSelected(false);
    allOrganPanel.add(allOrganRadioButton);

    JPanel everythingPanel = new JPanel();
    selectProjectPanel.add(everythingPanel);
    everythingPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

    everythingRadioButton = new JRadioButton("Alles (alle Orgeln und Projekte)");
    everythingPanel.add(everythingRadioButton);

    JPanel singelOrganPAnel = new JPanel();
    selectProjectPanel.add(singelOrganPAnel);
    singelOrganPAnel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

    singelOrganRadioButton = new JRadioButton("Einzelne Orgeln:");
    singelOrganRadioButton.setSelected(false);
    singelOrganPAnel.add(singelOrganRadioButton);

    singelMission = new JTextField();
    singelMission.setToolTipText("Mehrere Orgeln mit einem Komma trennen. z.B \r\nBern, Zürich, Basel");
    singelOrganPAnel.add(singelMission);
    singelMission.setColumns(15);

    // Group the radio buttons.
    radioButtonGroup = new ButtonGroup();
    radioButtonGroup.add(allOrganRadioButton);
    radioButtonGroup.add(everythingRadioButton);
    radioButtonGroup.add(singelOrganRadioButton);
    allOrganRadioButton.setSelected(true);
  }

  // 0: allOrgans, 1: everything, 2: singleOrgan
  public Integer getSelectedMission() {
    if (allOrganRadioButton.isSelected()) {
      return 0;
    }
    if (everythingRadioButton.isSelected()) {
      return 1;
    }
    if (singelOrganRadioButton.isSelected()) {
      return 2;
    }
    return null;
  }

  public List<String> getSingelMission() {
    String[] missions = singelMission.getText().split(",");
    List<String> missionsTrimed = new ArrayList<String>();
    for (String mission : missions) {
      if (!"".equals(mission)) {
        missionsTrimed.add(mission.trim());
      }
    }
    return missionsTrimed;
  }

  @Override
  public Boolean validateFields() {

    if (getSelectedMission() == 2) {

      if ("".equals(singelMission.getText())) {
        getParentPanel().showError(
            "Es muss mind. eine Orgel angegeben werden. Mehrere Orgeln können mit einem Komma , separiert werden.");
        return false;
      }

      if (getSingelMission().size() == 0) {
        getParentPanel().showError("Es gab ein Problem beim lesen der Eingabe. Bitte die Orgeln neu eingeben.");
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
    singelMission.setText("");
    allOrganRadioButton.setSelected(true);

  }

}
