package ch.bli.mez.controller;

import ch.bli.mez.view.SearchPanel;

public class SearchController {
	
	private final SearchPanel searchPanel;
	
	public SearchController(){
		this.searchPanel = new SearchPanel();
	}
	
	public SearchPanel getSearchPanel(){
		return this.searchPanel;
	}
}
