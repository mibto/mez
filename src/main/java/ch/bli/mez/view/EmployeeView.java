package ch.bli.mez.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class EmployeeView extends JPanel {

   /**
   * 
   */
	private static final long serialVersionUID = 8767516928379563985L;

	private JTabbedPane tabbedPane;

	

	// SearchPanel Wird noch umgeschrieben!!
	public EmployeeView(SearchPanel searchPanel) {

		// @Zumbühl, ich habe ein SearchPanel kreiert, welches sowohl für diese
		// Klasse als auch für die Klasse ZeiterfassenView wiederverwendet
		// werden kann
		this.tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		
		this.setLayout(new BorderLayout());
		this.add(searchPanel, BorderLayout.NORTH);
		this.add(tabbedPane, BorderLayout.CENTER);
		

		// musterTab - muss weg, da die Tabs vom Controller mit der Methode
		// addEmployeeTab hinzugefügt werden sollte

	}

	
	
	
	/*
	 * Fügt einen neuen Tab hinzu
	 */
	public void addEmployeeTab(String name, JPanel employeePanel) {

		tabbedPane.addTab(name, null, employeePanel, null);

	}


}
