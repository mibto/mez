package ch.bli.mez.view.time;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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

public class TimePanel extends JPanel {

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

    JPanel northPanel = new JPanel();
    northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    add(new JScrollPane(northPanel), BorderLayout.NORTH);

    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());
    northPanel.add(topPanel);

    JPanel entryPanel = new JPanel();
    entryPanel.setLayout(new BoxLayout(entryPanel, BoxLayout.X_AXIS));
    topPanel.add(entryPanel, BorderLayout.CENTER);

    JPanel timeDatePanel = new JPanel();
    timeDatePanel.setLayout(new BoxLayout(timeDatePanel, BoxLayout.Y_AXIS));
    timeDatePanel.setAlignmentY(BOTTOM_ALIGNMENT);
    entryPanel.add(timeDatePanel);

    JLabel dateLabel = new JLabel("Datum");
    dateLabel.setAlignmentX(LEFT_ALIGNMENT);
    timeDatePanel.add(dateLabel);

    dateTextField = new JTextField();
    dateTextField.setColumns(10);
    dateTextField.setAlignmentX(LEFT_ALIGNMENT);
    timeDatePanel.add(dateTextField);

    JPanel timeMissionPanel = new JPanel();
    timeMissionPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    entryPanel.add(timeMissionPanel);
    timeMissionPanel
        .setLayout(new BoxLayout(timeMissionPanel, BoxLayout.Y_AXIS));

    JLabel missionLabel = new JLabel("Auftrag");
    timeMissionPanel.add(missionLabel);

    missionTextField = new JTextField();
    timeMissionPanel.add(missionTextField);
    missionTextField.setColumns(10);

    JPanel timePositionPanel = new JPanel();
    timePositionPanel.setLayout(new BoxLayout(timePositionPanel,
        BoxLayout.Y_AXIS));
    timePositionPanel.setAlignmentY(BOTTOM_ALIGNMENT);
    entryPanel.add(timePositionPanel);

    JLabel positionLabel = new JLabel("Position");
    positionLabel.setAlignmentX(LEFT_ALIGNMENT);
    timePositionPanel.add(positionLabel);

    positionTextField = new JTextField();
    positionTextField.setColumns(25);
    positionTextField.setAlignmentX(LEFT_ALIGNMENT);
    timePositionPanel.add(positionTextField);

    JPanel timeWorktimePanel = new JPanel();
    timeWorktimePanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    entryPanel.add(timeWorktimePanel);
    timeWorktimePanel.setLayout(new BoxLayout(timeWorktimePanel,
        BoxLayout.Y_AXIS));

    JLabel worktimeLabel = new JLabel("Zeit");
    timeWorktimePanel.add(worktimeLabel);

    worktimeTextField = new JTextField();
    timeWorktimePanel.add(worktimeTextField);
    worktimeTextField.setColumns(10);

    addButton = new JButton("Speichern");
    addButton.setAlignmentY(BOTTOM_ALIGNMENT);
    entryPanel.add(addButton);

    messagePanel = new JPanel();
    messagePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    topPanel.add(messagePanel, BorderLayout.SOUTH);

    messageLabel = new JLabel(" ");
    messagePanel.add(messageLabel);

    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    add(new JScrollPane(centerPanel), BorderLayout.CENTER);

    listPanel = new JPanel();
    listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
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
    setDate("");
    setPosition("");
    setMission("");
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

}
