package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BackGround extends JFrame implements KeyListener {

    private static final long serialVersionUID = 1L;
    private static final int SCREEN_HEIGHT = 600;
    private static final int DIVIDER_Y = (int) (SCREEN_HEIGHT * 0.66);

    Controlers.Container container;
    JPanel panel;
    Timer enemyTimer;
    Timer bulletTimer;
    GamePanel gamePanel;
    public BackGround() {

        setTitle("Galaga");
        setSize(800,SCREEN_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        container = new Controlers.Container();
        gamePanel = new GamePanel(container);

        panel = new JPanel();
        panel.setBackground(Color.black);
        setContentPane(panel);

        addKeyListener(this);

        enemyTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //container.moveDown(3);
                container.killEnemies();
                if (container.isGameOver(DIVIDER_Y)) {
                    //stopGame();
                }
                repaint();
            }
        });
        enemyTimer.start();

        bulletTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.moveBullets();
                repaint();
            }
        });
        bulletTimer.start();

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        container.draw(g);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        setFocusable(true);
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A : {
                container.moveLeft(10);
                break;
            }
            case KeyEvent.VK_D : {
                container.moveRight(10);
                break;
            }
            case KeyEvent.VK_SPACE : {
                container.shootHeroBullet();
                break;
            }
            default:
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    private void stopGame() {
        enemyTimer.stop();
        bulletTimer.stop();
        JOptionPane.showMessageDialog(this, "Game Over!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }
}