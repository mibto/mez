package ch.bli.mez.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MissionListEntry extends JPanel {

	private static final long serialVersionUID = -2823140194213618642L;
	
	private JTextField name;
	private JTextField comment;
	
	private JButton btnSave;
	private JButton btnDelete;

	/**
	 * Create the panel.
	 */
	public MissionListEntry() {
		setForeground(new Color(0, 0, 0));
		setLayout(new CardLayout(0, 0));
		setMinimumSize(new Dimension(800, 50));
		setMaximumSize(new Dimension(800, 50));
		
		JLayeredPane layeredPane = new JLayeredPane();
		add(layeredPane, "name_19448412410232");
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(10, 11, 216, 30);
		layeredPane.add(name);
		
		
		comment = new JTextField();
		comment.setColumns(10);
		comment.setBounds(236, 11, 216, 30);
		layeredPane.add(comment);
		
		btnSave = new JButton("Speichern");
		btnSave.setBounds(474, 15, 89, 23);
		layeredPane.add(btnSave);
		
		btnDelete = new JButton("Löschen");
		btnDelete.setBounds(569, 15, 89, 23);
		layeredPane.add(btnDelete);

	}
	
	
	private void showError(){
		setBackground(new Color(255, 150, 150));
	}
	
	private void showSuccess(){
		setBackground(new Color(150, 255, 150));
	}
	
	public void showNameError() {
		this.name.setBackground(new Color(255, 0, 0));
		showError();
	}
	
	public void hideNameError() {
		this.name.setBackground(new Color(255, 255, 255));
		showSuccess();
	}

	
	public void setSaveMissionEntryListListener(ActionListener actionListener) {
		btnSave.addActionListener(actionListener);
	}
	
	public void setDeleteMissionEntryListListener(ActionListener actionListener) {
		btnDelete.addActionListener(actionListener);
	}

	
	public JTextField getTextField_Name(){
		return this.comment;
	}

	public JTextField getTextField_Comment(){
		return this.name;
	}

	public String getNameMission() {
		return this.name.getText();
	}

	public String getComment() {
		return this.comment.getText();
	}
	
	public void setName(String value) {
		this.name.setText(value);
	}

	public void setComment(String value) {
		this.comment.setText(value);
	}
	
	
}
