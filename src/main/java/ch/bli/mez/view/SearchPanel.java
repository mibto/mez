package ch.bli.mez.view;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

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
		this.setLayout(new GridLayout(0, 10, 0, 0));
		add(txtSearch);
		txtSearch.setText("search");
		txtSearch.setColumns(10);
		add(btnSearch);
	}

	public void setButtonSearchActionListener(ActionListener actionListener) {
		btnSearch.addActionListener(actionListener);
	}

}
