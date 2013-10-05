package ch.bli.mez.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class MainFrame extends JFrame {

	private JTextField txtSearch;
	
	public static void main (String[] args){
		MainFrame test = new MainFrame();
		test.initView();
	}

	public MainFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new CardLayout(0, 0));

		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}

		JTabbedPane tabbedPaneMain = new JTabbedPane(JTabbedPane.TOP);
		getContentPane().add(tabbedPaneMain, "name_1380829781889670000");

		JPanel panelTimeMgmt = new JPanel();
		tabbedPaneMain.addTab("Zeiten eingeben", null, panelTimeMgmt, null);
		panelTimeMgmt.setLayout(new BorderLayout(0, 0));

		JPanel panelSearch = new JPanel();
		panelTimeMgmt.add(panelSearch, BorderLayout.NORTH);
		panelSearch.setLayout(new GridLayout(0, 10, 0, 0));

		txtSearch = new JTextField();
		panelSearch.add(txtSearch);
		txtSearch.setText("search");
		txtSearch.setColumns(10);

		JButton btnSearch = new JButton("Suchen");
		panelSearch.add(btnSearch);

		JTabbedPane tabbedPaneTimeMgmt = new JTabbedPane(JTabbedPane.LEFT);
		panelTimeMgmt.add(tabbedPaneTimeMgmt, BorderLayout.CENTER);
				
						JPanel panelNeu = new JPanel();
						tabbedPaneTimeMgmt.addTab("+ Neu", null, panelNeu, null);
		
				JPanel panelTESTROLF = new JPanel();
				tabbedPaneTimeMgmt.addTab("Rolf", null, panelTESTROLF, null);

		JPanel panelTESTMARTIN = new JPanel();
		tabbedPaneTimeMgmt.addTab("Martin", null, panelTESTMARTIN, null);

		JTabbedPane tabbedPaneEmplMgmt = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPaneMain.addTab("Mitarbeiter verwalten", null,
				tabbedPaneEmplMgmt, null);

		JTabbedPane tabbedPaneMgmt = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPaneMain.addTab("Management", null, tabbedPaneMgmt, null);
	}
	
	private void initView(){
		this.pack();
		this.setVisible(true);
	}

}
