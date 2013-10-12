package ch.bli.mez.view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
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
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
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
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblovertime = new JLabel("Überstunden");
		add(lblovertime, "4, 4, right, default");
		
		textField_overtime = new JTextField();
		textField_overtime.setEditable(false);
		add(textField_overtime, "6, 4, fill, default");
		textField_overtime.setColumns(10);
		
		JLabel lblholidays = new JLabel("Ferientage");
		add(lblholidays, "4, 6, right, default");
		
		textField_holidays = new JTextField();
		textField_holidays.setEditable(false);
		add(textField_holidays, "6, 6, fill, default");
		textField_holidays.setColumns(10);
		
		JLabel lbltimetransfer = new JLabel("Übertrag:");
		add(lbltimetransfer, "4, 8, right, default");
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		add(textField_2, "6, 8, fill, default");
		textField_2.setColumns(10);

	}

}
