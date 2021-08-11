import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {
    int ballWidth;
    int ballHeight;
    int x;
    int y;
    int randomXVelocity;
    int randomYVelocity;
    int randomXDirection;
    int randomYDirection;
    int ballSpeed = 5;
    Random random;

    Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        random = new Random();
        randomXVelocity = random.nextInt(10);
        // this.ballDiameter = ballDiameter;
        ballWidth = width;
        ballHeight = height;
        this.x = x;
        this.y = y;
        randomXVelocity = ballSpeed;
        randomYVelocity = ballSpeed;
        randomXDirection = random.nextInt(2);
        randomYDirection = random.nextInt(2);
        if (randomXDirection == 0) {
            randomXVelocity *= -1;
        }
        if (randomYDirection == 0) {
            randomYVelocity *= -1;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, width, height);
    }

    public void move() {
        x += randomXVelocity;
        y += randomYVelocity;
    }

    public void updateBallSpeed() {
        ballSpeed += 10;
    }
}
