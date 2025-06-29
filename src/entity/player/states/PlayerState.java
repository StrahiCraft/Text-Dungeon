package entity.player.states;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class PlayerState {
    protected ArrayList<String> possibleCommands = new ArrayList<>();

    public PlayerState() {
        possibleCommands.add("help");
    }

    public void getInput(Scanner input) {
        System.out.println("Type what you want to do: ");
        String inputText = input.nextLine();
        checkInput(inputText.toLowerCase(), input);
    }

    public abstract void checkInput(String inputText, Scanner input);

    protected void getHelp(Scanner input) {
        System.out.println("List of current possible commands:");
        for (String command : possibleCommands) {
            System.out.println(command);
        }
        System.out.println();
        getInput(input);
    }

    protected void onRandomStuffInputed(){
        System.out.println("Seems like you need help, type 'help' to get some.\n");
    }
}
