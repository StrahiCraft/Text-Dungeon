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
    private DungeonRoom previousRoom;
    private PlayerState currentState;

    public Player(String name, Stats stats) {
        super(name, stats);
        Instance = this;
        currentRoom = Dungeon.getStartingRoom();
    }

    @Override
    public void handleDeath() {

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
        previousRoom = this.currentRoom;
        this.currentRoom = currentRoom;
        currentState = currentRoom.getRoomState();
        currentRoom.setExplored(true);

        currentRoom.onRoomEntered();
    }

    public DungeonRoom getPreviousRoom() {
        return previousRoom;
    }

    public void setPreviousRoom(DungeonRoom previousRoom) {
        this.previousRoom = previousRoom;
    }

    public PlayerState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(PlayerState currentState) {
        this.currentState = currentState;
    }
}
