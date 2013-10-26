package ch.bli.mez.view.management;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class PositionPanel extends JPanel {

	private static final long serialVersionUID = -8686804124961218430L;

	private JComboBox missionComboBox;

	private JTextField numberTextField;
	private JTextField nameTextField;
	private JTextField commentTextField;

	private JButton saveButton;

	private JLabel messageLabel;

	private JPanel listPanel;

	public PositionPanel() {

		setLayout(new BorderLayout());

		// EntryPanel (north)
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		add(new JScrollPane(northPanel), BorderLayout.NORTH);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		northPanel.add(topPanel);

		JPanel missionChoosePanel = new JPanel();
		missionChoosePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(missionChoosePanel, BorderLayout.NORTH);

		JLabel missionLabel = new JLabel("Auftrag");
		missionChoosePanel.add(missionLabel);

		missionComboBox = new JComboBox();
		missionChoosePanel.add(missionComboBox);

		JPanel entryPanel = new JPanel();
		entryPanel.setLayout(new BoxLayout(entryPanel, BoxLayout.X_AXIS));
		topPanel.add(entryPanel, BorderLayout.CENTER);

		JPanel numberPanel = new JPanel();
		numberPanel.setLayout(new BoxLayout(numberPanel, BoxLayout.Y_AXIS));
		numberPanel.setAlignmentY(BOTTOM_ALIGNMENT);
		entryPanel.add(numberPanel);

		JLabel numberLabel = new JLabel("ID");
		numberLabel.setAlignmentX(LEFT_ALIGNMENT);
		numberPanel.add(numberLabel);

		numberTextField = new JTextField();
		numberTextField.setAlignmentX(LEFT_ALIGNMENT);
		numberTextField.setColumns(4);
		numberPanel.add(numberTextField);

		JPanel namePanel = new JPanel();
		namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.Y_AXIS));
		namePanel.setAlignmentY(BOTTOM_ALIGNMENT);
		entryPanel.add(namePanel);

		JLabel nameLabel = new JLabel("Name der Position");
		nameLabel.setAlignmentX(LEFT_ALIGNMENT);
		namePanel.add(nameLabel);

		nameTextField = new JTextField();
		nameTextField.setAlignmentX(LEFT_ALIGNMENT);
		nameTextField.setColumns(10);
		namePanel.add(nameTextField);

		JPanel commentPanel = new JPanel();
		commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS));
		commentPanel.setAlignmentY(BOTTOM_ALIGNMENT);
		entryPanel.add(commentPanel);

		JLabel commentLabel = new JLabel("Kommentar");
		commentLabel.setAlignmentX(LEFT_ALIGNMENT);
		commentPanel.add(commentLabel);

		commentTextField = new JTextField();
		commentTextField.setAlignmentX(LEFT_ALIGNMENT);
		commentTextField.setColumns(25);
		commentPanel.add(commentTextField);

		saveButton = new JButton("Speichern");
		saveButton.setAlignmentY(BOTTOM_ALIGNMENT);
		entryPanel.add(saveButton);

		JPanel messagePanel = new JPanel();
		topPanel.add(messagePanel, BorderLayout.SOUTH);

		messageLabel = new JLabel(" ");
		messagePanel.add(messageLabel);

		// ListPanel (center)
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		add(new JScrollPane(centerPanel), BorderLayout.CENTER);

		listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.PAGE_AXIS));
		centerPanel.add((listPanel));

		addGuiFeatureListener();
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

	private void hideMessage() {
		Timer timer = new Timer(1800, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				messageLabel.setText(" ");
			}
		});
		timer.setRepeats(false);
		timer.start();
	}

	public void setSaveButtonListener(ActionListener actionListener) {
		saveButton.addActionListener(actionListener);
	}

	public String getNumber() {
		return numberTextField.getText();
	}

	public String getPositionName() {
		return nameTextField.getText();
	}

	public String getComment() {
		return commentTextField.getText();
	}

	public void setComboBoxItems(HashMap<Integer, String> missionList) {
		for (int i = 0; i < missionList.size(); i++) {
			missionComboBox.addItem(new missionComboBoxItem(i, missionList
					.get(i)));
		}
		missionComboBox.setSelectedIndex(0);
	}

	public Integer getSelectedMission() {
		return ((missionComboBoxItem) missionComboBox.getSelectedItem())
				.getId();
	}

	private void cleanFields() {
		numberTextField.setText("");
		nameTextField.setText("");
		commentTextField.setText("");
	}

	private void addGuiFeatureListener() {
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cleanFields();
			}
		});
		KeyListener enterKeyListener = new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					saveButton.doClick();
				}
			}
		};
		numberTextField.addKeyListener(enterKeyListener);
		nameTextField.addKeyListener(enterKeyListener);
		commentTextField.addKeyListener(enterKeyListener);
	}

	private class missionComboBoxItem {
		private Integer id;
		private String name;

		public missionComboBoxItem(Integer id, String name) {
			this.id = id;
			this.name = name;
		}

		public Integer getId() {
			return id;
		}

		@Override
		public String toString() {
			return name;
		}

	}
}