package combat;

import dungeon.Dungeon;
import dungeon.rooms.EmptyRoom;
import entity.enemy.Enemy;
import entity.inventory.item.equipment.EquipItem;
import entity.player.Player;
import graphics.Color;
import graphics.TextRenderer;

import java.util.ArrayList;

public class CombatManager {
    private static ArrayList<Enemy> enemies = new ArrayList<>();
    private static boolean inCombat = false;
    private static int goldReward;

    public static void resetCombat() {
        enemies.clear();
        Player.Instance.getStats().refillSpeed();
        inCombat = true;
    }

    public static void endCombat() {
        inCombat = false;
        Dungeon.setRoom(new EmptyRoom(Player.Instance.getCurrentRoom().getPosition()),
                Player.Instance.getCurrentRoom().getPosition());
        TextRenderer.printText("You won the battle and got " + goldReward + Color.getColor("yellow") +
                " gold " + Color.resetColor() + "for it.");
        Player.Instance.addGold(goldReward);
    }

    public static void onEnemyDeath(Enemy deadEnemy) {
        enemies.remove(deadEnemy);
        if(enemies.isEmpty()){
            CombatManager.endCombat();
        }
    }

    public static void checkForTurnEnd(){
        if(Player.Instance.getStats().getCurrentSpeed() > 0){
            return;
        }

        for(Enemy enemy : enemies) {
            if(enemy.getStats().getCurrentSpeed() > 0) {
                return;
            }
        }

        onTurnEnd();
    }

    public static void attackEnemy(int index) {
        if(index < 1 || index > enemies.size()){
            TextRenderer.printText(Color.getColor("red") + "There is no such enemy!" + Color.resetColor());
            return;
        }

        Enemy attackedEnemy = enemies.get(index - 1);


        float currenEnemyHealth = attackedEnemy.getStats().getCurrentHealth();

        attackedEnemy.takeDamage(Player.Instance.getStats().getDamage());
        Player.Instance.getStats().useSpeed(2);

        if(attackedEnemy.getStats().getCurrentHealth() == 0){
            TextRenderer.printText("Defeated " + attackedEnemy.getName());
        }
        else {
            TextRenderer.printText("Dealt " + (currenEnemyHealth - attackedEnemy.getStats().getCurrentHealth()) +
                    Color.getColor("red") + " damage " + Color.resetColor() + "to " + attackedEnemy.getName());
        }

        checkForTurnEnd();
    }

    public static void takeEnemyAttack() {
        Enemy attackingEnemy = getFastestEnemy();

        float currentPlayerHealth = Player.Instance.getStats().getCurrentHealth();

        Player.Instance.takeDamage(attackingEnemy.getStats().getDamage());
        TextRenderer.printText(attackingEnemy.getName() + " attacks and deals " +
                (currentPlayerHealth - Player.Instance.getStats().getCurrentHealth()) +
                Color.getColor("red") + " damage" + Color.resetColor() + ".");
        attackingEnemy.getStats().useSpeed(2);

        checkForTurnEnd();
    }

    public static void useItem(int index) {
        if(index < 1 || index > Player.Instance.getInventory().getItems().size()){
            TextRenderer.printText(Color.getColor("red") + "There is no such item!" + Color.resetColor());
            return;
        }

        if(Player.Instance.getInventory().getItem(index).getClass() == EquipItem.class){
            TextRenderer.printText(Color.getColor("red") +
                    "You cannot use equip items during combat!" + Color.resetColor());
            return;
        }

        Player.Instance.getInventory().useItem(index);
        Player.Instance.getStats().useSpeed(1);
        checkForTurnEnd();
    }

    private static void onTurnEnd(){
        Player.Instance.getStats().refillSpeed();
        for(Enemy enemy : enemies) {
            enemy.getStats().refillSpeed();
        }
    }

    public static boolean playerTurn() {
        try {
            return Player.Instance.getStats().getCurrentSpeed() > getFastestEnemy().getStats().getCurrentSpeed();
        } catch (NullPointerException ignored){
            return false;
        }
    }

    private static Enemy getFastestEnemy() {
        if(enemies.isEmpty()){
            return null;
        }

        Enemy fastestEnemy = enemies.get(0);

        for(Enemy enemy : enemies) {
            if(enemy.getStats().getCurrentSpeed() > fastestEnemy.getStats().getCurrentSpeed()){
                fastestEnemy = enemy;
            }
        }
        return fastestEnemy;
    }

    public static ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public static void setEnemies(ArrayList<Enemy> enemies) {
        CombatManager.enemies = enemies;
        goldReward = (int)(Dungeon.getDungeonStats().getCurrentThreat() * enemies.size());
    }

    public static boolean isInCombat() {
        return inCombat;
    }

    public static void setInCombat(boolean inCombat) {
        CombatManager.inCombat = inCombat;
    }

    public static int getGoldReward() {
        return goldReward;
    }

    public static void setGoldReward(int goldReward) {
        CombatManager.goldReward = goldReward;
    }

    public static String asString() {
        StringBuilder combatStatus = new StringBuilder();

        combatStatus.append(Color.getColor("red")).append("Enemies:\n").append(Color.resetColor());

        int aliveEnemyIndex = 1;
        for(Enemy enemy : enemies) {
            if(enemy.getStats().getCurrentHealth() > 0) {
                combatStatus.append(aliveEnemyIndex++).append(". ").append(enemy).append('\n');
            }
        }

        return combatStatus.toString();
    }
}
