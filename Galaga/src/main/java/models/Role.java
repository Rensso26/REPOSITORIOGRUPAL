package models;

import Actions.Drawable;

import java.awt.*;

public class Role implements Drawable {

    private int [] coordX;
    private int [] coordY;

    private int x, y;
    public Role(int[] cordX, int[] cordY) {
        this.coordX = cordX;
        this.coordY = cordY;
    }
    public Role(int size){
        this.coordX = new int[size];
        this.coordY = new int[size];
    }

    public Role() {

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

    public int[] getCoordX() {
        return coordX;
    }

    public void setCoordX(int[] coordX) {
        this.coordX = coordX;
    }

    public int[] getCoordY() {
        return coordY;
    }

    public void setCoordY(int[] coordY) {
        this.coordY = coordY;
    }

    @Override
    public void draw(Graphics graphics) {

    }
}
