package ch.bli.mez.view;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

public class MainView extends JFrame {

  private JButton btnZeitErfassen = new JButton("Zeit erfassen");
  private JButton btnBlaa = new JButton("Blaa");
  private JButton btnVerwaltung = new JButton("Verwaltung");

  public MainView() {
    initView();
  }

  private void initView() {
    getContentPane().setLayout(
        new FormLayout(new ColumnSpec[] { ColumnSpec.decode("159px"),
            ColumnSpec.decode("130px"), }, new RowSpec[] {
            FormFactory.LINE_GAP_ROWSPEC, RowSpec.decode("25px"),
            FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
            FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
            FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
            FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
            FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

    getContentPane().add(btnZeitErfassen, "2, 8, left, top");
    getContentPane().add(btnBlaa, "2, 10");
    getContentPane().add(btnVerwaltung, "2, 12");
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

}
