package com.p0_arcade.service;

import com.p0_arcade.repo.PlayerRepository;
import com.p0_arcade.classes.Player;

import java.sql.SQLException;
import java.util.List;

/* 
 *   Serice Layer for Player Operations
 *- hides SQLE Exceptions from PlayerRepo from rest of app
 * - contains business logic around Players
*/
public class PlayerService {

    private final PlayerRepository repo;

    public PlayerService(PlayerRepository repo){
        this.repo = repo;
    }


    //CREATE
    public Player createPlayer(String name){
        Player p = new Player(name);

        try {
            return repo.insert(p);
        }catch(SQLException e){
            System.out.println("Service Error: Could not create Player.");
            e.printStackTrace();
            return null;
        }
    }

    //READ
    public List<Player> getAllPlayers() {
        try {
            return repo.findAll();
        }catch(SQLException e){
            System.out.println("Service Error: Could not load players from Database.");
            e.printStackTrace();
            return null;
        }
    }

    public Player getPlayerById(int id){
        try{
            return repo.findById(id);
        }catch(SQLException e){
            System.out.println("Service Error: Could not look up Player with ID: " + id);
            e.printStackTrace();
            return null;
        }
    }

    //UPDATE
    public boolean updatePlayer(Player p){ //return true on success, false on failure
        try{
            repo.update(p);
            return true;
        }catch(SQLException e){
            System.out.println("Service Error: Could not update Player with ID: " + p.getId());
            e.printStackTrace();
            return false;
        }
    }

    public boolean updatePlayer(Player p, int delta){ //return true on success, false on failure, this function adjusts the player's points based on win or lose
        try {
            if (delta > 0) {
                p.addPoints(delta);
            } else if (delta < 0) {
                // delta is negative → spend |delta|
                p.spendPoints(-delta);
            } // delta == 0 → no change

            repo.update(p);
            return true;

        } catch (IllegalArgumentException e) { // from addPoints/spendPoints
            System.out.println("Service Error: Invalid point adjustment for player ID: " + p.getId());
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            System.out.println("Service Error: Could not persist point change for player ID: " + p.getId());
            e.printStackTrace();
            return false;
        }
    }
    
    //DELETE
}
