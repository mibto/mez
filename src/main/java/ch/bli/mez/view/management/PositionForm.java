package ch.bli.mez.view.management;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PositionForm extends JPanel {

  private static final long serialVersionUID = 7585160645657478969L;

  private JTextField code;
  private JTextField missionName;
  private JTextField positionName;
  private JTextField comment;

  private JButton saveButton;
  private JButton statusButton;

  public PositionForm(boolean isActive) {
    build();
    setActive(isActive);
    addGuiFeatureListener();
  }
  
  private void build(){
    setLayout(new FlowLayout(FlowLayout.LEFT));
    JLabel numberLabel = new JLabel("Position");
    this.add(numberLabel);

    code = new JTextField();
    code.setColumns(4);
    this.add(code);

    JLabel nameLabel = new JLabel("Name");
    this.add(nameLabel);

    positionName = new JTextField();
    positionName.setColumns(10);
    this.add(positionName);

    JLabel commentLabel = new JLabel("Kommentar");
    this.add(commentLabel);

    comment = new JTextField();
    comment.setColumns(25);
    this.add(comment);

    saveButton = new JButton("Speichern");
    this.add(saveButton);
  }

  public void setActive(boolean status) {
    if (status) {
      statusButton.setText("Deaktivieren");
    } else {
      statusButton.setText("Aktivieren");
    }
    code.setEnabled(status);
    positionName.setEnabled(status);
    comment.setEnabled(status);
    saveButton.setEnabled(status);
  }
  
  public void cleanFields() {
    code.setText("");
    positionName.setText("");
    missionName.setText("");
    comment.setText("");
  }

  // Getter & Setter
  public void setCode(String code) {
    this.code.setText(code);
  }

  public String getCode() {
    return code.getText();
  }

  public void setPositionName(String name) {
    positionName.setText(name);
  }
  
  public String getPositionName(){
    return positionName.getText();
  }

  public void setComment(String comment) {
    this.comment.setText(comment);
  }
  
  public String getComment() {
    return comment.getText();
  }

  public void setMissionName(String mission) {
    missionName.setText(mission);
  }
  
  public String getMissionName(){
    return positionName.getText();
  }

  // setListeners
  public void setSaveListener(ActionListener actionListener) {
    saveButton.addActionListener(actionListener);
  }

  public void setStatusButtonListener(ActionListener actionListener) {
    statusButton.addActionListener(actionListener);
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
    code.addKeyListener(enterKeyListener);
    positionName.addKeyListener(enterKeyListener);
    comment.addKeyListener(enterKeyListener);
  }

  public Boolean validateFields() {
    return true;
  }
}
