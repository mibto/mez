package ch.bli.mez.view.employee;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ch.bli.mez.view.SearchPanel;

public class EmployeeView extends JPanel {

  private static final long serialVersionUID = 8767516928379563985L;

  private JTabbedPane tabbedPane;

  public EmployeeView() {
    this.tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
    this.setLayout(new BorderLayout());
    JPanel searchPanel = new SearchPanel();
    this.add(searchPanel, BorderLayout.NORTH);
    this.add(tabbedPane, BorderLayout.CENTER);
  }

  public void addTab(String name, JPanel employeePanel) {
    tabbedPane.addTab(name, employeePanel);
  }
  
  public void removeTab(int value) {
	    tabbedPane.remove(value);
	}
  
  public int getSelectedIndex() {
	  return tabbedPane.getSelectedIndex();
	}
  
}
