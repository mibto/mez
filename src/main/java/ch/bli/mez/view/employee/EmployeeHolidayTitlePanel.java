package ch.bli.mez.view.employee;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class EmployeeHolidayTitlePanel extends JPanel{

  private static final long serialVersionUID = 1L;
  private JLabel preWorkdays;
  private JLabel publicHolidays;
  private JLabel year;
  private JLabel holiday;
  private JPanel spacer;

  public EmployeeHolidayTitlePanel(){
    build();
  }
  
  private void build() {
    setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
    
    holiday = new JLabel("Ferien");
    holiday.setHorizontalAlignment(SwingConstants.LEFT);
    this.add(holiday);
    holiday.setPreferredSize(new Dimension(80, 25));
    
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
    flowLayout.setHgap(42);
    flowLayout.setVgap(0);
    add(spacer);
  }

}
