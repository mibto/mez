package ch.bli.mez.view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;



public class ManagementView extends JPanel {

	private static final long serialVersionUID = -4318728728729330881L;
	
	private JTabbedPane tabbedPane;
	
	/*
	 * Fügt einen neuen Tab hinzu
	 */
	public void addEmployeeTab(String name, JPanel mission) {

		tabbedPane.addTab(name, null, mission, null);

	}

}
