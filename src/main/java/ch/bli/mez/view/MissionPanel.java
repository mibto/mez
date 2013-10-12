package ch.bli.mez.view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class MissionPanel extends JPanel {
	private JTextField name;
	private JTextField comment;

	/**
	 * Create the panel.
	 */
	public MissionPanel() {
		setLayout(new CardLayout(0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		add(layeredPane, "name_25118593990762");
		
		JButton btnAdd = new JButton("Hinzufügen");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdd.setBounds(488, 63, 89, 23);
		layeredPane.add(btnAdd);
		
		name.setBounds(21, 62, 213, 24);
		layeredPane.add(name);
		name.setColumns(10);
		
		JLabel lblMissionName = new JLabel("Auftragsname:");
		lblMissionName.setBounds(21, 28, 109, 23);
		layeredPane.add(lblMissionName);
		
		JLabel lblComment = new JLabel("Kommentar:");
		lblComment.setBounds(252, 28, 109, 23);
		layeredPane.add(lblComment);
		
		comment.setColumns(10);
		comment.setBounds(252, 62, 213, 24);
		layeredPane.add(comment);
		
		JButton btnClear = new JButton("Leeren");
		btnClear.setBounds(587, 63, 82, 23);
		layeredPane.add(btnClear);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(21, 160, 648, 138);
		layeredPane.add(panel);
		
		JTextPane txtpnPlatzhalterFrDie = new JTextPane();
		panel.add(txtpnPlatzhalterFrDie);
		txtpnPlatzhalterFrDie.setEnabled(false);
		txtpnPlatzhalterFrDie.setEditable(false);
		txtpnPlatzhalterFrDie.setText("Platzhalter für die spätere Liste");

	}

	
	// Automatisch generierte GET methoden
	
	public JTextField getTextField_Name() {
		return name;
	}

	public JTextField getTextField_Comment() {
		return comment;
	}
}
