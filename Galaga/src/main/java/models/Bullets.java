package models;

import Actions.Drawable;
import Actions.MovableY;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service("bullets")
public class Bullets extends Role implements Drawable, MovableY {
    private int speed = 10; // Velocidad con la que la bala se moverá hacia arriba

    public Bullets(int startX, int startY) {
        setX(startX);
        setY(startY);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillOval(getX(), getY(), 5, 10);
    }

    @Override
    public void moveUp(int distance) {
        setY(getY() - distance * speed);
    }

    @Override
    public void moveDown(int distance) {
        setY(getY() + distance * speed);
    }

    @Override
    public void speed(int speed) {
        this.speed = speed;
    }
}
