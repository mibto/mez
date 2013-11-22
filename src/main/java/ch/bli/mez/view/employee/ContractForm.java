package ch.bli.mez.view.employee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 * 
 * @author dave
 * @version 1.0
 */
public class ContractForm extends JPanel {

  private static final long serialVersionUID = -4040659975448618036L;

  private JTextField workquotaTextField;
  private JTextField startDateTextField;
  private JTextField endDateTextField;

  private JLabel messageLabel;

  private JButton addButton;

  private JPanel listPanel;

  public ContractForm() {

    setLayout(new BorderLayout());

    // EntryPanel (north)
    JPanel northPanel = new JPanel();
    add(new JScrollPane(northPanel), BorderLayout.NORTH);

    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());
    northPanel.add(topPanel);

    JPanel entryPanel = new JPanel();
    topPanel.add(entryPanel, BorderLayout.CENTER);

    JLabel workquotaLabel = new JLabel("Pensum");
    entryPanel.add(workquotaLabel);

    workquotaTextField = new JTextField(3);
    entryPanel.add(workquotaTextField);

    JLabel startDateLabel = new JLabel("Start");
    entryPanel.add(startDateLabel);

    startDateTextField = new JTextField(7);
    entryPanel.add(startDateTextField);

    JLabel endDateLabel = new JLabel("Ende");
    entryPanel.add(endDateLabel);

    endDateTextField = new JTextField(7);
    entryPanel.add(endDateTextField);

    addButton = new JButton("Speichern");
    entryPanel.add(addButton);

    JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topPanel.add(messagePanel, BorderLayout.SOUTH);

    messageLabel = new JLabel(" ");
    messagePanel.add(messageLabel);

    // ListPanel (center)
    JPanel centerPanel = new JPanel();
    add(new JScrollPane(centerPanel), BorderLayout.CENTER);

    listPanel = new JPanel();
    listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
    centerPanel.add((listPanel));

    addGuiFeatureListener();
  }

  public void cleanFields() {
    setWorkquota("");
    setStartDate("");
    setEndDate("");
  }

  /**
   * @param confirmationMessage
   *          the confirmation message to be displayed
   */
  public void showConfirmation(String confirmationMessage) {
    messageLabel.setForeground(new Color(0, 128, 0));
    messageLabel.setText(confirmationMessage);
    hideMessage();
  }

  /**
   * @param errorMessage
   *          the error message to be displayed
   */
  public void showError(String errorMessage) {
    messageLabel.setForeground(new Color(255, 0, 0));
    messageLabel.setText(errorMessage);
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

  public void addContractListEntry(ContractForm contractForm) {
    listPanel.add(contractForm);
    listPanel.revalidate();
  }

  public void removeContractListEntry(ContractForm contractForm) {
    listPanel.remove(contractForm);
    listPanel.revalidate();
  }

  // getter & setter
  public String getWorkquota() {
    return workquotaTextField.getText();
  }

  public void setWorkquota(String workquota) {
    this.workquotaTextField.setText(workquota);
  }

  public String getStartDate() {
    return startDateTextField.getText();
  }

  public void setStartDate(String startDate) {
    this.startDateTextField.setText(startDate);
  }

  public String getEndDate() {
    return endDateTextField.getText();
  }

  public void setEndDate(String endDate) {
    this.endDateTextField.setText(endDate);
    ;
  }

  // setListeners
  public void setSaveListener(ActionListener actionListener) {
    addButton.addActionListener(actionListener);
  }

  // internal methods
  private void addGuiFeatureListener() {
    addButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        cleanFields();
      }
    });
    KeyListener enterKeyListener = new KeyListener() {
      public void keyTyped(KeyEvent e) {
      }

      public void keyReleased(KeyEvent e) {
      }

      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          addButton.doClick();
        }
      }
    };
    workquotaTextField.addKeyListener(enterKeyListener);
    startDateTextField.addKeyListener(enterKeyListener);
    endDateTextField.addKeyListener(enterKeyListener);
  }

  public void showSuccess() {
    // TODO Auto-generated method stub
    
  }

  public void setDeleteListener(ActionListener actionListener) {
    // TODO Auto-generated method stub
    
  }

}
