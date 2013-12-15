package ch.bli.mez.view.report;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import ch.bli.mez.util.Parser;
import ch.bli.mez.view.DefaultForm;

public class MissionReportForm extends DefaultForm {

  private static final long serialVersionUID = -3677971641329988164L;
  private JTextField dateFrom;
  private JTextField dateUntil;
  private JTextField singelMission;
  private JTextField positions;
  private ButtonGroup radioButtonGroup;
  private JCheckBox reportWithEmployee;
  private JButton generateProjectReportButton;

  /**
   * Create the panel.
   */
  public MissionReportForm() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    JPanel TitelPanel = new JPanel();
    add(TitelPanel);
    TitelPanel.setLayout(new BoxLayout(TitelPanel, BoxLayout.Y_AXIS));

    JPanel panel_2 = new JPanel();
    FlowLayout flowLayout_4 = (FlowLayout) panel_2.getLayout();
    TitelPanel.add(panel_2);

    JLabel lblDatum = new JLabel("Datum");
    panel_2.add(lblDatum);

    JPanel panel_3 = new JPanel();
    TitelPanel.add(panel_3);
    panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));

    JPanel panel = new JPanel();
    panel_3.add(panel);

    JLabel lblNewLabel = new JLabel("Von:");
    panel.add(lblNewLabel);

    dateFrom = new JTextField();
    panel.add(dateFrom);
    dateFrom.setColumns(10);

    JPanel panel_1 = new JPanel();
    panel_3.add(panel_1);

    JLabel dateUntillbl = new JLabel("Bis:");
    panel_1.add(dateUntillbl);

    dateUntil = new JTextField();
    panel_1.add(dateUntil);
    dateUntil.setColumns(10);

    JPanel OptionPanel_1 = new JPanel();
    add(OptionPanel_1);
    OptionPanel_1.setLayout(new BoxLayout(OptionPanel_1, BoxLayout.X_AXIS));

    JPanel SelectProjektPanel = new JPanel();
    SelectProjektPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
    OptionPanel_1.add(SelectProjektPanel);
    SelectProjektPanel.setLayout(new BoxLayout(SelectProjektPanel, BoxLayout.Y_AXIS));

    JPanel panel_4 = new JPanel();
    FlowLayout flowLayout_3 = (FlowLayout) panel_4.getLayout();
    flowLayout_3.setAlignment(FlowLayout.LEFT);
    SelectProjektPanel.add(panel_4);

    JLabel lblNewLabel_2 = new JLabel("Projekte Auswählen:");
    panel_4.add(lblNewLabel_2);

    JPanel panel_5 = new JPanel();
    FlowLayout flowLayout_1 = (FlowLayout) panel_5.getLayout();
    flowLayout_1.setAlignment(FlowLayout.LEFT);
    SelectProjektPanel.add(panel_5);

    JRadioButton allOrganRadioButton = new JRadioButton("Alle Orgeln");
    allOrganRadioButton.setEnabled(true);
    allOrganRadioButton.setSelected(false);
    panel_5.add(allOrganRadioButton);

    JPanel panel_6 = new JPanel();
    FlowLayout flowLayout = (FlowLayout) panel_6.getLayout();
    flowLayout.setAlignment(FlowLayout.LEFT);
    SelectProjektPanel.add(panel_6);

    JRadioButton everythingRadioButton = new JRadioButton("Alles");
    panel_6.add(everythingRadioButton);

    JPanel panel_7 = new JPanel();
    FlowLayout flowLayout_2 = (FlowLayout) panel_7.getLayout();
    flowLayout_2.setAlignment(FlowLayout.LEFT);
    SelectProjektPanel.add(panel_7);

    JRadioButton singelOrganRadioButton = new JRadioButton("Einzelne Orgel: ");
    singelOrganRadioButton.setSelected(false);
    panel_7.add(singelOrganRadioButton);

    singelMission = new JTextField();
    panel_7.add(singelMission);
    singelMission.setColumns(10);

    JPanel SelectEmployeePanel = new JPanel();
    SelectEmployeePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
    OptionPanel_1.add(SelectEmployeePanel);
    SelectEmployeePanel.setLayout(new BoxLayout(SelectEmployeePanel, BoxLayout.Y_AXIS));

    JPanel panel_8 = new JPanel();
    FlowLayout flowLayout_5 = (FlowLayout) panel_8.getLayout();
    flowLayout_5.setAlignment(FlowLayout.LEFT);
    SelectEmployeePanel.add(panel_8);

    JLabel lblUnterteilung = new JLabel("Unterteilung:");
    panel_8.add(lblUnterteilung);

    JPanel panel_9 = new JPanel();
    FlowLayout flowLayout_6 = (FlowLayout) panel_9.getLayout();
    flowLayout_6.setAlignment(FlowLayout.LEFT);
    SelectEmployeePanel.add(panel_9);

    reportWithEmployee = new JCheckBox("Mitarbeiter");
    panel_9.add(reportWithEmployee);

    JPanel OptionPanel_2 = new JPanel();
    OptionPanel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
    add(OptionPanel_2);
    OptionPanel_2.setLayout(new BoxLayout(OptionPanel_2, BoxLayout.Y_AXIS));

    JPanel panel_10 = new JPanel();
    FlowLayout flowLayout_7 = (FlowLayout) panel_10.getLayout();
    flowLayout_7.setAlignment(FlowLayout.LEFT);
    OptionPanel_2.add(panel_10);

    JLabel lblPositionenZusammenfassen = new JLabel("Positionen zusammenfassen:");
    panel_10.add(lblPositionenZusammenfassen);

    JPanel panel_11 = new JPanel();
    FlowLayout flowLayout_8 = (FlowLayout) panel_11.getLayout();
    flowLayout_8.setAlignment(FlowLayout.LEFT);
    OptionPanel_2.add(panel_11);

    positions = new JTextField();
    panel_11.add(positions);
    positions.setColumns(50);

    JPanel ButtonPanel = new JPanel();
    add(ButtonPanel);
    ButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

    generateProjectReportButton = new JButton("Projekt Report generieren");
    ButtonPanel.add(generateProjectReportButton);

    // Group the radio buttons.
    radioButtonGroup = new ButtonGroup();
    radioButtonGroup.add(allOrganRadioButton);
    radioButtonGroup.add(everythingRadioButton);
    radioButtonGroup.add(singelOrganRadioButton);

  }

  public Calendar getDateFrom() {
    return Parser.parseDateStringToCalendar(dateFrom.getText());
  }

  public List<String> getPositions() {
    String[] positions = this.positions.getText().split(",");
    List<String> positionsTrimed = new ArrayList<String>();
    for(String position : positions){
      if(!"".equals(position)){     
        positionsTrimed.add(position.trim());
      }
    }
    return positionsTrimed;
  }

  public List<String> getSingelMission() {
    String[] missions = singelMission.getText().split(",");
    List<String> missionsTrimed = new ArrayList<String>();
    for(String mission : missions){
      if(!"".equals(mission)){ 
        missionsTrimed.add(mission.trim());
      }
    }
    return missionsTrimed;
  }

  public Calendar getDateUntil() {
    return Parser.parseDateStringToCalendar(dateUntil.getText());
  }

  // TODO
  // Diverse Rückgaben möglich. z.B auch Integer für die RadioButton Auswahl.
  // Bitte Integer 0: allOrgans, 1: everything, 2: singleOrgan 
  public Integer getSelectedMission() {
    return 1;
    // return radioButtonGroup.getSelection().toString();
  }

  public boolean getReportWithEmployee() {
    return reportWithEmployee.isSelected();
  }

  @Override
  public Boolean validateFields() {
    // TODO: Überprüfen ob ein RadioButton ausgewählt ist, und falls singelOrgan prüfen ob Textfeld nicht leer.

    if ("Einzelne Orgel:".equals(getSelectedMission())) {
      if ("".equals(getSingelMission())) {
        getParentPanel().showError("Es wurde kein Startdatum eingegeben");
        return false;
      }
    }

    return true;
  }

  public void setGenerateProjectReportListener(ActionListener actionListener) {
    generateProjectReportButton.addActionListener(actionListener);
  }

  @Override
  public void showAsCreateNew() {
    // brauchen wir hier nicht
  }

  @Override
  public void cleanFields() {
    dateFrom.setText("");
    positions.setText("");
    singelMission.setText("");
    dateUntil.setText("");
    radioButtonGroup.clearSelection();
    reportWithEmployee.setEnabled(false);
  }

}
