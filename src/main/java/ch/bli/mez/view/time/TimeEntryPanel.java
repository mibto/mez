package ch.bli.mez.view.time;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ch.bli.mez.util.Worktime;

public class TimeEntryPanel extends JPanel {
  
  private static final long serialVersionUID = -1084526692534142942L;
  private JPanel timeEntryPanel;
  private JPanel listPanel;
  private JPanel messagePanel;
  private JLabel messageLabel;

  public TimeEntryPanel(Integer employeeId) {
    build();
  }
  
  private void build(){
    setLayout(new BorderLayout());

    // EntryPanel (north)
    JPanel northPanel = new JPanel();
    add(new JScrollPane(northPanel), BorderLayout.NORTH);

    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());
    northPanel.add(topPanel);
    
    messagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topPanel.add(messagePanel, BorderLayout.SOUTH);

    messageLabel = new JLabel(" ");
    messagePanel.add(messageLabel);

    timeEntryPanel = new JPanel();
    timeEntryPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    topPanel.add(timeEntryPanel, BorderLayout.NORTH);

    // ListPanel (center)
    JPanel centerPanel = new JPanel();
    add(new JScrollPane(centerPanel), BorderLayout.CENTER);

    listPanel = new JPanel();
    listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
    centerPanel.add((listPanel));
  }

  public void setNewTimeEntryForm(TimeEntryForm form) {
    form.showAsCreateNew();
    form.setTimeEntryPanel(this);
    timeEntryPanel.add(form);
  }

  public void addTimeEntryForm(TimeEntryForm form) {
    form.setTimeEntryPanel(this);
    listPanel.add(form);
    listPanel.revalidate();
    listPanel.repaint();
  }

  public void removeTimeListEntry(TimeEntryForm form) {
    listPanel.remove(form);
  }
  
  public void showError(String message){
    messageLabel.setForeground(Color.RED);
    setMessageLabelText(message);
  }
  
  public void showSuccess(String message){
    messageLabel.setForeground(new Color(0, 128, 0));
    setMessageLabelText(message);
  }
  
  public void setMessageLabelText(String message){
    messageLabel.setText(message);
  }

  public static Boolean showDeleteWarning(TimeEntryForm form) {
    Object[] options = { "Ja", "Nein" };
    int choice = JOptionPane.showOptionDialog(form, "Zeiteintrag wirklich löschen?\n\n Datum: "
        + Worktime.parseCalendar(form.getDate()) + "\n Auftrag: " + form.getMissionName() + "\n Position: " + form.getPositionCode() + "\n Zeit: "
        + Worktime.parseMinutesToWorkTime(form.getWorktime()), "Löschen bestätigen", JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE, null, options, options[1]);

    if (choice == JOptionPane.YES_OPTION) {
      return true;
    } else {
      return false;
    }
  }
}
