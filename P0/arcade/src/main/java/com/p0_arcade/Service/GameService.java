package com.p0_arcade.service;

import com.p0_arcade.repo.DAO.GameRepository;
import com.p0_arcade.repo.entities.GameEntity;
import com.p0_arcade.service.models.Game;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* 
 *   Service Layer for Game Operations
 *- hides SQL Exceptions from GameRepo from rest of app
 * - contains business logic around Games
*/
public class GameService implements ServiceInterface<GameEntity, Game>{

    private static final Logger log = LoggerFactory.getLogger(GameService.class);

    private final GameRepository gameRepo = new GameRepository();

    //CREATE
    public Integer createEntity(GameEntity entity) {
        log.warn("Attempted to create Game via GameService: name={}", 
                 entity != null ? entity.getName() : "null");

        throw new UnsupportedOperationException(
            "Games are pre-defined and cannot be created through the service layer"
        );
    }

    //READ ALL
    public List<GameEntity> readAllEntities(){
        try {
            return gameRepo.findAll();
        } catch (SQLException e) {
            log.error("Failed to read all games", e);
            return null;
        }
    }

    //READ BY ID
    public Optional<GameEntity> readEntityById(Integer id){
        try{
            Optional<GameEntity> gameEntity = gameRepo.findById(id);
            
            if (gameEntity.isEmpty()){
                throw new RuntimeException("Game not found");
            } 

            return gameEntity;
        }catch(SQLException | RuntimeException e){
            log.error("Failed to read Game by id={}", id, e);
            return Optional.empty();
        }
    }

    //UPDATE
    public GameEntity updateEntity(GameEntity g){
        log.warn("Attempted to update Game via GameService: name={}", 
                 g != null ? g.getName() : "null");

        throw new UnsupportedOperationException(
            "Games are pre-defined and cannot be updated through the service layer"
        );
    }

    //DELETE
    public void deleteEntityById(Integer id){
        log.warn("Attempted to delete Game via GameService: id={}", 
                 id != null ? id : "null");

        throw new UnsupportedOperationException(
            "Games are pre-defined and cannot be deleted through the service layer"
        );
    }


    // Conversation
    public Optional<Game> convertEntityToModel(GameEntity g){
        Game game = new Game();
        
        game.setId(g.getId());
        game.setName(g.getName());
        game.setDescription(g.getDescription());
        game.setMinWager(g.getMinWager());
        game.setMultiplier(g.getMultiplier());

        return Optional.of(game);
    }

    public Optional<Game> getModelById(Integer id){
        Optional<GameEntity> gameEntity = readEntityById(id);

        try{

            if(gameEntity.isPresent()){
                Optional<Game> game = convertEntityToModel(gameEntity.get());
                if(game.isPresent()){
                    log.info("Converted gameEntity to Game model: id={}, name={}", game.get().getId(), game.get().getName());
                    return game;
                }else{
                    throw new RuntimeException("gameEntity conversion failed");
                }
            }else{
                throw new RuntimeException("gameEntity not found");
            }

        }catch(RuntimeException e){
            log.error("Failed to get gameModel by id={}", id, e);
            return Optional.empty();
        }
    }

    // Game Logic
    public int playCoinFlip(Game game, int wager, char guess){
        if (guess != 'H' && guess != 'T') {
            throw new IllegalArgumentException("Guess must be 'H' or 'T'");
        }
        double multiplier = game.getMultiplier();
        boolean coin = Math.random() < 0.5; // Heads is true, Tails is false
        boolean win = (guess == 'H' && coin) || (guess == 'T' && !coin);

        return win ? (int) (wager * multiplier) : -wager;
    }
}
