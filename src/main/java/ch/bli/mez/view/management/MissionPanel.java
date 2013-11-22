package ch.bli.mez.view.management;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

public class MissionPanel extends JPanel {

  private static final long serialVersionUID = -7537968850748849818L;
  private JPanel messagePanel;
  private JLabel messageLabel;
  private JPanel listPanel;
  private JPanel topPanel;

  public MissionPanel() {
    build();
  }
  
  private void build(){
    setLayout(new BorderLayout());

    JPanel northPanel = new JPanel();
    add(new JScrollPane(northPanel), BorderLayout.NORTH);

    topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());
    northPanel.add(topPanel);

    messagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topPanel.add(messagePanel, BorderLayout.SOUTH);

    messageLabel = new JLabel(" ");
    messagePanel.add(messageLabel);

    JPanel centerPanel = new JPanel();
    add(new JScrollPane(centerPanel), BorderLayout.CENTER);

    listPanel = new JPanel();
    listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
    centerPanel.add((listPanel));
  }
  
  public void setNewMissionForm(MissionForm form){
    topPanel.add(form, BorderLayout.CENTER);
  }
  
  public void addMissionForm(MissionForm missionForm) {
    listPanel.add(missionForm);
    listPanel.revalidate();
  }

  public void removeMissionForm(MissionForm missionForm) {
    listPanel.remove(missionForm);
    listPanel.revalidate();
  }

  public void showConfirmation(String name) {
    messageLabel.setForeground(new Color(0, 128, 0));
    messageLabel.setText(name + " wurde zur Liste hinzugefügt!");
    hideMessage();
  }

  public void showNameError() {
    messageLabel.setForeground(new Color(255, 0, 0));
    messageLabel.setText("Auftragsname darf nicht leer sein");
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
}
