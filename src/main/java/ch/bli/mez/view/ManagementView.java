package ch.bli.mez.view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;



public class ManagementView extends JPanel {

	private static final long serialVersionUID = -4318728728729330881L;
	
	private JTabbedPane tabbedPane;
	
	/*
	 * Fügt einen neuen Tab hinzu
	 */
	public void addManagementTab(String name, JPanel management) {

		tabbedPane.addTab(name, null, management, null);

	}
 //views zu den tabs hinzufügen (tabbedpane). siehe auch mainView
	
  public void setMissionPanel(MissionPanel view) {
    // TODO Auto-generated method stub
    
  }

}
