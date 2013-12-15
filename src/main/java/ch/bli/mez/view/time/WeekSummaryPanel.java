package ch.bli.mez.view.time;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.bli.mez.util.Parser;

public class WeekSummaryPanel extends JPanel {

  private static final long serialVersionUID = -3126830084419690967L;
  private JLabel currentWeekLabel;
  private JLabel lastWeekLabel;
  private JTextField currentWeekTextField;
  private JTextField lastWeekTextField;

  public WeekSummaryPanel() {
    build();
  }

  private void build() {
    JLabel titleLabel = new JLabel("Stundensumme:   ");
    titleLabel.setFont(getFont().deriveFont(Font.BOLD));
    this.add(titleLabel);

    currentWeekLabel = new JLabel("KW (leer)");
    this.add(currentWeekLabel);

    currentWeekTextField = new JTextField(4);
    currentWeekTextField.setEditable(false);
    this.add(currentWeekTextField);

    lastWeekLabel = new JLabel("KW (leer)");
    this.add(lastWeekLabel);

    lastWeekTextField = new JTextField(4);
    lastWeekTextField.setEditable(false);
    this.add(lastWeekTextField);
  }

  public void setCurrentWeekNumber(Integer weekNumber, Integer year) {
    this.currentWeekLabel.setText("KW " + weekNumber + " (" + year + ")");
  }

  public void setLastWeekNumber(Integer weekNumber, Integer year) {
    this.lastWeekLabel.setText("KW " + weekNumber + " (" + year + ")");
  }

  public void setCurrentWeekAmount(Integer workMinutes) {
    this.currentWeekTextField.setText(Parser.parseMinutesIntegerToString(workMinutes));
  }

  public void setLastWeekAmount(Integer workMinutes) {
    this.lastWeekTextField.setText(Parser.parseMinutesIntegerToString(workMinutes));
  }

}
