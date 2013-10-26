package ch.bli.mez.view.time;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ch.bli.mez.view.SearchPanel;

/**
 * GUI für den Tab "Zeiten erfassen"
 * 
 * @author dave
 * @version 1.0
 */
public class TimeView extends JPanel {

  private static final long serialVersionUID = -9040213583757030012L;

  private JTabbedPane tabbedPane;

  public TimeView(SearchPanel searchPanel) {

    this.tabbedPane = new JTabbedPane(JTabbedPane.LEFT);

    this.setLayout(new BorderLayout());
    this.add(searchPanel, BorderLayout.NORTH);
    this.add(tabbedPane, BorderLayout.CENTER);

  }

  public void addTab(String name, JPanel employeePanel) {
    tabbedPane.addTab(name, employeePanel);
  }

  public void removeTab(int value) {
    tabbedPane.remove(value);
  }

  public int getSelectedIndex() {
    return tabbedPane.getSelectedIndex();
  }

}
