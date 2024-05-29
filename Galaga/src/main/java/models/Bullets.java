package models;

import Actions.Drawable;
import Actions.MovableY;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service("bullets")
public class Bullets implements Drawable, MovableY {
    private int x, y;
    private int speed = 10; // Velocidad con la que la bala se moverá hacia arriba

    public Bullets(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillOval(x, y, 5, 10);
    }

    @Override
    public void moveUp(int distance) {
        y -= distance * speed;
    }

    @Override
    public void moveDown(int distance) {
        y += distance * speed;
    }

    @Override
    public void speed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
