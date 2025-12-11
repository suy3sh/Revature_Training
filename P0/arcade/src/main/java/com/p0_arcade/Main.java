package com.p0_arcade;


import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import com.p0_arcade.controller.PlayerController;

import com.p0_arcade.util.InputHandler;


 import com.p0_arcade.service.models.Player;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);


    //private final List<Player> players = new ArrayList<>();
    //private final PlayerRepository playerRepo = new PlayerRepository();
    //private final PlayerService playerService = new PlayerService(playerRepo);

    
    // private List<Game> games = new ArrayList<>();


    // private Player currPlayer = null;

    // private final GameService gameService = new GameService(scanner);

    public static void main(String[] args){

        PlayerController playerController = new PlayerController();
        
        printMenu(playerController.getCurrPlayer());

        InputHandler.getIntInput("Enter 1: ");

        playerController.addPlayer();
        

        


        //new Main().run();   
    }

    private static void printMenu(Player currPlayer){

            System.out.println("\n========== Welcome to the Arcade! ==========");
            System.out.println(
                "Current Player: " + (currPlayer == null ? "[none]" : 
                currPlayer.getName() + " (ID " + currPlayer.getId() + " | " + currPlayer.getPoints() + " pts)")
            );
            System.out.println("============================================");
            System.out.println("1.) Enter the Arcade");
            System.out.println("2.) Log in as Existing Player");
            System.out.println("3.) Create a New Player");
            System.out.println("4.) List Players");
            System.out.println("0.) Exit the Arcade");
            //return chooseMenuOption();
        }


//     private void run(){
//         boolean running = true;

//         while (running){
//             String choice = printMenu();
//             initializeGames();

//             switch (choice){
//                 case "1": //PICK A GAME
                    
//                     if (currPlayer == null){
//                         System.out.println("Current Player has not been chosen! Either create a new Player or Log in!");
//                         enterContinue();
//                         break;
//                     } else {
//                         Game chosenGame = pickGame();
//                         gameService.play(currPlayer, chosenGame);
//                     }
                    
//                     break;
//                 case "2": // LOG IN
//                     logIn();
//                     enterContinue();
//                     break;
//                 case "3": // CREATE NEW PLAYER
//                     createPlayer();
//                     enterContinue();
//                     break;
//                 case "4": // LIST CURRENT PLAYERS
//                     listPlayers();
//                     enterContinue();
//                     break;
//                 case "0": // EXIT GAME
//                     running = false;
//                     break;
//             }
//         }
//     }

        

//     private String chooseMenuOption(){
//         while (true){
//             System.out.print("\nChoose an option: ");
//             String input = scanner.nextLine();

//             //validate
//             if (input.equals("1") ||
//                 input.equals("2") ||
//                 input.equals("3") ||
//                 input.equals("4") ||
//                 input.equals("0")){
//                     return input;
//                 }

//             System.out.println("Invalid Choice. Choose again.");
//         }
//     }

//     private String chooseGameOption(){
//         while (true){
//             System.out.print("\nChoose an option: ");
//             String input = scanner.nextLine();

//             //validate
//             if (input.equals("1") ||
//                 input.equals("2") ||
//                 input.equals("0")){
//                     return input;
//                 }

//             System.out.println("Invalid Choice. Choose again.");
//         }
//     }

//     private Game pickGame(){ 

//         System.out.println("\nPick a Game to Play");
//         System.out.println("===================");
//         System.out.println("1.) Coin Flip (Minimum Wager: 20 | Multiplier: 1.5x)");
//         System.out.println("2.) Rock, Paper, Scissors (Minumum Wager: 10 | Multiplier: 2.0x)");
//         System.out.println("0.) Go back to Main Menu");
        
//         String input = chooseGameOption();
//         Game game = null;


//         switch (input){
//             case "1":
//                 game = games.get(0);
//                 break;
//             case "2":
//                 game = games.get(1);
//                 break;
//             case "0":
//                 return null;
//         }
//         return game;
//     }

//     private void createPlayer(){
//         System.out.println("\nCreating New Player ...");
//         System.out.println("=======================");
//         String name = "";

//         while(name.isEmpty()){
//             System.out.print("Enter player name: ");
//             name = scanner.nextLine();
//             if (name.isEmpty()){
//                 System.out.println("Player name cannot be empty.");
//             }
//         }

//         Player player = playerService.createPlayer(name);

//         System.out.println("Created Player: " + player.getName() + " (ID: " + player.getId() + " | " + player.getPoints() + " pts)");
//         currPlayer = player;
//     }

//     private void listPlayers(){
        
//         List<Player> players = playerService.getAllPlayers();
//         System.out.println("\nList of Current Available Players");
//         System.out.println("=================================");

//         if (players.isEmpty()){
//             System.out.println("[No players created yet...]");
//             return;
//         } else {

//             System.out.printf("%-5s %-20s %-10s%n", "ID", "NAME", "POINTS");
//             System.out.println("------------------------------------------");
//             for (Player p : players){
//                 System.out.printf("%-5d %-20s %-10d%n", p.getId(), p.getName(), p.getPoints());
//             }
//         }
//     }

//     private void logIn(){

//         List<Player> players = playerService.getAllPlayers();
//         if(players.isEmpty()){
//             System.out.println("\nNo available Players. Create a player first.");
//             return;
//         }

//         int id = -1;
//         boolean invalidID = true;

//         while (invalidID){
//             System.out.print("\nEnter the ID of the player you want to play as: ");
            
//             String input = scanner.nextLine();
//             try {
//                 id = Integer.parseInt(input);
//                 invalidID = false;
//             } catch (NumberFormatException e){
//                 System.err.println("Enter a valid ID.");
//             }
//         }

//         Player p = playerService.getPlayerById(id); //either returns the player or null;

//         if (p == null){
//             System.out.println("No Player found with ID " + id);
//             return;
//         }
        

        
            
//         currPlayer = p;
//         System.out.println("You are now logged in as: " + p.getName() + " (ID " + p.getId() + " | " + p.getPoints() + " pts)");
//         return;
            
        

        
//     }

//     private void enterContinue(){
//         System.err.println("\nPress Enter to return to the Main Menu.");
//         scanner.nextLine();
//     }    
 }