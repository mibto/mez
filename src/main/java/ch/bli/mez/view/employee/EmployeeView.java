package ch.bli.mez.view.employee;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class EmployeeView extends JPanel {

  private static final long serialVersionUID = 8767516928379563985L;

  private JTabbedPane tabbedPane;
  private EmployeeSearchPanel searchPanel;

  public EmployeeView() {
    this.tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
    build();
  }

  private void build() {
    this.setLayout(new BorderLayout());
    this.add(tabbedPane, BorderLayout.CENTER);
  }

  public void addTab(String name, EmployeePanel employeePanel) {
    tabbedPane.addTab(name, employeePanel);
  }

  public void removeTab(int value) {
    tabbedPane.remove(value);
  }

  public void removeAllTabs() {
    tabbedPane.removeAll();
  }

  public int getSelectedIndex() {
    return tabbedPane.getSelectedIndex();
  }

  public EmployeeSearchPanel getSearchPanel() {
    return this.searchPanel;
  }

  public void setSearchPanel(EmployeeSearchPanel searchPanel) {
    this.searchPanel = searchPanel;
    this.add(searchPanel, BorderLayout.NORTH);
  }

}
