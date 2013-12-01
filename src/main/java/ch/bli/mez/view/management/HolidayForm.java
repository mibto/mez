package ch.bli.mez.view.management;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Timer;

import ch.bli.mez.view.DefaultForm;
import ch.bli.mez.view.DefaultPanel;

public class HolidayForm extends DefaultForm {

  private static final long serialVersionUID = -2823140194213618642L;

  private JTextField yearText;
  private JTextField publicHolidays;
  private JTextField preWorkdays;

  private JButton saveButton;

  private Color backGroundColor;
  DefaultPanel defaultPanel;

  public HolidayForm() {
    build();
    addGuiFeatureListener();
  }

  private void build() {

    setLayout(new FlowLayout(FlowLayout.LEFT));

    yearText = new JTextField(4);
    add(yearText);

    publicHolidays = new JTextField(2);
    add(publicHolidays);

    preWorkdays = new JTextField(2);
    add(preWorkdays);

    saveButton = new JButton("Speichern");
    add(saveButton);

    backGroundColor = getBackground();

    yearText.setEnabled(false);
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
    Timer timer = new Timer(1800, new ActionListener() {
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

  // getter & setter
  public String getYear() {
    return yearText.getText();
  }

  public String getPublicHolidays() {
    return publicHolidays.getText();
  }

  public String getPreWorkdays() {
    return preWorkdays.getText();
  }

  public void setYear(String value) {
    this.yearText.setText(value);
  }

  public void setPublicHolidays(String value) {
    publicHolidays.setText(value);
  }

  public void setPreWorkdays(String value) {
    preWorkdays.setText(value);
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

    yearText.addKeyListener(enterKeyListener);
    publicHolidays.addKeyListener(enterKeyListener);
    preWorkdays.addKeyListener(enterKeyListener);
  }

  public void setDefaultPanel(DefaultPanel defaultPanel) {
    this.defaultPanel = defaultPanel;
  }

  public DefaultPanel getDefaultPanel() {
    return defaultPanel;
  }

  public void cleanFields() {
    setYear("");
    setPublicHolidays("");
    setPreWorkdays("");
  }

  public void showAsCreateNew() {
    yearText.setEnabled(true);
  }

  public Boolean validateFields() {
    // TODO Auto-generated method stub

    if ("".equals(getYear()) || getYear().matches("[0-9]*") == false) {
      defaultPanel.showError("Das eingegebene Jahr ist nicht gültig, es dürfen nur Zahlen eingegeben werden");
      return false;
    }

    if ("".equals(getPublicHolidays()) || getPublicHolidays().matches("[0-9]*") == false) {
      defaultPanel.showError("Es müssen die Feiertage eingegeben werden. Es dürfen nur Zahlen eingegeben werden");
      return false;
    }

    if ("".equals(getPreWorkdays()) || getPreWorkdays().matches("[0-9]*") == false) {
      defaultPanel.showError("Es müssen die Vorholtage eingegeben werden. Es dürfen nur Zahlen eingegeben werden");
      return false;
    }

    return true;
  }
}
