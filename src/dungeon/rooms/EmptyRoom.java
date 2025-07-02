package dungeon.rooms;

import entity.player.states.PlayerState;
import entity.player.states.PlayerWandering;
import graphics.Color;
import graphics.TextRenderer;
import utility.Vector2Int;

public class EmptyRoom extends DungeonRoom {

    public EmptyRoom(Vector2Int position) {
        super(position);
    }

    @Override
    public PlayerState getRoomState() {
        return new PlayerWandering();
    }

    @Override
    public void onRoomEntered() {
        StringBuilder onRoomEnterText = new StringBuilder();
        onRoomEnterText.append(getDescriptionText() + "\n"
                + getFlavourText() +
                "\nYou can either check your " + Color.getColor("blue") + "map" + Color.resetColor()
                + ", or go ");

        if(getNorthRoom() != null){
            onRoomEnterText.append(Color.getColor("bright yellow") + "north " + Color.resetColor());
        }
        if(getEastRoom() != null){
            onRoomEnterText.append(Color.getColor("bright yellow") + "east " + Color.resetColor());
        }
        if(getSouthRoom() != null){
            onRoomEnterText.append(Color.getColor("bright yellow") + "south " + Color.resetColor());
        }
        if(getWestRoom() != null){
            onRoomEnterText.append(Color.getColor("bright yellow") + "west " + Color.resetColor());
        }

        TextRenderer.printText(onRoomEnterText.toString());
    }
}
