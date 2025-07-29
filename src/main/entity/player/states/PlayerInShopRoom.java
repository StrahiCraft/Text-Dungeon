package entity.player.states;

import entity.player.Player;
import graphics.Color;

import java.util.Scanner;

public class PlayerInShopRoom extends PlayerWandering {
    public PlayerInShopRoom(){
        super();
        possibleCommands.add(Color.getColor("magenta") + "shop" + Color.resetColor());
    }

    @Override
    public boolean checkInput(String inputText, Scanner input) {
        if(super.checkInput(inputText, input)){
            return true;
        }

        if(!inputText.equals("shop")){
            return false;
        }

        Player.Instance.setCurrentState(new PlayerShopping());
        return true;
    }
}
