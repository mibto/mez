package ch.bli.mez.view.management;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;

public class ManagementView extends JPanel {

  private static final long serialVersionUID = -4318728728729330881L;

  private JTabbedPane tabbedPane;

  public ManagementView() {
    tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
    this.setLayout(new BorderLayout());
    this.add(tabbedPane, BorderLayout.CENTER);
  }

  public void addTab(String name, JPanel jpanel) {
    tabbedPane.addTab(name, jpanel);
  }
  
  public void setTabListener(ChangeListener changeListener){
	  tabbedPane.addChangeListener(changeListener);
  }
}