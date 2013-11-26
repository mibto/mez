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

    addGuiFeatureListener();
  }

  public String getHolidays() {
    return holidaysTextField.getText();
  }

  public void setHolidays(String holidays) {
    this.holidaysTextField.setText(holidays);
  }

  // internal methods
  private void addGuiFeatureListener() {
    holidaysTextField.addKeyListener(getEnterKeyListener());
  }

  public Object validateFields() {
    // TODO Auto-generated method stub
    return null;
  }

}