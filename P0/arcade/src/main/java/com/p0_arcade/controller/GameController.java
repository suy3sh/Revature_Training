package com.p0_arcade.controller;

import com.p0_arcade.util.InputHandler;
import com.p0_arcade.service.GameService;
import com.p0_arcade.service.models.Game;
import com.p0_arcade.service.models.Player;
import com.p0_arcade.repo.entities.WagerEntity;


import java.util.Scanner;


public class GameController {

    private GameService gameService = new GameService();
    private WagerController wagerController = new WagerController();
    private PlayerController playerController = new PlayerController();

    public int winLose;

    
    private static final Scanner scanner = new Scanner(System.in);

    public Player handleInput(Player currPlayer){
        boolean running = true;

        while(running){
            printMenu(currPlayer);
            int choice = InputHandler.getIntInput("Choose an option: ");
            switch(choice){
                case 1:
                    currPlayer = coinFlip(currPlayer);
                    System.out.println("Game result: " + (winLose > 0 ? "+" + winLose : winLose) + " points");
                    enterContinue();
                    break;
                case 2:
                    currPlayer = RPS(currPlayer);
                    System.out.println("Game result: " + (winLose > 0 ? "+" + winLose : winLose) + " points");
                    enterContinue();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid Choice.");
            }
        }

        return currPlayer;
    }

    public int getWinLose(){
        return this.winLose;
    }

    private void printMenu(Player currPlayer){
        System.out.println("\n==== Pick a Game to Play ====");
        System.out.println("Your current points: " + currPlayer.getPoints() + "pts");
        System.out.println("-----------------------------");
        System.out.println("1.) Coin Flip (Minimum Wager: 20 | Multiplier: 1.5x)");
        System.out.println("2.) Rock, Paper, Scissors (Minumum Wager: 10 | Multiplier: 2.0x)");
        System.out.println("0.) Go back to Main Menu");
    }

    private Player coinFlip(Player currPlayer){
        Game cf = gameService.getModelById(1).get();
        WagerEntity wager = new WagerEntity();
        char choice;    //the choice the the user chose
        int points;     //the amount of points the user chose to wager
        int winLose;    //the amount of the points the user either won or lose
        
        System.out.println("\n====== Welcome to CoinFlip ======");
        System.out.println(cf.getDescription());
        System.out.println("Minimum Wager: " + cf.getMinWager());
        System.out.println("Multiplier: " + cf.getMultiplier());    
        System.out.println("---------------------------------");

        points = wagerController.addWager(cf, currPlayer);
        
        wager.setPoints(points);


        System.out.println("\nPick a side of the Coin!");
        System.out.println("------------------------");
        System.out.println("H: Heads");
        System.out.println("T: Tails");

        do {
            choice = Character.toUpperCase(InputHandler.getCharInput("Choose either Heads or Tails (H/T): "));

            if (choice != 'H' && choice != 'T') {
                System.out.println("Invalid choice. Please enter 'H' or 'T'.");
            }

        } while (choice != 'H' && choice != 'T');

        System.out.println("Press Enter to Flip the coin!");
        scanner.nextLine();

        System.out.println("Flipping Coin...");
        try {
            Thread.sleep(1000); // dramatic pause
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); 
        }

        winLose = gameService.playCoinFlip(cf, wager, choice);

        // Update player points with winnings (winLose is already the winnings amount)
        currPlayer = playerController.updatePlayer(currPlayer, winLose);

        this.winLose = winLose;
        return currPlayer;
    }

    private Player RPS(Player currPlayer){
        Game cf = gameService.getModelById(2).get();
        WagerEntity wager = new WagerEntity();
        char choice;    //the choice the the user chose
        int points;     //the amount of points the user chose to wager
        int winLose;    //the amount of the points the user either won or lose
        
        System.out.println("\n====== Welcome to Rock, Paper, Scissors!======");
        System.out.println(cf.getDescription());
        System.out.println("Minimum Wager: " + cf.getMinWager());
        System.out.println("Multiplier: " + cf.getMultiplier());    
        System.out.println("---------------------------------");

        points = wagerController.addWager(cf, currPlayer);
        
        wager.setPoints(points);


        System.out.println("\nPick Either Rock, Paper or Scissors!");
        System.out.println("------------------------");
        System.out.println("R: Rock");
        System.out.println("P: Paper");
        System.out.println("S: Scissors");

        do {
            choice = Character.toUpperCase(InputHandler.getCharInput("Choose your hand (R/P/S): "));

            if (choice != 'R' && choice != 'P' && choice != 'S') {
                System.out.println("Invalid choice. Please enter 'R', 'P', or 'S'.");
            }
            
            
        } while (choice != 'R' && choice != 'P' && choice != 'S');

        System.out.println("Press Enter to Shoot");
        scanner.nextLine();

        System.out.println("Rock, Paper, Scissors...");
        try {
            Thread.sleep(1000); // dramatic pause
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); 
        }
        System.out.println("Shoot!");

        winLose = gameService.playRPS(cf, wager, choice);

        // Update player points with winnings (winLose is already the winnings amount)
        currPlayer = playerController.updatePlayer(currPlayer, winLose);

        this.winLose = winLose;
        return currPlayer;
    }

    private void enterContinue(){
        System.err.println("\nPress Enter to return to the Game Menu.");
        scanner.nextLine();
    }
}