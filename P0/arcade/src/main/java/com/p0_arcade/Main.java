package com.p0_arcade;

import java.util.*;

import com.p0_arcade.Classes.*;;

public class Main {

    private Scanner scanner = new Scanner(System.in);
    private final List<Player> players = new ArrayList<>();
    private Player currPlayer = null;
    private int nextId = 1;

    public static void main(String[] args) {
        new Main().run();   
    }

    private void run(){
        boolean running = true;

        while (running) {
            String choice = printMenu();

            switch (choice){
                case "1": //PICK A GAME
                    pickGame();
                    enterContinue();
                    break;
                case "2": // LOG IN
                    logIn();
                    enterContinue();
                    break;
                case "3": // CREATE NEW PLAYER
                    createPlayer();
                    enterContinue();
                    break;
                case "4": // LIST CURRENT PLAYERS
                    listPlayers();
                    enterContinue();
                    break;
                case "0": // EXIT GAME
                    running = false;
                    break;
            }
        }
    }

    private String printMenu(){
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
        return chooseMenuOption();
    }

    private String chooseMenuOption(){
        while (true) {
            System.out.print("\nChoose an option: ");
            String input = scanner.nextLine();

            //validate
            if (input.equals("1") ||
                input.equals("2") ||
                input.equals("3") ||
                input.equals("4") ||
                input.equals("0")) {
                    return input;
                }

            System.out.println("Invalid Choice. Choose again.");
        }
    }

    private String chooseGameOption(){
        while (true) {
            System.out.print("\nChoose an option: ");
            String input = scanner.nextLine();

            //validate
            if (input.equals("1") ||
                input.equals("2") ||
                input.equals("0")) {
                    return input;
                }

            System.out.println("Invalid Choice. Choose again.");
        }
    }

    private void pickGame() {
        System.out.println("\nPick a Game to Play");
        System.out.println("===================");
        System.out.println("1.) Coin Flip (Minimum Wager: 20 | Multiplier: 1.5x)");
        System.out.println("2.) Rock, Paper, Scissors (Minumum Wager: 10 | Multiplier: 2.0x)");
        System.out.println("0.) Go back to Main Menu");
        
        String input = chooseGameOption();


        switch (input) {
            case "1":
                System.out.println("COIN FLIP UNDER CONSTRUCTION");
                break;
            case "2":
                System.out.println("R.P.S. UNDER CONSTRUCTION");
                break;
            case "0":
                break;
        }
    }

    private void createPlayer() {
        System.out.println("\nCreating New Player ...");
        System.out.println("=======================");
        String name = "";

        while(name.isEmpty()){
            System.out.print("Enter player name: ");
            name = scanner.nextLine();
            if (name.isEmpty()){
                System.out.println("Player name cannot be empty.");
            }
        }

        Player player = new Player(name);

        player.setId(nextId);
        players.add(player);

        System.out.println("Created Player: " + name);
        currPlayer = player;
        nextId++;

    }

    private void listPlayers(){
        System.out.println("\nList of Current Available Players");
        System.out.println("=================================");

        if (players.isEmpty()){
            System.out.println("[No players created yet...]");
            return;
        } else {

            System.out.printf("%-5s %-20s %-10s%n", "ID", "NAME", "POINTS");
            System.out.println("------------------------------------------");
            for (Player p : players){
                System.out.printf("%-5d %-20s %-10d%n", p.getId(), p.getName(), p.getPoints());
            }
        }
    }

    private void logIn(){

        if(players.isEmpty()){
            System.out.println("\nNo available Players. Create a player first.");
            return;
        }

        int id = -1;
        boolean invalidID = true;

        while (invalidID){
            System.out.print("\nEnter the ID of the player you want to play as: ");
            
            String input = scanner.nextLine();
            try {
                id = Integer.parseInt(input);
                invalidID = false;
            } catch (NumberFormatException e){
                System.err.println("Enter a valid ID.");
            }
        }
        

        for (Player p : players){
            if (id == p.getId()){
                currPlayer = p;
                System.out.println("You are now logged in as: " + p.getName() + " (ID " + p.getId() + " | " + p.getPoints() + " pts)");
                return;
            }
        }

        System.out.println("No Player found with ID " + id);
    }

    private void enterContinue(){
        System.err.println("\nPress Enter to return to the Main Menu.");
        scanner.nextLine();
    }    
}