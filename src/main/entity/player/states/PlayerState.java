package entity.player.states;

import entity.player.Player;
import game.Game;
import graphics.Color;
import graphics.TextRenderer;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class PlayerState {
    protected ArrayList<String> possibleCommands = new ArrayList<>();
    private String inputQuestion;

    public PlayerState() {
        possibleCommands.add(Color.getColor("green") + "help" + Color.resetColor());
        possibleCommands.add("check");
        possibleCommands.add(Color.getColor("red") + "quit" + Color.resetColor());
        inputQuestion = "What will you do?";
    }

    public void getInput(Scanner input) {
        TextRenderer.printText(inputQuestion);
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
        switch (inputText) {
            case "help" -> getHelp(input);
            case "check" -> TextRenderer.printText(Color.getColor("yellow") + "Gold: " +
                    Color.resetColor() + Player.Instance.getGold() + "\n" +
                    Player.Instance.getStats() + "\n" +
                    Player.Instance.getEquipment());
            case "quit" -> Game.quitGame();
            default -> onRandomStuffInputted();
        }
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

    public ArrayList<String> getPossibleCommands() {
        return possibleCommands;
    }

    public void setPossibleCommands(ArrayList<String> possibleCommands) {
        this.possibleCommands = possibleCommands;
    }

    public String getInputQuestion() {
        return inputQuestion;
    }

    public void setInputQuestion(String inputQuestion) {
        this.inputQuestion = inputQuestion;
    }
}
