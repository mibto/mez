package ch.bli.mez.view.management;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * 
 * @author dave
 * @version 1.0
 */
public class HolidayPanel extends JPanel {

  private static final long serialVersionUID = -7537968850748849818L;

  private JTextField yearTextField;
  private JTextField publicHolidaysTextField;
  private JTextField preWorkdaysTextField;

  private JLabel messageLabel;

  private JButton addButton;

  private JPanel listPanel;

  public HolidayPanel() {

    setLayout(new BorderLayout());

    // EntryPanel (north)
    JPanel northPanel = new JPanel();
    add(new JScrollPane(northPanel), BorderLayout.NORTH);

    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());
    northPanel.add(topPanel);

    JPanel entryPanel = new JPanel();
    topPanel.add(entryPanel, BorderLayout.CENTER);

    JLabel yearLabel = new JLabel("Jahr");
    entryPanel.add(yearLabel);

    yearTextField = new JTextField(4);
    entryPanel.add(yearTextField);

    JLabel publicHolidaysLabel = new JLabel("Feiertage");
    entryPanel.add(publicHolidaysLabel);

    publicHolidaysTextField = new JTextField(2);
    entryPanel.add(publicHolidaysTextField);

    JLabel preWorkdaysLabel = new JLabel("Vorholtage");
    entryPanel.add(preWorkdaysLabel);

    preWorkdaysTextField = new JTextField(2);
    entryPanel.add(preWorkdaysTextField);

    addButton = new JButton("Speichern");
    entryPanel.add(addButton);

    JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topPanel.add(messagePanel, BorderLayout.SOUTH);

    messageLabel = new JLabel(" ");
    messagePanel.add(messageLabel);

    // ListPanel (center)
    JPanel centerPanel = new JPanel();
    add(new JScrollPane(centerPanel), BorderLayout.CENTER);

    listPanel = new JPanel();
    listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
    centerPanel.add((listPanel));

    addGuiFeatureListener();
  }

  public void cleanFields() {
    setYear("");
    setPublicHolidays("");
    setPreWorkdays("");
  }

  /**
   * @param confirmationMessage
   *          the confirmation message to be displayed
   */
  public void showConfirmation(String confirmationMessage) {
    messageLabel.setForeground(new Color(0, 128, 0));
    messageLabel.setText(confirmationMessage);
    hideMessage();
  }

  /**
   * @param errorMessage
   *          the error message to be displayed
   */
  public void showError(String errorMessage) {
    messageLabel.setForeground(new Color(255, 0, 0));
    messageLabel.setText(errorMessage);
    hideMessage();
  }

  private void hideMessage() {
    Timer timer = new Timer(1800, new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        messageLabel.setText(" ");
      }
    });
    timer.setRepeats(false);
    timer.start();
  }

  public void addHolidayListEntry(HolidayListEntry holidayListEntry) {
    listPanel.add(holidayListEntry);
    listPanel.revalidate();
  }

  public void removeHolidayListEntry(HolidayListEntry holidayListEntry) {
    listPanel.remove(holidayListEntry);
    listPanel.revalidate();
  }

  // getter & setter
  public void setYear(String value) {
    this.yearTextField.setText(value);
  }

  public String getYear() {
    return yearTextField.getText();
  }

  public void setPublicHolidays(String value) {
    this.publicHolidaysTextField.setText(value);
  }

  public String getPublicHolidays() {
    return publicHolidaysTextField.getText();
  }

  public void setPreWorkdays(String value) {
    this.preWorkdaysTextField.setText(value);
  }

  public String getPreWorkdays() {
    return preWorkdaysTextField.getText();
  }

  // setListeners
  public void setSaveListener(ActionListener actionListener) {
    addButton.addActionListener(actionListener);
  }

  // internal methods
  private void addGuiFeatureListener() {
    addButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        cleanFields();
      }
    });
    KeyListener enterKeyListener = new KeyListener() {
      public void keyTyped(KeyEvent e) {
      }

      public void keyReleased(KeyEvent e) {
      }

      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          addButton.doClick();
        }
      }
    };
    yearTextField.addKeyListener(enterKeyListener);
    publicHolidaysTextField.addKeyListener(enterKeyListener);
    preWorkdaysTextField.addKeyListener(enterKeyListener);
  }
}
