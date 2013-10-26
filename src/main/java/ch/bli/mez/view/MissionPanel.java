package ch.bli.mez.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class MissionPanel extends JPanel {

	private static final long serialVersionUID = -7537968850748849818L;

	private JTextField missionNameTextField;
	private JTextField commentTextField;
	private JCheckBox isOrganCheckBox;

	private JPanel messagePanel;
	private JLabel messageLabel;

	private JButton addButton;
	private JButton clearButton;

	private JPanel missionListEntryContainer;

	public MissionPanel() {

		setLayout(new BorderLayout());
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		add(new JScrollPane(northPanel), BorderLayout.NORTH);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		northPanel.add(topPanel);
		
		JPanel entryPanel = new JPanel();
		entryPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(entryPanel, BorderLayout.CENTER);

		JPanel missionNamePanel = new JPanel();
		missionNamePanel.setLayout(new BoxLayout(missionNamePanel,
				BoxLayout.Y_AXIS));
		entryPanel.add(missionNamePanel);

		JLabel nameLabel = new JLabel("Auftragsname:");
		nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		missionNamePanel.add(nameLabel);

		missionNameTextField = new JTextField();
		missionNameTextField.setColumns(10);
		missionNameTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
		missionNamePanel.add(missionNameTextField);

		JPanel missionCommentPanel = new JPanel();
		missionCommentPanel.setLayout(new BoxLayout(missionCommentPanel,
				BoxLayout.Y_AXIS));
		entryPanel.add(missionCommentPanel);

		JLabel commentLabel = new JLabel("Kommentar:");
		commentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
		missionCommentPanel.add(commentLabel);

		commentTextField = new JTextField();
		commentTextField.setColumns(25);
		commentTextField.setAlignmentX(Component.LEFT_ALIGNMENT);
		missionCommentPanel.add(commentTextField);

		isOrganCheckBox = new JCheckBox("Orgel-Code", true);
		entryPanel.add(isOrganCheckBox);

		addButton = new JButton("Hinzufügen");
		entryPanel.add(addButton);

		clearButton = new JButton("Leeren");
		entryPanel.add(clearButton);

		messagePanel = new JPanel();
		messagePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(messagePanel, BorderLayout.SOUTH);

		messageLabel = new JLabel(" ");
		messagePanel.add(messageLabel);

		JPanel listPanel = new JPanel();
		listPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		add(new JScrollPane(listPanel), BorderLayout.CENTER);

		missionListEntryContainer = new JPanel();
		missionListEntryContainer.setLayout(new BoxLayout(
				missionListEntryContainer, BoxLayout.PAGE_AXIS));
		listPanel.add((missionListEntryContainer));

		addGuiFeatureListener();
	}

	public void setSaveMissionListener(ActionListener actionListener) {
		addButton.addActionListener(actionListener);
	}

	public void setClearMissionListener(ActionListener actionListener) {
		clearButton.addActionListener(actionListener);
	}

	public void cleanFields() {
		missionNameTextField.setBackground(new Color(255, 255, 255));
		setMissionName("");
		setComment("");
	}
	
	public void showConfirmation(String name) {
		messageLabel.setForeground(new Color(0, 128, 0));
		messageLabel.setText(name + " wurde zur Liste hinzugefügt!");
		hideMessageLabel();
	}

	public void showNameError() {
		messageLabel.setForeground(new Color(255, 0, 0));
		messageLabel.setText("Auftragsname darf nicht leer sein");
		hideMessageLabel();
	}
	
	private void hideMessageLabel(){
		Timer timer = new Timer(1800, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				messageLabel.setText(" ");
			}});
		timer.setRepeats(false);
		timer.start();
	}

	public String getMissionName() {
		return missionNameTextField.getText();
	}

	public String getComment() {
		return commentTextField.getText();
	}

	public void setMissionName(String missionName) {
		this.missionNameTextField.setText(missionName);
	}

	public void setComment(String value) {
		commentTextField.setText(value);
	}

	public void addMissionListEntry(MissionListEntry missionListEntry) {
		missionListEntryContainer.add(missionListEntry);
		missionListEntryContainer.revalidate();
		missionListEntryContainer.repaint();
	}

	public void removeMissionListEntry(MissionListEntry missionListEntry) {
		missionListEntryContainer.remove(missionListEntry);
	}

	public boolean getIsOrgan() {
		if (isOrganCheckBox.isSelected()) {
			return true;
		}
		return false;
	}

	private void addGuiFeatureListener() {
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cleanFields();
				isOrganCheckBox.setSelected(true);
			}
		});
		KeyListener enterKeyListener = new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					addButton.doClick();
				}
			}
		};
		missionNameTextField.addKeyListener(enterKeyListener);
		commentTextField.addKeyListener(enterKeyListener);
	}
}
