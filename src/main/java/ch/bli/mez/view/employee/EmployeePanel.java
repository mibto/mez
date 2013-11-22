package ch.bli.mez.view.employee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

public class EmployeePanel extends JPanel{

  private static final long serialVersionUID = 1L;
  private JPanel topPanel;
  private JLabel messageLabel;
  private JPanel holidayContentPanel;
  private JPanel contractPanel;

  public EmployeePanel() {
    build();
  }
  
  private void build(){
    setLayout(new BorderLayout());

    JPanel northPanel = new JPanel();
    add(new JScrollPane(northPanel), BorderLayout.NORTH);

    topPanel = new JPanel(new BorderLayout());
    northPanel.add(topPanel);
    
    JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topPanel.add(messagePanel, BorderLayout.SOUTH);
    
    messageLabel = new JLabel(" ");
    messagePanel.add(messageLabel);
    
    JPanel centerPanel = new JPanel(new GridLayout(1, 0));
    add(centerPanel, BorderLayout.CENTER);

    // HolidayPanel
    JPanel centerLeftPanel = new JPanel(new BorderLayout());
    centerPanel.add(centerLeftPanel);

    JPanel holidayTitlePanel = new JPanel();
    centerLeftPanel.add(new JScrollPane(holidayTitlePanel), BorderLayout.NORTH);

    JLabel holidayTitleLabel = new JLabel("Ferien");
    holidayTitlePanel.add(holidayTitleLabel);

    JPanel holidayHelpPanel = new JPanel();
    centerLeftPanel.add(new JScrollPane(holidayHelpPanel), BorderLayout.CENTER);

    holidayContentPanel = new JPanel();
    holidayContentPanel.setLayout(new BoxLayout(holidayContentPanel, BoxLayout.Y_AXIS));
    holidayHelpPanel.add(holidayContentPanel);

    // ContractPanel (center)
    contractPanel = new JPanel(new BorderLayout());
    centerPanel.add(contractPanel);

    JPanel contractTitlePanel = new JPanel();
    contractPanel.add(new JScrollPane(contractTitlePanel), BorderLayout.NORTH);

    JLabel contractTitleLabel = new JLabel("Verträge");
    contractTitlePanel.add(contractTitleLabel);
    
  }

  public void setEmployeeForm(EmployeeForm employeeForm){
    topPanel.add(employeeForm, BorderLayout.CENTER);
  }
  
  public void setContractForm(ContractForm contractForm) {
    contractPanel.add(new JScrollPane(contractForm), BorderLayout.CENTER);
  }

  public void setEmployeeHolidayForm(EmployeeHolidayForm employeeHolidayForm) {
    holidayContentPanel.add(employeeHolidayForm);
  }
  
  public void showConfirmation(String name) {
    messageLabel.setForeground(new Color(0, 128, 0));
    messageLabel.setText(name + " wurde gespeichert.");
    hideMessage();
  }

  /**
   * 
   * @param errorFieldName
   *          fehlerhaftes Feld. Fehlermeldung wird generisch mit dem Feldname
   *          erstellt: "'errorFieldName' ist nicht gültig!"
   */
  public void showError(String errorFieldName) {
    messageLabel.setForeground(new Color(255, 0, 0));
    messageLabel.setText(errorFieldName + " ist nicht gültig!");
    hideMessage();
  }

  private void hideMessage() {
    Timer timer = new Timer(1800, new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        messageLabel.setText(" ");
      }
    });
    timer.setRepeats(false);
    timer.start();
  }
  
}
