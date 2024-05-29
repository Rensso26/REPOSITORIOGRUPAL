package models;

import Actions.Drawable;
import Actions.MovableY;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service("enemy")
public class Enemy extends Role implements Drawable, MovableY {
    int[] XPoints = new int[5];
    int[] YPoints = new int[5];

    private int life;
    private int rewild;

    public Enemy(int randomX, int randomY,int life, int rewild){
        super(5);
        XPoints[0]=  randomX;
        XPoints[1]=  randomX + 100;
        XPoints[2]=  randomX + 100;
        XPoints[3]=  randomX + 50;
        XPoints[4]=  randomX;

        YPoints[0]=  randomY;
        YPoints[1]=  randomY;
        YPoints[2]=  randomY + 50;
        YPoints[3]=  randomY + 25;
        YPoints[4]=  randomY + 50;
        setCoordX(XPoints);
        setCoordY(YPoints);
        this.life = life;
        this.rewild = rewild;
    }


    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getRewild() {
        return rewild;
    }

    public void setRewild(int rewild) {
        this.rewild = rewild;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillPolygon(getCoordX(),getCoordY(),getCoordY().length);
    }

    @Override
    public void moveUp(int distance) {

    }

    @Override
    public void moveDown(int distance) {
        for(int i = 0; i < YPoints.length; i++) {
            YPoints[i] = YPoints[i] + distance;
            setCoordY(YPoints);
        }

    }

    @Override
    public void speed(int speed) {

    }

}