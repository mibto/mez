package ch.bli.mez.view.time;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class TimeListEntry extends JPanel {

  private static final long serialVersionUID = 9171774652563879025L;

  private JTextField dateTextField;
  private JTextField missionTextField;
  private JTextField positionTextField;
  private JTextField worktimeTextField;

  private JButton saveButton;
  private JButton deleteButton;
  private Color backGroundColor;

  public TimeListEntry() {
    setLayout(new FlowLayout(FlowLayout.LEFT));

    dateTextField = new JTextField();
    dateTextField.setColumns(10);
    add(dateTextField);

    missionTextField = new JTextField();
    missionTextField.setColumns(10);
    add(missionTextField);

    positionTextField = new JTextField();
    positionTextField.setColumns(10);
    add(positionTextField);

    worktimeTextField = new JTextField();
    worktimeTextField.setColumns(5);
    add(worktimeTextField);

    saveButton = new JButton("Speichern");
    add(saveButton);

    deleteButton = new JButton("Löschen");
    add(deleteButton);

    backGroundColor = getBackground();
    addGuiFeatureListener();
  }

  public void setSaveTimeEntryListListener(ActionListener actionListener) {
    saveButton.addActionListener(actionListener);
  }

  public void setDeleteTimeEntryListListener(ActionListener actionListener) {
    deleteButton.addActionListener(actionListener);
  }

  public void showSuccess() {
    setBackground(new Color(150, 255, 150));
    hideConfirmation();
  }

  public void showError() {
    setBackground(new Color(255, 150, 150));
    hideConfirmation();
  }

  private void hideConfirmation() {
    Timer timer = new Timer(900, new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        setBackground(backGroundColor);
      }
    });
    timer.setRepeats(false);
    timer.start();
  }

  public void showDateError() {
    hideTextFieldError(dateTextField);
  }

  public void showWorktimeError() {
    hideTextFieldError(worktimeTextField);
  }

  public void showPositionError() {
    hideTextFieldError(positionTextField);
  }

  public void showMissionError() {
    hideTextFieldError(missionTextField);
  }

  private void hideTextFieldError(final JTextField field) {
    Timer timer = new Timer(1800, new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        field.setBackground(new Color(255, 0, 0));
      }
    });
    timer.setRepeats(false);
    timer.start();
  }

  public String getDate() {
    return dateTextField.getText();
  }

  public String getPosition() {
    return positionTextField.getText();
  }

  public String getMission() {
    return missionTextField.getText();
  }

  public String getWorktime() {
    return worktimeTextField.getText();
  }

  public void setDate(String date) {
    this.dateTextField.setText(date);
  }

  public void setPosition(String position) {
    this.positionTextField.setText(position);
  }

  public void setMission(String mission) {
    this.missionTextField.setText(mission);
  }

  public void setWorktime(String worktime) {
    this.worktimeTextField.setText(worktime);
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
    missionTextField.addKeyListener(enterKeyListener);
    worktimeTextField.addKeyListener(enterKeyListener);
    positionTextField.addKeyListener(enterKeyListener);
    dateTextField.addKeyListener(enterKeyListener);
  }

}
