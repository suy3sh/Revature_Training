package com.p0_arcade.repo;


import java.sql.*;
import java.util.*;

import com.p0_arcade.classes.Player;
import com.p0_arcade.database.DatabaseConfig;


public class PlayerRepository {
    
    public PlayerRepository(){
        
    }

    //Return whole Player Table
    public List<Player> findAll(){
        String sql = "SELECT * FROM players ORDER BY id";
        List<Player> players = new ArrayList<>();

        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            
            while(rs.next()){
                int playerId = rs.getInt("id");
                String name = rs.getString("name");
                int points = rs.getInt("points");

                players.add(new Player(playerId, name, points));
            }
            
        }catch(SQLException e){
            throw new RuntimeException("Failed to load Players");
        }

        return players;
    }

    //Finding Players in DB by ID
    public Player findById(int id){
        String sql = "SELECT * FROM players WHERE id = ?";

        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int playerId = rs.getInt("id");
                String name = rs.getString("name");
                int points = rs.getInt("points");
                return new Player(playerId, name, points);
            }

        }catch(SQLException e){
            throw new RuntimeException("Failed to find Player in DB",e);
        }

        return null; //return null is no player is found with provided ID
    }

    //Inserting Players into DB
    public Player insert(Player p){
        String sql = "INSERT INTO players (name, points) VALUES (?, ?) RETURNING id";

        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, p.getName());
            ps.setInt(2, p.getPoints());

            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int generatedId = rs.getInt(1);
                p.setId(generatedId);
            }


        }catch(SQLException e){
            throw new RuntimeException("Failed to insert Player to DB",e);
        }

        return p;
    }

    //Update Player's points in DB
    public void update(Player p, int points){
        String sql = "UPDATE players SET name = ?, points = ? WHERE id = ?";

        try(Connection conn = DatabaseConfig.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setString(1, p.getName());
            ps.setInt(2, points);
            ps.setInt(3, p.getId());

            int rows = ps.executeUpdate();
            if (rows == 0){
                throw new IllegalArgumentException("No player found with id " + p.getId() + " (INSIDE PLAYERREPO.JAVA");
            }
        }catch(SQLException e){
            throw new RuntimeException("Failed to update Player with ID " + p.getId(), e);
        }
    }
}
