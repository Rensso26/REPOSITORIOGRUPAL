package Controlers;

import State.GameManager;
import State.GameState;
import models.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
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
    Life life;
    LifeSuperEnemy lifeSuperEnemy;

    Score score = new Score(hero.getScore());
    List<Enemy> enemyList = new ArrayList<>();
    List<Bullets> bulletsHeroList = new ArrayList<>();
    List<Bullets> bulletsEnemyList = new ArrayList<>();
    Random random = new Random();

    private int currentLevelIndex;
    private Long currentGameStateId = 4L; // ID fijo para el estado del juego

    List<SuperEnemy> superEnemyList = new ArrayList<>();

    // Instancia de GameManager para manejar el estado del juego
    private GameManager gameManager;
    LevelParameters[] levelParams = {
            new LevelParameters(1, 5, 1, 5, 5, 0),   // Nivel 1
            new LevelParameters(1, 15, 2, 10, 10, 0),  // Nivel 2
            new LevelParameters(1, 100, 3, 15, 15, 100)  // Nivel 3 con SuperEnemy
    };

    public Container() {
        this.currentLevelIndex = 0;
        this.gameManager = new GameManager(); // Inicializa GameManager
        loadGameState(); // Cargar el estado del juego al iniciar
    }

    public void InitialHero(String name, String password) {
        hero.setName(name);
        hero.setPassword(password);
    }

    private void initializeLevel1() {
        LevelParameters params = levelParams[0];
        enemyList.clear();
        for (int i = 0; i < params.getNumEnemies(); i++) {
            enemyList.add(new Enemy(random.nextInt(SCREEN_WIDTH), random.nextInt(SCREEN_HEIGHT - GAME_STATE_PANEL) + GAME_STATE_PANEL, params.getEnemyLife(),  5));
        }
    }

    private void initializeLevel2() {
        LevelParameters params = levelParams[1];
        enemyList.clear();
        for (int i = 0; i < params.getNumEnemies(); i++) {
            enemyList.add(new Enemy(random.nextInt(SCREEN_WIDTH), random.nextInt(SCREEN_HEIGHT - GAME_STATE_PANEL) + GAME_STATE_PANEL, params.getEnemyLife(), 10));
        }
    }

    private void initializeLevel3() {
        LevelParameters params = levelParams[2];
        enemyList.clear();
        superEnemyList.clear();
        int centerX = SCREEN_WIDTH / 2 - (int) (14 * 2.5) / 2;
        int centerY = SCREEN_HEIGHT / 2 - (int) (30 * 2.5) / 2;
        SuperEnemy superEnemy = new SuperEnemy(centerX, centerY + GAME_STATE_PANEL, params.getSuperEnemyLife(), 15);
        superEnemyList.add(superEnemy);
    }

    private void initializeNextLevel() {
        score.plus(hero.getScore());
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
                resetGameState();
                saveGameState();
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
            lifeSuperEnemy = new LifeSuperEnemy(superenemy.getLife(), superenemy.getCoordX()[0], superenemy.getCoordY()[0] - 30);
            lifeSuperEnemy.draw(g);
        }
    }

    public void moveLeft(int distance) {
        hero.moveLeft(distance);
    }

    public void moveRight(int distance) {
        hero.moveRight(distance);
    }

    public void moveDown(int distance) {
        for (Enemy enemy : enemyList) {
            enemy.moveDown(distance);
            for (int y : enemy.getCoordY()) {
                if (y >= 600 * 0.66) {
                    enemy.repostEnemy(random.nextInt(SCREEN_WIDTH), random.nextInt(SCREEN_HEIGHT - GAME_STATE_PANEL) + GAME_STATE_PANEL);
                    hero.setLife(hero.getLife() - 5);
                }
            }
        }
        for (SuperEnemy superEnemy : superEnemyList) {
            superEnemy.moveDown(distance);
            lifeSuperEnemy.moveDown(distance);
            for (int y : superEnemy.getCoordY()) {
                if (y >= 600 * 0.66) {
                    superEnemy.repostEnemy(random.nextInt(SCREEN_WIDTH), random.nextInt(SCREEN_HEIGHT - GAME_STATE_PANEL) + GAME_STATE_PANEL);
                    hero.setLife(hero.getLife() - 5);
                }
            }
        }
    }

    public void shootHeroBullet() {
        bulletsHeroList.add(new Bullets(hero.getX(), hero.getY(), Color.WHITE));
    }

    public void enemyShoot() {
        LevelParameters params = levelParams[currentLevelIndex - 1]; // Obtener los parámetros del nivel actual

        for (Enemy enemy : enemyList) {
            for (int i = 0; i < params.getEnemyShootCount(); i++) { // Usar parámetro del nivel
                bulletsEnemyList.add(new Bullets(enemy.getCoordX()[0] + i * 35, enemy.getCoordY()[1], Color.RED));
            }
        }
        for (SuperEnemy superEnemy : superEnemyList) {
            for (int i = 0; i < params.getEnemyShootCount(); i++) { // Usar parámetro del nivel
                bulletsEnemyList.add(new Bullets(superEnemy.getCoordX()[0] + i * 45, superEnemy.getCoordY()[1], Color.YELLOW));
            }
        }
    }

    public void levelstarter() {
        if (enemyList.isEmpty() && superEnemyList.isEmpty() && currentLevelIndex <= 3) {
            currentLevelIndex += 1;
            initializeNextLevel();

            saveGameState(); // Guardar el estado del juego al iniciar el siguiente nivel
        }
    }

    public void killEnemies() {
        LevelParameters params = levelParams[currentLevelIndex - 1]; // Obtener los parámetros del nivel actual
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
                        hero.setScore(hero.getScore() + enemy.getRewild());
                        score.plus(hero.getScore());
                        hero.setLife(hero.getLife() + params.getHeroLifeBonus()); // Usar parámetro del nivel
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
                        hero.setScore(hero.getScore() + superEnemy.getRewild());
                        score.plus(hero.getScore());
                        bullets.setDamage(5);
                    }
                    break;
                }
            }
        }
    }

    public void damageSuperEnemi(){
        Iterator<SuperEnemy> iteratorS = superEnemyList.iterator();
        while (iteratorS.hasNext()) {
            SuperEnemy superEnemy = iteratorS.next();
            for (Bullets bullets : bulletsEnemyList) {
                    if (superEnemy.getLife() <= 0) {
                        iteratorS.remove();
                        hero.setScore(hero.getScore()+superEnemy.getRewild());
                        score.plus(superEnemy.getRewild());
                    } else if (superEnemy.getLife() < 50) {
                        bullets.setDamage(5); // Deduce 5 puntos si la vida < 50
                    } else if (superEnemy.getLife() > 50 && superEnemy.getLife() < 75) {
                        bullets.setDamage(10); // Deduce 10 puntos si la vida > 50 y < 75
                    } else if (superEnemy.getLife() > 75) {
                        bullets.setDamage(15); // Deduce 15 puntos si la vida > 75
                    }
                    break;
            }
        }
    }
    public void killhero() {
        Iterator<Bullets> enemyBulletIterator = bulletsEnemyList.iterator();
        while (enemyBulletIterator.hasNext()) {
            Bullets bullet = enemyBulletIterator.next();
            if (bullet.getY() >= hero.getCoordY()[0] && bullet.getY() <= hero.getCoordY()[2]&&
                    bullet.getX() >= hero.getCoordX()[2] && bullet.getX() <= hero.getCoordX()[1]) {
                enemyBulletIterator.remove();
                hero.setLife(hero.getLife() - 5);
                if (hero.getLife() <= 0) {
                    resetGameState();
                    saveGameState();
                    stopGame();
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

            if (bullet.getY() < 0) {
                enemyBulletIterator.remove();
            }
        }
    }



    private void stopGame() {
        String message = (hero.getLife() <= 0) ? "¡Has sido derrotado!" : "¡Has completado todos los niveles!";
        JOptionPane.showMessageDialog(null, message, "Juego terminado", JOptionPane.INFORMATION_MESSAGE);

        // Resetear el estado del juego
        resetGameState();

        // Guardar el estado inicial del juego en la base de datos
        saveGameState();
        System.exit(0);
    }

    public boolean isGameOver() {
        return hero.getLife() <= 0;
    }

    // Métodos para guardar y cargar el estado del juego

    private void saveGameState() {
        GameState gameState = new GameState();
        gameState.setId(currentGameStateId); // ID fijo
        gameState.setName(hero.getName());
        gameState.setLevel(currentLevelIndex);
        gameState.setLife(hero.getLife());
        gameState.setPoints(hero.getScore());

        gameManager.saveCurrentGameState(gameState);
    }

    private void loadGameState() {
        gameManager.loadGameStateById(currentGameStateId); // Cargar el estado del juego con ID fijo
        GameState gameState = gameManager.getCurrentGameState();

        if (gameState != null) {
            hero.setName(gameState.getName());
            currentLevelIndex = gameState.getLevel();
            hero.setLife(gameState.getLife());
            hero.setScore(gameState.getPoints());
            initializeNextLevel();
        }
    }

    private void resetGameState() {
        hero.setLife(100);
        hero.setScore(0);
        currentLevelIndex = 1;
    }
}
