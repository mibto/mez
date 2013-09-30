package ch.bli.mez.view;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

import java.awt.GridBagLayout;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.Box;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

public class MainView extends JFrame {

  private JButton btnZeitErfassen = new JButton("Zeit erfassen");
  private JButton btnBlaa = new JButton("Blaa");
  private JButton btnVerwaltung = new JButton("Verwaltung");

  public MainView() {
    try
    {
//     UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
      UIManager.setLookAndFeel( "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel" );
    }
    catch ( Exception e )
    {
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

  public void setBlaaActionListener(ActionListener al) {
    btnBlaa.addActionListener(al);
  }

  public void setVerwaltungActionListener(ActionListener al) {
    btnVerwaltung.addActionListener(al);
  }

  private static final long serialVersionUID = 1L;
  private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
}
