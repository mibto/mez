package ch.bli.mez.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class DefaultForm extends JPanel {

  private static final long serialVersionUID = -4757726896100150339L;

  private DefaultPanel parentPanel;

  public abstract Boolean validateFields();

  public abstract void showAsCreateNew();

  public abstract void cleanFields();

  public void setParentPanel(DefaultPanel parentPanel) {
    this.parentPanel = parentPanel;
  }

  public DefaultPanel getParentPanel() {
    return this.parentPanel;
  }

  protected void setEnterKeyListener(final JButton saveButton, JTextField... textFields) {
    KeyListener enterKeyListener = new KeyListener() {
      public void keyTyped(KeyEvent arg0) {
      }

      public void keyReleased(KeyEvent arg0) {
      }

      public void keyPressed(KeyEvent arg0) {
        if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
          saveButton.doClick();
        }
      }
    };

    for (JTextField textField : textFields) {
      textField.addKeyListener(enterKeyListener);
    }
  }

}
