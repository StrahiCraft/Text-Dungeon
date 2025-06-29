package dungeon.rooms;

import entity.player.states.PlayerState;
import entity.player.states.PlayerWandering;
import utility.Vector2Int;

public class EmptyRoom extends DungeonRoom {

    public EmptyRoom(Vector2Int position) {
        super(position);
    }

    @Override
    public PlayerState getRoomState() {
        return new PlayerWandering();
    }
}
