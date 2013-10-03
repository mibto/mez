package ch.bli.mez.view;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import java.awt.CardLayout;
import java.awt.event.ActionListener;

public class EmployeeView extends JFrame {

  /**
   * 
   */
  private static final long serialVersionUID = 8767516928379563985L;
  private JButton btnZeitErfassen = new JButton("Zeit erfassen");
  private JButton btnBlaa = new JButton("Blaa");
  private JButton btnVerwaltung = new JButton("Verwaltung");
  private JButton btnBlubb = new JButton("Blubb");
  

  public EmployeeView() {
	  
	// Close
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    try {
      UIManager
          .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch (Exception e) {
      e.printStackTrace();
    }
    setResizable(true);
    getContentPane().setLayout(new CardLayout(0, 0));

    JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
    getContentPane().add(tabbedPane_1, "name_81679655182644");

    JPanel panel = new JPanel();
    tabbedPane_1.addTab("New tab", null, panel, null);
    panel.setLayout(new CardLayout(0, 0));

    JTabbedPane tabbedPane_2 = new JTabbedPane(JTabbedPane.LEFT);
    panel.add(tabbedPane_2, "name_81722033527761");

    JPanel panel_4 = new JPanel();
    tabbedPane_2.addTab("New tab", null, panel_4, null);
    
    panel_4.add(btnBlubb);
    panel_4.add(btnBlaa);

    JPanel panel_5 = new JPanel();
    tabbedPane_2.addTab("New tab", null, panel_5, null);

    JPanel panel_6 = new JPanel();
    tabbedPane_2.addTab("New tab", null, panel_6, null);

    JPanel panel_1 = new JPanel();
    tabbedPane_1.addTab("New tab", null, panel_1, null);

    JPanel panel_2 = new JPanel();
    tabbedPane_1.addTab("New tab", null, panel_2, null);

    JPanel panel_3 = new JPanel();
    tabbedPane_1.addTab("New tab", null, panel_3, null);

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

  public void setBlubbctionListener(ActionListener al) {
    btnBlubb.addActionListener(al);
  }
  
  public void setBlaaActionListener(ActionListener al) {
    btnBlaa.addActionListener(al);
  }

  public void setVerwaltungActionListener(ActionListener al) {
    btnVerwaltung.addActionListener(al);
  }
}
