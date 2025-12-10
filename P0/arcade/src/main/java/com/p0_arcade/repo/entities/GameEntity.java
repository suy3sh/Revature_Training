package com.p0_arcade.repo.entities;

import java.util.Objects;

public class GameEntity {
    private Integer id;
    private String name;
    private String description;
    private Integer minWager; // minimum wager amount allowed for the game
    private double multiplier;

    public GameEntity(){
    }

    public GameEntity(Integer id, String name, String desc, Integer min, double multi){
        this.id = id;
        this.name = name;
        this.description = desc;
        setMinWager(min);
        setMultiplier(multi);
    }


    //Getters and Setters
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

    @Override
    public String toString(){
        return "GameEntity {id: " + id + 
        ", name: " + name + 
        ", description: " + description + 
        ", minWager: " + minWager + 
        ", multiplier: " + multiplier + "}";
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        GameEntity that = (GameEntity) obj;
        return Objects.equals(id, that.id) && 
        Objects.equals(name, that.name) && 
        Objects.equals(description, that.description) && 
        Objects.equals(minWager, that.minWager) && 
        Objects.equals(multiplier, that.multiplier);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, name, description, minWager, multiplier);
    }
}


