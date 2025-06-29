package entity.player.states;

import game.Game;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class PlayerState {
    protected ArrayList<String> possibleCommands = new ArrayList<>();

    public PlayerState() {
        possibleCommands.add("help");
        possibleCommands.add("quit");
    }

    public void getInput(Scanner input) {
        System.out.println("Type what you want to do: ");
        String inputText = input.nextLine();

        checkInput(inputText.toLowerCase(), input);
        defaultInputCheck(inputText.toLowerCase(), input);
    }

    public abstract void checkInput(String inputText, Scanner input);

    private void defaultInputCheck(String inputText, Scanner input) {
        if(inputText.equals("help")){
            getHelp(input);
            return;
        }

        if(inputText.equals("quit")){
            Game.setGameRunning(false);
            return;
        }

        onRandomStuffInputed();
    }

    private void getHelp(Scanner input) {
        System.out.println("List of current possible commands:");
        for (String command : possibleCommands) {
            System.out.println(command);
        }
        System.out.println();
        getInput(input);
    }

    private void onRandomStuffInputed(){
        System.out.println("Seems like you need help, type 'help' to get some.\n");
    }
}
