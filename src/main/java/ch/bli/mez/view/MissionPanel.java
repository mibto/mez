package ch.bli.mez.view;

import java.awt.BorderLayout;
import java.awt.Color;
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

	private JPanel listPanel;

	public MissionPanel() {

		setLayout(new BorderLayout());
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		add(new JScrollPane(northPanel), BorderLayout.NORTH);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		northPanel.add(topPanel);
		
		JPanel entryPanel = new JPanel();
		entryPanel.setLayout(new BoxLayout(entryPanel, BoxLayout.X_AXIS));
		topPanel.add(entryPanel, BorderLayout.CENTER);

		JPanel missionNamePanel = new JPanel();
		missionNamePanel.setLayout(new BoxLayout(missionNamePanel,
				BoxLayout.Y_AXIS));
		missionNamePanel.setAlignmentY(BOTTOM_ALIGNMENT);
		entryPanel.add(missionNamePanel);

		JLabel nameLabel = new JLabel("Auftragsname");
		nameLabel.setAlignmentX(LEFT_ALIGNMENT);
		missionNamePanel.add(nameLabel);

		missionNameTextField = new JTextField();
		missionNameTextField.setColumns(10);
		missionNameTextField.setAlignmentX(LEFT_ALIGNMENT);
		missionNamePanel.add(missionNameTextField);

		JPanel missionCommentPanel = new JPanel();
		missionCommentPanel.setLayout(new BoxLayout(missionCommentPanel,
				BoxLayout.Y_AXIS));
		missionCommentPanel.setAlignmentY(BOTTOM_ALIGNMENT);
		entryPanel.add(missionCommentPanel);

		JLabel commentLabel = new JLabel("Kommentar");
		commentLabel.setAlignmentX(LEFT_ALIGNMENT);
		missionCommentPanel.add(commentLabel);

		commentTextField = new JTextField();
		commentTextField.setColumns(25);
		commentTextField.setAlignmentX(LEFT_ALIGNMENT);
		missionCommentPanel.add(commentTextField);

		isOrganCheckBox = new JCheckBox("Orgel-Code", true);
		isOrganCheckBox.setAlignmentY(BOTTOM_ALIGNMENT);
		entryPanel.add(isOrganCheckBox);

		addButton = new JButton("Speichern");
		addButton.setAlignmentY(BOTTOM_ALIGNMENT);
		entryPanel.add(addButton);

		messagePanel = new JPanel();
		messagePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(messagePanel, BorderLayout.SOUTH);

		messageLabel = new JLabel(" ");
		messagePanel.add(messageLabel);

		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		add(new JScrollPane(centerPanel), BorderLayout.CENTER);

		listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
		centerPanel.add((listPanel));

		addGuiFeatureListener();
	}

	public void setSaveMissionListener(ActionListener actionListener) {
		addButton.addActionListener(actionListener);
	}

	public void cleanFields() {
		missionNameTextField.setBackground(new Color(255, 255, 255));
		setMissionName("");
		setComment("");
	}
	
	public void showConfirmation(String name) {
		messageLabel.setForeground(new Color(0, 128, 0));
		messageLabel.setText(name + " wurde zur Liste hinzugefügt!");
		hideMessage();
	}

	public void showNameError() {
		messageLabel.setForeground(new Color(255, 0, 0));
		messageLabel.setText("Auftragsname darf nicht leer sein");
		hideMessage();
	}
	
	private void hideMessage(){
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
		listPanel.add(missionListEntry);
		listPanel.revalidate();
		listPanel.repaint();
	}

	public void removeMissionListEntry(MissionListEntry missionListEntry) {
		listPanel.remove(missionListEntry);
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
