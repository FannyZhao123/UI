import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;    
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JTextField;

public class RuleView extends JFrame implements Observer {
	private Model model;
    /**
     * Create a new View.
     */
    public RuleView(Model model) {
        JPanel ruleView;

        JButton back;
        ImageIcon backbtn = new ImageIcon("buttons/back.png");
        back = new JButton("");
        back.setIcon(backbtn);

        JTextArea label1 = new JTextArea("");
        label1.setText("Game Rule:" + "\n" +
            "1. You can move the aircraft in any of the four directions" + "\n" +"   (up, down, left, right) using the keyboard arrows or the keys W,A,S,D." + "\n" +
            "2. The aircraft cannot move outside of the world, or touch the obstacle." +  "\n" +
            "3. You can play, pause, and restart the level during the game." + "\n" +
            "4. If you reache the right end of the horizontal boundary, you win." + "\n" +
            "5. You can see your score so far in the top right corner during the game."  + "\n" +  
            "6. You can load previously saved levels from a file and play them." + "\n");
        label1.setFont(new Font("Futura",Font.BOLD,30));

        ruleView = new JPanel(new BorderLayout());
        this.getContentPane().add(ruleView);
        ruleView.add(back, BorderLayout.SOUTH);
        ruleView.add(label1, BorderLayout.CENTER);
        

        //frame.setPreferredSize(new Dimension(128,128));
        this.setTitle("Jungle Filght -- RuleView");
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
       
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RuleView.this.dispose();
            }
        }); 
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
