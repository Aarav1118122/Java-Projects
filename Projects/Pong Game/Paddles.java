import java.awt.*;
import java.awt.event.*;

public class Paddles extends Rectangle {
    int id;
    int yvelocity;
    int paddleSpeed = 10;

    Paddles(int x, int y, int PADDLES_WIDTH, int PADDLES_HEIGHT, int id) {
        super(x, y, PADDLES_WIDTH, PADDLES_HEIGHT);
        this.id = id;
    }

    public void draw(Graphics g) {
        if (id == 1) {
            g.setColor(Color.blue);
        }
        if (id == 2) {
            g.setColor(Color.red);
        }
        g.fillRect(x, y, width, height);
    }

    public void keyPressed(KeyEvent e) {
        switch (this.id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-paddleSpeed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(paddleSpeed);
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(-paddleSpeed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(paddleSpeed);
                    move();
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (this.id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                }
                break;
        }
    }

    public void setYDirection(int yDirection) {
        yvelocity = yDirection;
    }

    public void move() {
        y = y + yvelocity;
    }
}
