package Controlers;

import models.*;

import javax.swing.*;
import java.awt.*;
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
    Life life = new Life(hero.getLife());
    Score score = new Score(hero.getScore());
    List<Enemy> enemyList = new ArrayList<>();
    List<Bullets> bulletsHeroList = new ArrayList<>();
    List<Bullets> bulletsEnemyList = new ArrayList<>();
    Random random = new Random();

    private int currentLevelIndex;
    List<SuperEnemy> superEnemyList = new ArrayList<>();

    public Container() {
        this.currentLevelIndex = 0;
        initializeNextLevel();

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
        superEnemyList.add(new SuperEnemy(centerX, centerY + GAME_STATE_PANEL, 100));
    }

    private void initializeNextLevel() {

            switch (currentLevelIndex) {
                case 1:
                    initializeLevel3();
                    break;
                case 2:
                    initializeLevel2();
                    break;
                case 3:
                    initializeLevel1();

                    break;

                case 4:
                    stopGame();
                    break;
            }

        }





    public void draw(Graphics g) {
        line.draw(g);
        hero.draw(g);
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
            if(enemy.getCoordY()[2]>=(600*0.66)){
                enemyList.add(new Enemy(random.nextInt(SCREEN_WIDTH), random.nextInt(SCREEN_HEIGHT)));
                hero.setLife(hero.getLife()-enemy.getDamage());
                enemyList.remove(enemy);
            }
        }

    }

    public void shootHeroBullet() {
        bulletsHeroList.add(new Bullets(hero.getX(), hero.getY()));
    }

    public void killEnemies() {
        if (enemyList.isEmpty()&&superEnemyList.isEmpty()) {
            currentLevelIndex+=1;
            initializeNextLevel();
        }
        Iterator<Enemy> iterator = enemyList.iterator();
        Iterator<SuperEnemy> iteratorS = superEnemyList.iterator();
        while (iterator.hasNext()) {
            Enemy enemys = iterator.next();
            for (Bullets bullets : bulletsHeroList) {
                if (bullets.getY() <= enemys.getCoordY()[2] &&
                        bullets.getX() >= enemys.getCoordX()[0] && bullets.getX() <= enemys.getCoordX()[1]) {
                    bulletsHeroList.remove(bullets);
                    enemys.setLife(enemys.getLife()-5);
                    if(enemys.getLife()<=0){
                        iterator.remove();
                        score.plus(enemys.getRewild());
                    }
                    break;
                }

            }

        }
        while (iteratorS.hasNext()) {
            SuperEnemy superEnemy = iteratorS.next();
            for (Bullets bullets : bulletsHeroList) {
                if (bullets.getY() <= superEnemy.getCoordY()[2] &&
                        bullets.getX() >= superEnemy.getCoordX()[0] && bullets.getX() <= superEnemy.getCoordX()[1]){
                    bulletsHeroList.remove(bullets);
                    superEnemy.setLife(superEnemy.getLife() - 5);
                    if (superEnemy.getLife() <= 0) {
                        iteratorS.remove();
                    }
                    break;
                }
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
            if (bullet.getY() > SCREEN_HEIGHT) {
                enemyBulletIterator.remove();
            }
        }
    }


        private void stopGame() {

            JOptionPane.showMessageDialog(null, "¡Has completado todos los niveles!", "Juego completado", JOptionPane.INFORMATION_MESSAGE);

        }

    public boolean isGameOver(int dividerY) {
        if (hero.getLife() <= 0) {
            return true;
        }

        for (Enemy enemy : enemyList) {
            for (int y : enemy.getCoordY()) {
                if (y >= dividerY) {
                    return true;
                }
            }
        }


        return false;
    }

}
