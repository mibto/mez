package ch.bli.mez.view;

import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

public class TimeTransferPanel extends PanelWithMap {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3988811507879670389L;
	private JTextField textField_overtime;
	private JTextField textField_holidays;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public TimeTransferPanel() {
		setLayout(new CardLayout(0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		add(layeredPane, "name_14246905828695");
		
		JLabel lblovertime = new JLabel("Überstunden");
		lblovertime.setBounds(10, 55, 62, 14);
		layeredPane.add(lblovertime);
		
		textField_overtime = new JTextField();
		textField_overtime.setBounds(96, 52, 86, 20);
		layeredPane.add(textField_overtime);
		textField_overtime.setEditable(false);
		textField_overtime.setColumns(10);
		
		JLabel lblholidays = new JLabel("Ferientage");
		lblholidays.setBounds(10, 24, 52, 14);
		layeredPane.add(lblholidays);
		
		textField_holidays = new JTextField();
		textField_holidays.setBounds(96, 21, 86, 20);
		layeredPane.add(textField_holidays);
		textField_holidays.setEditable(false);
		textField_holidays.setColumns(10);
		
		JLabel lbltimetransfer = new JLabel("Übertrag:");
		lbltimetransfer.setBounds(10, 86, 47, 14);
		layeredPane.add(lbltimetransfer);
		
		textField_2 = new JTextField();
		textField_2.setBounds(96, 83, 86, 20);
		layeredPane.add(textField_2);
		textField_2.setEditable(false);
		textField_2.setColumns(10);

	}

}
