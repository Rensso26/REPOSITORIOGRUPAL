package models;

public class LevelParameters {
    int numEnemies;
    int enemyLife;
    int enemyShootCount;
    int heroLifeDeduction;
    int heroLifeBonus;
    int superEnemyLife;

    public LevelParameters(int numEnemies, int enemyLife, int enemyShootCount, int heroLifeDeduction, int heroLifeBonus, int superEnemyLife) {
        this.numEnemies = numEnemies;
        this.enemyLife = enemyLife;
        this.enemyShootCount = enemyShootCount;
        this.heroLifeDeduction = heroLifeDeduction;
        this.heroLifeBonus = heroLifeBonus;
        this.superEnemyLife = superEnemyLife;
    }

    // Getters para cada atributo
    public int getNumEnemies() { return numEnemies; }
    public int getEnemyLife() { return enemyLife; }
    public int getEnemyShootCount() { return enemyShootCount; }
    public int getHeroLifeDeduction() { return heroLifeDeduction; }
    public int getHeroLifeBonus() { return heroLifeBonus; }
    public int getSuperEnemyLife() { return superEnemyLife; }
}
