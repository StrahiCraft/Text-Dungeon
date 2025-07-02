package dungeon;

import dungeon.rooms.DungeonRoom;
import dungeon.utility.DungeonBounds;
import entity.player.Player;
import graphics.Color;
import utility.Vector2Int;

import java.util.Arrays;
import java.util.Collections;

public class DungeonMapRenderer {

    public static String renderDungeonMap(){
        char[][] dungeonMap = fillDungeonMap();
        StringBuilder mapString = new StringBuilder();

        for(int x = dungeonMap.length - 1; x > 0; x--){
            for(int y = 0; y < dungeonMap[x].length; y++){
                if(dungeonMap[x][y] == 'P'){
                    mapString.append(Color.getColor("green"));
                }
                mapString.append(dungeonMap[x][y] + "\u001B[0m");
            }
            mapString.append('\n');
        }
        return mapString.toString();
    }

    private static char[][] fillDungeonMap(){
        DungeonBounds dungeonBounds = Dungeon.getDungeonBounds();

        char[][]dungeonMap = new char[(dungeonBounds.getMaxDungeonCoordinate().getY() -
                dungeonBounds.getMinDungeonCoordinate().getY() + 1) * 3]
                [(dungeonBounds.getMaxDungeonCoordinate().getX() -
                dungeonBounds.getMinDungeonCoordinate().getX() + 1) * 3];

        for(int i = 0; i < dungeonMap.length; i++){
            Arrays.fill(dungeonMap[i], ' ');
        }

        for(DungeonRoom currentRoom : Collections.list(Dungeon.getDungeonRooms().elements())){
            fillDungeonRoom(currentRoom, dungeonMap, dungeonBounds);
        }

        return dungeonMap;
    }

    private static void fillDungeonRoom(DungeonRoom currentRoom, char[][] dungeonMap, DungeonBounds dungeonBounds){
        if(!currentRoom.getExplored()){
            return;
        }

        char roomSymbol = currentRoom.getPosition().equalValue(Player.Instance.getCurrentRoom().getPosition())? 'P' : '#';

        dungeonMap[(currentRoom.getPosition().getY() - dungeonBounds.getMinDungeonCoordinate().getY()) * 3 + 1]
                [(currentRoom.getPosition().getX() - dungeonBounds.getMinDungeonCoordinate().getX()) * 3 + 1] = roomSymbol;

        fillHallways(currentRoom, Vector2Int.up(), dungeonMap, dungeonBounds);
        fillHallways(currentRoom, Vector2Int.down(), dungeonMap, dungeonBounds);
        fillHallways(currentRoom, Vector2Int.left(), dungeonMap, dungeonBounds);
        fillHallways(currentRoom, Vector2Int.right(), dungeonMap, dungeonBounds);
    }

    private static void fillHallways(DungeonRoom currentRoom, Vector2Int direction, char[][] dungeonMap, DungeonBounds dungeonBounds) {
        if(currentRoom.getRoom(direction) == null){
            return;
        }

        char hallwayCharacter = direction.getX() == 0? '|' : '-';

        dungeonMap[(currentRoom.getPosition().getY() - dungeonBounds.getMinDungeonCoordinate().getY()) * 3 + 1 + direction.getY()]
                [(currentRoom.getPosition().getX() - dungeonBounds.getMinDungeonCoordinate().getX()) * 3 + 1 + direction.getX()] = hallwayCharacter;
    }
}
