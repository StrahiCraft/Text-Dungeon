package dungeon.rooms;

import entity.player.states.PlayerState;
import utility.Vector2Int;

public abstract class DungeonRoom {
    private Vector2Int position;

    private String descriptionText = "You find yourself in a room.";
    private String flavourText = "";
    private char roomSymbol = '#';

    private boolean explored;

    private DungeonRoom northRoom;
    private DungeonRoom eastRoom;
    private DungeonRoom southRoom;
    private DungeonRoom westRoom;

    public DungeonRoom(Vector2Int position) {
        this.position = position;
    }

    public abstract PlayerState getRoomState();
    public abstract void onRoomEntered();

    public void setRoom(DungeonRoom room, Vector2Int direction) {
        if(direction == null) {
            System.out.println("ERROR! Direction is null.");
            return;
        }

        if(direction.equalValue(Vector2Int.up())){
            setNorthRoom(room);
            return;
        }

        if(direction.equalValue(Vector2Int.down())){
            setSouthRoom(room);
            return;
        }

        if(direction.equalValue(Vector2Int.left())){
            setWestRoom(room);
            return;
        }

        if(direction.equalValue(Vector2Int.right())){
            setEastRoom(room);
            return;
        }

        System.out.println("ERROR! Invalid direction.");
    }

    public DungeonRoom getRoom(Vector2Int direction) {
        if(direction == null) {
            System.out.println("ERROR! Direction is null.");
            return this;
        }

        if(direction.equalValue(Vector2Int.up())){
            return getNorthRoom();
        }

        if(direction.equalValue(Vector2Int.down())){
            return getSouthRoom();
        }

        if(direction.equalValue(Vector2Int.left())){
            return getWestRoom();
        }

        if(direction.equalValue(Vector2Int.right())){
            return getEastRoom();
        }

        System.out.println("ERROR! Invalid direction.");
        return this;
    }

    public Vector2Int getPosition() {
        return position;
    }

    public void setPosition(Vector2Int position) {
        this.position = position;
    }

    public String getFlavourText() {
        return flavourText;
    }

    public void setFlavourText(String flavourText) {
        this.flavourText = flavourText;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public DungeonRoom getNorthRoom() {
        return northRoom;
    }

    public void setNorthRoom(DungeonRoom northRoom) {
        this.northRoom = northRoom;
    }

    public DungeonRoom getEastRoom() {
        return eastRoom;
    }

    public void setEastRoom(DungeonRoom eastRoom) {
        this.eastRoom = eastRoom;
    }

    public DungeonRoom getSouthRoom() {
        return southRoom;
    }

    public void setSouthRoom(DungeonRoom southRoom) {
        this.southRoom = southRoom;
    }

    public DungeonRoom getWestRoom() {
        return westRoom;
    }

    public void setWestRoom(DungeonRoom westRoom) {
        this.westRoom = westRoom;
    }

    public boolean getExplored() {
        return explored;
    }

    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    public char getRoomSymbol() {
        return roomSymbol;
    }

    public void setRoomSymbol(char roomSymbol) {
        this.roomSymbol = roomSymbol;
    }
}
