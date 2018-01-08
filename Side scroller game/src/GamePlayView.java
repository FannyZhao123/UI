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


public class GamePlayView extends JFrame implements Observer {
	private Model model;
    public GamePlayViewBottom gameplayViewBottom;

    /**
     * Create a new View.
     */
    public GamePlayView(Model model) {
        int score_int = 0;
        JPanel gameplayView;
//        gameplayViewTop = new GamePlayViewTop(model);
//        model.addObserver(gameplayViewTop);
        gameplayViewBottom = new GamePlayViewBottom(model);
        model.addObserver(gameplayViewBottom);

        //set up gameplayViewTop
        /////////////////////////////////////
        JButton restart;
        JButton pause;
        JButton end_pause;
        JButton back_menu;
        JLabel score;
        ImageIcon restartbtn = new ImageIcon("buttons/restart.png"); 
        ImageIcon pausebtn = new ImageIcon("buttons/pause.png");
        ImageIcon end_pausebtn = new ImageIcon("buttons/endpause.png");
        ImageIcon back_menubtn = new ImageIcon("buttons/back.png");
        JPanel gameplayViewTop = new JPanel();
        gameplayViewTop.setLayout(new BoxLayout(gameplayViewTop, BoxLayout.X_AXIS));
        
        gameplayViewTop.setBackground(Color.GRAY);

        //create the view UI
        pause = new JButton("");
        restart = new JButton("");
        pause.setIcon(pausebtn); 
        restart.setIcon(restartbtn);
        end_pause = new JButton("");
        back_menu = new JButton("");
        end_pause.setIcon(end_pausebtn);
        back_menu.setIcon(back_menubtn);
        score = new JLabel("score: " + score_int);
        score.setFont(new Font("Futura",Font.BOLD,60));

        gameplayViewTop.add(restart);
        gameplayViewTop.add(pause);
        gameplayViewTop.add(end_pause);
        gameplayViewTop.add(back_menu);
        gameplayViewTop.add(Box.createHorizontalGlue());
        gameplayViewTop.add(score);

        gameplayViewTop.setBackground(new Color(224,224,224));
        /////////////////////////////////////


        gameplayView = new JPanel(new BorderLayout());
        this.getContentPane().add(gameplayView);
        gameplayView.add(gameplayViewTop, BorderLayout.NORTH);
        gameplayView.add(gameplayViewBottom, BorderLayout.CENTER);
        

        //frame.setPreferredSize(new Dimension(128,128));
        this.setTitle("Jungle Filght -- GamePlay");
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
        pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GamePlayView.this.gameplayViewBottom.animationTimer.stop();
            }
        });

        end_pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GamePlayView.this.gameplayViewBottom.setFocusable(true);
                GamePlayView.this.gameplayViewBottom.requestFocusInWindow();
                GamePlayView.this.gameplayViewBottom.animationTimer.start();
            }
        }); 

        back_menu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GamePlayView.this.dispose();
                GamePlayView.this.gameplayViewBottom.animationTimer.stop();
            }
        }); 

        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GamePlayView.this.dispose();
                GamePlayView gamePlayView2 = new GamePlayView(model);
                gamePlayView2.setVisible(true);
                gamePlayView2.gameplayViewBottom.setFocusable(true);
                gamePlayView2.gameplayViewBottom.requestFocusInWindow();
                gamePlayView2.gameplayViewBottom.animationTimer.start();
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


