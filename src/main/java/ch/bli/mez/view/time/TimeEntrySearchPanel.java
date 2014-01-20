package ch.bli.mez.view.time;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import ch.bli.mez.view.DefaultSearchPanel;

import javax.swing.JPanel;

public class TimeEntrySearchPanel extends DefaultSearchPanel {
  
  private static final long serialVersionUID = 1L;
  private JTextField date;
  private JTextField missionName;
  private JTextField positionCode;
  private JTextField worktime;
  private JButton searchButton;
  private JButton resetButton;
  private JPanel spacer;

  public TimeEntrySearchPanel(){
    build();
  }
  
  private void build() {
    setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));

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
    worktime.setColumns(7);
    
    searchButton = new JButton("Suche");
    this.add(searchButton);
    
    resetButton = new JButton("Reset");
    this.add(resetButton);
    
    spacer = new JPanel();
    FlowLayout flowLayout = (FlowLayout) spacer.getLayout();
    flowLayout.setHgap(2);
    flowLayout.setVgap(0);
    add(spacer);
  }
  
  public String getSearchText() {
    String searchString = "";
    searchString += "date=" + date.getText(); 
    searchString += "&missionName=" + missionName.getText();
    searchString += "&positionCode=" + positionCode.getText();
    searchString += "&worktime=" + worktime.getText();
    return searchString;
  }

  public void setSearchListener(ActionListener actionListener) {
    searchButton.addActionListener(actionListener);
  }
  
  public void setResetListener(ActionListener actionListener){
    resetButton.addActionListener(actionListener);
  }
  
  public void resetFields(){
    date.setText("");
    missionName.setText("");
    positionCode.setText("");
    worktime.setText("");
  }
}
