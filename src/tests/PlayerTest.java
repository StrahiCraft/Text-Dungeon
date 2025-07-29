import entity.player.Player;
import exceptions.IllegalScoreIncreaseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;

    @BeforeEach
    void initializePlayer(){
        player = new Player();
    }

    @Test
    void positiveScoreIncreaseTest(){
        int scoreIncrease = 5;
        int scoreBeforeIncrease = Player.Instance.getCurrentScore();
        int expectedScoreAfterIncrease = scoreIncrease + scoreBeforeIncrease;

        int actualScoreAfterIncrease = Player.Instance.increaseScore(scoreIncrease);

        assertEquals(expectedScoreAfterIncrease, actualScoreAfterIncrease);
    }

    @Test
    void noScoreIncreaseTest(){
        int scoreBeforeIncrease = Player.Instance.getCurrentScore();

        int scoreAfterIncrease = Player.Instance.increaseScore(0);

        assertEquals(scoreBeforeIncrease, scoreAfterIncrease);
    }

    @Test
    void negativeScoreIncreaseTest(){
        int scoreIncrease = -5;

        assertThrowsExactly(IllegalScoreIncreaseException.class, () -> Player.Instance.increaseScore(scoreIncrease));
    }
}