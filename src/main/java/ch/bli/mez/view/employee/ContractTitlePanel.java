package ch.bli.mez.view.employee;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ContractTitlePanel extends JPanel{

  private static final long serialVersionUID = 1L;
  private JLabel endDate;
  private JLabel startDate;
  private JLabel workquota;
  private JPanel spacer;

  public ContractTitlePanel(){
    build();
  }
  
  private void build() {
    setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
    
    workquota = new JLabel("Pensum");
    workquota.setHorizontalAlignment(SwingConstants.LEFT);
    this.add(workquota);
    workquota.setPreferredSize(new Dimension(80, 25));
    
    startDate = new JLabel("Start");
    startDate.setHorizontalAlignment(SwingConstants.LEFT);
    this.add(startDate);
    startDate.setPreferredSize(new Dimension(80, 25));
    
    endDate = new JLabel("Ende");
    endDate.setHorizontalAlignment(SwingConstants.LEFT);
    this.add(endDate);
    endDate.setPreferredSize(new Dimension(80, 25));

    spacer = new JPanel();
    FlowLayout flowLayout = (FlowLayout) spacer.getLayout();
    flowLayout.setHgap(84);
    flowLayout.setVgap(0);
    add(spacer);
  }

}
