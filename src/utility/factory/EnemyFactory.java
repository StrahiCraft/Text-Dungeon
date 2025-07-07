package utility.factory;

import entity.enemy.Enemy;
import utility.Stats;

/**
 * A factory class for creating enemy types,
 * run the createEnemyFiles() function
 * from the main file when you add a new enemy type or change an existing one.
 */
public class EnemyFactory {

    public static void main(String[] args) {
        createEnemyFiles();
    }

    /**
     * Creates enemy files in the assets/enemies directory
     */
    public static void createEnemyFiles(){
        Enemy slime = new Enemy("Slime", new Stats(2f, 2f, 0f, 1f), 0.15f);
        slime.writeToFile();

        Enemy troll = new Enemy("Troll", new Stats(10f, 10f, 5f, 3f), 0.8f);
        troll.writeToFile();

        Enemy livingStatue = new Enemy("Living Statue", new Stats(10f, 10f, 10f, 1.5f), 0.6f);
        livingStatue.writeToFile();

        Enemy crystalSpider = new Enemy("Crystal Spider", new Stats(15f, 15f, 7f, 2.5f), 1f);
        crystalSpider.writeToFile();

        Enemy livingSword = new Enemy("Living Sword", new Stats(5f, 5f, 0f, 7.5f), 1.3f);
        livingSword.writeToFile();
    }
}
