package entity.player.states;

import dungeon.DungeonMapRenderer;
import dungeon.rooms.DungeonRoom;
import entity.player.Player;
import utility.Vector2Int;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class PlayerWandering extends PlayerState{

    private Dictionary<String, Vector2Int> directionalDictionary =  new Hashtable<>();

    public PlayerWandering() {
        super();
        possibleCommands.add("map");

        DungeonRoom currentRoom = Player.Instance.getCurrentRoom();

        if(currentRoom.getNorthRoom() != null){
            possibleCommands.add("north");
        }
        if(currentRoom.getEastRoom() != null){
            possibleCommands.add("east");
        }
        if(currentRoom.getSouthRoom() != null){
            possibleCommands.add("south");
        }
        if(currentRoom.getWestRoom() != null){
            possibleCommands.add("west");
        }

        directionalDictionary.put("north", Vector2Int.up());
        directionalDictionary.put("east", Vector2Int.right());
        directionalDictionary.put("south", Vector2Int.down());
        directionalDictionary.put("west", Vector2Int.left());
    }

    @Override
    public void checkInput(String inputText, Scanner input) {
        if(inputText.equals("map")){
            DungeonMapRenderer.renderDungeonMap();
            return;
        }

        if(inputText.equals("north") ||
                inputText.equals("east") ||
                inputText.equals("south") ||
                inputText.equals("west")){
            goToRoom(inputText, input);
        }
    }

    private void goToRoom(String direction, Scanner input) {
        if(!possibleCommands.contains(direction)){
            System.out.println("This room doesn't have a " + direction + "ern door, try another direction.\n");
            getInput(input);
            return;
        }

        Player.Instance.setCurrentRoom(Player.Instance.getCurrentRoom().getRoom(directionalDictionary.get(direction)));
    }
}
