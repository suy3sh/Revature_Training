package com.p0_arcade.service.models;


public class Wager{

    private Integer id;
    private Player player;
    private Game game;
    private Integer points;

    //Constructors
    public Wager(){

    }

    public Wager(Integer id, Player p, Game g, Integer points){
        this.id = id;
        this.player = p;
        this.game = g;
        setPoints(points);
    }

    // Getters & Setters
    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Player getPlayer(){
        return this.player;
    }

    public void setPlayer(Player p){
        this.player = p;
    }

    public Game getGame(){
        return this.game;
    }

    public void setGame(Game g){
        this.game = g;
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
        return "Wager {id=" + id + ", player=" + player + ", game=" + game + ", points=" + points + "}";
    }

}
