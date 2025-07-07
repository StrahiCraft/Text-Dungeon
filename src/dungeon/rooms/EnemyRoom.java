package dungeon.rooms;

import dungeon.Dungeon;
import entity.enemy.Enemy;
import entity.enemy.EnemyGenerator;
import entity.player.states.PlayerPreCombat;
import entity.player.states.PlayerState;
import graphics.Color;
import graphics.TextRenderer;
import utility.Vector2Int;

import java.util.ArrayList;

public class EnemyRoom extends DungeonRoom {

    private ArrayList<Enemy> enemies;

    public EnemyRoom(Vector2Int position, ArrayList<Enemy> enemies) {
        super(position);
        this.enemies = enemies;
        setRoomSymbol('E');
        setDescriptionText("You find yourself in a room with "
                + Color.getColor("bright yellow") + enemies.size()
                + Color.getColor("red") + " enemies" + Color.resetColor()
                + ". You can either " + Color.getColor("bright red") + " fight " + Color.resetColor()
                + "or " + Color.getColor("bright blue") + " flee " + Color.resetColor() + ".");
    }

    public EnemyRoom(Vector2Int position) {
        super(position);
        generateEnemies();
        setRoomSymbol('E');
        setDescriptionText("You find yourself in a room with "
                + Color.getColor("bright yellow") + enemies.size()
                + Color.getColor("red") + " enemies" + Color.resetColor()
                + ". You can either " + Color.getColor("bright red") + " fight " + Color.resetColor()
                + "or " + Color.getColor("bright blue") + " flee " + Color.resetColor() + ".");
    }

    @Override
    public PlayerState getRoomState() {
        return new PlayerPreCombat();
    }

    @Override
    public void onRoomEntered() {
        TextRenderer.printText(getDescriptionText());
    }

    private void generateEnemies() {
        enemies = new ArrayList<Enemy>();

        float remainingThreat = Dungeon.getCurrentDungeonThreat();

        while(remainingThreat > 0) {
            Enemy generatedEnemy = EnemyGenerator.generateEnemy(remainingThreat);

            if(generatedEnemy == null){
                return;
            }

            remainingThreat -= generatedEnemy.getThreatLevel();
            enemies.add(generatedEnemy);

            if(Math.random() < 0.25){
                return;
            }
        }
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }
}
