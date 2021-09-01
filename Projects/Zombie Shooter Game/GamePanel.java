import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int SCREEN_HEIGHT = 700;
    final int SCREEN_WIDTH = 1300;
    final double UPDATE_RATE = 1.0 / 60.0;
    Player player;
    Thread thread;
    Image image;
    Graphics graphics;
    boolean running = false;

    GamePanel() {
        this.setVisible(true);
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        thread = new Thread(this);
        thread.start();
        player = new Player(200, 290, 90, 90);
    }

    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, null);
    }

    public void draw(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        player.draw(g2D);
    }

    public void move() {

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
}
