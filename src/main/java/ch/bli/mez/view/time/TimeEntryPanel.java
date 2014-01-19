package ch.bli.mez.view.time;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ch.bli.mez.model.Employee;
import ch.bli.mez.util.Parser;
import ch.bli.mez.view.DefaultPanel;

public class TimeEntryPanel extends DefaultPanel {
  
  private static final long serialVersionUID = -1084526692534142942L;
  
  private Employee employee;
  private JPanel weekSummaryPanel;
  private boolean prepared;
  
  public TimeEntryPanel(Employee employee){
    this.employee = employee;
    build();
  }
  
  private void build(){
    weekSummaryPanel = new JPanel();
    setListTitlePanel(weekSummaryPanel);
  }
  
  public void setWeekSummaryPanel(JPanel panel){
    weekSummaryPanel.add(panel);
  }
  
  public Employee getEmployee(){
    return this.employee;
  }

  public static Boolean showDeleteWarning(TimeEntryForm form) {
    Object[] options = { "Ja", "Nein" };
    int choice = JOptionPane.showOptionDialog(form, "Zeiteintrag wirklich löschen?\n\n Datum: "
        + form.getDate() + "\n Auftrag: " + form.getMissionName() + "\n Position: " + form.getPositionCode() + "\n Zeit: "
        + Parser.parseMinutesIntegerToString(form.getWorktime()), "Löschen bestätigen", JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE, null, options, options[1]);
    if (choice == JOptionPane.YES_OPTION) {
      return true;
    } else {
      return false;
    }
  }

  public boolean getIsPrepared() {
    return prepared;
  }

  public void setIsPrepared(boolean prepared) {
    this.prepared = prepared;
  }
}
