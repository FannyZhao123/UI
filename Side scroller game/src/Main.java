import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;


public class Main {
//	static private Model model;
//	static private MainView mainView;

    public static void main(String[] args) {
    	//create Mode and initialize it
        Model model = new Model();

        // create View, tell it about model (and controller)
        MainView mainView = new MainView(model);
        // tell Model about View. 
		model.addObserver(mainView);
		
        // let all the views know that they're connected to the model
		model.notifyObservers();
    }
}
