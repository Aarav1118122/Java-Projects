import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle {
    int xVelocity;
    int yVelocity;
    int randomXDirection;
    int randomYDirection;
    int ballSpeed = 5;
    Random random;

    Ball(int x, int y, int width, int height) {
        super(x, y, width, height);
        random = new Random();
        randomXDirection = random.nextInt(2);
        if (randomXDirection == 0) {
            randomXDirection--;
        }
        setXDirection(randomXDirection * ballSpeed);
        randomYDirection = random.nextInt(2);
        if (randomYDirection == 0) {
            randomYDirection--;
        }
        setYDirection(randomYDirection * ballSpeed);
    }

    public void setXDirection(int randomXDirection) {
        xVelocity = randomXDirection;
    }

    public void setYDirection(int randomYDirection) {
        yVelocity = randomYDirection;
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, width, height);
    }

    public void move() {
        x += xVelocity;
        y += yVelocity;
    }
}
