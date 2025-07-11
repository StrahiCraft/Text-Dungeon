package dungeon;

import dungeon.rooms.DungeonRoom;
import dungeon.utility.DungeonBounds;
import entity.player.Player;
import utility.Vector2Int;

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

    public static void setRoom(DungeonRoom newRoom, Vector2Int roomPosition){
        DungeonRoom oldRoom = dungeonRooms.get(roomPosition.toString());

        if(oldRoom.getNorthRoom() != null){
            oldRoom.getNorthRoom().setSouthRoom(newRoom);
        }
        if(oldRoom.getEastRoom() != null){
            oldRoom.getEastRoom().setWestRoom(newRoom);
        }
        if(oldRoom.getSouthRoom() != null){
            oldRoom.getSouthRoom().setNorthRoom(newRoom);
        }
        if(oldRoom.getWestRoom() != null){
            oldRoom.getWestRoom().setEastRoom(newRoom);
        }

        newRoom.setNorthRoom(oldRoom.getNorthRoom());
        newRoom.setEastRoom(oldRoom.getEastRoom());
        newRoom.setEastRoom(oldRoom.getSouthRoom());
        newRoom.setWestRoom(oldRoom.getWestRoom());

        dungeonRooms.put(roomPosition.toString(), newRoom);

        if(Player.Instance.getCurrentRoom().getPosition().equalValue(roomPosition)){
            Player.Instance.setCurrentRoom(newRoom);
        }
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
