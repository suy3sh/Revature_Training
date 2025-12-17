import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.p0_arcade.repo.DAO.PlayerRepository;
import com.p0_arcade.repo.entities.PlayerEntity;
import com.p0_arcade.service.PlayerService;
import com.p0_arcade.service.models.Player;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest{

    @Mock
    private PlayerRepository playerRepo;

    @InjectMocks
    private PlayerService playerService;

    private PlayerEntity testPlayerEntity;
    private Player testPlayerModel;

    @BeforeEach
    void setup(){
        testPlayerEntity = new PlayerEntity();
        testPlayerEntity.setId(1);
        testPlayerEntity.setName("testPlayer");
        testPlayerEntity.setPoints(1000);

        testPlayerModel = new Player();
        testPlayerModel.setId(1);
        testPlayerModel.setName("testPlayer");
        testPlayerModel.setPoints(1000);
    }

    /*
    public Integer createEntity(PlayerEntity p){
        try{
            Integer id = playerRepo.insert(p);
            p.setId(id); // Set the generated ID on the entity
            log.debug("Created new Player: id={}, name={}", p.getId(), p.getName());

            return id;
        }catch(SQLException e){
            log.error("Failed to create Player: name={}", p.getName(), e);
            e.printStackTrace();
            return -1;
        }
    }
    */

    @Test
    void testCreateEntity_Success_ReturnsNewId() throws SQLException{
        //Arrange
        when(playerRepo.insert(testPlayerEntity)).thenReturn(100);

        //Act
        Integer result = playerService.createEntity(testPlayerEntity);

        //Assert
        assertEquals(100, result);

        //Verify
        verify(playerRepo, times(1)).insert(testPlayerEntity);
    }

    @Test
    void testCreateEntity_ThrowsSQLException_ReturnsMinusOne() throws SQLException{
        //Arrange
        when(playerRepo.insert(testPlayerEntity)).thenThrow(new SQLException());

        //Act
        Integer result = playerService.createEntity(testPlayerEntity);

        //Assert
        assertEquals(-1, result);
        
        //Verify
        verify(playerRepo, times(1)).insert(testPlayerEntity);
    }

    /*
    public PlayerEntity updateEntity(PlayerEntity p){
        //check if points is valid before update
        if (p.getPoints() < 0) throw new IllegalArgumentException("Player points cannot be negative");

        try{
            playerRepo.update(p);
            log.debug("Updated Player: id={}, name={}, points={}", p.getId(), p.getName(), p.getPoints());
            return p;
        }catch(SQLException e){
            log.error("Failed to update Player: id={}, name={}", p.getId(), p.getName(), e);
            e.printStackTrace();
            return null;
        }
    }
    */

    @Test 
    void testUpdateEntity_Success_ReturnsUpdatedPlayer() throws SQLException{
        //Arrange
        doNothing().when(playerRepo).update(testPlayerEntity);

        //Act
        PlayerEntity result = playerService.updateEntity(testPlayerEntity);

        //Assert
        assertEquals(testPlayerEntity, result);
        assertEquals(1, result.getId());
        assertEquals("testPlayer", result.getName());
        assertEquals(1000, result.getPoints());
        
        //Verify
        verify(playerRepo, times(1)).update(testPlayerEntity);
    }

    @Test
    void testUpdateEntity_ThrowsSQLException_ReturnNull() throws SQLException{
        //Arrange
        doThrow(new SQLException()).when(playerRepo).update(testPlayerEntity);

        //Act
        PlayerEntity result = playerService.updateEntity(testPlayerEntity);

        //Assert
        assertNull(result);

        //Verify
        verify(playerRepo, times(1)).update(testPlayerEntity);
    }

   

}