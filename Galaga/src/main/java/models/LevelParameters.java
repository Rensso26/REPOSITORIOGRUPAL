package models;

public class LevelParameters {
    int numEnemies;
    int enemyLife;
    int enemyShootCount;
    int heroLifeDeduction;
    int superEnemyLife;

    public LevelParameters(int numEnemies, int enemyLife, int enemyShootCount, int heroLifeDeduction, int superEnemyLife) {
        this.numEnemies = numEnemies;
        this.enemyLife = enemyLife;
        this.enemyShootCount = enemyShootCount;
        this.heroLifeDeduction = heroLifeDeduction;

        this.superEnemyLife = superEnemyLife;
    }

    // Getters para cada atributo
    public int getNumEnemies() { return numEnemies; }
    public int getEnemyLife() { return enemyLife; }
    public int getEnemyShootCount() { return enemyShootCount; }
    public int getHeroLifeDeduction() { return heroLifeDeduction; }
    public int getSuperEnemyLife() { return superEnemyLife; }
}
