package ch.bli.mez.view;

import javax.swing.JPanel;

public class DefaultSearchPanel extends JPanel{

  private static final long serialVersionUID = 1L;
  private DefaultPanel parentPanel;
  
  public void setParentPanel(DefaultPanel parentPanel){
    this.parentPanel = parentPanel;
  }
  
  public DefaultPanel getParentPanel(){
    return parentPanel;
  }
}
