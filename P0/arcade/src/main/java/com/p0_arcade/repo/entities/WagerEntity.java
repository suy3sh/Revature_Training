package com.p0_arcade.repo.entities;

import java.util.Objects;

public class WagerEntity {
    private Integer id;
    private Integer playerId;
    private Integer gameId;
    private Integer points;


    public WagerEntity(){
    }

    public WagerEntity(Integer id, Integer playerId, Integer gameId, Integer points){
        this.id = id;
        this.playerId = playerId;
        this.gameId = gameId;
        setPoints(points);
    }

    //Getters and Setters
    public Integer getId(){
        return this.id;
    }

    public Integer getPlayerId(){
        return this.playerId;
    }
    
    public Integer getGameId(){
        return this.gameId;
    }

    public Integer getPoints(){
        return this.points;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setPlayerId(Integer playerId){
        this.playerId = playerId;
    }

    public void setGameId(Integer gameId){
        this.gameId = gameId;
    }
    
    public void setPoints(Integer points){
        if (points < 0){
            throw new IllegalArgumentException("Points cannot be negative!");
        }
        this.points = points;
    }

    @Override
    public String toString(){
        return "WagerEntity {id: " + id + 
        ", playerId: " + playerId + 
        ", gameId: " + gameId + 
        ", points: " + points + "}";
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        WagerEntity that = (WagerEntity) obj;
        return Objects.equals(id, that.id) && 
        Objects.equals(playerId, that.playerId) && 
        Objects.equals(gameId, that.gameId) && 
        Objects.equals(points, that.points);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, playerId, gameId, points);
    }
}
