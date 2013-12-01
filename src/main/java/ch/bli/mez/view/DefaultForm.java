package ch.bli.mez.view;

import javax.swing.JPanel;

public abstract class DefaultForm extends JPanel {

  private static final long serialVersionUID = -4757726896100150339L;
  
  private DefaultPanel parentPanel;

  public Boolean validateFields(){
    return null;
  }
  
  public void showAsCreateNew(){}
  
  public void setParentPanel(DefaultPanel parentPanel){
    this.parentPanel = parentPanel;
  }
  
  public DefaultPanel getParentPanel(){
    return this.parentPanel;
  }
  
}
