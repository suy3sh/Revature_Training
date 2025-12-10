package com.p0_arcade.service.models;


public class Player{

    private Integer id;
    private String name;
    private Integer points;


    //Constructors
    public Player(){

    }

    public Player(Integer id, String name, Integer points){
        this.id = id;
        this.name = name;
        this.points = points;
    }

    public Player(String name){
        this.name = name;
        points = 100;
    }

    //Getters & Setters
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

    public void setPoints(Integer balance){
        if (balance < 0) throw new IllegalArgumentException("Points cannot be negative");
        this.points = balance;
    }


    //Deposit and Withdraw Points
    public void addPoints(Integer amount){
        if (amount <= 0) throw new IllegalArgumentException("Points to add must be above 0");
        setPoints(points + amount);
    }

    public void spendPoints(Integer amount){
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
