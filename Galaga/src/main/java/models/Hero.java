package models;

import Actions.Drawable;
import Actions.MovableX;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.awt.*;

@Service ("hero")
public class Hero extends Role implements Drawable, MovableX {
    int[] XPoints = {400, 450, 350};
    int[] YPoints = {500, 550, 550};

    @Value("Payer 1")
    private String name;

    private String password;
    private int life;
    private int score;
    private int width; // Ancho del héroe
    private int height; // Altura del héroe

    public Hero() {
        super(3);
        setCoordX(XPoints);
        setCoordY(YPoints);
        this.life = 100;
        this.score = 0;
    }

    public Hero(String name, String password, int life, int score) {
        this.name = name;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillPolygon(getCoordX(), getCoordY(), getCoordY().length);
    }

    @Override
    public void moveRigth(int distance) {
        for (int i = 0; i < XPoints.length; i++) {
            XPoints[i] = XPoints[i] + distance;
            setCoordX(XPoints);
        }
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

    // Método para reducir la vida del héroe cuando recibe daño
    public void reduceLife(int damage) {
        life -= damage;
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[] getXPoints() {
        return XPoints;
    }

    public void setXPoints(int[] XPoints) {
        this.XPoints = XPoints;
    }

    public int[] getYPoints() {
        return YPoints;
    }

    public void setYPoints(int[] YPoints) {
        this.YPoints = YPoints;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean collidesWith(Bullets bullet) {
        Polygon heroPolygon = new Polygon(getCoordX(), getCoordY(), getCoordX().length);
        Rectangle bulletRect = new Rectangle(bullet.getX(), bullet.getY(), 5, 10);
        return heroPolygon.intersects(bulletRect);
    }
}