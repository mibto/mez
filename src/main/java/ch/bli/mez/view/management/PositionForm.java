package ch.bli.mez.view.management;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ch.bli.mez.view.DefaultForm;

public class PositionForm extends DefaultForm {

  private static final long serialVersionUID = 7585160645657478969L;

  private JTextField positionCode;
  private JTextField positionName;
  private JTextField missionName;
  private JTextField comment;

  private JLabel missionLabel;

  private JButton saveButton;
  private JButton statusButton;

  public PositionForm(boolean isActive) {
    build();
    setActive(isActive);
    setEnterKeyListener(saveButton, new JTextField[] { positionCode, positionName, missionName, comment });
  }

  private void build() {
    setLayout(new FlowLayout(FlowLayout.LEFT));

    JLabel numberLabel = new JLabel("Position");
    this.add(numberLabel);
    positionCode = new JTextField();
    positionCode.setColumns(4);
    this.add(positionCode);

    JLabel nameLabel = new JLabel("Name");
    this.add(nameLabel);
    positionName = new JTextField();
    positionName.setColumns(10);
    this.add(positionName);

    missionLabel = new JLabel("Auftrag");
    this.add(missionLabel);
    missionName = new JTextField();
    missionName.setColumns(10);
    missionName.setEnabled(false);
    this.add(missionName);

    JLabel commentLabel = new JLabel("Kommentar");
    this.add(commentLabel);
    comment = new JTextField();
    comment.setColumns(15);
    this.add(comment);

    saveButton = new JButton("Speichern");
    this.add(saveButton);

    statusButton = new JButton();
    this.add(statusButton);
  }

  public void setActive(boolean status) {
    if (status) {
      statusButton.setText("Deaktivieren");
    } else {
      statusButton.setText("Aktivieren");
    }
    positionName.setEnabled(status);
    positionCode.setEnabled(status);
    comment.setEnabled(status);
    saveButton.setEnabled(status);
  }

  public void showAsCreateNew() {
    missionLabel.setVisible(false);
    missionName.setVisible(false);
    statusButton.setVisible(false);
  }

  public void cleanFields() {
    positionName.setText("");
    positionCode.setText("");
    comment.setText("");
  }

  // Getter & Setter
  public void setPositionCode(String code) {
    positionCode.setText(code);
  }

  public String getPositionCode() {
    return positionCode.getText();
  }

  public void setMissionName(String mission) {
    missionName.setText(mission);
  }

  public void setComment(String comment) {
    this.comment.setText(comment);
  }

  public String getComment() {
    return comment.getText();
  }

  public void setPositionName(String positionName) {
    this.positionName.setText(positionName);
  }

  public String getPositionName() {
    return positionName.getText();
  }

  // setListeners
  public void setSaveListener(ActionListener actionListener) {
    saveButton.addActionListener(actionListener);
  }

  public void setStatusButtonListener(ActionListener actionListener) {
    statusButton.addActionListener(actionListener);
  }

  public Boolean validateFields() {
    if ("".equals(getPositionCode())) {
      getParentPanel().showError("Es wurde keine Position eingegeben.");
      return false;
    }
    if ("".equals(getPositionName())) {
      getParentPanel().showError("Es wurde keine Name eingegeben.");
      return false;
    }
    return true;
  }
}
