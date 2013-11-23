package ch.bli.mez.view.time;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TimeEntryForm extends JPanel {
  private static final long serialVersionUID = 9171774652563879025L;

  private JTextField date;
  private JTextField missionName;
  private JTextField positionName;
  private JTextField worktime;
  private JButton saveButton;
  private JButton deleteButton;
  private JLabel dateLabel;
  private JLabel positionLaben;
  private JLabel auftragLabel;
  private JLabel zeitLabel;
  private JPanel datePanel;
  private JPanel positionPanel;
  private JPanel missionPanel;
  private JPanel timePanel;

  public TimeEntryForm() {
    build();
    addGuiFeatureListener();
    showAsListEntry();
  }

  private void build() {
    setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

    datePanel = new JPanel();
    add(datePanel);

    dateLabel = new JLabel("Datum");
    datePanel.add(dateLabel);

    date = new JTextField();
    date.setToolTipText("Datumsformat: 01.01.2014 oder 01.01.14");
    datePanel.add(date);
    date.setColumns(10);

    missionPanel = new JPanel();
    add(missionPanel);

    auftragLabel = new JLabel("Auftrag");
    missionPanel.add(auftragLabel);

    missionName = new JTextField();
    missionPanel.add(missionName);
    missionName.setColumns(10);

    positionPanel = new JPanel();
    add(positionPanel);

    positionLaben = new JLabel("Position");
    positionPanel.add(positionLaben);

    positionName = new JTextField();
    positionName.setToolTipText("Position ist vom Auftrag abhängig.");
    positionPanel.add(positionName);
    positionName.setColumns(10);

    timePanel = new JPanel();
    add(timePanel);

    zeitLabel = new JLabel("Zeit");
    timePanel.add(zeitLabel);

    worktime = new JTextField();
    timePanel.add(worktime);
    worktime.setColumns(5);

    saveButton = new JButton("Speichern");
    add(saveButton);

    deleteButton = new JButton("Löschen");
    add(deleteButton);
  }

  private void showAsListEntry() {
    deleteButton.setVisible(true);
    dateLabel.setVisible(false);
    positionLaben.setVisible(false);
    auftragLabel.setVisible(false);
    zeitLabel.setVisible(false);
  }
  
  public void showAsHeader() {
    deleteButton.setVisible(false);
    dateLabel.setVisible(true);
    positionLaben.setVisible(true);
    auftragLabel.setVisible(true);
    zeitLabel.setVisible(true);
  }

  public void setSaveListener(ActionListener actionListener) {
    saveButton.addActionListener(actionListener);
  }

  public void setDeleteListener(ActionListener actionListener) {
    deleteButton.addActionListener(actionListener);
  }

  public void cleanFields() {
    worktime.setText("");
  }

  public Calendar getDate() {
    return null;
    // return createDate(dateTextField.getText());
  }

  public String getPositionName() {
    return positionName.getText();
  }

  public String getMissionName() {
    return missionName.getText();
  }

  public Integer getWorktime() {
    return null;
    // return parseWorkTimeToMinutes(worktimeTextField.getText());
  }

  public void setDate(Calendar calendar) {
    // this.dateTextField.setText(parseCalendar(calendar));
  }

  public void setPosition(String position) {
    this.positionName.setText(position);
  }

  public void setMission(String mission) {
    this.missionName.setText(mission);
  }

  public void setWorktime(Integer worktime) {
    // this.worktimeTextField.setText(parseMinutesToWorkTime(worktime));
  }

  private void addGuiFeatureListener() {

    KeyListener enterKeyListener = new KeyListener() {
      public void keyTyped(KeyEvent arg0) {
      }

      public void keyReleased(KeyEvent arg0) {
      }

      public void keyPressed(KeyEvent arg0) {
        if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
          saveButton.doClick();
        }
      }
    };

    missionName.addKeyListener(enterKeyListener);
    worktime.addKeyListener(enterKeyListener);
    positionName.addKeyListener(enterKeyListener);
    date.addKeyListener(enterKeyListener);

  }

  public boolean validateFields() {
    if (getDate().equals("")) {
      return false;
    }
    if (getMissionName().equals("")) {
      return false;
    }
    if (getPositionName().equals("")) {
      return false;
    }
    if (worktime.getText().equals("")
        || (worktime.getText().matches("[0-9]*[:,.]{1}[0-9]{2}") || worktime.getText().matches("[0-9]*")) == false) {
      return false;
    }
    return true;
  }

}
