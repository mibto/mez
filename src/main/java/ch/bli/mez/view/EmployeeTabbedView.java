package ch.bli.mez.view;

import java.awt.BorderLayout;

import ch.bli.mez.view.employee.EmployeeSearchPanel;

public class EmployeeTabbedView extends DefaultTabbedView {

  private static final long serialVersionUID = 1L;

  public EmployeeTabbedView() {
  }
  
  public void setEmployeeSearchPanel(EmployeeSearchPanel searchPanel) {
    this.add(searchPanel, BorderLayout.NORTH);
  }
}
