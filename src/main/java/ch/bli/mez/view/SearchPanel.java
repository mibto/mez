package ch.bli.mez.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchPanel extends JPanel {

	/**
	 * SearchPanel ist das kleine Suchfeld welches oberhalb der Tabs mit
	 * Mitarbeiternamen platziert wird Der SearchPanel wird für
	 * "Zeiten erfassen" sowie "Mitarbeiter verwalten" verwendet
	 */
	private static final long serialVersionUID = 5735685030723299266L;
	private JTextField txtSearch;
	private JButton btnSearch;

	public SearchPanel() {
		txtSearch = new JTextField();
		btnSearch = new JButton("Suchen");
		initializePanel();
	}

	private void initializePanel() {
		JPanel panel = new JPanel();
		initializeTextField();
		panel.add(txtSearch);
		panel.add(btnSearch);
		this.setLayout(new BorderLayout());
		this.add(panel, BorderLayout.WEST);
	}
	
	public void initializeTextField(){
		txtSearch.setText("search");
		txtSearch.setColumns(10);
		txtSearch.addFocusListener(new FocusListener() {
			
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				txtSearch.setText(null);
			}
		});
	}

	public void setButtonSearchActionListener(ActionListener actionListener) {
		btnSearch.addActionListener(actionListener);
	}
	
	public String getSearchText(){
		return txtSearch.getText();
	}

}
