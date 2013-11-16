package ch.bli.mez.view.time;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class TimeListEntry extends JPanel {
  private static final long serialVersionUID = 9171774652563879025L;

  private Integer id;
  private JTextField dateTextField;
  private JTextField missionTextField;
  private JTextField positionTextField;
  private JTextField worktimeTextField;

  private JButton saveButton;
  private JButton deleteButton;

  private Color backGroundColor;
  private JLabel dateLabel;
  private JLabel positionLaben;
  private JLabel auftragLabel;
  private JLabel zeitLabel;
  private JPanel datePanel;
  private JPanel positionPanel;
  private JPanel missionPanel;
  private JPanel timePanel;

  /**
   * 
   * @param displayTyp
   *          True = Header False = Liste
   */
  public TimeListEntry() {
    setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

    datePanel = new JPanel();
    add(datePanel);

    dateLabel = new JLabel("Datum");
    datePanel.add(dateLabel);

    dateTextField = new JTextField();
    datePanel.add(dateTextField);
    dateTextField.setColumns(10);

    positionPanel = new JPanel();
    add(positionPanel);

    positionLaben = new JLabel("Position");
    positionPanel.add(positionLaben);

    missionTextField = new JTextField();
    positionPanel.add(missionTextField);
    missionTextField.setColumns(10);

    missionPanel = new JPanel();
    add(missionPanel);

    auftragLabel = new JLabel("Auftrag");
    missionPanel.add(auftragLabel);

    positionTextField = new JTextField();
    missionPanel.add(positionTextField);
    positionTextField.setColumns(10);

    timePanel = new JPanel();
    add(timePanel);

    zeitLabel = new JLabel("Zeit");
    timePanel.add(zeitLabel);

    worktimeTextField = new JTextField();
    timePanel.add(worktimeTextField);
    worktimeTextField.setColumns(5);

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

  public void showAsHeader() {
    deleteButton.setVisible(false);
    dateLabel.setVisible(true);
    positionLaben.setVisible(true);
    auftragLabel.setVisible(true);
    zeitLabel.setVisible(true);
  }

  public void showAsListEntry() {
    deleteButton.setVisible(true);
    dateLabel.setVisible(false);
    positionLaben.setVisible(false);
    auftragLabel.setVisible(false);
    zeitLabel.setVisible(false);
  }

  public void showDateError() {
    showErrorOnTextField(dateTextField);
  }

  public void showWorktimeError() {
    showErrorOnTextField(worktimeTextField);
  }

  public void showPositionError() {
    showErrorOnTextField(positionTextField);
  }

  public void showMissionError() {
    showErrorOnTextField(missionTextField);
  }

  private void showErrorOnTextField(final JTextField field) {
    field.setBackground(new Color(255, 0, 0));
    Timer timer = new Timer(1900, new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        field.setBackground(new Color(255, 255, 255));
      }
    });
    timer.setRepeats(false);
    timer.start();
  }

  public void showErrorOnPanel() {
    setBackground(new Color(255, 150, 150));
    datePanel.setBackground(new Color(255, 150, 150));
    positionPanel.setBackground(new Color(255, 150, 150));
    missionPanel.setBackground(new Color(255, 150, 150));
    timePanel.setBackground(new Color(255, 150, 150));

    hideStatusOnPanel();
  }

  public void showSuccess() {
    setBackground(new Color(150, 255, 150));
    datePanel.setBackground(new Color(150, 255, 150));
    positionPanel.setBackground(new Color(150, 255, 150));
    missionPanel.setBackground(new Color(150, 255, 150));
    timePanel.setBackground(new Color(150, 255, 150));

    hideStatusOnPanel();
  }

  private void hideStatusOnPanel() {
    Timer timer = new Timer(1900, new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        setBackground(backGroundColor);
        datePanel.setBackground(backGroundColor);
        positionPanel.setBackground(backGroundColor);
        missionPanel.setBackground(backGroundColor);
        timePanel.setBackground(backGroundColor);
      }
    });
    timer.setRepeats(false);
    timer.start();
  }

  public void cleanFields() {
    worktimeTextField.setText("");
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

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  // private void addGuiFeatureListener() {
  // KeyListener enterKeyListener = new KeyListener() {
  // public void keyTyped(KeyEvent arg0) {
  // }
  //
  // public void keyReleased(KeyEvent arg0) {
  // }
  //
  // public void keyPressed(KeyEvent arg0) {
  // if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
  // addButton.doClick();
  // }
  // }
  // };
  // }

  public boolean validateFields() {
    boolean valid = true;

    if (getDate().equals("")) {
      showDateError();
      valid = false;
    }
    if (getMission().equals("")) {
      showMissionError();
      valid = false;
    }
    if (getPosition().equals("")) {
      showPositionError();
      valid = false;
    }

    if (getWorktime().equals("")
        || (getWorktime().matches("[0-9]*[:,.]{1}[0-9]{2}") || getWorktime()
            .matches("[0-9]*")) == false) {
      showWorktimeError();
      valid = false;
    }

    if (!valid) {
      showErrorOnPanel();
    }

    return valid;
  }

}
