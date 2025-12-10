package com.p0_arcade.service.models;


public class Wager{

    private Integer id;
    private Integer playerId;
    private Integer gameId;
    private Integer points;

    //Constructors
    public Wager(){

    }

    public Wager(Integer id, Integer playerId, Integer gameId, Integer points){
        this.id = id;
        this.playerId = playerId;
        this.gameId = gameId;
        setPoints(points);
    }

    // Getters & Setters
    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getPlayerId(){
        return this.playerId;
    }

    public void setPlayerId(Integer id){
        this.playerId = id;
    }

    public Integer getGameId(){
        return this.gameId;
    }

    public void setGameId(Integer id){
        this.gameId = id;
    }

    public Integer getPoints(){
        return points;
    }

    public void setPoints(Integer points){
        if (points < 0){
            throw new IllegalArgumentException("Wager amount must be positive!");
        }

        this.points = points;
    }

    // toString
    @Override
    public String toString(){
        return "Wager {id=" + id + ", playerId=" + playerId + ", gameId=" + gameId + ", points=" + points + "}";
    }

}
