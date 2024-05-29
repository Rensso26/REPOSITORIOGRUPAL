package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BackGround extends JFrame implements KeyListener {

    Controlers.Container container;
    JPanel panel;
    private static final long serialVersionUID = 1L;

    public BackGround() {

        setTitle("Galaga");
        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        container = new Controlers.Container();
        panel= new JPanel();
        panel.setBackground(Color.black);
        setContentPane(panel);
        addKeyListener(this);
        Timer timer = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                container.moveDown(5);
                repaint();
            }
        });
        timer.start();
        Timer bullettimer = new Timer(100, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                container.moveBullets();
                repaint();
            }
        });
        bullettimer.start();


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
}
