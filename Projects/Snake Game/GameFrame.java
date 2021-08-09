import javax.swing.*;

public class GameFrame extends JFrame {

    public static SnakeGame frame;

    GameFrame() {
        this.add(new GamePanel());
        this.setTitle("Snake");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}