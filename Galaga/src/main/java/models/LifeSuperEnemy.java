package models;

import Actions.Drawable;
import Actions.MovableX;
import Actions.MovableY;

import java.awt.*;

public class LifeSuperEnemy implements Drawable, MovableY {
    int lifeSuperEnemy;
    int x,y;

    public LifeSuperEnemy(int life,int x, int y){
        this.lifeSuperEnemy = life;
        this.x = x;
        this.y = y;
    }

    public void setLife(int life) {
        this.lifeSuperEnemy = life;
    }
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        graphics.fillRect(x, y, lifeSuperEnemy*2, 15);
    }

    @Override
    public void moveUp(int distance) {
        y-= distance;
    }

    @Override
    public void moveDown(int distance) {
        y += distance;
    }
}
