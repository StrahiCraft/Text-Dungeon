package entity.player;

import dungeon.Dungeon;
import dungeon.rooms.DungeonRoom;
import entity.Entity;
import entity.player.states.PlayerState;
import utility.Stats;

import java.util.Scanner;

public class Player extends Entity {
    public static Player Instance;

    private DungeonRoom currentRoom;
    private PlayerState currentState;

    public Player(Stats stats, String name) {
        super(stats, name);
        Instance = this;
        currentRoom = Dungeon.getStartingRoom();
    }

    public void getInput(Scanner input) {
        if(currentRoom == null){
            return;
        }

        currentState.getInput(input);
    }

    public DungeonRoom getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(DungeonRoom currentRoom) {
        this.currentRoom = currentRoom;
        currentState = currentRoom.getRoomState();
    }
}
