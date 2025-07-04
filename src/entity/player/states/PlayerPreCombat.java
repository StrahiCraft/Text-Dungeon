package entity.player.states;

import dungeon.DungeonMapRenderer;
import dungeon.rooms.DungeonRoom;
import entity.player.Player;

import java.util.Scanner;

public class PlayerPreCombat extends PlayerState {

    public PlayerPreCombat() {
        super();
        possibleCommands.add("fight");
        possibleCommands.add("flee");
    }

    @Override
    public boolean checkInput(String inputText, Scanner input) {
        if(inputText.equals("fight")){
            // go into battle
            return true;
        }

        if(inputText.equals("flee")){
            Player.Instance.setCurrentRoom(Player.Instance.getPreviousRoom());
            return true;
        }

        return false;
    }
}
