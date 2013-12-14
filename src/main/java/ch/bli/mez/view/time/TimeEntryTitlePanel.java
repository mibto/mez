package ch.bli.mez.view.time;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TimeEntryTitlePanel extends JPanel {

  private static final long serialVersionUID = 1L;
  private Dimension defaultDim;
  private JLabel date;
  private JLabel missionName;
  private JLabel positionCode;
  private JLabel worktime;
  private JPanel spacer;

  public TimeEntryTitlePanel() {
    defaultDim = new Dimension(114, 25);
    build();
  }

  private void build() {
    setLayout(new FlowLayout(FlowLayout.CENTER, 23, 0));

    date = new JLabel("Datum");
    date.setHorizontalAlignment(SwingConstants.LEFT);
    this.add(date);
    date.setPreferredSize(defaultDim);

    missionName = new JLabel("Auftrag");
    missionName.setHorizontalAlignment(SwingConstants.LEFT);
    this.add(missionName);
    missionName.setPreferredSize(defaultDim);

    positionCode = new JLabel("Position");
    positionCode.setHorizontalAlignment(SwingConstants.LEFT);
    this.add(positionCode);
    positionCode.setPreferredSize(defaultDim);

    worktime = new JLabel("Zeit");
    worktime.setHorizontalAlignment(SwingConstants.LEFT);
    this.add(worktime);
    worktime.setPreferredSize(defaultDim);

    spacer = new JPanel();
    FlowLayout flowLayout = (FlowLayout) spacer.getLayout();
    flowLayout.setHgap(57);
    flowLayout.setVgap(0);
    add(spacer);
  }
}