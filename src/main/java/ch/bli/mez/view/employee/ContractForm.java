package ch.bli.mez.view.employee;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import ch.bli.mez.util.Parser;
import ch.bli.mez.view.DefaultForm;

/**
 * 
 * @author dave
 * @version 1.0
 */
public class ContractForm extends DefaultForm {

  private static final long serialVersionUID = -4040659975448618036L;

  private JTextField workquotaTextField;
  private JTextField startDateTextField;
  private JTextField endDateTextField;

  private JLabel workquotaLabel;
  private JLabel startDateLabel;
  private JLabel endDateLabel;

  private JButton saveButton;

  public ContractForm() {
    build();
    setEnterKeyListener(saveButton, new JTextField[] {workquotaTextField, startDateTextField, endDateTextField});
  }

  private void build(){
    setLayout(new FlowLayout(FlowLayout.LEFT));

    workquotaLabel = new JLabel("Pensum");
    add(workquotaLabel);

    workquotaTextField = new JTextField(3);
    add(workquotaTextField);

    startDateLabel = new JLabel("Start");
    add(startDateLabel);

    startDateTextField = new JTextField(7);
    add(startDateTextField);

    endDateLabel = new JLabel("Ende");
    add(endDateLabel);

    endDateTextField = new JTextField(7);
    add(endDateTextField);

    saveButton = new JButton("Speichern");
    add(saveButton);
    
    showAsCreateNew(false);
  }

  @Override
  public void cleanFields() {
    setWorkquota("");
    setStartDate("");
    setEndDate("");
  }

  public void setSaveListener(ActionListener actionListener) {
    saveButton.addActionListener(actionListener);
  }

  @Override
  public Boolean validateFields() {
    if ("".equals(getWorkquota())){
      getParentPanel().showError("Es wurde kein Pensum eingegeben");
      return false;
    } else {
      try {
        Integer.valueOf(getWorkquota());
      } catch (NumberFormatException e){
      getParentPanel().showError("Das Format des eingegebenen Pensum ist nicht gültig");
      return false;
      }
    }
    if ("".equals(getStartDate())){
      getParentPanel().showError("Es wurde kein Startdatum eingegeben");
      return false;
    } else {
      try {
        Parser.parseDateStringToCalendar(getStartDate());
      } catch (NumberFormatException e){
      getParentPanel().showError("Das Format des eingegebenen Startdatum ist nicht gültig");
      return false;
      }
    }
    if (!"".equals(getEndDate())){
      try {
        Parser.parseDateStringToCalendar(getEndDate());
      } catch (NumberFormatException e){
      getParentPanel().showError("Das Format des eingegebenen Enddatum ist nicht gültig");
      return false;
      }
    }
    return true;
  }

  @Override
  public void showAsCreateNew() {
    showAsCreateNew(true);
  }
  
  private void showAsCreateNew(boolean createNew){
    workquotaLabel.setVisible(createNew);
    startDateLabel.setVisible(createNew);
    endDateLabel.setVisible(createNew);
  }

  // getter & setter
  public String getWorkquota() {
    return workquotaTextField.getText();
  }

  public void setWorkquota(String workquota) {
    this.workquotaTextField.setText(workquota);
  }

  public String getStartDate() {
    return startDateTextField.getText();
  }

  public void setStartDate(String startDate) {
    this.startDateTextField.setText(startDate);
  }

  public String getEndDate() {
    return endDateTextField.getText();
  }

  public void setEndDate(String endDate) {
    this.endDateTextField.setText(endDate);
  }
}
