import javax.swing.*;
import java.awt.*;

import static java.lang.Math.*;

public interface Displayable {
    void paint(Graphics g);
}



class Obstacle implements Displayable {
	public int x, y, width, height;
	public Obstacle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public void paint(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect(this.x, this.y, this.width, this.height);
    }
}

//1*1 block
class Aircraft implements Displayable {
	public int x, y, direction;
	public JComponent parent;
	public Aircraft(int x, int y, JComponent parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.direction = 10;

    }

    //move when press the KEY
    public void moveUP() {
        this.y -= this.direction;
    }
    public void moveDOWN() {
        this.y += this.direction;
    }
    public void moveLEFT() {
        this.x -= this.direction;
    }
    public void moveRIGHT() {
        this.x += this.direction;
    }

    public Point getPosition() {
        return new Point(this.x, this.y);
    }

    public void paint(Graphics g) {
        g.setColor(Color.green);
        g.fillOval(this.x, this.y, 20, 20);
    }


}



