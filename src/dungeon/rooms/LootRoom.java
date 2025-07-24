package dungeon.rooms;

import inventory.item.Item;
import inventory.item.ItemGenerator;
import entity.player.Player;
import entity.player.states.PlayerInLootRoom;
import entity.player.states.PlayerState;
import graphics.Color;
import graphics.TextRenderer;
import utility.Vector2Int;

public class LootRoom extends EmptyRoom {
    private Item loot;

    public LootRoom() {
        loot = ItemGenerator.generateItemInstance();

        setRoomSymbol('L');
    }

    public LootRoom(Vector2Int position) {
        super(position);
        loot = ItemGenerator.generateItemInstance();
        setRoomSymbol('L');
    }

    public LootRoom(Vector2Int position, Item loot) {
        super(position);
        this.loot = loot;
        setRoomSymbol('L');
    }

    @Override
    public DungeonRoom copy() {
        return new LootRoom();
    }

    @Override
    public PlayerState getRoomState() {
        return new PlayerInLootRoom();
    }

    @Override
    public void onRoomEntered() {
        TextRenderer.printText("You find yourself in a room with a chest! There could be" +
                Color.getColor("bright blue") + " loot" + Color.resetColor() + " inside." + directionText());
        Player.Instance.increaseScore(2);
    }

    public Item getLoot() {
        return loot;
    }

    public void setLoot(Item loot) {
        this.loot = loot;
    }
}
