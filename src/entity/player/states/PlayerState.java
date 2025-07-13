package entity.player.states;

import game.Game;
import graphics.Color;
import graphics.TextRenderer;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class PlayerState {
    protected ArrayList<String> possibleCommands = new ArrayList<>();

    public PlayerState() {
        possibleCommands.add(Color.getColor("green") + "help" + Color.resetColor());
        possibleCommands.add(Color.getColor("red") + "quit" + Color.resetColor());
    }

    public void getInput(Scanner input) {
        TextRenderer.printText("What will you do?");
        String inputText = input.nextLine();

        if(checkInput(inputText.toLowerCase(), input)){
            return;
        }
        defaultInputCheck(inputText.toLowerCase(), input);
    }

    /**
     * Checks if the input the player has given makes sense for the context they are in.
     * @param inputText
     * The inputted text that will be checked.
     * @param input
     * Scanner that is used to read the input
     * @return
     * False if the inputText doesn't correspond to anything, True if it does.
     */
    public abstract boolean checkInput(String inputText, Scanner input);

    private void defaultInputCheck(String inputText, Scanner input) {
        if(inputText.equals("help")){
            getHelp(input);
            return;
        }

        if(inputText.equals("quit")){
            Game.setGameRunning(false);
            return;
        }

        onRandomStuffInputted();
    }

    private void getHelp(Scanner input) {
        StringBuilder helpText = new StringBuilder(Color.getColor("bright yellow") + "List of current possible commands:\n" + Color.resetColor());
        for (String command : possibleCommands) {
            helpText.append(command).append("\n");
        }
        TextRenderer.printText(helpText.toString());
        getInput(input);
    }

    private void onRandomStuffInputted(){
        TextRenderer.printText("Seems like you need help, type " +
                Color.getColor("green") + "help" + Color.resetColor() +
                " to get some.");
    }
}
