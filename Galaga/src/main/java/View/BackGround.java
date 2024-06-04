package View;

import Controlers.Container;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BackGround extends JFrame implements KeyListener {

    private static final long serialVersionUID = 1L;
    private static final int SCREEN_HEIGHT = 600;
    private boolean movingLeft = false;
    private boolean movingRight = false;

    Controlers.Container container = new Controlers.Container();
    JPanel panel = new JPanel();
    Timer enemyTimer;
    Timer bulletTimer;
    GamePanel gamePanel = new GamePanel(container);


    public BackGround(boolean inizialization){
        setTitle("Inisio de sesio");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTextField name = new JTextField(20);
        JTextField password = new JTextField(20);
        JButton botonIni = new JButton("Iniciar sesión");

        botonIni.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            container.InitialHero(name.getText(), password.getText());
                new BackGround();
            }
        });
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Usuario:"));
        panel.add(name);
        panel.add(new JLabel("Contraseña:"));
        panel.add(password);
        panel.add(botonIni);

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setVisible(true);
    }

//        botonIni.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                String usuario = name.getText();
//                String contrasena = new String(password.getText());
//
//                // Aquí deberías verificar las credenciales, por ejemplo:
//                if (usuario.equals("usuario") && contrasena.equals("contrasena")) {
//                    new BackGround();
//                } else {
//                    JOptionPane.showMessageDialog(BackGround.this, "Credenciales incorrectas");
//                }
//            }
//        });
    public BackGround() {

        setTitle("Galaga");
        setSize(800,SCREEN_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        panel.setBackground(Color.black);
        add(gamePanel);

        addKeyListener(this);

        enemyTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                container.moveDown(5);
                container.killEnemies();
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
    }
}