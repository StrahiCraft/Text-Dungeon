package dungeon.rooms;

import entity.player.states.PlayerState;
import entity.player.states.PlayerWandering;
import graphics.Color;
import graphics.TextRenderer;
import utility.Vector2Int;

public class EmptyRoom extends DungeonRoom {

    public EmptyRoom() {

    }
    public EmptyRoom(Vector2Int position) {
        super(position);
    }

    @Override
    public PlayerState getRoomState() {
        return new PlayerWandering();
    }

    @Override
    public void onRoomEntered() {
        String onRoomEnterText = "You find yourself in an empty room." + directionText();
        TextRenderer.printText(onRoomEnterText);
    }

    protected String directionText(){
        StringBuilder actions = new StringBuilder();

        actions.append("\nYou can either check your ").
                append(Color.getColor("blue")).
                append("map").append(Color.resetColor()).append(", or go ");

        if(getNorthRoom() != null){
            actions.append(Color.getColor("bright yellow")).append("north ").append(Color.resetColor());
        }
        if(getEastRoom() != null){
            actions.append(Color.getColor("bright yellow")).append("east ").append(Color.resetColor());
        }
        if(getSouthRoom() != null){
            actions.append(Color.getColor("bright yellow")).append("south ").append(Color.resetColor());
        }
        if(getWestRoom() != null){
            actions.append(Color.getColor("bright yellow")).append("west ").append(Color.resetColor());
        }

        return actions.toString();
    }
}
