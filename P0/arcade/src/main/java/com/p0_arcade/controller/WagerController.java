package com.p0_arcade.controller;

import com.p0_arcade.util.InputHandler;
import com.p0_arcade.service.models.Game;
import com.p0_arcade.service.models.Player;
import com.p0_arcade.service.PlayerService;
import com.p0_arcade.repo.entities.PlayerEntity;

import java.util.Optional;

import com.p0_arcade.repo.entities.WagerEntity;
import com.p0_arcade.service.WagerService;
import com.p0_arcade.service.models.Wager;

public class WagerController {

    private final WagerService wagerService = new WagerService();
    private final PlayerService playerService = new PlayerService();

    public int addWager(Game game, Player player){
        int points;
        while (true){
            points = InputHandler.getIntInput("How many points would you like to wager? ");

            if (points < game.getMinWager()){
                System.out.println("Wager must be greater than or equal to minumum Wager amount.");
            }else if(points > player.getPoints()){
                System.out.println("You do not have enough points.");
                return 0;
            }else{
                break;
            }
        }

        WagerEntity wagerEntity = new WagerEntity();
        wagerEntity.setPlayerId(player.getId());
        wagerEntity.setGameId(game.getId());
        wagerEntity.setPoints(points);

        Integer newWagerId = wagerService.createEntity(wagerEntity);



        if (newWagerId == -1){
            System.out.println("Unable to submit Wager");
        } else {
            wagerEntity.setId(newWagerId); // Set the generated ID on the entity
            Optional<Wager> wager = wagerService.convertEntityToModel(wagerEntity);
            System.out.println("Submitted Wager: (ID " + wager.get().getId() + " | " + wager.get().getPoints() + " pts)");
            
            // Deduct points from player and save to database
            Optional<PlayerEntity> playerEntity = playerService.readEntityById(player.getId());
            if (playerEntity.isPresent()) {
                PlayerEntity pe = playerEntity.get();
                pe.setPoints(pe.getPoints() - points);
                playerService.updateEntity(pe);
                // Update the player model to reflect the change
                player.setPoints(pe.getPoints());
            }
        }

        return points;
        
    }
}
