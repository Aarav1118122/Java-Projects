import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    GamePanel panel;

    GameFrame() {
        panel = new GamePanel();
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Pong");
        this.setResizable(false);
        this.setVisible(true);
        this.setBackground(Color.black);
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
