package ch.bli.mez.view.time;

import java.awt.FlowLayout;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

import ch.bli.mez.view.DefaultSearchPanel;

public class TimeEntrySearchPanel extends DefaultSearchPanel {
  
  private static final long serialVersionUID = 1L;
  private JTextField date;
  private JTextField missionName;
  private JTextField positionCode;
  private JTextField worktime;

  public TimeEntrySearchPanel(){
    build();
  }
  
  private void build() {
    setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

    date = new JTextField();
    this.add(date);
    date.setColumns(10);

    missionName = new JTextField();
    this.add(missionName);
    missionName.setColumns(10);

    positionCode = new JTextField();
    this.add(positionCode);
    positionCode.setColumns(10);

    worktime = new JTextField();
    this.add(worktime);
    worktime.setColumns(5);
  }
  
  public String getSearchText() {
    String searchString = "";
    searchString += "date=" + date.getText(); 
    searchString += "&missionName=" + missionName.getText();
    searchString += "&positionCode=" + positionCode.getText();
    searchString += "&worktime=" + worktime.getText();
    return searchString;
  }

  public void setKeyListener(KeyListener keyListener) {
    date.addKeyListener(keyListener);
    missionName.addKeyListener(keyListener);
    positionCode.addKeyListener(keyListener);
    worktime.addKeyListener(keyListener);
  }
}
