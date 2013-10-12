package ch.bli.mez.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.CardLayout;
import javax.swing.JLayeredPane;

public class ContractPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ContractPanel() {
		setLayout(new CardLayout(0, 0));
		
		JLayeredPane layeredPane = new JLayeredPane();
		add(layeredPane, "name_14434211068954");
		
		JLabel lblVertraege = new JLabel("Verträge ....");
		lblVertraege.setBounds(130, 37, 183, 24);
		layeredPane.add(lblVertraege);

	}

}
