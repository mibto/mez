package ch.bli.mez.util;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class JRapidScrollPane extends JScrollPane {

  private static final long serialVersionUID = -6484891562495115662L;

  public JRapidScrollPane(JPanel panel) {
    super(panel);
    this.getVerticalScrollBar().setUnitIncrement(16);
    this.getHorizontalScrollBar().setUnitIncrement(10);
  }
}
