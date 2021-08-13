import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel implements Runnable {
    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = (int) (1000 * 0.5555);
    static final double UPDATE_RATE = 1.0 / 60.0;
    final int PADDLES_WIDTH = 25;
    final int PADDLES_HEIGHT = 100;
    final int BALL_DIAMETER = 20;
    Thread thread;
    Image image;
    Graphics graphics;
    Paddles paddle1;
    Paddles paddle2;
    Ball ball;
    Random random;
    boolean running = false;
    int paddley = ((SCREEN_HEIGHT / 2) - (PADDLES_HEIGHT / 2));
    int paddle2x = (SCREEN_WIDTH - PADDLES_WIDTH);
    int randomBallStart;
    int paddle1Score = 0;
    int paddle2Score = 0;

    GamePanel() {
        this.setVisible(true);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.blue);
        newBall();
        newPaddles();
        thread = new Thread(this);
        thread.start();
        random = new Random();
        this.addKeyListener(new keyListener());
        randomBallStart = random.nextInt(SCREEN_HEIGHT);
    }

    public void newBall() {
        ball = new Ball(((SCREEN_WIDTH - BALL_DIAMETER) / 2), randomBallStart, BALL_DIAMETER, BALL_DIAMETER);
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
        g.setColor(Color.white);
        g.setFont(new Font("Consolas", Font.PLAIN, 60));
        g.drawLine(SCREEN_WIDTH / 2, 0, SCREEN_WIDTH / 2, SCREEN_HEIGHT);
        g.drawString(Integer.toString(paddle1Score), (SCREEN_WIDTH / 2) - 50, 50);
        g.drawString(Integer.toString(paddle2Score), (SCREEN_WIDTH / 2) + 20, 50);
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
    }

    public void move() {
        paddle1.move();
        paddle2.move();
        ball.move();
    }

    public void checkCollisions() {
        // checking paddle for not going out of frame
        if (paddle1.y < 0) {
            paddle1.y = 0;
        }
        if (paddle2.y < 0) {
            paddle2.y = 0;
        }
        if (paddle1.y > (SCREEN_HEIGHT - PADDLES_HEIGHT)) {
            paddle1.y = (SCREEN_HEIGHT - PADDLES_HEIGHT);
        }
        if (paddle2.y > (SCREEN_HEIGHT - PADDLES_HEIGHT)) {
            paddle2.y = (SCREEN_HEIGHT - PADDLES_HEIGHT);
        }
        // bouncing the ball at the top and the bottom of the frame;
        if (ball.y < 0 || ball.y > (SCREEN_HEIGHT - BALL_DIAMETER)) {
            ball.yVelocity *= -1;
            ball.setYDirection(ball.yVelocity);
        }
        // checking if the ball is missed by any paddle
        if (ball.x < 0) {
            paddle2Score++;
            newBall();
            newPaddles();
        }
        if (ball.x > (SCREEN_WIDTH - BALL_DIAMETER)) {
            paddle1Score++;
            newBall();
            newPaddles();
        }
        if (ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if (ball.yVelocity < 0) {
                ball.setYDirection(-ball.xVelocity);
            } else {
                ball.setYDirection(ball.xVelocity);
            }
        }
        if (ball.intersects(paddle2)) {
            ball.xVelocity = -(Math.abs(ball.xVelocity));
            ball.xVelocity--;
            if (ball.yVelocity < 0) {
                ball.setYDirection(ball.xVelocity);
            } else {
                ball.setYDirection(-ball.xVelocity);
            }
        }
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
