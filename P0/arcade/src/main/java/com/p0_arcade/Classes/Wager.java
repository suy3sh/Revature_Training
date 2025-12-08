package com.p0_arcade.Classes;

import java.io.Serializable;

public class Wager implements Serializable{
    private static final long serialVersionUID = 1L;

    private int id;
    private int playerId;
    private int gameId;
    private int points;

    //Constructors
    public Wager(){

    }

    public Wager(int id, int playerId, int gameId, int points){
        this.id = id;
        this.playerId = playerId;
        this.gameId = gameId;
        setPoints(points);
    }

    // Getters & Setters
    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getPlayerId(){
        return this.playerId;
    }

    public void setPlayerId(int id){
        this.playerId = id;
    }

    public int getGameId(){
        return this.gameId;
    }

    public void setGameId(int id){
        this.gameId = id;
    }

    public int getPoints(){
        return points;
    }

    public void setPoints(int points){
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
