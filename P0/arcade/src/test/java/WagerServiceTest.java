import java.sql.SQLException;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


import com.p0_arcade.repo.DAO.WagerRepository;
import com.p0_arcade.repo.entities.WagerEntity;
import com.p0_arcade.service.GameService;
import com.p0_arcade.service.PlayerService;
import com.p0_arcade.service.WagerService;
import com.p0_arcade.service.models.Wager;
import com.p0_arcade.service.models.Player;
import com.p0_arcade.service.models.Game;



@ExtendWith(MockitoExtension.class)
public class WagerServiceTest {
    @Mock
    private WagerRepository wagerRepo;

    @Mock
    private GameService gameService;

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private WagerService wagerService;


    private WagerEntity testWagerEntity;
    private Wager testWagerModel;
    private Player testPlayerModel;
    private Game testGameModel;

    @BeforeEach
    void setup() {
        testWagerEntity = new WagerEntity();
        testWagerEntity.setId(1);
        testWagerEntity.setPoints(100);
        testWagerEntity.setGameId(1);
        testWagerEntity.setPlayerId(1);


        //Test Models
        testWagerModel = new Wager();
        testWagerModel.setId(1);
        testWagerModel.setPoints(100);
        testWagerModel.setGame(testGameModel);
        testWagerModel.setPlayer(testPlayerModel);

        testPlayerModel = new Player();
        testPlayerModel.setId(1);
        testPlayerModel.setName("TestPlayer");
        testPlayerModel.setPoints(1000);

        testGameModel = new Game();
        testGameModel.setId(1);
        testGameModel.setName("TestGame");
        testGameModel.setDescription("TestDesc");
        testGameModel.setMinWager(20);
        testGameModel.setMultiplier(2.0);
    }


    /*
    public Integer createEntity(WagerEntity entity) {
        try{
            Integer newId = wagerRepo.insert(entity);
            log.debug("Created new Wager: id={}, points={}", entity.getId(), entity.getPoints());
            return newId;
        }catch(SQLException e){
            log.error("Failed to create Wager: id={}, points={}", entity.getId(), entity.getPoints(), e);
            e.printStackTrace();
            return -1;
        }
    }
    */

    @Test
    void testCreateWager_Success_ReturnsNewId() throws SQLException{
        // AAA
        //Arange - prepare the test for the scenario we want to test for
        when(wagerRepo.insert(testWagerEntity)).thenReturn(100);
        
        //Act - use the method as we have mocked
        Integer result = wagerService.createEntity(testWagerEntity);

        //Assert - verify the result of the method call
        assertEquals(100, result);

        //Verify the behavior of the service function by how it calls its mocks
        verify(wagerRepo, times(1)).insert(testWagerEntity);
    }

    @Test
    void testCreateWager_ThrowsSQLException_ReturnsMinusOne() throws SQLException{
        // AAA
        //Arange - prepare the test for the scenario we want to test for
        when(wagerRepo.insert(testWagerEntity)).thenThrow(new SQLException());
        
        //Act - use the method as we have mocked
        Integer result = wagerService.createEntity(testWagerEntity);

        //Assert - verify the result of the method call
        assertEquals(-1, result);

        //Verify the behavior of the service function by how it calls its mocks
        verify(wagerRepo, times(1)).insert(testWagerEntity);
    }

    /*
    public Optional<Wager> convertEntityToModel(WagerEntity entity) {
        Wager wager = new Wager();

        Optional<Player> player = playerService.getModelById(entity.getPlayerId());

        if (player.isEmpty()){
            throw new RuntimeException("invalid Player id");
        }

        Optional<Game> game = gameService.getModelById(entity.getGameId());

        if (game.isEmpty()){
            throw new RuntimeException("invalid Game id");
        }


        wager.setId(entity.getId());
        wager.setPlayer(player.get());
        wager.setGame(game.get());
        wager.setPoints(entity.getPoints());

        log.info("Converted wagerEntity to Wager model: id={}, points={}", wager.getId(), wager.getPoints());


        return Optional.of(wager);
    }
    */

    @Test
    void testConvertEntityToModel_Success_ReturnsWagerModel(){
    
        //Arrange
        when(playerService.getModelById(testWagerEntity.getPlayerId()))
            .thenReturn(Optional.of(testPlayerModel));
        when(gameService.getModelById(testWagerEntity.getGameId()))
            .thenReturn(Optional.of(testGameModel));

        //Act
        Optional<Wager> result = wagerService.convertEntityToModel(testWagerEntity);

        //Assert
        assertEquals(true, result.isPresent());
        assertEquals(testWagerEntity.getId(), result.get().getId());

        //Verify
        verify(playerService, times(1)).getModelById(testWagerEntity.getPlayerId());
        verify(gameService, times(1)).getModelById(testWagerEntity.getGameId());
    }
}
