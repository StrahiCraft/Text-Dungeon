import dungeon.DungeonGenerator;
import dungeon.DungeonMapRenderer;

public class Main {
    public static void main(String[] args) {
        DungeonGenerator.generateDungeon();

        DungeonMapRenderer.renderDungeonMap();
    }
}