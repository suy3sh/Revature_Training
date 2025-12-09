package com.p0_arcade.repo;

import java.sql.*;
import java.util.*;

import com.p0_arcade.classes.Game;
import com.p0_arcade.database.DatabaseConfig;

public class GameRepository implements DOAInterface<Game>{
    
    private Connection conn = DatabaseConfig.getConnection();

    public GameRepository(){
        
    }

    // CREATE (you cannot insert a new game)
    @Override
    public Game insert(Game entity) throws SQLException{
        throw new UnsupportedOperationException("Games are pre-defined and cannot be created");
    }

    // READ BY ID
    @Override
    public Game findById(int id) throws SQLException{
        String sql = "SELECT * FROM games WHERE id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setInt(1, id);

            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    int gameId = rs.getInt("id");
                    String name = rs.getString("name");
                    String desc = rs.getString("description");
                    int minWager = rs.getInt("min_wager");
                    double multi = rs.getDouble("multiplier");

                    return new Game(gameId, name, desc, minWager, multi);
                }
            }
        }catch(SQLException e){
            throw new RuntimeException("Failed to find Game in DB", e);
        }

        return null;
    }

    // READ ALL
    @Override
    public List<Game> findAll() throws SQLException{
        String sql = "SELECT * FROM games ORDER BY id";
        List<Game> games = new ArrayList<>();

        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while(rs.next()){

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String desc = rs.getString("description");
                int minWager = rs.getInt("min_wager");
                double multi = rs.getDouble("multiplier");

                games.add(new Game(id, name, desc, minWager, multi));
            }
        }catch(SQLException e){
            throw new RuntimeException("Failed to load Games");
        }

        return games;
    }

    // UPDATE (you can't update the games)
    @Override
    public void update(Game entity) throws SQLException{
        throw new UnsupportedOperationException("Games are pre-defined and cannot be updated");
    }

    // DELETE (you can't delete the games)
    @Override
    public void deleteById(int id) throws SQLException{
        throw new UnsupportedOperationException("Games are pre-defined and cannot be deleted");
    }
}
