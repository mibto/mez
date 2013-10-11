package ch.bli.mez.view;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EmployeePanel extends JPanel {
	private JPanel panel_for_timetransfer;
	private JTextField textField_lastname;
	private JTextField textField_firstname;
	private JTextField textField_street;
	private JTextField textField_city;
	private JTextField textField_plz;
	private JTextField textField_homeNumber;
	private JTextField textField_mobileNumber;
	private JTextField textField_email;

	/**
	 * Create the panel for employees
	 * 
	 * Panels for the contracts and timetransfers are separated
	 * 
	 */
	public EmployeePanel() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(80dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(30dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(50dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lbllastname = new JLabel("Name");
		add(lbllastname, "4, 4, left, default");
		
		textField_lastname = new JTextField();
		add(textField_lastname, "6, 4, fill, default");
		textField_lastname.setColumns(10);
		
		JLabel lblfirstname = new JLabel("Vorname");
		add(lblfirstname, "4, 6, left, default");
		
		textField_firstname = new JTextField();
		add(textField_firstname, "6, 6, fill, default");
		textField_firstname.setColumns(10);
		
		JLabel lblstreet = new JLabel("Strasse");
		add(lblstreet, "4, 8, left, default");
		
		textField_street = new JTextField();
		add(textField_street, "6, 8, fill, default");
		textField_street.setColumns(10);
		
		JLabel lblcity = new JLabel("Ort");
		add(lblcity, "4, 10, left, default");
		
		textField_city = new JTextField();
		add(textField_city, "6, 10, fill, default");
		textField_city.setColumns(10);
		
		JLabel lblplz = new JLabel("Plz");
		add(lblplz, "4, 12, left, default");
		
		textField_plz = new JTextField();
		add(textField_plz, "6, 12, fill, default");
		textField_plz.setColumns(10);
		
		JLabel lblhomeNumber = new JLabel("Festnetz Tel.Nr.");
		add(lblhomeNumber, "4, 14, left, default");
		
		textField_homeNumber = new JTextField();
		add(textField_homeNumber, "6, 14, fill, default");
		textField_homeNumber.setColumns(10);
		
		JLabel lblmobileNumber = new JLabel("Natel Tel.Nr.");
		add(lblmobileNumber, "4, 16, left, default");
		
		textField_mobileNumber = new JTextField();
		add(textField_mobileNumber, "6, 16, fill, default");
		textField_mobileNumber.setColumns(10);
		
		JLabel lblemail = new JLabel("E-Mail");
		add(lblemail, "4, 18, left, default");
		
		textField_email = new JTextField();
		add(textField_email, "6, 18, fill, default");
		textField_email.setColumns(10);
	}
	
	/*
	 * Koordinaten:
	 * Zeitübertragung: 	10, 4, 1, 15, fill, fill
	 * Verträge: 			4, 22, 8, 2, fill, fill
	 */
	public void setExtraPanel(JPanel panel, String coordinates){
		// EXAMPLE: panel_for_timetransfer = new TimeTransferPanel();
		// EXAMPLE add(panel_for_contracts, "4, 22, 8, 2, fill, fill");
		add(panel, coordinates);

	}
	
	// Automatisch generierter GET und SET methoden

	public JTextField getTextField_lastname() {
		return textField_lastname;
	}

	public void setTextField_lastname(JTextField textField_lastname) {
		this.textField_lastname = textField_lastname;
	}

	public JTextField getTextField_firstname() {
		return textField_firstname;
	}

	public void setTextField_firstname(JTextField textField_firstname) {
		this.textField_firstname = textField_firstname;
	}

	public JTextField getTextField_street() {
		return textField_street;
	}

	public void setTextField_street(JTextField textField_street) {
		this.textField_street = textField_street;
	}

	public JTextField getTextField_city() {
		return textField_city;
	}

	public void setTextField_city(JTextField textField_city) {
		this.textField_city = textField_city;
	}

	public JTextField getTextField_plz() {
		return textField_plz;
	}

	public void setTextField_plz(JTextField textField_plz) {
		this.textField_plz = textField_plz;
	}

	public JTextField getTextField_homeNumber() {
		return textField_homeNumber;
	}

	public void setTextField_homeNumber(JTextField textField_homeNumber) {
		this.textField_homeNumber = textField_homeNumber;
	}

	public JTextField getTextField_mobileNumber() {
		return textField_mobileNumber;
	}

	public void setTextField_mobileNumber(JTextField textField_mobileNumber) {
		this.textField_mobileNumber = textField_mobileNumber;
	}

	public JTextField getTextField_email() {
		return textField_email;
	}

	public void setTextField_email(JTextField textField_email) {
		this.textField_email = textField_email;
	}

}
