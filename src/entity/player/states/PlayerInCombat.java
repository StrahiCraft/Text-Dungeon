package entity.player.states;

import dungeon.rooms.EnemyRoom;
import entity.enemy.Enemy;
import entity.player.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayerInCombat extends PlayerState {
    private ArrayList<Enemy>  enemies;

    public PlayerInCombat() {
        super();

        possibleCommands.add("check");

        enemies = ((EnemyRoom)Player.Instance.getCurrentRoom()).getEnemies();

        printCombatInfo();
    }

    @Override
    public boolean checkInput(String inputText, Scanner input) {
        return false;
    }

    private void printCombatInfo(){

    }
}
