import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.p0_arcade.repo.DAO.GameRepository;
import com.p0_arcade.repo.entities.GameEntity;
import com.p0_arcade.repo.entities.WagerEntity;
import com.p0_arcade.service.GameService;
import com.p0_arcade.service.models.Game;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class GameServiceTest {
    
    @Mock
    private GameRepository gameRepository;

    @InjectMocks
    private GameService gameService;

    private GameEntity testGameEntity;
    private Game testGameModel;
    private WagerEntity testWagerEntity;

    @BeforeEach
    void setup(){
        // Create GameService with mocked repository
        gameService = new GameService(gameRepository);
        
        testGameEntity = new GameEntity();
        testGameEntity.setId(1);
        testGameEntity.setName("testName");
        testGameEntity.setDescription("testDesc");
        testGameEntity.setMinWager(10);
        testGameEntity.setMultiplier(2.0);

        testGameModel = new Game();
        testGameModel.setId(1);
        testGameModel.setName("testName");
        testGameModel.setDescription("testDesc");
        testGameModel.setMinWager(10);
        testGameModel.setMultiplier(2.0);

        testWagerEntity = new WagerEntity();
        testWagerEntity.setId(1);
        testWagerEntity.setPoints(50);
    }

    /*
    public int playCoinFlip(Game game, WagerEntity wager, char guess){

        if (guess != 'H' && guess != 'T') {
            throw new IllegalArgumentException("Invalid Input: Guess must be 'H' or 'T'");
        }

        double multiplier = game.getMultiplier();
        boolean coin = Math.random() < 0.5; // Heads is true, Tails is false

        System.out.println("It landed " + (coin ? "heads!" : "tails!"));

        boolean win = (guess == 'H' && coin) || (guess == 'T' && !coin);

        if (win){
            System.out.println("You win!");
            return (int) (wager.getPoints() * multiplier);
        } else {
            System.out.println("You lose!");
            return 0;
        }
    }
    */

    @Test
    void testPlayCoinFlip_ValidGuess_ReturnsValidResult(){
        //Arrange
        int wagerPoints = 100;
        double multi = 2.0;
        int expectedWin = 200;

        testWagerEntity.setPoints(wagerPoints);
        testGameModel.setMultiplier(multi);        

        //Act
        int result = gameService.playCoinFlip(testGameModel, testWagerEntity, 'H');

        //Assert
        assertTrue(result == 0 || result == expectedWin, 
            "Result should be either 0 (lose) or " + expectedWin + " (win), but got: " + result);
    }

    @Test
    void testPlayRPS_ValidGuess_ReturnsValidResult(){
        //Arrange
        int wagerPoints = 100;
        double multi = 1.5;
        int expectedWin = 150;

        testWagerEntity.setPoints(wagerPoints);
        testGameModel.setMultiplier(multi);        

        //Act
        int result = gameService.playRPS(testGameModel, testWagerEntity, 'R');

        //Assert
        assertTrue(result == 0 || result == expectedWin, 
            "Result should be either 0 (lose) or " + expectedWin + " (win), but got: " + result);
    }





}
