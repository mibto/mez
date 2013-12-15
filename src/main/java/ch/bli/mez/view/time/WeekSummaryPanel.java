package ch.bli.mez.view.time;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WeekSummaryPanel extends JPanel{
  
  private static final long serialVersionUID = -3126830084419690967L;
  private JLabel currentWeekLabel;
  private JLabel lastWeekLabel;
  private JTextField currentWeekTextField;
  private JTextField lastWeekTextField;

  public WeekSummaryPanel(){
    build();
  }
  
  private void build(){
    JLabel titleLabel = new JLabel("Stundensumme:   ");
    titleLabel.setFont(getFont().deriveFont(Font.BOLD));
    this.add(titleLabel);
    
    currentWeekLabel = new JLabel("KW xx");
    this.add(currentWeekLabel);
    
    currentWeekTextField = new JTextField(2);
    this.add(currentWeekTextField);
    
    lastWeekLabel = new JLabel("KW xx");
    this.add(lastWeekLabel);
    
    lastWeekTextField = new JTextField(2);
    this.add(lastWeekTextField);
  }
  
  public void setCurrentWeekLabelText(Integer weekNumber){
    this.currentWeekLabel.setText("KW " + weekNumber);
  }
  
  public void setLastWeekLabelText(Integer weekNumber){
    this.lastWeekLabel.setText("KW " + weekNumber);
  }
  
}
