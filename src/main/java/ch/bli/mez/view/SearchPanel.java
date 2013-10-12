package ch.bli.mez.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * SearchPanel ist das kleine Suchfeld welches oberhalb der Tabs mit
 * Mitarbeiternamen platziert wird Der SearchPanel wird für "Zeiten erfassen"
 * sowie "Mitarbeiter verwalten" verwendet
 * 
 * @author dave
 * @version 1.0
 */
public class SearchPanel extends JPanel {

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

	/**
	 * GUI interne Listener werden hier hinzugefügt
	 */
	public void initializeTextField() {
		txtSearch.setText("search...");
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
		txtSearch.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnSearch.doClick();
				}
			}
		});
	}

	/**
	 * Actionlistener für den "Search-Button" hinzufügen
	 * 
	 * @param actionListener
	 *            ActionsListener welche dem "Search-Button" hinzugefügt werden
	 *            soll
	 */
	public void setButtonSearchActionListener(ActionListener actionListener) {
		btnSearch.addActionListener(actionListener);
	}

	/**
	 * Such TextFeld auslesen
	 * 
	 * @return den String aus dem Textfeld
	 */
	public String getSearchText() {
		return txtSearch.getText();
	}

}
