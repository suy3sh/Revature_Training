package com.p0_arcade.classes;

import java.io.Serializable;

public class Player implements Serializable{

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private int points;


    //Constructors
    public Player(){

    }

    public Player(int id, String name, int points){
        this.id = id;
        this.name = name;
        this.points = points;
    }

    public Player(String name){
        this.name = name;
        points = 100;
    }

    //Getters & Setters
    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public int getPoints(){
        return this.points;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPoints(int balance){
        if (balance < 0) throw new IllegalArgumentException("Points cannot be negative");
        this.points = balance;
    }


    //Deposit and Withdraw Points
    public void addPoints(int amount){
        if (amount <= 0) throw new IllegalArgumentException("Points to add must be above 0");
        setPoints(points + amount);
    }

    public void spendPoints(int amount){
        if (amount <= 0) throw new IllegalArgumentException("Points to spend must be above 0");
        if (amount > points) throw new IllegalArgumentException("You don't have enough points!");
        setPoints(points - amount);
    }


    // Convert to String
    @Override
    public String toString(){
        return "Player {id: " + id + ", name: " + name + ", points: " + points + "}";
    }
}
