package ch.bli.mez.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class MissionListEntry extends JPanel {

	private static final long serialVersionUID = -2823140194213618642L;

	private JTextField missionNameTextField;
	private JTextField commentTextField;

	private JCheckBox isOrganCheckBox;

	private JButton saveButton;
	private JButton deleteButton;

	/**
	 * Create the panel.
	 */
	public MissionListEntry() {
		setLayout(new FlowLayout(FlowLayout.LEFT));

		missionNameTextField = new JTextField();
		missionNameTextField.setColumns(10);
		add(missionNameTextField);

		commentTextField = new JTextField();
		commentTextField.setColumns(25);
		add(commentTextField);

		isOrganCheckBox = new JCheckBox("Orgel-Code");
		add(isOrganCheckBox);

		saveButton = new JButton("Speichern");
		add(saveButton);

		deleteButton = new JButton("Löschen");
		add(deleteButton);

	}

	private void showError() {
		setBackground(new Color(255, 150, 150));
	}

	/**
	 * Färbt den hintergrund Grün. Verschwindet nach ca. 2Sek.
	 */
	private void showSuccess() {
		final Color originalcolor = getBackground();
		setBackground(new Color(150, 255, 150));

		// SWING Timer! jeeehaaa
		int delay = 1800;
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setBackground(originalcolor);
			}
		};
		Timer timer = new Timer(delay, taskPerformer);
		timer.setRepeats(false);
		timer.start();

	}

	public void showNameError() {
		this.missionNameTextField.setBackground(new Color(255, 90, 90));
		showError();
	}

	public void hideNameError() {
		this.missionNameTextField.setBackground(new Color(255, 255, 255));
		showSuccess();
	}

	public void setSaveMissionEntryListListener(ActionListener actionListener) {
		saveButton.addActionListener(actionListener);
	}

	public void setDeleteMissionEntryListListener(ActionListener actionListener) {
		deleteButton.addActionListener(actionListener);
	}

	public String getMissionName() {
		return missionNameTextField.getText();
	}

	public String getComment() {
		return commentTextField.getText();
	}

	public boolean getIsOrgan() {
		return isOrganCheckBox.isSelected();
	}

	public void setMissionName(String missionName) {
		this.missionNameTextField.setText(missionName);
	}

	public void setComment(String value) {
		commentTextField.setText(value);
	}

	public void setIsOrgan(boolean value) {
		isOrganCheckBox.setSelected(value);
	}
}
