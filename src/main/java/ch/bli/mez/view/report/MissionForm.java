package ch.bli.mez.view.report;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

    JLabel chooseProjectLabel = new JLabel("Aufträge Auswählen:");
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

    everythingRadioButton = new JRadioButton("Alles (alle Orgeln und Aufträge)");
    everythingPanel.add(everythingRadioButton);

    JPanel singelOrganPAnel = new JPanel();
    selectProjectPanel.add(singelOrganPAnel);
    singelOrganPAnel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

    singelOrganRadioButton = new JRadioButton("Einzelne Aufträge:");
    singelOrganRadioButton.setSelected(false);
    singelOrganPAnel.add(singelOrganRadioButton);

    singelMission = new JTextField();
    singelMission.setToolTipText("Mehrere Aufträge mit einem Komma trennen. z.B \r\nBern, Zürich, Tour");
    singelOrganPAnel.add(singelMission);
    singelMission.setColumns(18);

    // Group the radio buttons.
    radioButtonGroup = new ButtonGroup();
    radioButtonGroup.add(allOrganRadioButton);
    radioButtonGroup.add(everythingRadioButton);
    radioButtonGroup.add(singelOrganRadioButton);
    allOrganRadioButton.setSelected(true);
    
    setGuiListener();
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
  
  private void setGuiListener(){
    singelMission.addKeyListener(new KeyListener() {
      public void keyTyped(KeyEvent e) {
      }
      public void keyReleased(KeyEvent e) {
      }
      public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_BACK_SPACE || keyCode == KeyEvent.VK_DELETE){
          
        } else if (keyCode == KeyEvent.VK_ENTER){

        } else {
          singelOrganRadioButton.setSelected(true);
        }
        
      }
    });
  }
}
