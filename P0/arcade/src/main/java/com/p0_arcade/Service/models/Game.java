package com.p0_arcade.service.models;


public class Game{

    private Integer id;
    private String name;
    private String description;
    private Integer minWager; // minimum wager amount allowed for the game
    private double multiplier; // the multiplier applied when the game is won.

    // Constructors
    public Game(){
    }

    public Game(Integer id, String name, String desc, Integer min, double multi){
        this.id = id;
        this.name = name;
        this.description = desc;
        setMinWager(min);
        setMultiplier(multi);
    }

    // Getters & Setters
    public Integer getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public Integer getMinWager(){
        return this.minWager;
    }

    public double getMultiplier(){
        return this.multiplier;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String desc){
        this.description = desc;
    }

    public void setMinWager(Integer min){
        if (min < 0){
            throw new IllegalArgumentException("Minimum Wager cannot be less than 0!");
        }
        this.minWager = min;
    }

    public void setMultiplier(double multi){
        if (multi < 0){
            throw new IllegalArgumentException("Multiplier cannot be less than 0!");
        }
        this.multiplier = multi;
    }

    //Check if wager is valid
    public boolean isValidWager(Integer amount){
        return amount > minWager;
    }

    // Convert to String
    @Override
    public String toString(){
        return "Game {id: " + id + 
            ", name: " + name + 
            ", description: " + description + 
            ", minWager: " + minWager + 
            ",  multiplier: " + multiplier + "}";
    }

}
