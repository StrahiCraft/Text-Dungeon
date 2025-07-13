package entity.player.states;

import entity.inventory.item.Rarity;
import entity.player.Player;
import graphics.Color;
import graphics.TextRenderer;

import java.util.Scanner;

public class PlayerInInventory extends PlayerState {
    public PlayerInInventory() {
        super();
        possibleCommands.add(Color.getColor("yellow") + "drop" + Color.resetColor() +
                " either the index of the item or the name or name + the rarity of the item.");
        possibleCommands.add(Color.getColor("green") + "use" + Color.resetColor() +
                " either the index of the item or the name or name + the rarity of the item.");
        possibleCommands.add(Color.getColor("red") + "close" + Color.resetColor());

        printInventory();
    }

    @Override
    public boolean checkInput(String inputText, Scanner input) {
        String[] splitText = inputText.split(" ");

        switch (splitText[0]){
            case "close" -> {
                Player.Instance.setCurrentState(new PlayerWandering());
                return true;
            }
            case "use" -> {
                useItem(splitText);
                return true;
            }
            case "drop" -> {
                dropItem(splitText);
                return true;
            }
        }

        return false;
    }

    private void useItem(String[] instructions) {
        try{
            int itemIndex = Integer.parseInt(instructions[1]);

            Player.Instance.getInventory().useItem(itemIndex);
            return;
        } catch (NumberFormatException ignored){}

        try {
            Player.Instance.getInventory().
                    useItem(instructions[1], Rarity.valueOf(instructions[2].toUpperCase()));
            return;
        } catch (IllegalArgumentException ignored){}

        if(Player.Instance.getInventory().useItem(instructions[1])){
            printInventory();
            return;
        }

        TextRenderer.printText(Color.getColor("red" + "Something went wrong, try again." + Color.resetColor()));
    }

    private void dropItem(String[] instructions) {
        // TODO - add the ability for dropped items to appear in the current room
        try{
            int itemIndex = Integer.parseInt(instructions[1]);

            Player.Instance.getInventory().removeItem(itemIndex);
            return;
        } catch (NumberFormatException ignored){}

        try {
            Player.Instance.getInventory().
                    removeItem(instructions[1], Rarity.valueOf(instructions[2].toUpperCase()));
            return;
        } catch (IllegalArgumentException ignored){}

        if(Player.Instance.getInventory().removeItem(instructions[1])){
            printInventory();
            return;
        }
        TextRenderer.printText(Color.getColor("red" + "Something went wrong, try again." + Color.resetColor()));
    }

    private void printInventory(){
        TextRenderer.printText(Player.Instance.getInventory().toString());
    }
}
