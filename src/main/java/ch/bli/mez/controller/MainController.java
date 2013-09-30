package ch.bli.mez.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ch.bli.mez.model.MainModel;
import ch.bli.mez.view.MainView;

public class MainController {
    private MainView view;
    private MainModel model;
    
    public MainController(){
        this.model = new MainModel();
        this.view = new MainView();
        addListener();
    }
    public void showView(){
        this.view.setVisible(true);
    }
    
    private void addListener(){
        this.view.setBlaaActionListener(new BlaaListener());
        this.view.setZeitErfassenActionListener(new WurzelBerechnenListener());
        this.view.setVerwaltungActionListener(new WurzelBerechnenListener());
    }
    
    class BlaaListener implements ActionListener{
      public void actionPerformed(ActionEvent e) {
        String name = view.getName();
        model.updateName(name);
        view.setName(model.getName());
      }
    }

    class WurzelBerechnenListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

        }
    }

    class ResetFormListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {

        }
    }

}
