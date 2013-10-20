package ch.bli.mez.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class MissionListEntry extends JPanel {

  private static final long serialVersionUID = -2823140194213618642L;

  private JTextField missionName;
  private JTextField comment;

  private JButton btnSave;
  private JButton btnDelete;

  /**
   * Create the panel.
   */
  public MissionListEntry() {
    setPreferredSize(new Dimension(0,40));
    setLayout(null);

    missionName = new JTextField();
    missionName.setBounds(12, 12, 216, 30);
    add(missionName);
    missionName.setColumns(10);

    comment = new JTextField();
    comment.setBounds(249, 12, 216, 30);
    add(comment);
    comment.setColumns(10);

    btnSave = new JButton("Speichern");
    btnSave.setBounds(483, 12, 120, 30);
    add(btnSave);

    btnDelete = new JButton("Löschen");
    btnDelete.setBounds(612, 12, 120, 30);
    add(btnDelete);

  }

  private void showError() {
    setBackground(new Color(255, 150, 150));
  }

  /**
   * Färbt den hintergrund Grün. Verschwindet nach ca. 2Sek.
   */
  private void showSuccess() {
    final Color originalcolor = getBackground();
    setBackground(new Color(150, 255, 150));

    // SWING Timer! jeeehaaa
    int delay = 1800;
    ActionListener taskPerformer = new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        setBackground(originalcolor);
      }
    };
    Timer timer = new Timer(delay, taskPerformer);
    timer.setRepeats(false);
    timer.start();

  }

  public void showNameError() {
    this.missionName.setBackground(new Color(255, 90, 90));
    showError();
  }

  public void hideNameError() {
    this.missionName.setBackground(new Color(255, 255, 255));
    showSuccess();
  }

  public void setDeleteButtonName(String value) {
    btnDelete.setName(value);
  }

  public String getDeleteButtonName() {
    return btnDelete.getName();
  }

  public void setSaveMissionEntryListListener(ActionListener actionListener) {
    btnSave.addActionListener(actionListener);
  }

  public void setDeleteMissionEntryListListener(ActionListener actionListener) {
    btnDelete.addActionListener(actionListener);
  }

  public JTextField getTextField_Name() {
    return this.comment;
  }

  public JTextField getTextField_Comment() {
    return this.missionName;
  }

  public String getMissionName() {
    return missionName.getText();
  }

  public String getComment() {
    return comment.getText();
  }

  public void setMissionName(String missionName) {
    this.missionName.setText(missionName);
  }

  public void setComment(String value) {
    comment.setText(value);
  }
}
