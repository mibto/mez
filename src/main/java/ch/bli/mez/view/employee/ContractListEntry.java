package ch.bli.mez.view.employee;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class ContractListEntry extends JPanel {

	private static final long serialVersionUID = -2823140194213618642L;

	private JTextField workquotaTextField;
	private JTextField startDateTextField;
	private JTextField endDateTextField;

	private JButton saveButton;
	private JButton deleteButton;
	
	private KeyListener enterKeyListener;
	
	private Color backGroundColor;

	/**
	 * Create the panel.
	 */
	public ContractListEntry() {
		
		setLayout(new FlowLayout(FlowLayout.LEFT));

		workquotaTextField = new JTextField(3);
		add(workquotaTextField);
		
		startDateTextField = new JTextField(7);
		add(startDateTextField);
		
		endDateTextField = new JTextField(7);
		add(endDateTextField);

		saveButton = new JButton("Speichern");
		add(saveButton);
		
		deleteButton = new JButton("Löschen");
		add(deleteButton);

		backGroundColor = getBackground();
		
		addGuiFeatureListener();
	}

	public void showSuccess() {
		setBackground(new Color(150, 255, 150));
		hideConfirmation();
	}
	
	public void showError() {
		setBackground(new Color(255, 150, 150));
		hideConfirmation();
	}
	
	private void hideConfirmation(){
		Timer timer = new Timer(900, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setBackground(backGroundColor);
			}});
		timer.setRepeats(false);
		timer.start();
	}

	public void setSaveListener(ActionListener actionListener) {
		saveButton.addActionListener(actionListener);
	}
	
	public void setDeleteListener(ActionListener actionListener){
		deleteButton.addActionListener(actionListener);
	}
	
	/**
	 * @return the enterKeyListener which is used for the textfields
	 */
	public KeyListener getEnterKeyListener(){
		return enterKeyListener;
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
		this.endDateTextField.setText(endDate);;
	}
	
	// internal methods
	private void addGuiFeatureListener(){
		enterKeyListener = new KeyListener() {
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
		workquotaTextField.addKeyListener(enterKeyListener);
		startDateTextField.addKeyListener(enterKeyListener);
		endDateTextField.addKeyListener(enterKeyListener);
	}
}
