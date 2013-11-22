package ch.bli.mez.view.time;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TimeEntryPanel extends JPanel {
  private static final long serialVersionUID = -1084526692534142942L;

  private JPanel timeEntryPanel;

  private JPanel listPanel;

  /**
   * Create the panel.
   */
  public TimeEntryPanel() {

    setLayout(new BorderLayout());

    // EntryPanel (north)
    JPanel northPanel = new JPanel();
    add(new JScrollPane(northPanel), BorderLayout.NORTH);

    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());
    northPanel.add(topPanel);

    timeEntryPanel = new JPanel();
    timeEntryPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    topPanel.add(timeEntryPanel, BorderLayout.SOUTH);

    // ListPanel (center)
    JPanel centerPanel = new JPanel();
    add(new JScrollPane(centerPanel), BorderLayout.CENTER);

    listPanel = new JPanel();
    listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
    centerPanel.add((listPanel));

  }

  /*
   * Setzt die obere eingabeListe
   */
  public void addHeadInput(TimeEntryForm timeEntryForm) {
    timeEntryForm.showAsHeader();
    timeEntryPanel.add(timeEntryForm);
  }

  public void addAdditionalTimeListEntry(TimeEntryForm timeEntryForm) {
    //timeListEntry.showAsListEntry();
    listPanel.add(timeEntryForm);
    listPanel.repaint();
  }

  public void removeTimeListEntry(TimeEntryForm timeEntryForm) {
    listPanel.remove(timeEntryForm);
  }

  /*
   * Listet alle Zeiten auf
   */
  public void addTimeListEntrys(List<TimeEntryForm> entrys) {
    for (TimeEntryForm entry : entrys) {
      addAdditionalTimeListEntry(entry);
    }
  }

  public void addInputTimeListEntry() {
    // TODO Auto-generated method stub
    
  }
  
  

}
