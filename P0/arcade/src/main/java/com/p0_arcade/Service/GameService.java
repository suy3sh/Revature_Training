package com.p0_arcade.service;

import java.sql.SQLException;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.p0_arcade.classes.*;
import com.p0_arcade.repo.GameRepository;

public class GameService {

    private final GameRepository gameRepo;
    private final PlayerService playerService;
    private final Scanner scanner;
    private int nextWagerId = 1;
    private final Random random = new Random();

    public GameService(GameRepository gameRepo, PlayerService playerService, Scanner scanner) {
        this.gameRepo = gameRepo;
        this.playerService = playerService;
        this.scanner = scanner;
    }

    public void play(Player p, Game g){

        if (g == null){
            return;
        }

        System.out.println("\n====== " + g.getName() + " ======");
        System.out.println(g.getDescription());
        System.err.println("Minumum Wager: " + g.getMinWager() + " pts");
        System.err.println("Win Multiplier: " + g.getMultiplier() + "x");
        System.err.println("====================");

        System.out.println("You currently have " + p.getPoints() + " points.");

        if (p.getPoints() < g.getMinWager()){
            System.out.println("You do not have enough points to play this game!");
            return;
        }

        //Ask for wager amount
        int wager = getWagerAmount(p, g);

        if (wager == 0){
            return; //User cancelled game
        }

    
        //Create Wager and deduct that amount from Player points
        Wager w = new Wager(nextWagerId, p.getId(), g.getId(), wager);
        nextWagerId++;

        //Run game logic based on game selected
        if (g.getId() == 1){
            coinFlip(p, w);
        } else if (g.getId() == 2){
            rockPaperScissors(p, w);
        }
        //Apply win/loss to Player points based on game outcome

    }

    private int getWagerAmount(Player p, Game g){

        while(true){
            System.out.println("Enter you wager amount (Enter 0 to cancel game): ");
            String input = scanner.nextLine();

            int wager;

            try {
                wager = Integer.parseInt(input);
            } catch (NumberFormatException e){
                System.err.println("Enter a valid wager amount.");
                continue;
            }

            if (wager == 0){
                System.out.println("Cancelling Game...");
                return 0;
            }

            if (wager < g.getMinWager()){
                System.err.println("Wager amount must be at least " + g.getMinWager() + " points.");
                continue;
            }

            if (wager > p.getPoints()){
                System.out.println("You do not have enough points to make that wager. Enter a smaller wager.");
                continue;
            }
            
            System.out.println("You are wagering " + wager + " points.");
            System.out.println("Remaining Points: " + (p.getPoints() - wager) + " pts");

            return wager;
        }
    }

    private void coinFlip(Player p, Wager w){
        System.out.println("COIN FLIP IS UNDER CONSTRUCTION!!! (INSIDE GAMESERVICE.JAVA) ");
    }

    private void rockPaperScissors(Player p, Wager w){
        System.out.println("RSP IS UNDER CONSTRUCTION!!! (INSIDE GAMESERVICE.JAVA)");
    }

}
