package ch.bli.mez.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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
}
