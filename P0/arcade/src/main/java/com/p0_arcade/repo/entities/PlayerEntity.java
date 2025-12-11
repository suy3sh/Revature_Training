package com.p0_arcade.repo.entities;

import java.util.Objects;

public class PlayerEntity {
    private Integer id;
    private String name;
    private Integer points;

    public PlayerEntity(){
    }

    public PlayerEntity(Integer id, String name, Integer points){
        this.id = id;
        this.name = name;
        setPoints(points);
    }

    public PlayerEntity(String name){
        this.name = name;
        setPoints(100);
    }

    //Getters and Setters
    public Integer getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }
    
    public Integer getPoints(){
        return this.points;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPoints(Integer points){
        if (points < 0){
            throw new IllegalArgumentException("Points cannot be negative!");
        }
        this.points = points;
    }

    @Override
    public String toString(){
        return "PlayerEntity {id: " + id + 
        ", name: " + name + 
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
        PlayerEntity that = (PlayerEntity) obj;
        return Objects.equals(id, that.id) && 
        Objects.equals(name, that.name) && 
        Objects.equals(points, that.points);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, name, points);
    }
}
