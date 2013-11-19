package ch.bli.mez.view.management;

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

public class PositionListEntry extends JPanel {

  private static final long serialVersionUID = 7585160645657478969L;

  private JTextField codeTextField;
  private JTextField nameTextField;
  private JTextField commentTextField;
  private JTextField missionTextField;

  private JButton saveButton;
  private JButton statusButton;

  private Color backGroundColor;

  public PositionListEntry(boolean isActive) {

    setLayout(new FlowLayout(FlowLayout.LEFT));

    codeTextField = new JTextField();
    codeTextField.setColumns(4);
    add(codeTextField);

    nameTextField = new JTextField();
    nameTextField.setColumns(10);
    add(nameTextField);

    commentTextField = new JTextField();
    commentTextField.setColumns(25);
    add(commentTextField);

    missionTextField = new JTextField();
    missionTextField.setColumns(10);
    missionTextField.setEnabled(false);
    add(missionTextField);

    saveButton = new JButton("Speichern");
    add(saveButton);

    statusButton = new JButton();
    add(statusButton);

    backGroundColor = getBackground();
    setActive(isActive);
    addGuiFeatureListener();
  }

  public void setActive(boolean status) {
    if (status) {
      statusButton.setText("Deaktivieren");
    } else {
      statusButton.setText("Aktivieren");
    }
    codeTextField.setEnabled(status);
    nameTextField.setEnabled(status);
    commentTextField.setEnabled(status);
    saveButton.setEnabled(status);
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

  // Getter & Setter
  public void setCode(String code) {
    codeTextField.setText(code);
  }

  public String getCode() {
    return codeTextField.getText();
  }

  public void setPositionName(String name) {
    nameTextField.setText(name);
  }

  public void setComment(String comment) {
    commentTextField.setText(comment);
  }

  public void setMission(String mission) {
    missionTextField.setText(mission);
  }

  public String getPositionName() {
    return nameTextField.getText();
  }

  public String getComment() {
    return commentTextField.getText();
  }

  // setListeners
  public void setSaveButtonListener(ActionListener actionListener) {
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
    codeTextField.addKeyListener(enterKeyListener);
    nameTextField.addKeyListener(enterKeyListener);
    commentTextField.addKeyListener(enterKeyListener);
  }

  public Boolean validateFields() {
    return null;
  }

  public Object getSelectedMission() {
    // TODO Auto-generated method stub
    return null;
  }
}
