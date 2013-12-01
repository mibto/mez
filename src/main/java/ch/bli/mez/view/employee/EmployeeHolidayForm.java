package ch.bli.mez.view.employee;

import javax.swing.JTextField;

import ch.bli.mez.view.management.HolidayForm;

/**
 * @author dave
 * @version 1.0
 */
public class EmployeeHolidayForm extends HolidayForm {

  private static final long serialVersionUID = -3988811507879670389L;

  private JTextField holidaysTextField;

  public EmployeeHolidayForm() {

    holidaysTextField = new JTextField(3);
    super.add(holidaysTextField, 1);

    setEnterKeyListener(new JTextField[] { holidaysTextField });
  }

  public String getHolidays() {
    return holidaysTextField.getText();
  }

  public void setHolidays(String holidays) {
    this.holidaysTextField.setText(holidays);
  }

  @Override
  public Boolean validateFields() {
    if ("".equals(getHolidays())) {
      getParentPanel().showError("Es wurden keine Ferien eingegeben");
      return false;
    } else {
      try {
        Integer.valueOf(getHolidays());
      } catch (NumberFormatException e) {
        getParentPanel().showError("Das Format der eingegebenen Ferien ist nicht gültig");
        return false;
      }
    }
    return super.validateFields();
  }

}
