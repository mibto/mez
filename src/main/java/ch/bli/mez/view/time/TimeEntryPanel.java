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


  public TimeEntryPanel() {
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
  public void setNewTimeEntryForm(TimeEntryForm form) {
    form.showAsHeader();
    timeEntryPanel.add(form);
  }

  public void addTimeEntryForm(TimeEntryForm form) {
    listPanel.add(form);
    listPanel.repaint();
  }

  public void removeTimeListEntry(TimeEntryForm form) {
    listPanel.remove(form);
  }
  
  public void showDateError() {
    showErrorOnTextField(dateTextField);
  }

  public void showWorktimeError() {
    showErrorOnTextField(worktimeTextField);
  }

  public void showPositionError() {
    showErrorOnTextField(positionTextField);
  }

  public void showMissionError() {
    showErrorOnTextField(missionTextField);
  }

  private void showErrorOnTextField(final JTextField field) {
    field.setBackground(new Color(255, 0, 0));
    Timer timer = new Timer(1900, new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        field.setBackground(new Color(255, 255, 255));
      }
    });
    timer.setRepeats(false);
    timer.start();
  }

  public void showErrorOnPanel() {
    setBackground(new Color(255, 150, 150));
    datePanel.setBackground(new Color(255, 150, 150));
    positionPanel.setBackground(new Color(255, 150, 150));
    missionPanel.setBackground(new Color(255, 150, 150));
    timePanel.setBackground(new Color(255, 150, 150));

    hideStatusOnPanel();
  }

  public void showSuccess() {
    setBackground(new Color(150, 255, 150));
    datePanel.setBackground(new Color(150, 255, 150));
    positionPanel.setBackground(new Color(150, 255, 150));
    missionPanel.setBackground(new Color(150, 255, 150));
    timePanel.setBackground(new Color(150, 255, 150));

    hideStatusOnPanel();
  }

  private void hideStatusOnPanel() {
    Timer timer = new Timer(1900, new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        setBackground(backGroundColor);
        datePanel.setBackground(backGroundColor);
        positionPanel.setBackground(backGroundColor);
        missionPanel.setBackground(backGroundColor);
        timePanel.setBackground(backGroundColor);
      }
    });
    timer.setRepeats(false);
    timer.start();
  }
  
  public Boolean showMessageWarning() {
    Object[] options = { "Ja", "Nein" };
    int choice = JOptionPane.showOptionDialog(this, "Zeiteintrag wirklich löschen?\n\n Datum: "
        + parseCalendar(getDate()) + "\n Auftrag: " + getMission() + "\n Position: " + getPosition() + "\n Zeit: "
        + parseMinutesToWorkTime(getWorktime()), "Löschen bestätigen", JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE, null, options, options[1]);

    if (choice == JOptionPane.YES_OPTION) {
      return true;
    } else {
      return false;
    }
  }
  

}
