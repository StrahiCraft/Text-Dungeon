package entity.enemy;

import utility.file.FileReader;

import java.io.File;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Generates enemy instances to copy from files located in assets/enemies.
 */
public class EnemyGenerator {
    private static Dictionary<String, Float> enemiesByThreat = new Hashtable<>();
    private static Dictionary<String, Enemy> enemiesByInstance = new Hashtable<>();

    public static void generateEnemiesFromFiles(){
        File enemyAssetFolder =  new File("assets/enemies");

        for(File currentEnemyFile : enemyAssetFolder.listFiles()) {
            Enemy currentEnemy = getEnemyFromFile(currentEnemyFile);

            enemiesByThreat.put(currentEnemy.getName(), currentEnemy.getThreatLevel());
            enemiesByInstance.put(currentEnemy.getName(), currentEnemy);
        }
    }

    private static Enemy getEnemyFromFile(File enemyFile){
        Enemy newEnemy = new Enemy();
        newEnemy.interpretFileData(FileReader.readFile(enemyFile));

        return newEnemy;
    }

    public static Dictionary<String, Float> getEnemiesByThreat() {
        return enemiesByThreat;
    }

    public static void setEnemiesByThreat(Dictionary<String, Float> enemiesByThreat) {
        EnemyGenerator.enemiesByThreat = enemiesByThreat;
    }

    public static Dictionary<String, Enemy> getEnemiesByInstance() {
        return enemiesByInstance;
    }

    public static void setEnemiesByInstance(Dictionary<String, Enemy> enemiesByInstance) {
        EnemyGenerator.enemiesByInstance = enemiesByInstance;
    }
}
