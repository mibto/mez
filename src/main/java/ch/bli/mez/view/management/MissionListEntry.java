package ch.bli.mez.view.management;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class MissionListEntry extends JPanel {

	private static final long serialVersionUID = -2823140194213618642L;

	private JTextField nameTextField;
	private JTextField commentTextField;

	private JCheckBox isOrganCheckBox;

	private JButton saveButton;
	private JButton statusButton;
	private Color backGroundColor;

	/**
	 * Create the panel.
	 */
	public MissionListEntry(boolean isActive) {
		setLayout(new FlowLayout(FlowLayout.LEFT));

		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		add(nameTextField);
		

		commentTextField = new JTextField();
		commentTextField.setColumns(25);
		add(commentTextField);

		isOrganCheckBox = new JCheckBox("Orgel-Code");
		add(isOrganCheckBox);

		saveButton = new JButton("Speichern");
		add(saveButton);

		statusButton = new JButton("Löschen");
		add(statusButton);

		backGroundColor = getBackground();
		setActive(isActive);
		addGuiFeatureListener();
	}
	
	public void setActive(boolean status){
		if(status){
			statusButton.setText("Deaktivieren");
		} else {
			statusButton.setText("Aktivieren");
		}
		nameTextField.setEnabled(status);
		commentTextField.setEnabled(status);
		saveButton.setEnabled(status);
		isOrganCheckBox.setEnabled(status);
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
	
	 public boolean validateFields(String missionName){
	    if (getMissionName().equals("")){
	      showError();
	      setMissionName(missionName);
	      return false;
	    }
	    showSuccess();
	    return true;
	  }

	public void setSaveMissionEntryListListener(ActionListener actionListener) {
		saveButton.addActionListener(actionListener);
	}

	public void setStatusMissionEntryListListener(ActionListener actionListener) {
		statusButton.addActionListener(actionListener);
	}

	public String getMissionName() {
		return nameTextField.getText();
	}

	public String getComment() {
		return commentTextField.getText();
	}

	public boolean getIsOrgan() {
		return isOrganCheckBox.isSelected();
	}

	public void setMissionName(String missionName) {
		this.nameTextField.setText(missionName);
	}

	public void setComment(String value) {
		commentTextField.setText(value);
	}

	public void setIsOrgan(boolean value) {
		isOrganCheckBox.setSelected(value);
	}
	
	private void addGuiFeatureListener(){
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
		nameTextField.addKeyListener(enterKeyListener);
		commentTextField.addKeyListener(enterKeyListener);
	}
}
