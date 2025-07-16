package dungeon.rooms;

import entity.inventory.item.Item;
import entity.inventory.item.ItemGenerator;
import entity.player.states.PlayerInLootRoom;
import entity.player.states.PlayerState;
import entity.player.states.PlayerWandering;
import graphics.Color;
import graphics.TextRenderer;
import utility.Vector2Int;

public class LootRoom extends EmptyRoom {
    private Item loot;

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
    public PlayerState getRoomState() {
        return new PlayerInLootRoom();
    }

    @Override
    public void onRoomEntered() {
        super.onRoomEntered();

        TextRenderer.printText("You also spot a chest! There could be" +
                Color.getColor("bright blue") + " loot" + Color.resetColor() + " inside.");
    }

    public Item getLoot() {
        return loot;
    }

    public void setLoot(Item loot) {
        this.loot = loot;
    }
}
