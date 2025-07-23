package entity.player;

import combat.CombatManager;
import dungeon.Dungeon;
import dungeon.rooms.DungeonRoom;
import entity.Entity;
import entity.inventory.Inventory;
import entity.inventory.item.equipment.Equipment;
import entity.player.states.PlayerState;
import game.Game;
import graphics.Color;
import graphics.TextRenderer;
import utility.Stats;

import java.util.*;

public class Player extends Entity {
    public static Player Instance;

    private DungeonRoom currentRoom;
    private DungeonRoom previousRoom;
    private PlayerState currentState;
    private PlayerState previousState;

    private Inventory inventory;
    private Equipment equipment;

    private int gold = 0;

    public Player() {
        super();
        Instance = this;
        currentRoom = Dungeon.getStartingRoom();
        inventory = new Inventory(10);

        equipment = new Equipment();
    }

    public Player(String name, Stats stats) {
        super(name, stats);
        Instance = this;
        currentRoom = Dungeon.getStartingRoom();
        inventory = new Inventory(10);

        equipment = new Equipment();
    }

    @Override
    public void handleDeath() {
        CombatManager.setInCombat(false);
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

    public void addGold(int amount){
        gold += amount;

        if(gold < 0){
            gold = 0;
        }
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
        previousState = this.currentState;
        this.currentState = currentState;
    }

    public PlayerState getPreviousState() {
        return previousState;
    }

    public void setPreviousState(PlayerState previousState) {
        this.previousState = previousState;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
