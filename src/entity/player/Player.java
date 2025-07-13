package entity.player;

import dungeon.Dungeon;
import dungeon.rooms.DungeonRoom;
import entity.Entity;
import entity.inventory.Inventory;
import entity.player.states.PlayerState;
import game.Game;
import graphics.Color;
import graphics.TextRenderer;
import utility.Stats;

import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Entity {
    public static Player Instance;

    private DungeonRoom currentRoom;
    private DungeonRoom previousRoom;
    private PlayerState currentState;

    private Inventory inventory;

    public Player(String name, Stats stats) {
        super(name, stats);
        Instance = this;
        currentRoom = Dungeon.getStartingRoom();
        inventory = new Inventory(10);
    }

    @Override
    public void handleDeath() {
        TextRenderer.printText(Color.getColor("red") + "GAME OVER" + Color.resetColor());
        Game.setGameRunning(false);
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

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
