package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ch.bli.mez.model.EmployeModel;
import ch.bli.mez.view.MainView;

public class MainController {
    private MainView view;
    private EmployeModel model;
    
    public MainController(){
        this.model = new EmployeModel();
        this.view = new MainView();
        addListener();
    }
    public void showView(){
        this.view.setVisible(true);
    }
    
    private void addListener(){
        /*this.view.setWurzelBerechnenListener(new WurzelBerechnenListener());
        this.view.setResetFormListener(new ResetFormListener());*/
    }

    class WurzelBerechnenListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            /*long wert = Long.valueOf(view.getEingabe());
            model.updateUser(wert);
            view.setErgebnis(String.valueOf(model.getWurzel()));*/
        }
    }

    class ResetFormListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            /*view.resetView();
            model.zurückSetzen();*/
        }
    }

}
