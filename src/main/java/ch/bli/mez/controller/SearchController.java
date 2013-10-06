package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ch.bli.mez.view.SearchPanel;

public class SearchController {

	private final SearchPanel searchPanel;

	public SearchController() {
		this.searchPanel = new SearchPanel();
		addListener();
	}

	public SearchPanel getSearchPanel() {
		return this.searchPanel;
	}

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
