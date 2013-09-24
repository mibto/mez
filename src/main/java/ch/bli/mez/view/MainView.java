package ch.bli.mez.view;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;

import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class MainView extends JFrame {

  private JButton btnZeitErfassen = new JButton("Zeit erfassen");
  private JButton btnBlaa = new JButton("Blaa");
  private JButton btnVerwaltung = new JButton("Verwaltung");

  public MainView() {
    setResizable(false);
    
    JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
    getContentPane().add(tabbedPane_1, BorderLayout.CENTER);
    
    JPanel timeTab = new JPanel();
    tabbedPane_1.addTab("Zeit erfassen", null, timeTab, null);
    
    JPanel EmployeTab = new JPanel();
    tabbedPane_1.addTab("Mitarbeiter verwalten", null, EmployeTab, null);
    
    JPanel ReportTab = new JPanel();
    tabbedPane_1.addTab("Reports erstellen", null, ReportTab, null);
    initView();
  }

  private void initView() {

  }
  
  public String getName() {
    return "maxel";
  }
  
  public void setName(String name) {
    this.btnBlaa.setText(name);
  }

  public void setZeitErfassenActionListener(ActionListener al) {
    btnZeitErfassen.addActionListener(al);
  }

  public void setBlaaActionListener(ActionListener al) {
    btnBlaa.addActionListener(al);
  }

  public void setVerwaltungActionListener(ActionListener al) {
    btnVerwaltung.addActionListener(al);
  }

  private static final long serialVersionUID = 1L;
  private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

}
