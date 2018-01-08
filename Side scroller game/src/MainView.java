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
//import java.util.Observable;
//import java.util.Observer;

public class MainView extends JFrame implements Observer {

    // the model that this view is showing
    private Model model;

    private JButton rule;
    private JButton paly;
    private JButton leveleditor;
    private GamePlayView gameplayView;
    private LevelEditorView leveleditorView;
    private RuleView ruleView;

    /**
     * Create a new View.
     */
    public MainView(Model model) {

        gameplayView = new GamePlayView(model);
        model.addObserver(gameplayView);
        leveleditorView = new LevelEditorView(model);
        model.addObserver(leveleditorView);
        ruleView = new RuleView(model);
        model.addObserver(ruleView);


        ImageIcon rulebtn = new ImageIcon("buttons/rule.png"); 
        ImageIcon palybtn = new ImageIcon("buttons/setting.png");
        ImageIcon leveleditorbtn = new ImageIcon("buttons/leveleditor.png");
        // Set up the window.
        this.setTitle("Jungle Filght");
        this.setMinimumSize(new Dimension(128, 128));
        this.setSize(1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Hook up this observer so that it will be notified when the model
        // changes.
        this.model = model;
        model.addObserver(this);
        
        //create the view UI
        rule = new JButton("");
        rule.setMaximumSize(new Dimension(220, 40));
        paly = new JButton("");
        paly.setMaximumSize(new Dimension(220, 40));
        leveleditor = new JButton("");
        leveleditor.setMaximumSize(new Dimension(220, 40));

        rule.setIcon(rulebtn); 
        paly.setIcon(palybtn);
        leveleditor.setIcon(leveleditorbtn);

        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.setBackground(new Color(31,175,41) );

        this.getContentPane().add(p);

        JLabel jlabel1 = new JLabel("Welcom to Jungle Flight!");
        jlabel1.setFont(new Font("Futura",Font.ITALIC+Font.BOLD,60));
        JLabel jlabel2 = new JLabel("Designed by Fanny");
        jlabel2.setFont(new Font("Futura",Font.BOLD+Font.ITALIC,35));

        p.add(jlabel1 ,BorderLayout.CENTER);
        p.add(jlabel2,BorderLayout.SOUTH);

        JPanel b = new JPanel();
        b.setLayout(new BoxLayout(b, BoxLayout.Y_AXIS));
        b.add(Box.createVerticalGlue());
        b.add(rule);
        b.add(Box.createVerticalStrut(10));
        b.add(paly);
        b.add(Box.createVerticalStrut(10));
        b.add(leveleditor);
//        b.setMaximumSize(new Dimension(220, 40));
        b.setBackground(new Color(31,175,41) );
        p.add(b, BorderLayout.WEST);

        setVisible(true);

        // set the model 
        this.model = model;



        // setup the event to go to the "controller"
        // (this anonymous class is essentially the controller)
        rule.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainView.this.ruleView.setVisible(true);
            }
        }); 



        paly.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainView.this.gameplayView.setVisible(true);
                MainView.this.gameplayView.gameplayViewBottom.setFocusable(true);
                MainView.this.gameplayView.gameplayViewBottom.requestFocusInWindow();
                MainView.this.gameplayView.gameplayViewBottom.animationTimer.start();
            }
        });

        leveleditor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainView.this.leveleditorView.setVisible(true);
            }
        });
    }//con

    /**
     * Update with data from the model.
     */

    /*
    @Override

    public void paintComponent(Graphics g){
            super.paintComponent(g); //calling the super class functions
            Graphics2D g2d = (Graphics2D)g; //casting to graphcis2D        
            g2d.drawImage(mainbackground, 0,0, null); //draw menu image
            repaint();
    }//end paintComponent
    */

    @Override

    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model
        // changes.
        System.out.println("Model changed!");
    }
}
