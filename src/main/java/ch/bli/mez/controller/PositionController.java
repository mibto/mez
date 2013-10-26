package ch.bli.mez.controller;

import ch.bli.mez.view.management.PositionPanel;

public class PositionController {
	
	private PositionPanel view;
//	private PositionDAO model;
	
	public PositionController(){
		this.view = new PositionPanel();
//		this.model = new PositionDAO();
	}
	
	public PositionPanel getView(){
		return view;
	}

}
