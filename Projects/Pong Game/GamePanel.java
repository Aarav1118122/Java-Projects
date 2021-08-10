import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = (int) (1000 * 0.5555);
    static final double UPDATE_RATE = 1.0 / 60.0;
    final int PADDLES_WIDTH = 25;
    final int PADDLES_HEIGHT = 100;
    Thread thread;
    Image image;
    Graphics graphics;
    Paddles paddle1;
    Paddles paddle2;
    Ball ball;
    boolean running = false;
    int paddley = ((SCREEN_HEIGHT / 2) - (PADDLES_HEIGHT / 2));
    int paddle2x = (SCREEN_WIDTH - PADDLES_WIDTH);

    GamePanel() {
        newBall();
        newPaddles();
        this.setVisible(true);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.blue);
        thread = new Thread(this);
        thread.start();
        this.addKeyListener(new keyListener());
    }

    public void newBall() {
        ball = new Ball();
    }

    public void newPaddles() {
        paddle1 = new Paddles(0, paddley, PADDLES_WIDTH, PADDLES_HEIGHT, 1);
        paddle2 = new Paddles(paddle2x, paddley, PADDLES_WIDTH, PADDLES_HEIGHT, 2);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, null);
    }

    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);
    }

    public void move() {
        // paddle1.move();
        // paddle2.move();
    }

    public void checkCollisions() {

    }

    public void run() {
        running = true;
        double lastTime = System.nanoTime() / 1000000000.0;
        double firstTime = 0;
        double passedTime = 0;
        double unprocessedTime = 0;
        while (running) {
            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            unprocessedTime += passedTime;
            lastTime = firstTime;
            while (unprocessedTime >= UPDATE_RATE) {
                unprocessedTime -= UPDATE_RATE;
                move();
                checkCollisions();
                repaint();
            }
        }
    }

    public class keyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }

        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
