package com.p0_arcade.controller;

import com.p0_arcade.repo.entities.PlayerEntity;
import com.p0_arcade.service.PlayerService;
import com.p0_arcade.service.models.Player;
import com.p0_arcade.util.InputHandler;

import java.util.Optional;
import java.util.List;

public class PlayerController {

    private final PlayerService playerService = new PlayerService();

    public Player currPlayer;

    public void handleInput(){
        boolean running = true;
        printMenu();

        while (running){
            
            int choice = InputHandler.getIntInput("Choose an option: ");
            switch (choice) {
                case 1: 
                    addPlayer(); //create a new Player
                    running = false;
                    break;
                case 2:
                    logIn();//set curr player to a new player
                    running = false;
                    break;
                case 3:
                    listAllPlayers(); //return a list of all current players
                    running = false;
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Please choose one of the provided options.");
            }
        }
    }

    private void printMenu(){
        System.out.println("\n=== Player Menu ===");
        System.out.println("1.) Create a New Player");
        System.out.println("2.) Log in as an Existing Player");
        System.out.println("3.) View a list of all Players");
        System.out.println("0.) Exit");
    }

    public void addPlayer(){
        String name;
        while (true){
            name = InputHandler.getStringInput("\nEnter the Player name: ");
            if (name != ""){
                break;
            } else {
                System.out.println("Please enter a valid name.");
            }
        }

        PlayerEntity playerEntity = new PlayerEntity(name);

        Integer newPlayerId = playerService.createEntity(playerEntity);

        Optional<Player> player = playerService.convertEntityToModel(playerEntity);

        if (newPlayerId == -1){
            System.out.println("Invalid Player Name");
        } else {
            System.out.println("Created Player: (ID " + player.get().getId() + " | " + player.get().getName() + " | " + player.get().getPoints() + " pts)");
            currPlayer = player.get();
        }

    }
    
    public void logIn(){
        List<Player> players = playerService.getAllModels();
        if (players.isEmpty()){
            System.out.println("\nNo available Players. Create a player first.");
            return;
        }

        int id;


        while (true){
            id = InputHandler.getIntInput("Enter the ID of the player you want to play as: ");

            if (playerService.readEntityById(id) == null){
                System.out.println("No Player found with ID " + id);
            } else {
                break;
            }
        }
        
        Optional<Player> chosenPlayer = playerService.getModelById(id);
        currPlayer = chosenPlayer.get();

        System.out.println("You are now logged in as " + currPlayer.getName() + 
                            " (ID " + currPlayer.getId() + " | " + currPlayer.getPoints() + " pts)");

    }

    public void listAllPlayers(){
        List<Player> players = playerService.getAllModels();

        System.out.println("\nList of Current Available Players");
        System.out.println("=================================");

        if (players.isEmpty()){
            System.out.println("[No players created yet...]");
        } else {
            System.out.printf("%-5s %-20s %-10s%n", "ID", "NAME", "POINTS");
            System.out.println("---------------------------------");
            
            for (Player p : players){
                System.out.printf("%-5d %-20s %-10d%n", p.getId(), p.getName(), p.getPoints());
            }
        }
    }

    public Player getCurrPlayer(){
        return currPlayer;
    }
}
