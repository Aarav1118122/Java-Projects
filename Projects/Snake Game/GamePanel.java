import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    static final int screenWidth = 600;
    static final int screenHeight = 600;
    static final int unitSize = 25;
    static final int gameUnits = (screenWidth * screenHeight) / unitSize;
    static final int delay = 80;
    int x[] = new int[gameUnits];
    int y[] = new int[gameUnits];
    int bodyparts;
    int applesEaten;
    int applex;
    int appley;
    char direction;
    boolean running = false;
    Timer timer;
    Random random;
    JButton startagainbutton = new JButton();
    JButton exitButton = new JButton();

    GamePanel() {
        startagainbutton = new JButton();
        random = new Random();
        startagainbutton.setVisible(false);
        startagainbutton.setBackground(Color.green);
        startagainbutton.setText("Start Again");
        startagainbutton.setFont(new Font("Ink Free", Font.BOLD, 30));
        startagainbutton.setFocusable(false);
        startagainbutton.addActionListener(this);
        exitButton.setVisible(false);
        exitButton.setBackground(Color.green);
        exitButton.setText("Exit");
        exitButton.setFont(new Font("Ink Free", Font.BOLD, 30));
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);
        this.add(startagainbutton);
        this.add(exitButton);
        this.setBounds(0, 0, screenWidth, screenHeight);
        this.setVisible(true);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new myKeyAdapter());
        startGame();
    }

    public void startGame() {
        bodyparts = 6;
        applesEaten = 0;
        x[0] = 5 * unitSize;
        y[0] = 5 * unitSize;
        direction = 'R';
        newApple();
        running = true;
        timer = new Timer(delay, this);
        timer.start();
        startagainbutton.setVisible(false);
        exitButton.setVisible(false);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            g.setColor(Color.red);
            g.fillOval(applex, appley, unitSize, unitSize);
            g.setFont(new Font("Ink Free", Font.BOLD, 30));
            g.drawString("Score: " + applesEaten, 20, 40);
            for (int i = 0; i < bodyparts; i++) {
                if (i == 0) {
                    g.setColor(Color.green);
                } else {
                    g.setColor(new Color(0, 150, 0));
                }
                g.fillRect(x[i], y[i], unitSize, unitSize);
            }
        } else {

            gameOver(g);
        }
    }

    public void newApple() {
        applex = (random.nextInt((int) (screenWidth / unitSize)) * unitSize);
        appley = (random.nextInt((int) (screenHeight / unitSize)) * unitSize);
    }

    public void move() {
        for (int i = bodyparts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U':
                y[0] = y[0] - unitSize;
                x[0] = x[0];

                break;
            case 'D':
                y[0] = y[0] + unitSize;
                x[0] = x[0];
                break;
            case 'L':
                x[0] = x[0] - unitSize;
                y[0] = y[0];
                break;
            case 'R':
                x[0] = x[0] + unitSize;
                y[0] = y[0];
                break;
        }
    }

    public void checkApple() {

        if ((applex == x[0]) && (appley == y[0])) {
            applesEaten++;
            bodyparts++;
            newApple();
        }
    }

    public void checkCollisions() {
        for (int i = 1; i < bodyparts; i++) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
                deleteBodyParts();
            }
        }
        if (x[0] < 0) {
            running = false;
            deleteBodyParts();
        }
        if (x[0] > screenWidth - unitSize) {
            running = false;
            deleteBodyParts();
        }
        if (y[0] < 0) {
            running = false;
            deleteBodyParts();
        }
        if (y[0] > screenHeight - unitSize) {
            running = false;
            deleteBodyParts();
        }
        if (!running) {
            timer.stop();
        }
    }

    public void deleteBodyParts() {
        for (int i = 0; i < bodyparts; i++) {
            x[i] = 5 * unitSize;
            y[i] = 5 * unitSize;
        }
    }

    public void gameOver(Graphics g) {
        if (!running) {
            g.setColor(Color.red);
            g.setFont(new Font("Ink Free", Font.BOLD, 60));
            g.drawString("Score: " + applesEaten, 190, 100);
            g.setFont(new Font("Ink Free", Font.BOLD, 70));
            g.drawString("Game Over", 125, 300);
            startagainbutton.setBounds(75, 400, 200, 75);
            startagainbutton.setVisible(true);
            exitButton.setBounds(325, 400, 200, 75);
            exitButton.setVisible(true);
        }
        if (running) {
            startagainbutton.setVisible(false);
            exitButton.setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkCollisions();
            checkApple();
        }
        repaint();
        if (!running) {
            if (e.getSource() == startagainbutton) {
                startGame();
            }
            if (e.getSource() == exitButton) {
                SnakeGame.exitFrame();
            }
        }
    }

    public class myKeyAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
            }

        }
    }
}
