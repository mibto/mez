package ch.bli.mez.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class EmployeeView extends JPanel {

  private static final long serialVersionUID = 8767516928379563985L;

  private JTabbedPane tabbedPane;

  public EmployeeView(SearchPanel searchPanel) {

    this.tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

    this.setLayout(new BorderLayout());
    this.add(searchPanel, BorderLayout.NORTH);
    this.add(tabbedPane, BorderLayout.CENTER);

  }

  public void addTab(String name, JPanel employeePanel) {
    tabbedPane.addTab(name, employeePanel);
  }
}
