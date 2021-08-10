import java.awt.*;
import java.awt.event.*;

public class Paddles extends Rectangle {
    int id;
    int yvelocity = 10;

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
                    yvelocity *= -1;
                    move();
                    yvelocity *= -1;
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    move();
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    yvelocity *= -1;
                    move();
                    yvelocity *= -1;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    move();
                }
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (this.id) {
            case 1:
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    yvelocity = 0;
                    move();
                    yvelocity = 10;
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    yvelocity = 0;
                    move();
                    yvelocity = 10;
                }
                break;
            case 2:
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    yvelocity = 0;
                    move();
                    yvelocity = 10;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    yvelocity = 0;
                    move();
                    yvelocity = 10;
                }
                break;
        }
    }

    public void move() {
        y = y + yvelocity;
        if (y < 0) {
            y = 0;
        }
    }
}
