package game;

import dungeon.Dungeon;
import dungeon.DungeonGenerator;
import entity.enemy.EnemyGenerator;
import inventory.item.ItemGenerator;
import entity.player.Player;
import graphics.Color;
import graphics.TextRenderer;
import utility.Stats;
import utility.file.FileReader;

import java.util.Scanner;

public class Game {
    private static boolean gameRunning = true;
    private static Player champion = new Player();

    public static void startGame(){
        Scanner input = new Scanner(System.in);
        EnemyGenerator.generateEnemiesFromFiles();
        ItemGenerator.generateItemsFromFiles();
        updateChampion();

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
                gameRunning = true;
                gameLoop(input);
            }
        }
    }

    public static void updateChampion() {
        champion.interpretFileData(FileReader.readFile("assets/champion.txt"));
    }

    private static void renderMainMenu() {
        TextRenderer.skipLine();

        TextRenderer.printText("Welcome to the " + Color.getColor("green") + "TEXT DUNGEON" + Color.resetColor() + "!");

        if(champion.getCurrentScore() > 0){
            TextRenderer.skipLine();
            TextRenderer.printText("The current champion is " + Color.getColor("red") + champion.getName() + Color.resetColor() +
                    " with a" + Color.getColor("green") + " score " + Color.resetColor() + "of " + champion.getCurrentScore() + ".");
        }

        TextRenderer.printText("\nType in " + Color.getColor("green") + "start" + Color.resetColor() + " to start the game." +
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

        Dungeon.resetDungeon();
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

    public static boolean isGameRunning() {
        return gameRunning;
    }

    public static Player getChampion() {
        return champion;
    }

    public static void setChampion(Player champion) {
        Game.champion = champion;
    }
}
