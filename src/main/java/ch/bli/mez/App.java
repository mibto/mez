package ch.bli.mez;

import ch.bli.mez.controller.MainController;


/**
 * Hello world!
 *
 */
public class App 
{
  static MainController mainController;
  
    public static void main( String[] args )
    {
       mainController = new MainController();
       mainController.showView();
    }
}
