package ch.bli.mez.view.management;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

public class PositionPanel extends JPanel {

  private static final long serialVersionUID = -8686804124961218430L;

  private JComboBox<missionComboBoxItem> missionComboBox;
  private JLabel messageLabel;
  private JPanel listPanel;

  private Container topPanel;

  public PositionPanel() {
    build();
  }
  
  private void build(){
    setLayout(new BorderLayout());

    JPanel northPanel = new JPanel();
    add(new JScrollPane(northPanel), BorderLayout.NORTH);

    topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());
    northPanel.add(topPanel);
    
    JPanel missionChooserPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topPanel.add(missionChooserPanel, BorderLayout.NORTH);
    
    missionComboBox = new JComboBox<missionComboBoxItem>();
    missionChooserPanel.add(missionComboBox);

    JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topPanel.add(messagePanel, BorderLayout.SOUTH);

    messageLabel = new JLabel(" ");
    messagePanel.add(messageLabel);

    JPanel centerPanel = new JPanel();
    add(new JScrollPane(centerPanel), BorderLayout.CENTER);

    listPanel = new JPanel();
    listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
    centerPanel.add((listPanel));
  }
  
  public void setNewPositionForm(PositionForm form){
    topPanel.add(form, BorderLayout.CENTER);
  }
  
  public void addPositionForm(PositionForm positionForm) {
    listPanel.add(positionForm);
    listPanel.revalidate();
  }

  public void removePositionForm(PositionForm positionForm) {
    listPanel.remove(positionForm);
    listPanel.revalidate();
  }

  public void showConfirmation(String name) {
    messageLabel.setForeground(new Color(0, 128, 0));
    messageLabel.setText(name + " wurde zur Liste hinzugefügt!");
    hideMessage();
  }

  public void showNameError() {
    messageLabel.setForeground(new Color(255, 0, 0));
    messageLabel.setText("Name darf nicht leer sein");
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
  
  public void setComboBoxItems(HashMap<Integer, String> missionList) {
    missionComboBox.removeAllItems();
    for (Entry<Integer, String> entry : missionList.entrySet()) {

      missionComboBox.addItem(new missionComboBoxItem(entry.getKey(), entry.getValue()));
    }
    missionComboBox.setSelectedIndex(0);
  }

  public Integer getSelectedMission() {
    return ((missionComboBoxItem) missionComboBox.getSelectedItem()).getId();
  }
  
  public class missionComboBoxItem {
    private Integer id;
    private String name;

    public missionComboBoxItem(Integer id, String name) {
      this.id = id;
      this.name = name;
    }

    public Integer getId() {
      return id;
    }

    @Override
    public String toString() {
      return name;
    }
  }
}