package ch.bli.mez.view.time;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;

import ch.bli.mez.controller.TimeInterface;

public class TimePanel extends JPanel implements TimeInterface {

  private static final long serialVersionUID = -1084526692534142942L;

  private JTextField dateTextField;
  private JTextField positionTextField;
  private JTextField missionTextField;
  private JTextField worktimeTextField;

  private JPanel messagePanel;
  private JLabel messageLabel;

  private JButton addButton;

  private JPanel listPanel;

  /**
   * Create the panel.
   */
  public TimePanel() {

    setLayout(new BorderLayout());

    // EntryPanel (north)
    JPanel northPanel = new JPanel();
    add(new JScrollPane(northPanel), BorderLayout.NORTH);

    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());
    northPanel.add(topPanel);

    JPanel entryPanel = new JPanel();
    topPanel.add(entryPanel, BorderLayout.CENTER);

    JLabel dateLabel = new JLabel("Datum");
    entryPanel.add(dateLabel);

    dateTextField = new JTextField();
    dateTextField.setColumns(10);
    entryPanel.add(dateTextField);

    JLabel missionLabel = new JLabel("Auftrag");
    entryPanel.add(missionLabel);

    missionTextField = new JTextField();
    missionTextField.setColumns(10);
    entryPanel.add(missionTextField);

    JLabel positionLabel = new JLabel("Position");
    entryPanel.add(positionLabel);

    positionTextField = new JTextField();
    positionTextField.setColumns(10);
    entryPanel.add(positionTextField);

    JLabel worktimeLabel = new JLabel("Zeit");
    entryPanel.add(worktimeLabel);

    worktimeTextField = new JTextField();
    worktimeTextField.setColumns(5);
    entryPanel.add(worktimeTextField);

    addButton = new JButton("Speichern");
    entryPanel.add(addButton);

    messagePanel = new JPanel();
    messagePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    topPanel.add(messagePanel, BorderLayout.SOUTH);

    messageLabel = new JLabel(" ");
    messagePanel.add(messageLabel);

    // ListPanel (center)
    JPanel centerPanel = new JPanel();
    add(new JScrollPane(centerPanel), BorderLayout.CENTER);

    listPanel = new JPanel();
    listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
    centerPanel.add((listPanel));

    addGuiFeatureListener();

  }

  public void setSaveTimeListener(ActionListener actionListener) {
    addButton.addActionListener(actionListener);
  }

  public void cleanFields() {
    dateTextField.setBackground(new Color(255, 255, 255));
    positionTextField.setBackground(new Color(255, 255, 255));
    missionTextField.setBackground(new Color(255, 255, 255));
    worktimeTextField.setBackground(new Color(255, 255, 255));
    // setDate("");
    // setPosition("");
    // setMission("");
    setWorktime("");
  }

  public void addTimeListEntry(TimeListEntry timeListEntry) {
    listPanel.add(timeListEntry);
    listPanel.revalidate();
    listPanel.repaint();
  }

  public void removeMissionListEntry(TimeListEntry timeListEntry) {
    listPanel.remove(timeListEntry);
  }

  public void showConfirmation(String name) {
    showSuccessMessage(name + " wurde zur Liste hinzugefügt!");
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
    field.setBackground(new Color(255, 0, 0));
    Timer timer = new Timer(1800, new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        field.setBackground(new Color(255, 255, 255));
      }
    });
    timer.setRepeats(false);
    timer.start();
  }

  public void showErrorMessage(String message) {
    messageLabel.setForeground(new Color(255, 0, 0));
    messageLabel.setText(message);
    hideMessage();
  }

  public void showSuccessMessage(String message) {
    messageLabel.setForeground(new Color(0, 125, 0));
    messageLabel.setText(message);
    hideMessage();
  }

  private void hideMessage() {
    Timer timer = new Timer(1800, new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        messageLabel.setText(" ");
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
    return this.worktimeTextField.getText();
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
          addButton.doClick();
        }
      }
    };
    missionTextField.addKeyListener(enterKeyListener);
    worktimeTextField.addKeyListener(enterKeyListener);
    positionTextField.addKeyListener(enterKeyListener);
    dateTextField.addKeyListener(enterKeyListener);
  }

  public void showSuccess() {
    // TODO Auto-generated method stub

  }

  public void showError() {
    // TODO Auto-generated method stub

  }

}
