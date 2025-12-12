package com.p0_arcade.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.p0_arcade.repo.DAO.WagerRepository;
import com.p0_arcade.repo.entities.WagerEntity;
import com.p0_arcade.service.models.*;

public class WagerService implements ServiceInterface<WagerEntity, Wager>{

    private static final Logger log = LoggerFactory.getLogger(WagerService.class);

    private WagerRepository wagerRepo = new WagerRepository();

    private GameService gameService = new GameService();
    private PlayerService playerService = new PlayerService();

    @Override
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

    @Override
    public List<WagerEntity> readAllEntities() {
        try{
            return wagerRepo.findAll();
        }catch(SQLException e){
            log.error("Failed to read all Wagers", e);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<WagerEntity> readEntityById(Integer id) {
        try{
            Optional<WagerEntity> wagerEntity = wagerRepo.findById(id);
            
            if (wagerEntity.isEmpty()){
                throw new RuntimeException("Wager not found");
            } 

            return wagerEntity;
        }catch(SQLException e){
            log.error("Failed to read Wager with ID:{}", id, e);
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public WagerEntity updateEntity(WagerEntity entity) {
        log.warn("Attempted to update Wager via WagerService: id={}", 
                 entity != null ? entity.getId() : "null");

        throw new UnsupportedOperationException(
            "Wagers cannot be updated through the service layer"
        );
    }

    @Override
    public void deleteEntityById(Integer id) {
        log.warn("Attempted to delete Wager via WagerService: id={}", 
                 id != null ? id : "null");

        throw new UnsupportedOperationException(
            "Wagers cannot be deleted through the service layer"
        );
    }

    @Override
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

    @Override
    public Optional<Wager> getModelById(Integer id) {
        Optional<WagerEntity> wagerEntity = readEntityById(id);

        try{

            if(wagerEntity.isPresent()){
                Optional<Wager> wager = convertEntityToModel(wagerEntity.get());
                if(wager.isPresent()){
                    return wager;
                }else{
                    throw new RuntimeException("wagerEntity conversion failed");
                }
            }else{
                throw new RuntimeException("wagerEntity not found");
            }

        }catch(RuntimeException e){
            log.error("Failed to get wagerModel by id={}", id, e);
            e.printStackTrace();
            return Optional.empty();
        }
    }
    

}
