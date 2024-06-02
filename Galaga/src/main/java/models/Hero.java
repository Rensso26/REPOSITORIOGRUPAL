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
    private int life;
    private int score;

    public Hero() {
        super(3);
        setCoordX(XPoints);
        setCoordY(YPoints);
        this.life = 100; // Establece la vida inicial del héroe
        this.score = 0; // Establece el puntaje inicial del héroe
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
    public boolean collidesWith(Bullets bullet) {
        Polygon heroPolygon = new Polygon(getCoordX(), getCoordY(), getCoordX().length);
        Rectangle bulletRect = new Rectangle(bullet.getX(), bullet.getY(), 5, 10);
        return heroPolygon.intersects(bulletRect);
    }
}

