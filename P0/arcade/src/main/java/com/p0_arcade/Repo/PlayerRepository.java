package com.p0_arcade.repo;

import java.sql.*;
import java.util.*;

import com.p0_arcade.classes.Player;
import com.p0_arcade.database.DatabaseConfig;


public class PlayerRepository implements DOAInterface<Player>{

    private Connection conn = DatabaseConfig.getConnection();

    public PlayerRepository(){
        
    }

    //Inserting Players into DB
    @Override
    public Player insert(Player p) throws SQLException{
        String sql = "INSERT INTO players (name, points) VALUES (?, ?) RETURNING id";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, p.getName());
            ps.setInt(2, p.getPoints());

            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    int generatedId = rs.getInt(1);
                    p.setId(generatedId);
                }
            }
        }catch(SQLException e){
            throw new RuntimeException("Failed to insert Player to DB",e);
        }

        return p;
    }

    //Finding Players in DB by ID
    @Override
    public Player findById(int id) throws SQLException{
        String sql = "SELECT * FROM players WHERE id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setInt(1, id);

            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    int playerId = rs.getInt("id");
                    String name = rs.getString("name");
                    int points = rs.getInt("points");
                    return new Player(playerId, name, points);
                }
            }
            

        }catch(SQLException e){
            throw new RuntimeException("Failed to find Player in DB",e);
        }

        return null; //return null is no player is found with provided ID
    }

    //Return whole Player Table
    @Override
    public List<Player> findAll() throws SQLException{
        String sql = "SELECT * FROM players ORDER BY id";
        List<Player> players = new ArrayList<>();

        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
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

    //Update Player's points in DB
    public void update(Player p) throws SQLException{
        String sql = "UPDATE players SET points = ? WHERE id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, p.getPoints());
            ps.setInt(2, p.getId());

            int rows = ps.executeUpdate();

            if (rows == 0){
                throw new IllegalArgumentException("No player found with id " + p.getId() + " (INSIDE PLAYERREPO.JAVA");
            }

        }catch(SQLException e){
            throw new RuntimeException("Failed to update Player with ID " + p.getId(), e);
        }
    }

    // DELETE
    public void deleteById(int id) throws SQLException{
        String sql = "DELETE * FROM players WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows == 0){
                System.out.println("No player found with ID: " + id);
            }
        }catch(SQLException e){
            throw new RuntimeException("Failed to delete Player with ID " + id, e);
        }
    }


}
