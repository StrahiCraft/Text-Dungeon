package dungeon;

import dungeon.rooms.DungeonRoom;
import dungeon.rooms.EmptyRoom;
import entity.player.Player;
import utility.Vector2Int;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Stack;

public class DungeonGenerator {
    private static final int MAX_ROOMS = 20;

    public static void generateDungeon(){
        Dungeon.setDungeonRooms(new Hashtable<>());

        Stack<DungeonRoom> roomsToPropagate = new Stack<DungeonRoom>();

        Dungeon.getDungeonRooms().put(new Vector2Int().toString(), new EmptyRoom(new Vector2Int(0, 0)));
        roomsToPropagate.push(Dungeon.getDungeonRooms().get(new Vector2Int().toString()));

        while(Dungeon.getDungeonRooms().size() <= MAX_ROOMS && !roomsToPropagate.isEmpty()){
            DungeonRoom currentRoom = roomsToPropagate.pop();

            tryAddRoomOrConnection(currentRoom, roomsToPropagate, Vector2Int.up());
            tryAddRoomOrConnection(currentRoom, roomsToPropagate, Vector2Int.down());
            tryAddRoomOrConnection(currentRoom, roomsToPropagate, Vector2Int.left());
            tryAddRoomOrConnection(currentRoom, roomsToPropagate, Vector2Int.right());
        }

        Player.Instance.setCurrentRoom(Dungeon.getStartingRoom());
    }

    private static void tryAddRoomOrConnection(DungeonRoom currentRoom, Stack<DungeonRoom> roomsToPropagate, Vector2Int direction) {
        if(currentRoom.getRoom(direction) != null){
            return;
        }

        if(Math.random() * 100.0 >= (MAX_ROOMS - Dungeon.getDungeonRooms().size() - 1) * (100.0 / MAX_ROOMS)) {
            return;
        }

        DungeonRoom newRoom = makeNewRoomConnection(currentRoom, direction);

        if(newRoom == null){
            return;
        }

        currentRoom.setRoom(newRoom, direction);
        newRoom.setRoom(currentRoom, direction.reversed());

        if(Dungeon.getDungeonRooms().get(newRoom.getPosition().toString()) != null){
            return;
        }

        roomsToPropagate.push(newRoom);
        Dungeon.getDungeonRooms().put(newRoom.getPosition().toString(), newRoom);

        Dungeon.getDungeonBounds().updateDungeonBounds(newRoom);
    }

    private static DungeonRoom makeNewRoomConnection(DungeonRoom currentRoom, Vector2Int direction) {
        for (DungeonRoom dungeonRoom : Collections.list(Dungeon.getDungeonRooms().elements())) {
            if (dungeonRoom.getPosition().equalValue(currentRoom.getPosition().add(direction))) {
                currentRoom.setRoom(dungeonRoom, direction);
                dungeonRoom.setRoom(currentRoom, direction.reversed());
                return null;
            }
        }

        return new EmptyRoom(currentRoom.getPosition().add(direction));
    }
}
