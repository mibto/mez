package ch.bli.mez.view.employee;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ch.bli.mez.view.DefaultForm;
import ch.bli.mez.view.DefaultPanel;

public class EmployeePanel extends DefaultPanel {

  private static final long serialVersionUID = -7751432692594871588L;

  private JPanel contractPanel;
  private JPanel holidayPanel;
  private DefaultPanel currentHolidayPanel;

  public EmployeePanel() {
    createListPanel();
  }

  private void createListPanel() {
    JPanel centerPanel = new JPanel(new GridLayout(1, 0));
    add(centerPanel, BorderLayout.CENTER);

    // HolidayPanel
    holidayPanel = new JPanel(new BorderLayout());
    centerPanel.add(holidayPanel);

    JPanel holidayTitlePanel = new JPanel();
    holidayTitlePanel.add(new JLabel("Ferien"));
    holidayPanel.add(new JScrollPane(holidayTitlePanel), BorderLayout.NORTH);

    // ContractPanel
    contractPanel = new JPanel(new BorderLayout());
    centerPanel.add(contractPanel);

    JPanel contractTitlePanel = new JPanel();
    contractPanel.add(new JScrollPane(contractTitlePanel), BorderLayout.NORTH);

    JLabel contractTitleLabel = new JLabel("Verträge");
    contractTitlePanel.add(contractTitleLabel);
    
    holidayPanel.setVisible(false);
    contractPanel.setVisible(false);
  }

  public void setContractPanel(DefaultPanel panel) {
    contractPanel.setVisible(true);
    contractPanel.add(panel, BorderLayout.CENTER);
  }

  public void setHolidayPanel(DefaultPanel panel) {
    currentHolidayPanel = panel;
    holidayPanel.setVisible(true);
    holidayPanel.add(panel, BorderLayout.CENTER);
  }
  
  public void removeHolidayPanel(){
    holidayPanel.remove(currentHolidayPanel);
  }

  @Override
  public void addForm(DefaultForm form) {
    getTopPanel().add(form, BorderLayout.CENTER);
    form.setParentPanel(this);
  }
}
