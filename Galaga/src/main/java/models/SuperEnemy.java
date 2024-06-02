package models;

import Actions.Dieable;
import Actions.Drawable;
import Actions.MovableY;
import java.awt.*;

public class SuperEnemy extends Role implements Drawable, MovableY, Dieable {
    int[] XPoints = new int[5];
    int[] YPoints = new int[5];

    private int life;

    public SuperEnemy(int centerX, int centerY, int life) {
        super(5);
        int width = 40;
        int height = 30;
        double scale = 2.5;

        XPoints[0] = centerX;
        XPoints[1] = centerX + (int)(width * scale);
        XPoints[2] = centerX + (int)(width * scale);
        XPoints[3] = centerX + (int)((width / 2) * scale);
        XPoints[4] = centerX;

        YPoints[0] = centerY;
        YPoints[1] = centerY;
        YPoints[2] = centerY + (int)(height * scale);
        YPoints[3] = centerY + (int)((height / 2) * scale);
        YPoints[4] = centerY + (int)(height * scale);

        setCoordX(XPoints);
        setCoordY(YPoints);
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED); // Usar un color diferente para diferenciar el SuperEnemy
        g.fillPolygon(getCoordX(), getCoordY(), getCoordY().length);
    }

    @Override
    public void moveUp(int distance) {}

    @Override
    public void moveDown(int distance) {
        for (int i = 0; i < YPoints.length; i++) {
            YPoints[i] = YPoints[i] + distance;
            setCoordY(YPoints);
        }
    }

    @Override
    public void die(int xbullet, int ybullet, int i) {
        if (YPoints[0] == ybullet) {
            if (xbullet >= XPoints[0] && xbullet <= XPoints[1]) {
                life -= i;
            }
        }
    }

    public boolean collidesWith(Bullets bullet) {
        Polygon enemyPolygon = new Polygon(getCoordX(), getCoordY(), getCoordX().length);
        Rectangle bulletRect = new Rectangle(bullet.getX(), bullet.getY(), 5, 10);
        return enemyPolygon.intersects(bulletRect);
    }
}
