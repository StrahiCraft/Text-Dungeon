package game;

import dungeon.DungeonGenerator;
import entity.enemy.EnemyGenerator;
import entity.inventory.item.ItemGenerator;
import entity.player.Player;
import graphics.Color;
import graphics.TextRenderer;
import utility.Stats;
import utility.file.FileReader;

import java.util.Scanner;

public class Game {
    private static boolean gameRunning = true;

    public static void startGame(){
        Scanner input = new Scanner(System.in);
        EnemyGenerator.generateEnemiesFromFiles();
        ItemGenerator.generateItemsFromFiles();

        mainMenu(input);
        input.close();
    }

    public static void mainMenu(Scanner input) {
        boolean enteredGame = false;



        while (!enteredGame) {
            renderMainMenu();
            String command = input.nextLine();

            if(command.equalsIgnoreCase("quit")) {
                TextRenderer.printText("Goodbye!");
                break;
            }

            if(command.equalsIgnoreCase("start")) {
                gameLoop(input);
            }
        }

    }

    private static void renderMainMenu() {
        TextRenderer.skipLine();

        TextRenderer.printText("Welcome to the " + Color.getColor("green") + "TEXT DUNGEON" + Color.resetColor() + "!"
        + "\nType in " + Color.getColor("green") + "start" + Color.resetColor() + " to start the game." +
                "\nType in " + Color.getColor("red") + "quit" + Color.resetColor() + " to quit game");
    }

    private static void gameLoop(Scanner input){
        Player player = new Player();
        Stats playerStats = new Stats();
        playerStats.interpretFileData(FileReader.readFile("assets/config/playerStats.txt"));
        player.setStats(playerStats);

        TextRenderer.printText("Enter your name " + Color.getColor("blue") + "PLAYER" + Color.resetColor() + ": ");
        player.setName(input.nextLine());

        TextRenderer.skipLine();
        TextRenderer.printText("Entering " + Color.getColor("red") + "DUNGEON" +  Color.resetColor() + "...");
        TextRenderer.skipLine();

        DungeonGenerator.generateDungeon();

        while(gameRunning){
            player.getInput(input);
        }
    }

    public static void quitGame(){
        setGameRunning(false);
    }

    public static void setGameRunning(boolean gameRunning) {
        Game.gameRunning = gameRunning;
    }
}
