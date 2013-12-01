package ch.bli.mez.view.employee;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ch.bli.mez.view.DefaultPanel;

public class EmployeePanel extends DefaultPanel{

  private static final long serialVersionUID = -7751432692594871588L;
  
  private JPanel holidayContentPanel;
  private JPanel contractPanel;

  public EmployeePanel() {
    createListPanel();
  }
  
  private void createListPanel(){
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

    // ContractPanel
    contractPanel = new JPanel(new BorderLayout());
    centerPanel.add(contractPanel);

    JPanel contractTitlePanel = new JPanel();
    contractPanel.add(new JScrollPane(contractTitlePanel), BorderLayout.NORTH);

    JLabel contractTitleLabel = new JLabel("Verträge");
    contractTitlePanel.add(contractTitleLabel);
  }
  
  public void setContractForm(ContractForm form) {
    contractPanel.add(new JScrollPane(form), BorderLayout.CENTER);
  }

  public void setEmployeeHolidayForm(EmployeeHolidayForm form) {
    holidayContentPanel.add(form);
  }
}
