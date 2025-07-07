package dungeon;

import dungeon.rooms.DungeonRoom;
import dungeon.utility.DungeonBounds;

import java.util.*;

public class Dungeon {
    private static final float startingDungeonThreat = 0.5f;
    private static final float perFloorThreatIncrease = 0.1f;
    private static float currentDungeonThreat = startingDungeonThreat;

    private static Dictionary<String, DungeonRoom> dungeonRooms = new Hashtable<>();
    private static DungeonBounds dungeonBounds = new DungeonBounds();

    public static void resetDungeon(){
        currentDungeonThreat = startingDungeonThreat;
    }

    public static void increaseDungeonThreat(){
        currentDungeonThreat += perFloorThreatIncrease;
    }

    public static float getCurrentDungeonThreat() {
        return currentDungeonThreat;
    }

    public static void setCurrentDungeonThreat(float currentDungeonThreat) {
        Dungeon.currentDungeonThreat = currentDungeonThreat;
    }

    public static Dictionary<String, DungeonRoom> getDungeonRooms() {
        return dungeonRooms;
    }

    public static DungeonBounds getDungeonBounds() {
        return dungeonBounds;
    }

    public static void setDungeonBounds(DungeonBounds dungeonBounds) {
        Dungeon.dungeonBounds = dungeonBounds;
    }

    public static void setDungeonRooms(Dictionary<String, DungeonRoom> dungeonRooms) {
        Dungeon.dungeonRooms = dungeonRooms;
    }

    public static DungeonRoom getStartingRoom(){
        return dungeonRooms.get("x=0, y=0");
    }
}
