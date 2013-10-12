package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ch.bli.mez.view.SearchPanel;

/**
 * Controller für das Searchpanel mit der Aufgabe die Suche zu verarbeiten, und
 * das Resultat anzuzeigen
 * 
 * @author dave
 * @version 1.0
 */
public class SearchController {

	private final SearchPanel searchPanel;

	public SearchController() {
		this.searchPanel = new SearchPanel();
		addListener();
	}

	/**
	 * 
	 * @return das verwendete SearchPanel
	 */
	public SearchPanel getSearchPanel() {
		return this.searchPanel;
	}

	/**
	 * Listener auf dem Searchbutton setzen
	 */
	public void addListener() {
		searchPanel.setButtonSearchActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// muss überschrieben werden
				System.out.println(searchPanel.getSearchText());
			}
		});
	}
}
