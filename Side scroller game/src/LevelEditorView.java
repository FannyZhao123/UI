import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;


public class LevelEditorView extends JFrame implements Observer {
	private Model model;
//	public LevelEditorViewTop leveleditorViewTop;
    public LevelEditorViewMid leveleditorViewMid;
//    public LevelEditorViewBottom leveleditorViewBottom;

    /**
     * Create a new View.
     */
    public LevelEditorView(Model model) {

//        init 3 parts of level editor
//        leveleditorViewTop = new LevelEditorViewTop(model);
//        model.addObserver(leveleditorViewTop);
        leveleditorViewMid = new LevelEditorViewMid(model);
        model.addObserver(leveleditorViewMid);
//        leveleditorViewBottom = new LevelEditorViewBottom(model);
//        model.addObserver(leveleditorViewBottom);

        //set main layout -- BorderLayout
        JPanel leveleditorView;
        leveleditorView = new JPanel(new BorderLayout());
        this.getContentPane().add(leveleditorView);
//        leveleditorView.add(leveleditorViewTop, BorderLayout.NORTH);
        leveleditorView.add(leveleditorViewMid, BorderLayout.CENTER);
//        leveleditorView.add(leveleditorViewBottom, BorderLayout.SOUTH);

        //frame.setPreferredSize(new Dimension(128,128));
        this.setTitle("Jungle Filght -- Level Editor");
        this.setMinimumSize(new Dimension(128, 128));
        this.setSize(1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.model = model;
        model.addObserver(this);
        this.setVisible(false);
        //set false at the begining;

        // set the model 
		this.model = model;

		// setup the event to go to the "controller"
        // (this anonymous class is essentially the controller)
    }//con

    /**
     * Update with data from the model.
     */
    @Override

    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model
        // changes.
        System.out.println("Model changed!");
    }
}


