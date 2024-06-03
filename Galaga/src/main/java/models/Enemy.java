package models;

import Actions.Dieable;
import Actions.Drawable;
import Actions.MovableY;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service("enemy")
public class Enemy extends Role implements Drawable, MovableY, Dieable {
    int[] XPoints = new int[5];
    int[] YPoints = new int[5];

    private int life;
    private int rewild;
    private int damage;
    public Enemy(int randomX, int randomY,int life, int rewild, int damage){
        super(5);
        XPoints[0]=  randomX;
        XPoints[1]=  randomX + 40;
        XPoints[2]=  randomX + 40;
        XPoints[3]=  randomX + 20;
        XPoints[4]=  randomX;

        YPoints[0]=  randomY;
        YPoints[1]=  randomY;
        YPoints[2]=  randomY + 30;
        YPoints[3]=  randomY + 15;
        YPoints[4]=  randomY + 30;
        setCoordX(XPoints);
        setCoordY(YPoints);
        this.life = life;
        this.rewild = rewild;
        this.damage = damage;
    }

    public void repostEnemy(int randomX, int randomY){

        XPoints[0]=  randomX;
        XPoints[1]=  randomX + 40;
        XPoints[2]=  randomX + 40;
        XPoints[3]=  randomX + 20;
        XPoints[4]=  randomX;

        YPoints[0]=  randomY;
        YPoints[1]=  randomY;
        YPoints[2]=  randomY + 30;
        YPoints[3]=  randomY + 15;
        YPoints[4]=  randomY + 30;
        setCoordX(XPoints);
        setCoordY(YPoints);
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

    public int getDamage() {
        return damage;
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
    public void die(int xbullet, int ybullet, int i) {
        if(YPoints[0] == ybullet){
            if(xbullet >= XPoints[0] && xbullet <= XPoints[1]){
                life -= i;
            }
        }
    }
    public void reduceLife(int damage) {
        this.life -= damage;
    }

    public int getWidth() {
        // La anchura es la distancia entre el primer y segundo punto en el eje X
        return XPoints[1] - XPoints[0];
    }

    public int getHeight() {
        int maxY = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        for (int y : YPoints) {
            if (y > maxY) {
                maxY = y;
            }
            if (y < minY) {
                minY = y;
            }
        }
        return maxY - minY;
    }
}