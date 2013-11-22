package ch.bli.mez.view.employee;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EmployeeForm extends JPanel {

  private static final long serialVersionUID = 2105749706100780883L;

  private JTextField lastnameTextField;
  private JTextField firstnameTextField;
  private JTextField streetTextField;
  private JTextField cityTextField;
  private JTextField plzTextField;
  private JTextField homeNumberTextField;
  private JTextField mobileNumberTextField;
  private JTextField emailTextField;

  private JButton saveButton;
  private JButton statusButton;

  private JPanel holidayContentPanel;

  public EmployeeForm() {
    build();
    addGuiFeatureListener();
  }

  private void build() {
    JPanel entryPanel = new JPanel();
    entryPanel.setLayout(new GridBagLayout());
    entryPanel.setPreferredSize(new Dimension(600, 162));

    // helpPanel to create distance between first & second column
    addPersonalComponent(entryPanel, new JPanel(), 2, 0, 1, 1.0);

    JLabel lbllastname = new JLabel("Name");
    lbllastname.setFont(lbllastname.getFont().deriveFont(Font.BOLD));
    addPersonalComponent(entryPanel, lbllastname, 0, 0, 1, 0);

    lastnameTextField = new JTextField();
    addPersonalComponent(entryPanel, lastnameTextField, 1, 0, 1, 1.0);

    JLabel lblfirstname = new JLabel("Vorname");
    lblfirstname.setFont(lblfirstname.getFont().deriveFont(Font.BOLD));
    addPersonalComponent(entryPanel, lblfirstname, 3, 0, 1, 0);

    firstnameTextField = new JTextField();
    addPersonalComponent(entryPanel, firstnameTextField, 4, 0, 1, 1.0);

    JLabel lblstreet = new JLabel("Strasse");
    addPersonalComponent(entryPanel, lblstreet, 0, 1, 1, 0);

    streetTextField = new JTextField();
    addPersonalComponent(entryPanel, streetTextField, 1, 1, 1, 1.0);

    JLabel lblplz = new JLabel("PLZ");
    addPersonalComponent(entryPanel, lblplz, 0, 2, 1, 0);

    plzTextField = new JTextField();
    plzTextField.setColumns(4);
    addPersonalComponent(entryPanel, plzTextField, 1, 2, 1, 1.0);

    JLabel lblcity = new JLabel("Ort");
    addPersonalComponent(entryPanel, lblcity, 3, 2, 1, 0);

    cityTextField = new JTextField();
    cityTextField.setColumns(10);
    addPersonalComponent(entryPanel, cityTextField, 4, 2, 2, 1.0);

    JLabel lblhomeNumber = new JLabel("Festnetz");
    addPersonalComponent(entryPanel, lblhomeNumber, 0, 3, 1, 0);

    homeNumberTextField = new JTextField();
    homeNumberTextField.setColumns(10);
    addPersonalComponent(entryPanel, homeNumberTextField, 1, 3, 1, 1.0);

    JLabel lblmobileNumber = new JLabel("Handy");
    addPersonalComponent(entryPanel, lblmobileNumber, 3, 3, 1, 0);

    mobileNumberTextField = new JTextField();
    mobileNumberTextField.setColumns(10);
    addPersonalComponent(entryPanel, mobileNumberTextField, 4, 3, 1, 1.0);

    JLabel lblemail = new JLabel("E-Mail");
    addPersonalComponent(entryPanel, lblemail, 0, 4, 1, 0);

    emailTextField = new JTextField();
    emailTextField.setColumns(20);
    addPersonalComponent(entryPanel, emailTextField, 1, 4, 4, 1.0);

    saveButton = new JButton("Speichern");
    addPersonalComponent(entryPanel, saveButton, 4, 5, 1, 0);

    statusButton = new JButton("Deaktivieren");
    addPersonalComponent(entryPanel, statusButton, 1, 5, 1, 0);

    this.add(entryPanel);
  }

  private void addPersonalComponent(JPanel panel, Component component, int x, int y, int width, double weightx) {
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.fill = GridBagConstraints.HORIZONTAL;
    constraints.gridx = x;
    constraints.gridy = y;
    constraints.gridwidth = width;
    constraints.gridheight = 1;
    constraints.weightx = weightx;
    constraints.weighty = 0;
    ((GridBagLayout) panel.getLayout()).setConstraints(component, constraints);
    panel.add(component);
  }

  public void removeEmployeeHolidayListEntries() {
    holidayContentPanel.removeAll();
  }

  /**
   * @param name
   *          Vor- und Nachname des Mitarbeiters. Meldung wird generisch
   *          erstellt: "'name' wurde gespeichert."
   */


  public void cleanFields() {
    setFirstname("");
    setLastname("");
    setStreet("");
    setPlz("");
    setCity("");
    setMobileNumber("");
    setHomeNumber("");
    setEmail("");
  }

  public boolean validateFields() {
    if (getFirstname().equals("")) {
      return false;
    }
    if (getLastname().equals("")) {
      return false;
    }
    return true;
  }

  // getter & setter
  public void setStatusButtonName(String value) {
    statusButton.setName(value);
  }

  public String getStatusButtonName() {
    return statusButton.getName();
  }

  public String getFirstname() {
    return firstnameTextField.getText();
  }

  public String getLastname() {
    return lastnameTextField.getText();
  }

  public String getStreet() {
    return streetTextField.getText();
  }

  public String getCity() {
    return cityTextField.getText();
  }

  public String getPlz() {
    return plzTextField.getText();
  }

  public String getMobileNumber() {
    return mobileNumberTextField.getText();
  }

  public String getHomeNumber() {
    return homeNumberTextField.getText();
  }

  public String getEmail() {
    return emailTextField.getText();
  }

  public void setLastname(String value) {
    this.lastnameTextField.setText(value);
  }

  public void setFirstname(String value) {
    this.firstnameTextField.setText(value);
  }

  public void setStreet(String value) {
    this.streetTextField.setText(value);
  }

  public void setCity(String value) {
    this.cityTextField.setText(value);
  }

  public void setPlz(String value) {
    this.plzTextField.setText(value);
  }

  public void setHomeNumber(String value) {
    this.homeNumberTextField.setText(value);
  }

  public void setMobileNumber(String value) {
    this.mobileNumberTextField.setText(value);
  }

  public void setEmail(String value) {
    this.emailTextField.setText(value);
  }

  // setListeners
  public void setSaveEmployeeListener(ActionListener actionListener) {
    saveButton.addActionListener(actionListener);
  }

  public void setStatusButtonListener(ActionListener actionListener) {
    statusButton.addActionListener(actionListener);
  }

  private void addGuiFeatureListener() {
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
    lastnameTextField.addKeyListener(enterKeyListener);
    firstnameTextField.addKeyListener(enterKeyListener);
    streetTextField.addKeyListener(enterKeyListener);
    cityTextField.addKeyListener(enterKeyListener);
    plzTextField.addKeyListener(enterKeyListener);
    homeNumberTextField.addKeyListener(enterKeyListener);
    mobileNumberTextField.addKeyListener(enterKeyListener);
    emailTextField.addKeyListener(enterKeyListener);
  }
}
