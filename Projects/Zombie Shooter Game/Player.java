import java.awt.*;

public class Player extends Rectangle {

    int degrees = 0;

    Player(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.black);
        g.rotate(Math.toRadians(degrees), x + width / 2, y + height / 2);
        // The outer circle of the player
        g.fillOval(x, y, width, height);
        g.fillRect(x + (width + 40), y + ((height / 2) - 12), 50, 25);
        g.setStroke(new BasicStroke(10));
        // The upper arm of the player
        g.drawLine(x + (width / 2 + 10), y + 5, x + (width + 50), y + ((height / 2)));
        // The lower arm of the player
        g.drawLine(x + (width / 2 + 10), y + 85, x + (width + 50), y + ((height / 2)));
        // The inner circle of the player
        g.setColor(Color.white);
        g.fillOval(x + 10, y + 10, width - 20, height - 20);
    }

    public void move() {

    }
}