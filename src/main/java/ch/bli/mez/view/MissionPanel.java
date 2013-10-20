package ch.bli.mez.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;

public class MissionPanel extends JPanel {

  private static final long serialVersionUID = -7537968850748849818L;

  private JTextField missionName;
  private JTextField comment;

  private JLabel confirmation;
  private JLabel nameError;

  private JButton btnAdd;
  private JButton btnClear;

  private JLayeredPane layeredPane = new JLayeredPane();
  private JScrollPane scrollPane;
  private JPanel missionListEntryContainer;


  public MissionPanel() {
    setLayout(new CardLayout(0, 0));

    add(layeredPane, "name_25118593990762");

    btnAdd = new JButton("Hinzufügen");
    btnAdd.setBounds(483, 72, 140, 30);
    layeredPane.add(btnAdd);

    missionName = new JTextField();
    missionName.setBounds(21, 73, 213, 30);
    layeredPane.add(missionName);
    missionName.setColumns(10);

    JLabel lblMissionName = new JLabel("Auftragsname:");
    lblMissionName.setBounds(21, 28, 109, 23);
    layeredPane.add(lblMissionName);

    JLabel lblComment = new JLabel("Kommentar:");
    lblComment.setBounds(252, 28, 109, 23);
    layeredPane.add(lblComment);

    comment = new JTextField();
    comment.setColumns(10);
    comment.setBounds(252, 73, 213, 30);
    layeredPane.add(comment);

    btnClear = new JButton("Leeren");
    btnClear.setBounds(642, 72, 92, 30);
    layeredPane.add(btnClear);

    confirmation = new JLabel("xxxyyy wurde zur Liste hinzugefügt!");
    confirmation.setForeground(new Color(0, 128, 0));
    confirmation.setBounds(21, 114, 352, 23);
    confirmation.setVisible(false);
    layeredPane.add(confirmation);

    nameError = new JLabel("Auftragsname darf nicht leer sein");
    nameError.setForeground(new Color(255, 0, 0));
    nameError.setBounds(21, 48, 213, 14);
    nameError.setVisible(false);
    layeredPane.add(nameError);
    
    missionListEntryContainer = new JPanel();
    missionListEntryContainer.setLayout(new BoxLayout(missionListEntryContainer, BoxLayout.Y_AXIS));

    scrollPane = new JScrollPane(missionListEntryContainer);
    scrollPane.setBounds(21, 182, 780, 190);
    layeredPane.add(scrollPane);

  }

  public void setSaveMissionListener(ActionListener actionListener) {
    btnAdd.addActionListener(actionListener);
  }

  public void setClearMissionListener(ActionListener actionListener) {
    btnClear.addActionListener(actionListener);
  }

  public void showConfirmation(String name) {
    confirmation.setText(name + " wurde zur Liste hinzugefügt!");
    confirmation.setVisible(true);
  }

  public void hideConfirmation() {
    confirmation.setVisible(false);
  }

  public void cleanFields() {
    missionName.setBackground(new Color(255, 255, 255));
    setName("");
    setComment("");
  }

  public void hideNameError() {
    nameError.setVisible(false);
  }

  public void showNameError() {
    nameError.setVisible(true);
    missionName.setBackground(new Color(255, 90, 90));
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

  public void addMissionListEntry(MissionListEntry missionListEntry) {
    missionListEntryContainer.add(missionListEntry);
    scrollPane.revalidate();
    scrollPane.repaint();
  }

  public void removeMissionListEntry(MissionListEntry missionListEntry) {
    missionListEntryContainer.remove(missionListEntry);
  }

  public boolean getIsOrgan() {
    return false;
  }
}
