package ch.bli.mez.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import ch.bli.mez.util.JRapidScrollPane;

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
    add(new JRapidScrollPane(northPanel), BorderLayout.NORTH);

    topPanel = new JPanel(new BorderLayout());
    northPanel.add(topPanel);

    JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topPanel.add(messagePanel, BorderLayout.SOUTH);

    messageLabel = new JLabel(" ");
    messagePanel.add(messageLabel);

    JPanel centerPanel = new JPanel(new BorderLayout());
    add(new JRapidScrollPane(centerPanel), BorderLayout.CENTER);

    JPanel centerNorthPanel = new JPanel();
    centerNorthPanel.setLayout(new BoxLayout(centerNorthPanel, BoxLayout.Y_AXIS));
    centerPanel.add(centerNorthPanel, BorderLayout.NORTH);

    listTitlePanel = new JPanel();
    listTitlePanel.setLayout(new BoxLayout(listTitlePanel, BoxLayout.Y_AXIS));
    centerNorthPanel.add(listTitlePanel);

    listSearchPanel = new JPanel();
    listSearchPanel.setLayout(new BoxLayout(listSearchPanel, BoxLayout.Y_AXIS));
    centerNorthPanel.add(listSearchPanel);

    JPanel centerCenterPanel = new JPanel();
    centerPanel.add(centerCenterPanel, BorderLayout.CENTER);

    listPanel = new JPanel();
    listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
    centerCenterPanel.add(listPanel);
  }
  
  protected void setChooserPanel(JPanel chooserPanel){
    topPanel.add(chooserPanel, BorderLayout.NORTH);
  }

  public void setCreateNewForm(DefaultForm form) {
    topPanel.add(form, BorderLayout.CENTER);
    form.showAsCreateNew();
    form.setParentPanel(this);
  }
  
  public void setListTitlePanel(JPanel titlePanel) {
    listTitlePanel.add(titlePanel);
  }

  public void setListSearchPanel(DefaultSearchPanel searchPanel) {
    listSearchPanel.add(searchPanel);
    searchPanel.setParentPanel(this);
  }

  public void addForm(DefaultForm form) {
    listPanel.add(form);
    this.revalidate();
    form.setParentPanel(this);
  }

  public void removeForm(DefaultForm form) {
    listPanel.remove(form);
    this.revalidate();
  }
  
  protected JPanel getTopPanel(){
    return this.topPanel;
  }
  
  public void removeAllForms(){
    listPanel.removeAll();
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