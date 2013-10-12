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


public class EmployeePanel extends PanelWithMap {

	private static final long serialVersionUID = -1774268602344482972L;
	
	private JTextField textField_lastname;
	private JTextField textField_firstname;
	private JTextField textField_street;
	private JTextField textField_city;
	private JTextField textField_plz;
	private JTextField textField_homeNumber;
	private JTextField textField_mobileNumber;
	private JTextField textField_email;
	
	private JButton btnSave;

	private JLayeredPane layeredPane = new JLayeredPane();
	
	/**
	 * Create the panel for employees
	 * 
	 * Panels for the contracts and timetransfers are separated
	 * 
	 */
	public EmployeePanel() {
		setLayout(new CardLayout(0, 0));
		
		add(layeredPane, "name_13971428008795");
		
		JLabel lbllastname = new JLabel("Name");
		lbllastname.setBounds(25, 27, 111, 14);
		layeredPane.add(lbllastname);
		
		textField_lastname = new JTextField();
		textField_lastname.setBounds(133, 22, 196, 24);
		layeredPane.add(textField_lastname);
		textField_lastname.setColumns(10);
		
		JLabel lblfirstname = new JLabel("Vorname");
		lblfirstname.setBounds(25, 68, 111, 14);
		layeredPane.add(lblfirstname);
		
		textField_firstname = new JTextField();
		textField_firstname.setBounds(133, 63, 196, 24);
		layeredPane.add(textField_firstname);
		textField_firstname.setColumns(10);
		
		JLabel lblstreet = new JLabel("Strasse");
		lblstreet.setBounds(25, 109, 111, 14);
		layeredPane.add(lblstreet);
		
		textField_street = new JTextField();
		textField_street.setBounds(133, 104, 196, 24);
		layeredPane.add(textField_street);
		textField_street.setColumns(10);

		JLabel lblcity = new JLabel("Ort");
		lblcity.setBounds(25, 150, 111, 14);
		layeredPane.add(lblcity);
		
		textField_city = new JTextField();
		textField_city.setBounds(133, 145, 196, 24);
		layeredPane.add(textField_city);
		textField_city.setColumns(10);
		
		JLabel lblplz = new JLabel("Plz");
		lblplz.setBounds(25, 191, 111, 14);
		layeredPane.add(lblplz);
		
		textField_plz = new JTextField();
		textField_plz.setBounds(133, 186, 196, 24);
		layeredPane.add(textField_plz);
		textField_plz.setColumns(10);

		JLabel lblhomeNumber = new JLabel("Festnetz Tel.Nr.");
		lblhomeNumber.setBounds(24, 232, 112, 14);
		layeredPane.add(lblhomeNumber);
		
		textField_homeNumber = new JTextField();
		textField_homeNumber.setBounds(133, 227, 196, 24);
		layeredPane.add(textField_homeNumber);
		textField_homeNumber.setColumns(10);
		
		JLabel lblmobileNumber = new JLabel("Natel Tel.Nr.");
		lblmobileNumber.setBounds(25, 273, 111, 14);
		layeredPane.add(lblmobileNumber);
		
		textField_mobileNumber = new JTextField();
		textField_mobileNumber.setBounds(133, 268, 196, 24);
		layeredPane.add(textField_mobileNumber);
		textField_mobileNumber.setColumns(10);
		
		JLabel lblemail = new JLabel("E-Mail");
		lblemail.setBounds(24, 314, 112, 14);
		layeredPane.add(lblemail);
		
		textField_email = new JTextField();
		textField_email.setBounds(133, 309, 196, 24);
		layeredPane.add(textField_email);
		textField_email.setColumns(10);
		
		btnSave = new JButton("Speichern");
		btnSave.setBounds(25, 363, 89, 23);
		layeredPane.add(btnSave);
		
	}
	
	/*
	 * Koordinaten:
	 * 
	*/
	public void setExtraPanel(JPanel panel, Rectangle coordinates){
		panel.setBounds(coordinates);
		this.layeredPane.add(panel);
	}
	
	// Automatisch generierte GET und SET methoden

	public JTextField getTextField_lastname() {
		return textField_lastname;
	}

	public void setTextField_lastname(String value) {
		this.textField_lastname.setText(value);
	}

	public JTextField getTextField_firstname() {
		return textField_firstname;
	}

	public void setTextField_firstname(String value) {
		this.textField_firstname.setText(value);
	}

	public JTextField getTextField_street() {
		return textField_street;
	}

	public void setTextField_street(String value) {
		this.textField_street.setText(value);
	}

	public JTextField getTextField_city() {
		return textField_city;
	}

	public void setTextField_city(String value) {
		this.textField_city.setText(value);
	}

	public JTextField getTextField_plz() {
		return textField_plz;
	}

	public void setTextField_plz(String value) {
		this.textField_plz.setText(value);
	}

	public JTextField getTextField_homeNumber() {
		return textField_homeNumber;
	}

	public void setTextField_homeNumber(String value) {
		this.textField_homeNumber.setText(value);
	}

	public JTextField getTextField_mobileNumber() {
		return textField_mobileNumber;
	}

	public void setTextField_mobileNumber(String value) {
		this.textField_mobileNumber.setText(value);
	}

	public JTextField getTextField_email() {
		return textField_email;
	}

	public void setTextField_email(String value) {
		this.textField_email.setText(value);
	}
	
	public JButton getBtnSave() {
		return btnSave;
	}
	
	public void setBtnSaveName(String id) {
		 btnSave.setName(id);
	}
}