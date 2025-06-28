package dungeon;

import dungeon.rooms.DungeonRoom;
import dungeon.utility.DungeonBounds;
import utility.Vector2Int;

import java.util.*;

public class Dungeon {
    private static Dictionary<String, DungeonRoom> dungeonRooms = new Hashtable<>();

    private static DungeonBounds dungeonBounds = new DungeonBounds();

    public static Dictionary<String, DungeonRoom> getDungeonRooms() {
        return dungeonRooms;
    }

    public static DungeonBounds getDungeonBounds() {
        return dungeonBounds;
    }

    public static void setDungeonRooms(Dictionary<String, DungeonRoom> dungeonRooms) {
        Dungeon.dungeonRooms = dungeonRooms;
    }

    public static DungeonRoom getStartingRoom(){
        return dungeonRooms.get("x=0, y=0");
    }
}
