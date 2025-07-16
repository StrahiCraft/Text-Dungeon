package combat;

import entity.enemy.Enemy;

import java.util.ArrayList;

public class CombatManager {
    private static ArrayList<Enemy> enemies = new ArrayList<>();
    private static boolean inCombat = false;

    public static void resetCombat(){
        enemies.clear();
    }

    public static ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public static void setEnemies(ArrayList<Enemy> enemies) {
        CombatManager.enemies = enemies;
    }

    public static boolean isInCombat() {
        return inCombat;
    }

    public static void setInCombat(boolean inCombat) {
        CombatManager.inCombat = inCombat;
    }
}
