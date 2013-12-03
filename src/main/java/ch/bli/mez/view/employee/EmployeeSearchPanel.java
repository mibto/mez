package ch.bli.mez.view.employee;

import java.awt.BorderLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.bli.mez.view.DefaultSearchPanel;

/**
 * SearchPanel ist das kleine Suchfeld welches oberhalb der Tabs mit
 * Mitarbeiternamen platziert wird Der SearchPanel wird für "Zeiten erfassen"
 * sowie "Mitarbeiter verwalten" verwendet
 * 
 * @author dave
 * @version 1.0
 */

public class EmployeeSearchPanel extends DefaultSearchPanel {

  private static final long serialVersionUID = 5735685030723299266L;
  private JTextField nameSearch;

  public EmployeeSearchPanel() {
    nameSearch = new JTextField();
    initializePanel();
  }

  private void initializePanel() {
    JPanel panel = new JPanel();
    initializeTextField();
    panel.add(nameSearch);
    this.setLayout(new  BorderLayout());
    this.add(panel, BorderLayout.WEST);
  }

  /**
   * GUI interne Listener werden hier hinzugefügt
   */
  public void initializeTextField() {
    nameSearch.setText("Search...");
    nameSearch.setColumns(10);
    nameSearch.addFocusListener(new FocusListener() {

      public void focusLost(FocusEvent e) {
      }

      public void focusGained(FocusEvent e) {
        nameSearch.setText(null);
      }
    });
  }

  public String getSearchText() {
    return "name=" + nameSearch.getText();
  }

  public void setKeyListener(KeyListener keyListener) {
    nameSearch.addKeyListener(keyListener);
  }
}
