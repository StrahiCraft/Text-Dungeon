package entity.player.states;

import dungeon.Dungeon;
import dungeon.DungeonGenerator;
import entity.player.Player;
import graphics.Color;

import java.util.Scanner;

public class PlayerInExitRoom extends PlayerWandering{
    public PlayerInExitRoom(){
        super();
        possibleCommands.add(Color.getColor("green") + "descend" + Color.resetColor());
    }

    @Override
    public boolean checkInput(String inputText, Scanner input) {
        if(super.checkInput(inputText, input)){
            return true;
        }

        if(!inputText.equals("descend")){
            return false;
        }

        Dungeon.progressFloor();
        Player.Instance.increaseScore(10);
        return true;
    }
}
