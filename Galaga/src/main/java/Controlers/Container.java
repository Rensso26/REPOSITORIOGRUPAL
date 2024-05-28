package Controlers;

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
    Random random = new Random();
    @Autowired
    Hero hero = new Hero();



    public Container(){
        enemyList.add(new Enemy(random.nextInt(SCREEN_WIDTH),random.nextInt(SCREEN_HEIGHT),5,10));
        enemyList.add(new Enemy(random.nextInt(SCREEN_WIDTH),random.nextInt(SCREEN_HEIGHT),5,10));
        enemyList.add(new Enemy(random.nextInt(SCREEN_WIDTH),random.nextInt(SCREEN_HEIGHT),5,10));
        enemyList.add(new Enemy(random.nextInt(SCREEN_WIDTH),random.nextInt(SCREEN_HEIGHT),5,10));
        enemyList.add(new Enemy(random.nextInt(SCREEN_WIDTH),random.nextInt(SCREEN_HEIGHT),5,10));

    }

    public void draw(Graphics g){
        this.hero.draw(g);
        for(int i = 0; i < enemyList.size(); i++) {
            enemyList.get(i).draw(g);
        }

    }

    public void moveLeft(int distance) {
        hero.moveLeft(distance);
    }

    public void moveRight(int distance) {
        hero.moveRigth(distance);
    }

    public void moveDown(int distance) {
        for(int i = 0; i < enemyList.size(); i++) {
            enemyList.get(i).moveDown(distance);
        }
    }
}

