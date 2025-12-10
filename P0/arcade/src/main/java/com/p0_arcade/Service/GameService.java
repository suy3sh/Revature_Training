package com.p0_arcade.service;

import com.p0_arcade.repo.DAO.GameRepository;
import com.p0_arcade.repo.entities.GameEntity;
import com.p0_arcade.service.models.Game;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/*
//CREATE
    public Integer createEntity(T entity);

    //READ
    public List<T> readAllEntities();
    public Optional<T> readEntityById(Integer id);

    //UPDATE
    public T updateEntity(T entity);

    //DELETE
    public void deleteEntityById(Integer id);


    // Conversation
    public Optional<U> convertEntityToModel(T entity);
    public Optional<U> getModelById(Integer id);
}
*/

public class GameService implements ServiceInterface<GameEntity, Game>{

    private final GameRepository gameRepo = new GameRepository();

    //CREATE
    public Integer createEntity(GameEntity entity){
        return null;
    }

    //READ
    public List<GameEntity> readAllEntities(){
        return null;
    }

    public Optional<GameEntity> readEntityById(Integer id){
        return Optional.empty();
    }

    //UPDATE
    public GameEntity updateEntity(GameEntity entity){
        return null;
    }

    //DELETE
    public void deleteEntityById(Integer id){

    }


    // Conversation
    public Optional<Game> convertEntityToModel(GameEntity entity){
        return Optional.empty();
    }
    public Optional<Game> getModelById(Integer id){
        return Optional.empty();
    }

}
