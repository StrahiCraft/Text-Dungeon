package dungeon.rooms;

import entity.player.states.PlayerInExitRoom;
import entity.player.states.PlayerState;
import graphics.Color;
import graphics.TextRenderer;

public class ExitRoom extends EmptyRoom {
    public ExitRoom() {
        setRoomSymbol('V');

    }

    @Override
    public PlayerState getRoomState() {
        return new PlayerInExitRoom();
    }

    @Override
    public void onRoomEntered() {
        String onRoomEnterText = "You find yourself in a room with stairs leading deeper in to the dungeon." +
                " Will you " + Color.getColor("bright red") + "descend" + Color.resetColor() + "?" + directionText();
        TextRenderer.printText(onRoomEnterText);
    }
}
