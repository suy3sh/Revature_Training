package com.p0_arcade.Classes;

import java.io.Serializable;

public class Game implements Serializable{

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String description;
    private int minWager; // minimum wager amount allowed for the game
    private double multiplier; // the multiplier applied when the game is won.

    // Constructors
    public Game(){
    }

    public Game(int id, String name, String desc, int min, double multi){
        this.id = id;
        this.name = name;
        this.description = desc;
        setMinWager(min);
        setMultiplier(multi);
    }

    // Getters & Setters
    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public int getMinWager(){
        return this.minWager;
    }

    public double getMultiplier(){
        return this.multiplier;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String desc){
        this.description = desc;
    }

    public void setMinWager(int min){
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
    public boolean isValidWager(int amount){
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
