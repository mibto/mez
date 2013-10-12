package ch.bli.mez.view;

import java.awt.CardLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class EmployeePanel extends JPanel {

	private static final long serialVersionUID = -1774268602344482972L;
	
	private JTextField lastname;
	private JTextField firstname;
	private JTextField street;
	private JTextField city;
	private JTextField plz;
	private JTextField homeNumber;
	private JTextField mobileNumber;
	private JTextField email;
	
	private JButton btnSave;

	private JLayeredPane layeredPane = new JLayeredPane();
	
	/**
	 * Create the panel for employees
	 * 
	 * Panels for the contracts and timetransfers are separated
	 * 
	 */
	public EmployeePanel(Integer id) {
		setLayout(new CardLayout(0, 0));
		
		add(layeredPane, "name_13971428008795");
		
		JLabel lbllastname = new JLabel("Name");
		lbllastname.setBounds(25, 27, 111, 14);
		layeredPane.add(lbllastname);
		
		lastname = new JTextField();
		lastname.setBounds(133, 22, 196, 24);
		layeredPane.add(lastname);
		lastname.setColumns(10);
		
		JLabel lblfirstname = new JLabel("Vorname");
		lblfirstname.setBounds(25, 68, 111, 14);
		layeredPane.add(lblfirstname);
		
		firstname = new JTextField();
		firstname.setBounds(133, 63, 196, 24);
		layeredPane.add(firstname);
		firstname.setColumns(10);
		
		JLabel lblstreet = new JLabel("Strasse");
		lblstreet.setBounds(25, 109, 111, 14);
		layeredPane.add(lblstreet);
		
		street = new JTextField();
		street.setBounds(133, 104, 196, 24);
		layeredPane.add(street);
		street.setColumns(10);

		JLabel lblcity = new JLabel("Ort");
		lblcity.setBounds(25, 150, 111, 14);
		layeredPane.add(lblcity);
		
		city = new JTextField();
		city.setBounds(133, 145, 196, 24);
		layeredPane.add(city);
		city.setColumns(10);
		
		JLabel lblplz = new JLabel("Plz");
		lblplz.setBounds(25, 191, 111, 14);
		layeredPane.add(lblplz);
		
		plz = new JTextField();
		plz.setBounds(133, 186, 196, 24);
		layeredPane.add(plz);
		plz.setColumns(10);

		JLabel lblhomeNumber = new JLabel("Festnetz Tel.Nr.");
		lblhomeNumber.setBounds(24, 232, 112, 14);
		layeredPane.add(lblhomeNumber);
		
		homeNumber = new JTextField();
		homeNumber.setBounds(133, 227, 196, 24);
		layeredPane.add(homeNumber);
		homeNumber.setColumns(10);
		
		JLabel lblmobileNumber = new JLabel("Natel Tel.Nr.");
		lblmobileNumber.setBounds(25, 273, 111, 14);
		layeredPane.add(lblmobileNumber);
		
		mobileNumber = new JTextField();
		mobileNumber.setBounds(133, 268, 196, 24);
		layeredPane.add(mobileNumber);
		mobileNumber.setColumns(10);
		
		JLabel lblemail = new JLabel("E-Mail");
		lblemail.setBounds(24, 314, 112, 14);
		layeredPane.add(lblemail);
		
		email = new JTextField();
		email.setBounds(133, 309, 196, 24);
		layeredPane.add(email);
		email.setColumns(10);
		
		btnSave = new JButton("Speichern");
		btnSave.setName(id.toString());
		btnSave.setBounds(25, 363, 89, 23);
		layeredPane.add(btnSave);
		
	}
	
	public void setSaveChangedEmployeeListener(ActionListener actionListener) {
		btnSave.addActionListener(actionListener);
	}
	
	/*
	 * Koordinaten:
	 * 
	*/
	public void setExtraPanel(JPanel panel, Rectangle coordinates){
		panel.setBounds(coordinates);
		this.layeredPane.add(panel);
	}
	
	public String getFirstname(){
		return firstname.getText();
	}
	
	public String getLastname(){
		return lastname.getText();
	}
	
	public String getStreet(){
		return street.getText();
	}
	
	public String getCity(){
		return city.getText();
	}
	
	public String getPlz(){
		return plz.getText();
	}
	
	public String getMobileNumber(){
		return mobileNumber.getText();
	}
	
	public String getHomeNumber(){
		return homeNumber.getText();
	}
	
	public String getEmail(){
		return email.getText();
	}
	
	// Automatisch generierte GET und SET methoden

	public JTextField getlastname() {
		return lastname;
	}

	public void setlastname(String value) {
		this.lastname.setText(value);
	}

	public JTextField getfirstname() {
		return firstname;
	}

	public void setfirstname(String value) {
		this.firstname.setText(value);
	}

	public JTextField getstreet() {
		return street;
	}

	public void setstreet(String value) {
		this.street.setText(value);
	}

	public JTextField getcity() {
		return city;
	}

	public void setcity(String value) {
		this.city.setText(value);
	}

	public JTextField getplz() {
		return plz;
	}

	public void setplz(String value) {
		this.plz.setText(value);
	}

	public JTextField gethomeNumber() {
		return homeNumber;
	}

	public void sethomeNumber(String value) {
		this.homeNumber.setText(value);
	}

	public JTextField getmobileNumber() {
		return mobileNumber;
	}

	public void setmobileNumber(String value) {
		this.mobileNumber.setText(value);
	}

	public JTextField getemail() {
		return email;
	}

	public void setemail(String value) {
		this.email.setText(value);
	}
	
	public JButton getBtnSave() {
		return btnSave;
	}
	
	public void setBtnSaveName(String id) {
		 btnSave.setName(id);
	}
}
