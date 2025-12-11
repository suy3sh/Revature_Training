package com.p0_arcade.service;

import com.p0_arcade.repo.DAO.PlayerRepository;
import com.p0_arcade.repo.entities.PlayerEntity;
import com.p0_arcade.service.models.Player;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/* 
 *   Serice Layer for Player Operations
 *- hides SQLE Exceptions from PlayerRepo from rest of app
 * - contains business logic around Players
*/
public class PlayerService implements ServiceInterface<PlayerEntity, Player>{

    private static final Logger log = LoggerFactory.getLogger(PlayerService.class);

    private final PlayerRepository playerRepo = new PlayerRepository();

    //CREATE
    public Integer createEntity(PlayerEntity p){
        try{
            Integer id = playerRepo.insert(p);
            log.debug("Created new Player: id={}, name={}", p.getId(), p.getName());

            return id;
        }catch(SQLException e){
            log.error("Failed to create Player: name={}", p.getName(), e);
            throw new RuntimeException("Failed to create player: " + p.getName(), e);
        }
    }

    //READ ALL
    public List<PlayerEntity> readAllEntities() {
        try {
            return playerRepo.findAll();
        }catch(SQLException e){
            log.error("Failed to read all players", e);
            return null;
        }
    }

    //READ BY ID
    public Optional<PlayerEntity> readEntityById(Integer id){
        try{
            Optional<PlayerEntity> playerEntity = playerRepo.findById(id);
            
            if (playerEntity.isEmpty()){
                throw new RuntimeException("Player not found");
            } 

            return playerEntity;

        }catch(SQLException | RuntimeException e){
            log.error("Failed to read Player by id={}", id, e);
            return Optional.empty();
        }
    }

    //UPDATE
    public PlayerEntity updateEntity(PlayerEntity p){ //return true on success, false on failure
        //check if points is valid after update
        if (p.getPoints() < 0) throw new IllegalArgumentException("Player points cannot be negative");

        try{
            playerRepo.update(p);
            log.debug("Updated Player: id={}, name={}, points={}", p.getId(), p.getName(), p.getPoints());
            return p;
        }catch(SQLException e){
            log.error("Failed to update Player: id={}, name={}", p.getId(), p.getName(), e);
            return null;
        }
    }
    
    //DELETE
    public void deleteEntityById(Integer id){
        try{
            playerRepo.deleteById(id);
            log.debug("Deleted Player with id={}", id);
        }catch(SQLException e){
            log.error("Failed to delete Player with id={}", id, e);
        }
    }

    //Conversation
    public Optional<Player> convertEntityToModel(PlayerEntity entity){
        Player player = new Player();
        player.setId(entity.getId());
        player.setName(entity.getName());
        player.setPoints(entity.getPoints());
        
        return Optional.of(player);
    }

    public Optional<Player> getModelById(Integer id){
        Optional<PlayerEntity> playerEntity = readEntityById(id);

        try{

            if(playerEntity.isPresent()){
                Optional<Player> player = convertEntityToModel(playerEntity.get());
                if(player.isPresent()){
                    log.info("Converted PlayerEntity to Player model: id={}, name={}", player.get().getId(), player.get().getName());
                    return player;
                }else{
                    throw new RuntimeException("PlayerEntity conversion failed");
                }
            }else{
                throw new RuntimeException("PlayerEntity not found");
            }

        }catch(RuntimeException e){
            log.error("Failed to get playerModel by id={}", id, e);
            return Optional.empty();
        }
    }
}
