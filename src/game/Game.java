package game;

import dungeon.DungeonGenerator;
import entity.enemy.EnemyGenerator;
import entity.player.Player;
import utility.Stats;

import java.util.Scanner;

public class Game {
    private static boolean gameRunning = true;

    public static void gameLoop(){
        EnemyGenerator.generateEnemiesFromFiles();

        Player player = new Player("Player",
                new Stats(10, 0, 1, 2.5f));

        DungeonGenerator.generateDungeon();

        Scanner input = new Scanner(System.in);

        while(gameRunning){
            player.getInput(input);
        }

        input.close();
    }

    public static void setGameRunning(boolean gameRunning) {
        Game.gameRunning = gameRunning;
    }
}
