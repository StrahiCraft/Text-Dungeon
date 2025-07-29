import dungeon.DungeonMapRenderer;
import graphics.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DungeonMapRendererTest {
    @Test
    void existingRoomCode() {
        char roomCode = 'P';
        String expectedColor = Color.getColor("green");

        String actualColor = DungeonMapRenderer.getRoomColor(roomCode);

        assertEquals(expectedColor, actualColor);
    }

    @Test
    void nonExistentRoomCode() {
        char roomCode = 'G';
        String expectedColor = Color.resetColor();

        String actualColor = DungeonMapRenderer.getRoomColor(roomCode);

        assertEquals(expectedColor, actualColor);
    }

    @Test
    void nullRoomCode() {
        char roomCode = '\0';
        String expectedColor = Color.resetColor();

        String actualColor = DungeonMapRenderer.getRoomColor(roomCode);

        assertEquals(expectedColor, actualColor);
    }
}