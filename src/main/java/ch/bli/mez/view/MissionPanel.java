package ch.bli.mez.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MissionPanel extends JPanel {

	private static final long serialVersionUID = -7537968850748849818L;
	
	private JTextField name;
	private JTextField comment;
	
	private JLabel confirmation;
	private JLabel nameError;
	
	private JButton btnAdd;
	private JButton btnClear;
	
	private JLayeredPane layeredPane = new JLayeredPane();
	

	/**
	 * Create the panel.
	 */
	public MissionPanel() {
		setLayout(new CardLayout(0, 0));
		
		add(layeredPane, "name_25118593990762");
		
		btnAdd = new JButton("Hinzufügen");
		btnAdd.setBounds(488, 74, 109, 23);
		layeredPane.add(btnAdd);
		
		name = new JTextField();
		name.setBounds(21, 73, 213, 30);
		layeredPane.add(name);
		name.setColumns(10);
		
		JLabel lblMissionName = new JLabel("Auftragsname:");
		lblMissionName.setBounds(21, 28, 109, 23);
		layeredPane.add(lblMissionName);
		
		JLabel lblComment = new JLabel("Kommentar:");
		lblComment.setBounds(252, 28, 109, 23);
		layeredPane.add(lblComment);
		
		comment = new JTextField();
		comment.setColumns(10);
		comment.setBounds(252, 73, 213, 30);
		layeredPane.add(comment);
		
		btnClear = new JButton("Leeren");
		btnClear.setBounds(607, 74, 92, 23);
		layeredPane.add(btnClear);
		
		confirmation = new JLabel("xxxyyy wurde zur Liste hinzugefügt!");
		confirmation.setForeground(new Color(0, 128, 0));
		confirmation.setBounds(21, 114, 352, 23);
		confirmation.setVisible(false);
		layeredPane.add(confirmation);
		
		nameError = new JLabel("Auftragsname darf nicht leer sein");
		nameError.setForeground(new Color(255, 0, 0));
		nameError.setBounds(21, 48, 213, 14);
		nameError.setVisible(false);
		layeredPane.add(nameError);
	
		
	}
	
	
	/**
	 * Ein JPanel auf diesem Panel platzieren. Dient im MissionPanel für die MissionListe.
	 * @param panel JPanel
	 * @param coordinates Rectangle koordinaten z.B 21, 160, 648, 138
	 */
	public void addAnotherPanel(JPanel panel, Rectangle coordinates){
		panel.setBounds(coordinates);
		layeredPane.add(panel);
	}

	public void setSaveMissionListener(ActionListener actionListener) {
		btnAdd.addActionListener(actionListener);
	}
	
	public void setClearMissionListener(ActionListener actionListener) {
		btnClear.addActionListener(actionListener);
	}
	
	public void showConfirmation(String name){
		confirmation.setText(name + " wurde zur Liste hinzugefügt!");
		confirmation.setVisible(true);
	}
	
	public void hideConfirmation() {
		confirmation.setVisible(false);
	}
	
	public void cleanFields(){
		name.setBackground(new Color(255,255,255));
		setName("");
		setComment("");
	}
	
	public void hideNameError(){
		nameError.setVisible(false);
	}
	
	public void showNameError(){
		nameError.setVisible(true);
		name.setBackground(new Color(255,90,90));
	}
	
	public String getNameMission() {
		return this.name.getText();
	}

	public String getComment() {
		return comment.getText();
	}

	
	public JTextField getTextField_Name() {
		return this.name;
	}

	public JTextField getTextField_Comment() {
		return this.comment;
	}

	public void setName(String value) {
		this.name.setText(value);
	}

	public void setComment(String value) {
		this.comment.setText(value);
	}
}
