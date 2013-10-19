package ch.bli.mez.view;

import java.awt.CardLayout;
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
		setLayout(new CardLayout(0, 0));
		setMinimumSize(new Dimension(700, 35));
		
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
