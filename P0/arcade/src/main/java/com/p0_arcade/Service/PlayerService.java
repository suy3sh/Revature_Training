package com.p0_arcade.service;

import com.p0_arcade.repo.DAO.PlayerRepository;
import com.p0_arcade.repo.entities.PlayerEntity;
import com.p0_arcade.service.models.Player;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


/* 
 *   Serice Layer for Player Operations
 *- hides SQLE Exceptions from PlayerRepo from rest of app
 * - contains business logic around Players
*/
public class PlayerService implements ServiceInterface<PlayerEntity, Player>{

    private final PlayerRepository playerRepo = new PlayerRepository();

    //CREATE
    public Integer createEntity(PlayerEntity p){
        try{
            Integer id = playerRepo.insert(p);
            return id;
        }catch(SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    //READ ALL
    public List<PlayerEntity> readAllEntities() {
        try {
            return playerRepo.findAll();
        }catch(SQLException e){
            System.out.println("Service Error: Could not load players from Database.");
            e.printStackTrace();
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
            System.out.println("Service Error: Could not look up Player with ID: " + id);
            e.printStackTrace();
            return Optional.empty();
        }
    }

    //UPDATE
    public PlayerEntity updateEntity(PlayerEntity p){ //return true on success, false on failure
        //check if points is valid after update
        if (p.getPoints() < 0) throw new IllegalArgumentException("Player points cannot be negative");
        
        try{
            playerRepo.update(p);
            return p;
        }catch(SQLException e){
            System.out.println("Service Error: Could not update Player with ID: " + p.getId());
            e.printStackTrace();
            return null;
        }
    }
    
    //DELETE
    public void deleteEntityById(Integer id){
        try{
            playerRepo.deleteById(id);
        }catch(SQLException | RuntimeException e){
            System.out.println("Service Error: Could not delete Player with ID: " + id);
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
            e.printStackTrace();
            return Optional.empty();
        }

    }

}
