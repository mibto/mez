package ch.bli.mez.view;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeListener;

/**
 * MainFrame beinhaltet die oberen Tabs, ursprünglich "Zeiten erfassen",
 * "Mitarbeiter verwalten", "Auswertungen", "Verwaltung"
 * 
 * @author dave
 * @version 1.0
 */
public class MainView extends JFrame {

  private static final long serialVersionUID = -8484150056391154851L;
  private JTabbedPane mainTabbedPane;

  public MainView() {

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    getContentPane().setLayout(new CardLayout(0, 0));
    setMinimumSize(new Dimension(1300, 700));

    try {
      UIManager
      // .setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
          .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch (Exception e) {
      e.printStackTrace();
    }

    mainTabbedPane = new JTabbedPane(JTabbedPane.TOP);
    getContentPane().add("Main", mainTabbedPane);

    mainTabbedPane.insertTab("Zeiten erfassen", null, new JPanel(), null, 0);
    mainTabbedPane.insertTab("Mitarbeiter verwalten", null, new JPanel(), null, 1);
    mainTabbedPane.insertTab("Auswertungen", null, new JPanel(), null, 2);
    mainTabbedPane.insertTab("Verwaltung", null, new JPanel(), null, 3);
  }

  public void setTimeEntryPanel(EmployeeTabbedView view) {
    mainTabbedPane.setComponentAt(0, view);
  }

  public void setEmployeePanel(EmployeeTabbedView view) {
    mainTabbedPane.setComponentAt(1, view);
  }

  public void setAnalysisPanel(DefaultTabbedView view) {
    mainTabbedPane.setComponentAt(2, view);
  }

  public void setManagementPanel(DefaultTabbedView view) {
    mainTabbedPane.setComponentAt(3, view);
  }

  public EmployeeTabbedView getTimeEntryView() {
    return (EmployeeTabbedView) mainTabbedPane.getTabComponentAt(0);
  }

  public EmployeeTabbedView getEmployeePanel() {
    return (EmployeeTabbedView) mainTabbedPane.getTabComponentAt(1);
  }

  public DefaultTabbedView getAnalysePanel() {
    return (DefaultTabbedView) mainTabbedPane.getTabComponentAt(2);
  }

  public DefaultTabbedView getManagementPanel() {
    return (DefaultTabbedView) mainTabbedPane.getTabComponentAt(3);
  }

  public void setTabChangeListener(ChangeListener cl) {
    mainTabbedPane.addChangeListener(cl);
  }

}
