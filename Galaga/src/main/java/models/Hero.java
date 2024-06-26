package models;

import Actions.Drawable;
import Actions.MovableX;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.awt.*;


public class Hero extends Role implements Drawable, MovableX {
    int[] XPoints = {400, 450, 350};
    int[] YPoints = {500, 550, 550};


    public String name;
    public int life;
    public int score;


    public Hero() {
        super(3);
        setCoordX(XPoints);
        setCoordY(YPoints);
        this.life = 100;
        this.score = 0;
    }

    public Hero(String name, int life, int score) {
        this.name = name;
        this.life = life;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillPolygon(getCoordX(), getCoordY(), getCoordY().length);
    }


    public void moveRight(int distance) {
        for (int i = 0; i < XPoints.length; i++) {
            XPoints[i] = XPoints[i] + distance;
            setCoordX(XPoints);
        }
    }

    @Override
    public void moveRigth(int distance) {

    }

    @Override
    public void moveLeft(int distance) {
        for (int i = 0; i < XPoints.length; i++) {
            XPoints[i] = XPoints[i] - distance;
            setCoordX(XPoints);
        }
    }

    // Método para obtener la coordenada X desde donde disparar
    public int getX() {
        return XPoints[0]; // Retorna la coordenada X del vértice superior
    }

    // Método para obtener la coordenada Y desde donde disparar
    public int getY() {
        return YPoints[0]; // Retorna la coordenada Y del vértice superior
    }


}