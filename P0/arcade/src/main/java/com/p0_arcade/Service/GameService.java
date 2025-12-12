package com.p0_arcade.service;

import com.p0_arcade.repo.DAO.GameRepository;
import com.p0_arcade.repo.entities.GameEntity;
import com.p0_arcade.repo.entities.WagerEntity;
import com.p0_arcade.service.models.Game;
import com.p0_arcade.service.models.Wager;

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
            e.printStackTrace();
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
            e.printStackTrace();
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

        log.info("Converted GameEntity to Game model: id={}, name={}", game.getId(), game.getName());


        return Optional.of(game);
    }

    public Optional<Game> getModelById(Integer id){
        Optional<GameEntity> gameEntity = readEntityById(id);

        try{

            if(gameEntity.isPresent()){
                Optional<Game> game = convertEntityToModel(gameEntity.get());
                if(game.isPresent()){
                    return game;
                }else{
                    throw new RuntimeException("gameEntity conversion failed");
                }
            }else{
                throw new RuntimeException("gameEntity not found");
            }

        }catch(RuntimeException e){
            log.error("Failed to get gameModel by id={}", id, e);
            e.printStackTrace();
            return Optional.empty();
        }
    }

    // Game Logic
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

    public int playRPS(Game game, WagerEntity wager, char guess){

        enum choice{
            ROCK,
            PAPER,
            SCISSORS
        }

        if (guess != 'R' && guess != 'P' && guess != 'S'){
            throw new IllegalArgumentException("Invalid Input: Guess must be 'R', 'P', or 'S'");
        }

        double multiplier = game.getMultiplier();

        boolean equal = true;

        choice opp = choice.values()[(int)(Math.random() * choice.values().length)];

        //makes sure opp and guess are not equal
        while(equal){
            if ((opp == choice.ROCK && guess == 'R') || (opp == choice.PAPER && guess != 'P') || (opp == choice.SCISSORS && guess == 'S')){
                opp = choice.values()[(int)(Math.random() * choice.values().length)];
            } else {
                equal = false;
            }
        }
        
        System.out.println("The arcade chose " + opp.name());


        if ((opp == choice.ROCK && guess == 'P') || (opp == choice.PAPER && guess == 'S') || (opp == choice.SCISSORS && guess == 'R')){
            if (guess == 'P'){
                System.out.println("Paper beats Rock.");
            } else if (guess == 'S'){
                System.out.println("Scissors beats Paper.");
            } else {
                System.out.println("Rock beats Scissors");
            }
            System.out.println("You win!");
            return (int) (wager.getPoints() * multiplier);
        } else {
            if (opp == choice.PAPER){
                System.out.println("Paper beats Rock.");
            } else if (opp == choice.SCISSORS){
                System.out.println("Scissors beats Paper.");
            } else {
                System.out.println("Rock beats Scissors");
            }
            System.out.println("You lose!");
            return 0;
        }
    }
}
