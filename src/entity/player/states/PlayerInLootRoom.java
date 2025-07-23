package entity.player.states;

import dungeon.Dungeon;
import dungeon.rooms.EmptyRoom;
import dungeon.rooms.LootRoom;
import entity.player.Player;
import graphics.Color;
import graphics.TextRenderer;

import java.util.Scanner;

public class PlayerInLootRoom extends PlayerWandering{
    public PlayerInLootRoom() {
        super();
        possibleCommands.add(Color.getColor("bright blue") + "loot" + Color.resetColor());
    }

    @Override
    public boolean checkInput(String inputText, Scanner input) {
        if(super.checkInput(inputText, input)){
            return true;
        }

        if(!inputText.equals("loot")){
            return false;
        }

        if(Player.Instance.getInventory().isFull()){
            TextRenderer.printText("Your inventory is " + Color.getColor("red") + "full" + Color.resetColor()
            + ". Drop something to " + Color.getColor("bright blue") + "loot " + Color.resetColor() +
                    "the chest.");
            return true;
        }

        TextRenderer.printText("Picked up " + ((LootRoom)Player.Instance.getCurrentRoom()).getLoot());

        Player.Instance.getInventory().addItem(((LootRoom)Player.Instance.getCurrentRoom()).getLoot());
        Player.Instance.increaseScore(1);

        Dungeon.setRoom(new EmptyRoom(Player.Instance.getCurrentRoom().getPosition()),
                Player.Instance.getCurrentRoom().getPosition());


        return true;
    }
}
