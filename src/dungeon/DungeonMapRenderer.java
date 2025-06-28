package dungeon;

import dungeon.rooms.DungeonRoom;
import dungeon.utility.DungeonBounds;
import utility.Vector2Int;

import java.util.Arrays;
import java.util.Collections;

public class DungeonMapRenderer {
    public static void renderDungeonMap(){
        DungeonBounds dungeonBounds = Dungeon.getDungeonBounds();

        char[][] dungeonMap = new char[(dungeonBounds.getMaxDungeonCoordinate().getY() -
                dungeonBounds.getMinDungeonCoordinate().getY() + 1) * 3]
                [(dungeonBounds.getMaxDungeonCoordinate().getX() -
                dungeonBounds.getMinDungeonCoordinate().getX() + 1) * 3];

        for(int i = 0; i < dungeonMap.length; i++){
            Arrays.fill(dungeonMap[i], ' ');
        }

        for(DungeonRoom dungeonRoom : Collections.list(Dungeon.getDungeonRooms().elements())){
            dungeonMap[(dungeonRoom.getPosition().getY() - dungeonBounds.getMinDungeonCoordinate().getY()) * 3 + 1]
                    [(dungeonRoom.getPosition().getX() - dungeonBounds.getMinDungeonCoordinate().getX()) * 3 + 1] = '#';

            if(dungeonRoom.getPosition().getX() == 0 && dungeonRoom.getPosition().getY() == 0){
                dungeonMap[(dungeonRoom.getPosition().getY() - dungeonBounds.getMinDungeonCoordinate().getY()) * 3 + 1]
                        [(dungeonRoom.getPosition().getX() - dungeonBounds.getMinDungeonCoordinate().getX()) * 3 + 1] = 'S';
            }

            renderHallways(dungeonRoom, Vector2Int.up(), dungeonMap, dungeonBounds);
            renderHallways(dungeonRoom, Vector2Int.down(), dungeonMap, dungeonBounds);
            renderHallways(dungeonRoom, Vector2Int.left(), dungeonMap, dungeonBounds);
            renderHallways(dungeonRoom, Vector2Int.right(), dungeonMap, dungeonBounds);
        }

        for(int x = dungeonMap.length - 1; x > 0; x--){
            for(int y = 0; y < dungeonMap[x].length; y++){
                System.out.printf("%c", dungeonMap[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void renderHallways(DungeonRoom currentRoom, Vector2Int direction, char[][] dungeonMap, DungeonBounds dungeonBounds) {
        if(currentRoom.getRoom(direction) == null){
            return;
        }

        char hallwayCharacter = direction.getX() == 0? '|' : '-';

        dungeonMap[(currentRoom.getPosition().getY() - dungeonBounds.getMinDungeonCoordinate().getY()) * 3 + 1 + direction.getY()]
                [(currentRoom.getPosition().getX() - dungeonBounds.getMinDungeonCoordinate().getX()) * 3 + 1 + direction.getX()] = hallwayCharacter;
    }
}
