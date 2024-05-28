package models;

import Actions.Drawable;
import Actions.MovableX;
import org.springframework.beans.factory.annotation.Value;

import java.awt.*;

public class Hero extends Role implements Drawable, MovableX {
    int[] XPoints = {400,450,350};

    int[] YPoints = {500,550,550};
    @Value("Payer 1")
    private String name;
    private int life;
    private int score;

    public Hero() {
        super(3);
        setCoordX(XPoints);
        setCoordY(YPoints);
    }
    public Hero(String name, int life, int score){
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
        g.fillPolygon(getCoordX(),getCoordY(),getCoordY().length);
    }

    @Override
    public void moveRigth(int distance) {
        for(int i = 0; i< XPoints.length; i++) {
            XPoints[i]= XPoints[i] + distance;
            setCoordX(XPoints);
        }
    }

    @Override
    public void moveLeft(int distance) {
        for(int i = 0; i< XPoints.length; i++) {
            XPoints[i]= XPoints[i] - distance;
            setCoordX(XPoints);
        }
    }
}
