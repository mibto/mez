package ch.bli.mez.view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;

public class DefaultTabbedView extends JPanel {

  private static final long serialVersionUID = 1L;
  private JTabbedPane tabbedPane;

  public DefaultTabbedView() {
    this.tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
    build();
  }

  private void build() {
    this.setLayout(new BorderLayout());
    this.add(tabbedPane, BorderLayout.CENTER);
  }

  public void addTab(String name, JPanel panel) {
    tabbedPane.addTab(name, panel);
  }
  
  public int getTabCount(){
    return tabbedPane.getTabCount();
  }
  
  public void setSelectedIndex(int index){
    tabbedPane.setSelectedIndex(index);
  }

  public void removeTab() {
    tabbedPane.remove(getSelectedIndex());
  }

  public void removeAllTabs() {
    tabbedPane.removeAll();
  }

  private int getSelectedIndex() {
    return tabbedPane.getSelectedIndex();
  }
  
  public Component getSelectedComponent(){
    return tabbedPane.getSelectedComponent();
  }

  public void setTabListener(ChangeListener changeListener) {
    tabbedPane.addChangeListener(changeListener);
  }
  
  public void updateTabName(String title) {
    tabbedPane.setTitleAt(getSelectedIndex(), title);
  }
}
