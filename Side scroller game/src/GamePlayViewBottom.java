import java.io.*;
import java.util.LinkedList;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.geom.Path2D;
import java.awt.event.*;    
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import java.awt.geom.AffineTransform;




import static java.awt.image.BufferedImage.TYPE_3BYTE_BGR;

public class GamePlayViewBottom extends JPanel implements Observer {
	private Model model;


    public static GamePlayViewBottom gameplayViewBottom;
    private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
    private boolean gameOver;
    private boolean started;
    private Random rand_num = new Random();
    private final int WIDTH = 100, HEIGHT = 530;
    private Renderer renderer = new Renderer();
    private Aircraft aircraft = new Aircraft(50, 100, this);
    private int FPS = 30;
    private LinkedList<Displayable> displayables = new LinkedList<Displayable>();
    Timer animationTimer;

    //used for AffineTransform


    public GamePlayViewBottom(Model model) {
        super();
        this.displayables.add(this.aircraft);

        //add 4 Obstacles
        this.addobstacle(true);
        this.addobstacle(true);
        this.addobstacle(true);
        this.addobstacle(true);

        for (Obstacle obstacle : obstacles)
            {
                this.displayables.add(obstacle);
            }

        // Using Keyboard Listeners to handle Keyboard events
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("Key Pressed!");
                switch(e.getKeyCode()){
                	case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
			            System.out.println("UP or W!");
                        GamePlayViewBottom.this.aircraft.moveUP();
			            break;
			        case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
			            System.out.println("DOWN or S!");
                        GamePlayViewBottom.this.aircraft.moveDOWN(); 
			            break;
			        case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
			            System.out.println("RIGHT or D!");
                        GamePlayViewBottom.this.aircraft.moveRIGHT();  
			            break;
			        case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
			            System.out.println("LEFT or A!"); 
                        GamePlayViewBottom.this.aircraft.moveLEFT();
			            break;
			        default :
			            System.out.println("Invalid KEY!");
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            	System.out.println("Key Released!");
            }
            @Override
            public void keyTyped(KeyEvent e) {
            	System.out.println("Key Typed!");
            }
        });

        // Using a Timer to handle animation.
        // Note that the following statement uses a lambda expression instead
        // of creating an anonymous class implementing ActionListener, but
        // both syntax are equivalent.
        started = true;
        this.animationTimer = new Timer(1000/this.FPS, event -> {            
            this.moveTHEscreen();
            this.repaint(); // note that we call repaint, not paintComponent
        });       
    }


    @Override
    protected void paintComponent(Graphics g) {
        // dBuff and gBuff are used for double buffering
        Image dBuff = new BufferedImage(this.getWidth(), this.getHeight(), TYPE_3BYTE_BGR);
        Graphics gBuff = dBuff.getGraphics();
        gBuff.setClip(0, 0, this.getWidth(), this.getHeight());
        gBuff.setColor(new Color(189,133,49));
        gBuff.fillRect(0, 0, this.getWidth(), this.getHeight());
        for (Displayable d : this.displayables) {
            d.paint(gBuff);
        }
        g.drawImage(dBuff, 0, 0, this.getWidth(), this.getHeight(), null);
    }
    @Override
    public void update(Object observable) {
        System.out.println("Model changed!");
    }


    //set obstacles in right place
    public void addobstacle(boolean start){
        int space = 100;
        int width = 50;
        int height = 70 + rand_num.nextInt(100);
        if (start){
            obstacles.add(new Obstacle(WIDTH + width + obstacles.size() * 50, HEIGHT - height, width, height));
            obstacles.add(new Obstacle(WIDTH + width + (obstacles.size() - 1) * 300, 0, width, HEIGHT - height - space));
        }
        else{
            obstacles.add(new Obstacle(obstacles.get(obstacles.size() - 1).x + 60, HEIGHT - height, width, height));
            obstacles.add(new Obstacle(obstacles.get(obstacles.size() - 1).x, 0, width, HEIGHT - height - space));
        }
    }

    public void moveTHEscreen()
    {
        int speed = 1;
        if (started){
            //move the obstacle
            for (int i = 0; i < obstacles.size(); i++){
                Obstacle obstacle = obstacles.get(i);
                obstacle.x -= speed;
            }
            for (int i = 0; i < obstacles.size(); i++){
                Obstacle obstacle = obstacles.get(i);
                if (obstacle.x + obstacle.width < 0){
                    obstacles.remove(obstacle);
                    if (obstacle.y == 0){
                        addobstacle(false);
                    }
                }
            }
            //check if aircraft hit the eage or obstacles
            for (Obstacle obstacle : obstacles){
                if (obstacle.x < aircraft.x + 10 && aircraft.x + 10 <obstacle.x + obstacle.width / 2 - 10 && obstacle.y < aircraft.y + 10 && aircraft.y + 10 <obstacle.y + obstacle.height / 2 - 10 )
                {   gameOver = true;
                    if (aircraft.x <= obstacle.x) aircraft.x = obstacle.x - 20;
                }
            }
            if (aircraft.y > HEIGHT || aircraft.y < 0 || aircraft.x > WIDTH || aircraft.x <0) gameOver = true;
        }
        renderer.repaint();
        if (gameOver) {
            obstacles.clear();
        }
    }

    //try to use for pint a AffineTransformed obstacle in GamePlay View, but failed
    //so just leave the method here to prove that I did try the AffineTransform.
    public void AffineTransform(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
        g2.translate(200, 130);
        g2.setColor(Color.YELLOW);
        this.paint(g2);
        AffineTransform at = g2.getTransform();
        g2.rotate(-Math.PI/9);
        g2.setColor(Color.BLUE);
        this.paint(g2);
        g2.setTransform(at);
        g2.rotate(-Math.PI/3);
        g2.setColor(Color.BLACK);
        this.paint(g2);
    }
}