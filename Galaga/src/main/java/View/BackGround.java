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
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private Timer enemyShootTimer;

    Controlers.Container container = new Controlers.Container();
    JPanel panel = new JPanel();
    Timer enemyTimer;
    Timer bulletTimer;
    GamePanel gamePanel = new GamePanel(container);


    public BackGround(boolean inizialization){
        setTitle("Inisio de sesio");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTextField name = new JTextField(20);
        JButton botonIni = new JButton("Iniciar sesión");

        botonIni.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                container.InitialHero(name.getText());
                new BackGround();
            }
        });

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Usuario:"));
        panel.add(name);
        panel.add(botonIni);

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setVisible(true);
    }
    public BackGround() {

        setTitle("Galaga");
        setSize(800,SCREEN_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);



        panel = new JPanel();
        panel.setBackground(Color.black);
        add(gamePanel);

        addKeyListener(this);

        enemyTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.moveDown(3);
                container.killEnemies();
                container.damageSuperEnemi();
                container.levelstarter();
                if (container.isGameOver()) {
                    stopGame();
                }
                gamePanel.repaint();
            }
        });
        enemyTimer.start();

        bulletTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.moveBullets();
                gamePanel.repaint();
                container.killhero();
            }
        });
        bulletTimer.start();

        setLocationRelativeTo(null);

        setVisible(true);

        Timer moveTimer = new Timer(150, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (movingLeft) {
                    container.moveLeft(10);
                }
                if (movingRight) {
                    container.moveRight(10);
                }
                gamePanel.repaint();
            }
        });
        moveTimer.start();

        enemyShootTimer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.enemyShoot();
            }
        });
        enemyShootTimer.start();
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
            case KeyEvent.VK_A: {
                movingLeft = true;
                container.moveLeft(10);
                break;
            }
            case KeyEvent.VK_D: {
                movingRight = true;
                container.moveRight(10);
                break;
            }
            case KeyEvent.VK_SPACE: {
                container.shootHeroBullet();
                break;
            }
            default:
                break;
        }
        gamePanel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A: {
                movingLeft = false;
                break;
            }
            case KeyEvent.VK_D: {
                movingRight = false;
                break;
            }
            default:
                break;
        }

    }

    private void stopGame() {
        enemyTimer.stop();
        bulletTimer.stop();
        JOptionPane.showMessageDialog(this, "Game Over!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        System.exit(0);

    }
}