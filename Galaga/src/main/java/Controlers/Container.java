package Controlers;

import models.Bullets;
import models.Enemy;
import models.Hero;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Container {

    final int SCREEN_WIDTH = 700;
    final int SCREEN_HEIGHT = 200;

    List <Enemy> enemyList = new ArrayList();
    List <Bullets> bulletsHeroList = new ArrayList();
    Random random = new Random();

    Hero hero = new Hero();

    Bullets bullet;



    public Container(){
        for (int i = 0; i < 5; i++) {
            enemyList.add(new Enemy(random.nextInt(SCREEN_WIDTH), random.nextInt(SCREEN_HEIGHT), 5, 10));
        }

    }

    public void draw(Graphics g){
        this.hero.draw(g);
        for (Enemy enemy : enemyList) {
            enemy.draw(g);
        }
        for (Bullets bullet : bulletsHeroList) {
            bullet.draw(g);
        }

    }

    public void moveLeft(int distance) {
        hero.moveLeft(distance);
    }

    public void moveRight(int distance) {
        hero.moveRigth(distance);
    }

    public void moveDown(int distance) {
        for (Enemy enemy : enemyList) {
            enemy.moveDown(distance);
        }
    }
    public void shootHeroBullet() {
        Bullets newBullet = new Bullets(hero.getX(), hero.getY());
        bulletsHeroList.add(newBullet);

    }
}

