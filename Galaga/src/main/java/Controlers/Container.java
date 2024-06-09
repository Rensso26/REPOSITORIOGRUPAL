package Controlers;

import models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Container {

    final int SCREEN_WIDTH = 700;
    final int SCREEN_HEIGHT = 100;
    final int GAME_STATE_PANEL = 50;
    Hero hero = new Hero();
    Line line = new Line();
    Life life ;
    LifeSuperEnemy lifeSuperEnemy ;

    Score score = new Score(hero.getScore());
    List<Enemy> enemyList = new ArrayList<>();
    List<Bullets> bulletsHeroList = new ArrayList<>();
    List<Bullets> bulletsEnemyList = new ArrayList<>();
    Random random = new Random();

    private int currentLevelIndex;
    List<SuperEnemy> superEnemyList = new ArrayList<>();


    public Container() {
        this.currentLevelIndex = 0;
    }

    public void InitialHero(String name, String password){
        hero.setName(name);
        hero.setPassword(password);
    }

    private void initializeLevel1() {
        for (int i = 0; i < 1; i++) {
            enemyList.add(new Enemy(random.nextInt(SCREEN_WIDTH), random.nextInt(SCREEN_HEIGHT - GAME_STATE_PANEL) + GAME_STATE_PANEL, 5, 10, 5));
        }
    }

    private void initializeLevel2() {
        enemyList.clear();
        for (int i = 0; i < 1; i++) {
            enemyList.add(new Enemy(random.nextInt(SCREEN_WIDTH), random.nextInt(SCREEN_HEIGHT - GAME_STATE_PANEL) + GAME_STATE_PANEL, 10, 20, 10));
        }
    }

    private void initializeLevel3() {
        enemyList.clear();
        superEnemyList.clear();
        int centerX = SCREEN_WIDTH / 2 - (int) (14 * 2.5) / 2;
        int centerY = SCREEN_HEIGHT / 2 - (int) (30 * 2.5) / 2;
        SuperEnemy superEnemy = new SuperEnemy(centerX, centerY + GAME_STATE_PANEL, 100,15);
        superEnemyList.add(superEnemy);
    }

    private void initializeNextLevel() {

            switch (currentLevelIndex) {
                case 1:
                    initializeLevel1();
                    break;
                case 2:
                    initializeLevel2();
                    break;
                case 3:
                    initializeLevel3();
                    break;

                case 4:
                    stopGame();
                    break;
            }

        }

    public void draw(Graphics g) {
        line.draw(g);
        hero.draw(g);
        life = new Life(hero.getLife());
        life.draw(g);
        score.draw(g);

        g.setColor(Color.WHITE);
        g.drawString("Nivel " + currentLevelIndex, 350, 20);

        for (Enemy enemy : enemyList) {
            enemy.draw(g);
        }
        for (Bullets bullet : bulletsHeroList) {
            bullet.draw(g);
        }
        for (Bullets bullet : bulletsEnemyList) {
            bullet.draw(g);
        }
        for (SuperEnemy superenemy : superEnemyList) {
            superenemy.draw(g);
            lifeSuperEnemy= new LifeSuperEnemy(superenemy.getLife(),superenemy.getCoordX()[0],superenemy.getCoordY()[0]-30);
            lifeSuperEnemy.draw(g);
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
            for (int y : enemy.getCoordY()) {
                if (y >= 600*0.66) {
                    enemy.repostEnemy(random.nextInt(SCREEN_WIDTH), random.nextInt(SCREEN_HEIGHT - GAME_STATE_PANEL) + GAME_STATE_PANEL);
                    hero.setLife(hero.getLife()-5);
                }
            }
        }
        for (SuperEnemy superEnemy : superEnemyList) {
            superEnemy.moveDown(distance);
            lifeSuperEnemy.moveDown(distance);
            for (int y : superEnemy.getCoordY()) {
                if (y >= 600*0.66) {
                    superEnemy.repostEnemy(random.nextInt(SCREEN_WIDTH), random.nextInt(SCREEN_HEIGHT - GAME_STATE_PANEL) + GAME_STATE_PANEL);
                    hero.setLife(hero.getLife()-5);
                }
            }
        }

    }

    public void shootHeroBullet() {
        bulletsHeroList.add(new Bullets(hero.getX(), hero.getY(),Color.WHITE));

    }
    public void enemyShoot() {
        for (Enemy enemy : enemyList) {
            bulletsEnemyList.add(new Bullets(enemy.getCoordX()[0], enemy.getCoordY()[1], Color.RED));
        }
        for (SuperEnemy superEnemy : superEnemyList) {
            bulletsEnemyList.add(new Bullets(superEnemy.getCoordX()[1], superEnemy.getCoordY()[1], Color.YELLOW));
        }
    }

    public void levelstarter(){
        if (enemyList.isEmpty() && superEnemyList.isEmpty() && currentLevelIndex <= 3) {
            currentLevelIndex += 1;
            initializeNextLevel();
        }
    }

    public void killEnemies() {
        Iterator<Enemy> iterator = enemyList.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            for (Bullets bullets : bulletsHeroList) {
                if (bullets.getY() <= enemy.getCoordY()[2] &&
                        bullets.getX() >= enemy.getCoordX()[0] && bullets.getX() <= enemy.getCoordX()[1]) {
                    bulletsHeroList.remove(bullets);
                    enemy.setLife(enemy.getLife() - 5);
                    if (enemy.getLife() <= 0) {
                        iterator.remove();
                        score.plus(enemy.getRewild());
                    }
                    break;
                }

            }
        }
        Iterator<SuperEnemy> iteratorS = superEnemyList.iterator();
        while (iteratorS.hasNext()) {
            SuperEnemy superEnemy = iteratorS.next();
            for (Bullets bullets : bulletsHeroList) {
                if (bullets.getY() <= superEnemy.getCoordY()[2] &&
                        bullets.getX() >= superEnemy.getCoordX()[0] && bullets.getX() <= superEnemy.getCoordX()[1]) {
                    bulletsHeroList.remove(bullets);
                    superEnemy.setLife(superEnemy.getLife() - 5);
                    if (superEnemy.getLife() <= 0) {
                        iteratorS.remove();
                        score.plus(superEnemy.getRewild());
                    }
                    break;
                }
            }
        }
    }
    public void killhero(){
        Iterator<Bullets> enemyBulletIterator = bulletsEnemyList.iterator();
        while (enemyBulletIterator.hasNext()) {
            Bullets bullet = enemyBulletIterator.next();
            if (bullet.getY() >= hero.getCoordY()[0] &&
                    bullet.getX() >= hero.getCoordX()[2] && bullet.getX() <=hero.getCoordX()[1] ) {
                enemyBulletIterator.remove();
                hero.setLife(hero.getLife() - 5);
            }
        }
    }


    public void moveBullets() {

        Iterator<Bullets> heroBulletIterator = bulletsHeroList.iterator();
        while (heroBulletIterator.hasNext()) {
            Bullets bullet = heroBulletIterator.next();
            bullet.moveUp(2);
            if (bullet.getY() < 0) {
                heroBulletIterator.remove();
            }
        }


        Iterator<Bullets> enemyBulletIterator = bulletsEnemyList.iterator();
        while (enemyBulletIterator.hasNext()) {
            Bullets bullet = enemyBulletIterator.next();
            bullet.moveDown(2);

            if (bullet.getY()  < 0) {
                enemyBulletIterator.remove();
            }
        }
    }



        private void stopGame() {
            //AGREGADO
            String message = (hero.getLife() <= 0) ? "¡Has sido derrotado!" : "¡Has completado todos los niveles!";
            JOptionPane.showMessageDialog(null, message, "Juego terminado", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }

    public boolean isGameOver() {
        //AGREGADO
        return hero.getLife() <= 0;
    }

}