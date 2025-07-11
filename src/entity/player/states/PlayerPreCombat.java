package entity.player.states;

import dungeon.Dungeon;
import dungeon.rooms.EmptyRoom;
import dungeon.rooms.EnemyRoom;
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
            //Player.Instance.setCurrentState(new PlayerInCombat());
            Dungeon.setRoom(new EmptyRoom(Player.Instance.getCurrentRoom().getPosition()),
                    Player.Instance.getCurrentRoom().getPosition());
            return true;
        }

        if(inputText.equals("flee")){
            Player.Instance.setCurrentRoom(Player.Instance.getPreviousRoom());
            return true;
        }

        return false;
    }
}
