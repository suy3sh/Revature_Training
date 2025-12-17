package com.p0_arcade;


import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import com.p0_arcade.controller.PlayerController;
import com.p0_arcade.controller.GameController;

import com.p0_arcade.util.InputHandler;


 import com.p0_arcade.service.models.Player;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    private static final Scanner scanner = new Scanner(System.in);

    private static Player currPlayer = null;
    private static PlayerController playerController = new PlayerController();
    private static GameController gameController = new GameController();

    public static void main(String[] args){

        currPlayer = playerController.getCurrPlayer();
        
        Main arcadeApp = new Main();
        arcadeApp.run();
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

    private void run(){
        boolean runnning = true;

        while(runnning){

            printMenu(currPlayer);

            int choice = InputHandler.getIntInput("Choose an option: ");

            switch(choice){
                case 1:
                    log.info("Entering the Arcade ...");
                    if (currPlayer == null){
                        System.out.println("No Player is logged in! Log in or create a new Player to play.");
                    } else {
                        currPlayer = gameController.handleInput(currPlayer);
                    }
                    break;
                case 2:
                    playerController.logIn();
                    currPlayer = playerController.getCurrPlayer(); 
                    enterContinue();
                    break;
                case 3:
                    playerController.addPlayer();
                    currPlayer = playerController.getCurrPlayer(); //logs in the new player as soon as they are created
                    enterContinue();
                    break;
                case 4:
                    playerController.listAllPlayers();
                    enterContinue();
                    break;
                case 0:
                    log.info("Exiting the Arcade ...");
                    runnning = false;
                    break;
                default:
                    log.warn("Invalid menu option chosen: {}", choice);
                    System.out.println("Invalid choice. Please choose again.");
            }
        }
    }

    private void enterContinue(){
        System.err.println("\nPress Enter to return to the Main Menu.");
        scanner.nextLine();
    }    
 }