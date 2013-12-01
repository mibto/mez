package ch.bli.mez.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

public class DefaultPanel extends JPanel {

  private static final long serialVersionUID = -8686804124961218430L;

  private JPanel topPanel;
  private JPanel listPanel;
  private JPanel listTitlePanel;
  private JPanel listSearchPanel;

  private JLabel messageLabel;

  public DefaultPanel() {
    build();
  }

  private void build() {
    setLayout(new BorderLayout());

    JPanel northPanel = new JPanel();
    add(new JScrollPane(northPanel), BorderLayout.NORTH);

    topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());
    northPanel.add(topPanel);

    JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topPanel.add(messagePanel, BorderLayout.SOUTH);

    messageLabel = new JLabel(" ");
    messagePanel.add(messageLabel);

    JPanel centerPanel = new JPanel(new BorderLayout());
    add(new JScrollPane(centerPanel), BorderLayout.CENTER);

    JPanel centerNorthPanel = new JPanel();
    centerNorthPanel.setLayout(new BoxLayout(centerNorthPanel, BoxLayout.Y_AXIS));
    centerPanel.add(centerNorthPanel, BorderLayout.NORTH);

    listTitlePanel = new JPanel();
    centerNorthPanel.add(listTitlePanel);

    listSearchPanel = new JPanel();
    centerNorthPanel.add(listSearchPanel);

    JPanel centerCenterPanel = new JPanel();
    centerPanel.add(centerCenterPanel, BorderLayout.CENTER);

    listPanel = new JPanel();
    listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
    centerCenterPanel.add(new JScrollPane(listPanel));
  }

  public void setCreateNewForm(DefaultForm form) {
    topPanel.add(form, BorderLayout.CENTER);
    form.showAsCreateNew();
    form.setParentPanel(this);
  }

  public void setListTitlePanel(JPanel titlePanel) {
    listTitlePanel.add(titlePanel);
  }

  public void setListSearchPanel(JPanel searchPanel) {
    listSearchPanel.add(searchPanel);
    searchPanel.setParentPanel(this);
  }

  public void addForm(DefaultForm form) {
    listPanel.add(form);
    listPanel.revalidate();
    form.setParentPanel(this);
  }

  public void removeForm(DefaultForm form) {
    listPanel.remove(form);
    listPanel.revalidate();
  }

  /**
   * @param message
   *          String welcher im MessagePanel angezeigt werden soll
   */
  public void showConfirmation(String message) {
    messageLabel.setForeground(new Color(0, 128, 0));
    messageLabel.setText(message);
    hideMessage();
  }

  /**
   * @param message
   *          String welcher im MessagePanel angezeigt werden soll
   */
  public void showError(String message) {
    messageLabel.setForeground(new Color(255, 0, 0));
    messageLabel.setText(message);
    hideMessage();
  }

  private void hideMessage() {
    Timer timer = new Timer(1800, new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        messageLabel.setText(" ");
      }
    });
    timer.setRepeats(false);
    timer.start();
  }
}