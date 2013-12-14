package ch.bli.mez.view.management;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HolidayTitlePanel extends JPanel{
  
  private static final long serialVersionUID = 1L;
  private JLabel preWorkdays;
  private JLabel publicHolidays;
  private JLabel year;
  private JPanel spacer;

  public HolidayTitlePanel(){
    build();
  }
  
  private void build() {
    setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
    
    year = new JLabel("Jahr");
    year.setHorizontalAlignment(SwingConstants.LEFT);
    this.add(year);
    year.setPreferredSize(new Dimension(80, 25));
    
    publicHolidays = new JLabel("Feiertage");
    publicHolidays.setHorizontalAlignment(SwingConstants.LEFT);
    this.add(publicHolidays);
    publicHolidays.setPreferredSize(new Dimension(80, 25));

    preWorkdays = new JLabel("Vorholtage");
    preWorkdays.setHorizontalAlignment(SwingConstants.LEFT);
    this.add(preWorkdays);
    preWorkdays.setPreferredSize(new Dimension(80, 25));

    spacer = new JPanel();
    FlowLayout flowLayout = (FlowLayout) spacer.getLayout();
    flowLayout.setHgap(37);
    flowLayout.setVgap(0);
    add(spacer);
  }

}
