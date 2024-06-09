package models;

import Actions.Drawable;
import Actions.MovableY;
import org.springframework.stereotype.Service;

import java.awt.*;



@Service("bullets")
public class Bullets extends Role implements Drawable, MovableY {
    static int speed = 10;
    private Color color;
    public int damage ;
    public Bullets(int startX, int startY, Color color) {
        setX(startX);
        setY(startY);
        this.color = color;
        damage = 5;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(color);
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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}

