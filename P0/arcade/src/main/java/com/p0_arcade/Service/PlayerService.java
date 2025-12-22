package com.p0_arcade.service;

import com.p0_arcade.repo.DAO.PlayerRepository;
import com.p0_arcade.repo.entities.PlayerEntity;
import com.p0_arcade.service.models.Player;

import java.sql.SQLException;
import java.util.ArrayList;
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

    private final PlayerRepository playerRepo;
    
    // Constructor for dependency injection (used in tests)
    public PlayerService(PlayerRepository playerRepo) {
        this.playerRepo = playerRepo;
    }
    
    // Default constructor for production use
    public PlayerService() {
        this.playerRepo = new PlayerRepository();
    }

    //CREATE
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

    //READ ALL
    public List<PlayerEntity> readAllEntities() {
        try {
            return playerRepo.findAll();
        }catch(SQLException e){
            log.error("Failed to read all players", e);
            e.printStackTrace();
            return null;
        }
    }

    //READ BY ID
    public Optional<PlayerEntity> readEntityById(Integer id){
        try{
            Optional<PlayerEntity> playerEntity = playerRepo.findById(id);
            
            if (playerEntity.isEmpty()){
                return null;
            } 

            return playerEntity;

        }catch(SQLException | RuntimeException e){
            log.error("Failed to read Player by id={}", id, e);
            e.printStackTrace();
            return Optional.empty();
        }
    }

    //UPDATE
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
    
    //DELETE
    public void deleteEntityById(Integer id){
        try{
            playerRepo.deleteById(id);
            log.debug("Deleted Player with id={}", id);
        }catch(SQLException e){
            log.error("Failed to delete Player with id={}", id, e);
            e.printStackTrace();
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
                    return player;
                }else{
                    throw new RuntimeException("PlayerEntity conversion failed");
                }
            }else{
                throw new RuntimeException("PlayerEntity not found");
            }

        }catch(RuntimeException e){
            log.error("Failed to get playerModel by id={}", id, e);
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<Player> getAllModels(){
        List<PlayerEntity> playerEntities = readAllEntities();
        List<Player> players = new ArrayList<>();

        for (PlayerEntity pe : playerEntities){
            Optional<Player> player = convertEntityToModel(pe);
            if (player.isPresent()){
                players.add(player.get());
            }
        }

        return players;
    }
}
