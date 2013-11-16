package ch.bli.mez.view.time;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TimePanel extends JPanel {
  private static final long serialVersionUID = -1084526692534142942L;

  private JPanel timeEntryPanel;

  private JPanel listPanel;

  /**
   * Create the panel.
   */
  public TimePanel() {

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
  public void addHeadInput(TimeListEntry timeListEntry) {
    timeListEntry.showAsHeader();
    timeEntryPanel.add(timeListEntry);
    timeEntryPanel.revalidate();
    timeEntryPanel.repaint();
  }

  public void addAdditionalTimeListEntry(TimeListEntry timeListEntry) {
    timeListEntry.showAsListEntry();
    listPanel.add(timeListEntry);
    listPanel.revalidate();
    listPanel.repaint();
  }

  // private void removeMissionListEntry(TimeListEntry timeListEntry) {
  // listPanel.remove(timeListEntry);
  // listPanel.revalidate();
  // listPanel.repaint();
  // }

  /*
   * Listet alle Zeiten auf
   */
  public void addTimeListEntrys(List<TimeListEntry> entrys) {
    for (TimeListEntry entry : entrys) {
      addAdditionalTimeListEntry(entry);
    }
  }

}
