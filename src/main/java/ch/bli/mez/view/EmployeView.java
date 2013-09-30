package ch.bli.mez.view;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;

public class EmployeView extends JFrame {

  JButton btnNewButton = new JButton("New button");

  public EmployeView() {
    initView();
  }

  private void initView() {
    getContentPane().add(btnNewButton, BorderLayout.CENTER);
  }

  public void setNewButtonActionListener(ActionListener al) {
    btnNewButton.addActionListener(al);
  }

  private static final long serialVersionUID = 5381206762286626035L;
}
