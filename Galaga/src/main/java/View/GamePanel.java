package View;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    Controlers.Container container;

    public GamePanel(Controlers.Container container) {
        this.container = container;
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        container.draw(g);

    }
}
