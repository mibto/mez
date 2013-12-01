package ch.bli.mez.view.management;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ch.bli.mez.view.DefaultForm;

public class HolidayForm extends DefaultForm {

  private static final long serialVersionUID = -2823140194213618642L;

  private JTextField year;
  private JTextField publicHolidays;
  private JTextField preWorkdays;

  private JLabel yearLabel;
  private JLabel publicHolidaysLabel;
  private JLabel preWorkdaysLabel;

  private JButton saveButton;

  public HolidayForm() {
    build();
    setEnterKeyListener(new JTextField[] { year, publicHolidays, preWorkdays });
  }

  private void build() {
    setLayout(new FlowLayout(FlowLayout.LEFT));

    yearLabel = new JLabel("Jahr");
    add(yearLabel);

    year = new JTextField(4);
    add(year);

    publicHolidaysLabel = new JLabel("Feiertage");
    add(publicHolidaysLabel);

    publicHolidays = new JTextField(2);
    add(publicHolidays);

    preWorkdaysLabel = new JLabel("Vorholtage");
    add(preWorkdaysLabel);

    preWorkdays = new JTextField(2);
    add(preWorkdays);

    saveButton = new JButton("Speichern");
    add(saveButton);

    showAsCreateNew(false);
  }

  public void setSaveListener(ActionListener actionListener) {
    saveButton.addActionListener(actionListener);
  }

  @Override
  public void cleanFields() {
    setYear("");
    setPublicHolidays("");
    setPreWorkdays("");
  }

  @Override
  public void showAsCreateNew() {
    showAsCreateNew(true);
  }

  private void showAsCreateNew(boolean createNew) {
    year.setEnabled(createNew);
    yearLabel.setVisible(createNew);
    publicHolidaysLabel.setVisible(createNew);
    preWorkdaysLabel.setVisible(createNew);
  }

  @Override
  public Boolean validateFields() {
    if ("".equals(getYear())) {
      getParentPanel().showError("Es wurde kein Jahr eingegeben");
      return false;
    } else {
      try {
        Integer.valueOf(getYear());
      } catch (NumberFormatException e) {
        getParentPanel().showError("Das Format des eingegebenen Jahr ist nicht gültig");
        return false;
      }
    }
    if ("".equals(getPublicHolidays())){
      getParentPanel().showError("Es wurden keine Feiertage eingegeben");
      return false;
    } else {
      try {
        Integer.valueOf(getPublicHolidays());
      } catch (NumberFormatException e){
      getParentPanel().showError("Das Format der eingegebenen Feiertage ist nicht gültig");
      return false;
      }
    }
    if ("".equals(getPreWorkdays())){
      getParentPanel().showError("Es wurden keine Vorholtage eingegeben");
      return false;
    } else {
      try {
        Integer.valueOf(getPreWorkdays());
      } catch (NumberFormatException e){
      getParentPanel().showError("Das Format der eingegebenen Vorholtage ist nicht gültig");
      return false;
      }
    }
    return true;
  }

  protected void setEnterKeyListener(JTextField... textFields) {
    setEnterKeyListener(this.saveButton, textFields);
  }

  // getter & setter
  public String getYear() {
    return year.getText();
  }

  public String getPublicHolidays() {
    return publicHolidays.getText();
  }

  public String getPreWorkdays() {
    return preWorkdays.getText();
  }

  public void setYear(String value) {
    this.year.setText(value);
  }

  public void setPublicHolidays(String value) {
    publicHolidays.setText(value);
  }

  public void setPreWorkdays(String value) {
    preWorkdays.setText(value);
  }
}
