package entity.player.states;

import entity.player.Player;
import graphics.Color;
import graphics.TextRenderer;

import java.util.Scanner;

public class PlayerInInventory extends PlayerState {
    public PlayerInInventory() {
        super();
        possibleCommands.add(Color.getColor("yellow") + "drop" + Color.resetColor() +
                " the index of the item.");
        possibleCommands.add(Color.getColor("green") + "use" + Color.resetColor() +
                " the index of the item.");
        possibleCommands.add(Color.getColor("red") + "close" + Color.resetColor());

        printInventory();
    }

    @Override
    public boolean checkInput(String inputText, Scanner input) {
        String[] splitText = inputText.split(" ");

        switch (splitText[0]){
            case "close" -> {
                Player.Instance.setCurrentState(Player.Instance.getPreviousState());
                TextRenderer.printText("Inventory " + Color.getColor("red") + "closed" + Color.resetColor());
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
            case "unequip" -> {
                // TODO add unequipping
                return true;
            }
        }

        return false;
    }

    private void useItem(String[] instructions) {
        if(instructions.length != 2){
            TextRenderer.printText("To use an item type in "
                    + Color.getColor("green") + "use " + Color.resetColor() +
                    "followed by the item index (number next to the item without the '.')");
            printInventory();
            return;
        }

        Player.Instance.getInventory().useItem(Integer.parseInt(instructions[1]));
        printInventory();
    }

    private void dropItem(String[] instructions) {
        if(instructions.length != 2){
            TextRenderer.printText("To drop an item type in "
                    + Color.getColor("yellow") + "drop " + Color.resetColor() +
                    "followed by the item index (number next to the item without the '.')");
            printInventory();
            return;
        }

        Player.Instance.getInventory().removeItem(Integer.parseInt(instructions[1]));
        printInventory();
    }

    private void printInventory() {
        TextRenderer.skipLine();
        TextRenderer.printText("You can either " + Color.getColor("green") + "use " + Color.resetColor()
        + "or " + Color.getColor("yellow") + "drop " + Color.resetColor() + "an item, " + Color.getColor("gray") +
                "unequip " + Color.resetColor() + "an equip item, or " + Color.getColor("red") + "close " + Color.resetColor()
        + "the inventory.");
        TextRenderer.printText(Player.Instance.getInventory().toString());
    }
}
