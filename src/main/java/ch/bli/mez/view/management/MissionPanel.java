package ch.bli.mez.view.management;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class MissionPanel extends JPanel {

  private static final long serialVersionUID = -7537968850748849818L;

  private JTextField missionNameTextField;
  private JTextField commentTextField;
  private JCheckBox isOrganCheckBox;

  private JPanel messagePanel;
  private JLabel messageLabel;

  private JButton addButton;

  private JPanel listPanel;

  public MissionPanel() {

    setLayout(new BorderLayout());

    // EntryPanel (north)
    JPanel northPanel = new JPanel();
    add(new JScrollPane(northPanel), BorderLayout.NORTH);

    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());
    northPanel.add(topPanel);

    JPanel entryPanel = new JPanel();
    topPanel.add(entryPanel, BorderLayout.CENTER);

    JLabel nameLabel = new JLabel("Auftragsname");
    entryPanel.add(nameLabel);

    missionNameTextField = new JTextField();
    missionNameTextField.setColumns(10);
    entryPanel.add(missionNameTextField);

    JLabel commentLabel = new JLabel("Kommentar");
    entryPanel.add(commentLabel);

    commentTextField = new JTextField();
    commentTextField.setColumns(25);
    entryPanel.add(commentTextField);

    isOrganCheckBox = new JCheckBox("Orgel-Code", true);
    entryPanel.add(isOrganCheckBox);

    addButton = new JButton("Speichern");
    entryPanel.add(addButton);

    messagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
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

  public void setSaveMissionListener(ActionListener actionListener) {
    addButton.addActionListener(actionListener);
  }

  public void cleanFields() {
    missionNameTextField.setBackground(new Color(255, 255, 255));
    setMissionName("");
    setComment("");
  }

  public void showConfirmation(String name) {
    messageLabel.setForeground(new Color(0, 128, 0));
    messageLabel.setText(name + " wurde zur Liste hinzugefügt!");
    hideMessage();
  }

  public void showNameError() {
    messageLabel.setForeground(new Color(255, 0, 0));
    messageLabel.setText("Auftragsname darf nicht leer sein");
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

  public String getMissionName() {
    return missionNameTextField.getText();
  }

  public String getComment() {
    return commentTextField.getText();
  }

  public void setMissionName(String missionName) {
    this.missionNameTextField.setText(missionName);
  }

  public void setComment(String value) {
    commentTextField.setText(value);
  }

  public void addMissionListEntry(MissionForm missionForm) {
    listPanel.add(missionForm);
    listPanel.revalidate();
  }

  public void removeMissionListEntry(MissionForm missionForm) {
    listPanel.remove(missionForm);
    listPanel.revalidate();
  }

  public boolean getIsOrgan() {
    if (isOrganCheckBox.isSelected()) {
      return true;
    }
    return false;
  }

  private void addGuiFeatureListener() {
    addButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        cleanFields();
        isOrganCheckBox.setSelected(true);
      }
    });
    KeyListener enterKeyListener = new KeyListener() {
      public void keyTyped(KeyEvent e) {
      }

      public void keyReleased(KeyEvent e) {
      }

      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          addButton.doClick();
        }
      }
    };
    missionNameTextField.addKeyListener(enterKeyListener);
    commentTextField.addKeyListener(enterKeyListener);
  }
}
