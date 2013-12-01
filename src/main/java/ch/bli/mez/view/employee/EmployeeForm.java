package ch.bli.mez.view.employee;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.bli.mez.util.Parser;
import ch.bli.mez.view.DefaultForm;

public class EmployeeForm extends DefaultForm {

  private static final long serialVersionUID = 2105749706100780883L;

  private JTextField lastname;
  private JTextField firstname;
  private JTextField street;
  private JTextField city;
  private JTextField plz;
  private JTextField homeNumber;
  private JTextField mobileNumber;
  private JTextField email;
  private JTextField birthday;
  private JTextField ahv;

  private JButton saveButton;
  private JButton statusButton;

  public EmployeeForm() {
    build();
    setEnterKeyListener(saveButton, new JTextField[] { lastname, firstname, street, city, plz, homeNumber,
        mobileNumber, email, birthday, ahv});
  }

  private void build() {
    JPanel entryPanel = new JPanel();
    entryPanel.setLayout(new GridBagLayout());
    entryPanel.setPreferredSize(new Dimension(600, 200));

    // helpPanel to create distance between first & second column
    addPersonalComponent(entryPanel, new JPanel(), 2, 0, 1, 1.0);

    JLabel lbllastname = new JLabel("Name");
    lbllastname.setFont(lbllastname.getFont().deriveFont(Font.BOLD));
    addPersonalComponent(entryPanel, lbllastname, 0, 0, 1, 0);

    lastname = new JTextField();
    addPersonalComponent(entryPanel, lastname, 1, 0, 1, 1.0);

    JLabel lblfirstname = new JLabel("Vorname");
    lblfirstname.setFont(lblfirstname.getFont().deriveFont(Font.BOLD));
    addPersonalComponent(entryPanel, lblfirstname, 3, 0, 1, 0);

    firstname = new JTextField();
    addPersonalComponent(entryPanel, firstname, 4, 0, 1, 1.0);

    JLabel lblstreet = new JLabel("Strasse");
    addPersonalComponent(entryPanel, lblstreet, 0, 1, 1, 0);

    street = new JTextField();
    addPersonalComponent(entryPanel, street, 1, 1, 1, 1.0);

    JLabel lblplz = new JLabel("PLZ");
    addPersonalComponent(entryPanel, lblplz, 0, 2, 1, 0);

    plz = new JTextField();
    plz.setColumns(4);
    addPersonalComponent(entryPanel, plz, 1, 2, 1, 1.0);

    JLabel lblcity = new JLabel("Ort");
    addPersonalComponent(entryPanel, lblcity, 3, 2, 1, 0);

    city = new JTextField();
    city.setColumns(10);
    addPersonalComponent(entryPanel, city, 4, 2, 2, 1.0);

    JLabel lblhomeNumber = new JLabel("Festnetz");
    addPersonalComponent(entryPanel, lblhomeNumber, 0, 3, 1, 0);

    homeNumber = new JTextField();
    homeNumber.setColumns(10);
    addPersonalComponent(entryPanel, homeNumber, 1, 3, 1, 1.0);

    JLabel lblmobileNumber = new JLabel("Handy");
    addPersonalComponent(entryPanel, lblmobileNumber, 3, 3, 1, 0);

    mobileNumber = new JTextField();
    mobileNumber.setColumns(10);
    addPersonalComponent(entryPanel, mobileNumber, 4, 3, 1, 1.0);

    JLabel lblBirthday = new JLabel("Geburtsdatum");
    addPersonalComponent(entryPanel, lblBirthday, 0, 5, 1, 0);

    birthday = new JTextField();
    birthday.setColumns(10);
    addPersonalComponent(entryPanel, birthday, 1, 5, 1, 1.0);

    JLabel lblAhv = new JLabel("AHV-Nummer");
    addPersonalComponent(entryPanel, lblAhv, 3, 5, 1, 0);

    ahv = new JTextField();
    ahv.setColumns(10);
    addPersonalComponent(entryPanel, ahv, 4, 5, 1, 1.0);

    JLabel lblemail = new JLabel("E-Mail");
    addPersonalComponent(entryPanel, lblemail, 0, 4, 1, 0);

    email = new JTextField();
    email.setColumns(20);
    addPersonalComponent(entryPanel, email, 1, 4, 4, 1.0);

    saveButton = new JButton("Speichern");
    addPersonalComponent(entryPanel, saveButton, 4, 6, 1, 0);

    statusButton = new JButton();
    addPersonalComponent(entryPanel, statusButton, 1, 6, 1, 0);

    add(entryPanel);
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

  @Override
  public void cleanFields() {
    setFirstname("");
    setLastname("");
    setStreet("");
    setPlz("");
    setCity("");
    setMobileNumber("");
    setHomeNumber("");
    setEmail("");
    setBirthday(null);
    setAhv("");
  }
  
  @Override
  public void showAsCreateNew() {
    statusButton.setVisible(false);
  }

  @Override
  public Boolean validateFields() {
    if ("".equals(getFirstname())) {
      getParentPanel().showError("Es wurde kein Vorname eingegeben");
      return false;
    }
    if ("".equals(getLastname())) {
      getParentPanel().showError("Es wurde kein Nachname eingegeben");
      return false;
    }
    if (!"".equals(getPlz())){
      try {
        Integer.valueOf(getPlz());
      } catch (NumberFormatException e) {
        getParentPanel().showError("Das Format der eingegebene PLZ ist nicht gültig");
        return false;
      }
    }
    if (!"".equals(getBirthday())){
      try {
        Parser.parseDateStringToCalendar(birthday.getText());
      } catch (NumberFormatException e){
        getParentPanel().showError("Das Format des eingegebenen Geburtstags ist nicht gültig");
        return false;
      }
    }
    return true;
  }

  // getter & setter
  public void setStatusButton(Boolean status) {
    if (status) {
      statusButton.setText("Deaktivieren");
    } else {
      statusButton.setText("Aktivieren");
    }
  }

  public String getFirstname() {
    return firstname.getText();
  }

  public String getLastname() {
    return lastname.getText();
  }

  public String getStreet() {
    return street.getText();
  }

  public String getCity() {
    return city.getText();
  }

  public String getPlz() {
    return plz.getText();
  }

  public String getMobileNumber() {
    return mobileNumber.getText();
  }

  public String getHomeNumber() {
    return homeNumber.getText();
  }

  public String getEmail() {
    return email.getText();
  }

  public void setLastname(String value) {
    this.lastname.setText(value);
  }

  public void setFirstname(String value) {
    this.firstname.setText(value);
  }

  public void setStreet(String value) {
    this.street.setText(value);
  }

  public void setCity(String value) {
    this.city.setText(value);
  }

  public void setPlz(String value) {
    this.plz.setText(value);
  }

  public void setHomeNumber(String value) {
    this.homeNumber.setText(value);
  }

  public void setMobileNumber(String value) {
    this.mobileNumber.setText(value);
  }

  public void setEmail(String value) {
    this.email.setText(value);
  }

  public Calendar getBirthday() {
    return Parser.parseDateStringToCalendar(birthday.getText());
  }

  public void setBirthday(Calendar birthday) {
    this.birthday.setText(Parser.parseDateCalendarToString(birthday));
  }

  public String getAhv() {
    return ahv.getText();
  }

  public void setAhv(String ahv) {
    this.ahv.setText(ahv);
  }

  // setListeners
  public void setSaveListener(ActionListener actionListener) {
    saveButton.addActionListener(actionListener);
  }

  public void setStatusButtonListener(ActionListener actionListener) {
    statusButton.addActionListener(actionListener);
  }
}
