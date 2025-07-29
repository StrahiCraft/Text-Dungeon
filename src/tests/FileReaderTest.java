import org.junit.jupiter.api.Test;
import utility.file.FileReader;

import static org.junit.jupiter.api.Assertions.*;
class FileReaderTest {
    @Test
    void formatedStringTest() {
        String input = "damage=11";
        String expectedOutput = "11";

        String actualOutput = FileReader.unFormatString(input);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void unformattedStringTest(){
        String input = "11";
        String expectedOutput = "11";

        String actualOutput = FileReader.unFormatString(input);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void nullStringTest(){
        String input = null;

        assertThrowsExactly(NullPointerException.class, () -> FileReader.unFormatString(input));
    }
}