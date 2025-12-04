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

        while (running){
            printMenu();

            String choice = scanner.nextLine();

            switch (choice){
                case "1":
                    System.out.println("UNDER CONSTRUCTION");
                    enterContinue();
                    break;
                case "2":
                    System.out.println("UNDER CONSTRUCTION");
                    enterContinue();
                    break;
                case "3":
                    createPlayer();
                    enterContinue();
                    break;
                case "4":
                    listPlayers();
                    enterContinue();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Choice. Choose again.");
                    enterContinue();
            }

        }
    }

    private void printMenu(){
        System.out.println("========== Welcome to the Arcade! ==========");
        System.out.println(
            "Current Player: " + (currPlayer == null ? "[none]" : 
            currPlayer.getName() + "(ID " + currPlayer.getId() + ", " + currPlayer.getPoints() + " points)")
        );
        System.out.println("==========================================");
        System.out.println("1.) Enter the Arcade");
        System.out.println("2.) Log in as Existing Player");
        System.out.println("3.) Create a New Player");
        System.out.println("4.) List Players");
        System.out.println("0.) Exit the Arcade");
    }

    private void createPlayer() {
        System.out.println("\nCreating New Player ...");
        System.out.println("=======================");
        String name = "";

        while(name.isEmpty()){
            System.out.println("Enter player name: ");
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
            for (Player player : players){
                System.out.printf("%-5d %-20s %-10d%n", player.getId(), player.getName(), player.getPoints());
            }
        }
    } 

    private void enterContinue(){
        System.err.println("\nPress Enter to return to the Main Menu.");
        scanner.nextLine();
    }    
}