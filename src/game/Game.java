package game;

import dungeon.DungeonGenerator;
import entity.enemy.EnemyGenerator;
import entity.inventory.item.ItemGenerator;
import entity.player.Player;
import utility.Stats;

import java.util.Scanner;

public class Game {
    private static boolean gameRunning = true;

    public static void startGame(){
        EnemyGenerator.generateEnemiesFromFiles();
        ItemGenerator.generateItemsFromFiles();

        // TODO add main menu

        gameLoop();
    }

    private static void gameLoop(){
        Player player = new Player("Player",
                new Stats(10, 0, 1, 2.5f));

        DungeonGenerator.generateDungeon();

        Scanner input = new Scanner(System.in);

        while(gameRunning){
            player.getInput(input);
        }

        input.close();
    }

    public static void quitGame(){
        setGameRunning(false);
    }

    public static void setGameRunning(boolean gameRunning) {
        Game.gameRunning = gameRunning;
    }
}
