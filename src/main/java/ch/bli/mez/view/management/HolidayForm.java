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

import ch.bli.mez.model.Holiday;

public class HolidayForm extends JPanel {

  private static final long serialVersionUID = -2823140194213618642L;

  private JTextField yearTextField;
  private JTextField publicHolidaysTextField;
  private JTextField preWorkdaysTextField;

  private JButton saveButton;

  private KeyListener enterKeyListener;

  private Color backGroundColor;

  /**
   * Create the panel.
   */
  public HolidayForm() {

    setLayout(new FlowLayout(FlowLayout.LEFT));

    yearTextField = new JTextField(4);
    yearTextField.setEnabled(false);
    add(yearTextField);

    publicHolidaysTextField = new JTextField(2);
    add(publicHolidaysTextField);

    preWorkdaysTextField = new JTextField(2);
    add(preWorkdaysTextField);

    saveButton = new JButton("Speichern");
    add(saveButton);

    backGroundColor = getBackground();

    addGuiFeatureListener();
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

  public void setSaveListener(ActionListener actionListener) {
    saveButton.addActionListener(actionListener);
  }

  /**
   * @return the enterKeyListener which is used for the textfields
   */
  public KeyListener getEnterKeyListener() {
    return enterKeyListener;
  }

  // getter & setter
  public String getYear() {
    return yearTextField.getText();
  }

  public String getPublicHolidays() {
    return publicHolidaysTextField.getText();
  }

  public String getPreWorkdays() {
    return preWorkdaysTextField.getText();
  }

  public void setYear(String value) {
    this.yearTextField.setText(value);
  }

  public void setPublicHolidays(String value) {
    publicHolidaysTextField.setText(value);
  }

  public void setPreWorkdays(String value) {
    preWorkdaysTextField.setText(value);
  }

  // internal methods
  private void addGuiFeatureListener() {
    enterKeyListener = new KeyListener() {
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
    publicHolidaysTextField.addKeyListener(enterKeyListener);
    preWorkdaysTextField.addKeyListener(enterKeyListener);
  }

  public Boolean validateFields(Holiday holiday) {
    // TODO Auto-generated method stub
    return null;
  }
}