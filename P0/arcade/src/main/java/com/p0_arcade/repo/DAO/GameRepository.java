package com.p0_arcade.repo.DAO;

import java.sql.*;
import java.util.*;

import com.p0_arcade.repo.entities.GameEntity;
import com.p0_arcade.database.DatabaseConfig;

public class GameRepository implements DAOInterface<GameEntity>{
    
    private Connection conn = DatabaseConfig.getConnection();


    // CREATE (you cannot insert a new game)
    @Override
    public Integer insert(GameEntity entity) throws SQLException{
        throw new UnsupportedOperationException("Games are pre-defined and cannot be created");
    }

    // READ BY ID
    @Override
    public Optional<GameEntity> findById(Integer id) throws SQLException{
        String sql = "SELECT * FROM games WHERE id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setInt(1, id);

            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    Integer gameId = rs.getInt("id");
                    String name = rs.getString("name");
                    String desc = rs.getString("description");
                    Integer minWager = rs.getInt("min_wager");
                    double multi = rs.getDouble("multiplier");

                    return Optional.of(new GameEntity(gameId, name, desc, minWager, multi));
                }
            }
        }

        return Optional.empty();
    }

    // READ ALL
    @Override
    public List<GameEntity> findAll() throws SQLException{
        String sql = "SELECT * FROM games ORDER BY id";
        List<GameEntity> games = new ArrayList<>();

        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while(rs.next()){

                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String desc = rs.getString("description");
                Integer minWager = rs.getInt("min_wager");
                double multi = rs.getDouble("multiplier");

                games.add(new GameEntity(id, name, desc, minWager, multi));
            }
        }

        return games;
    }

    // UPDATE (you can't update the games)
    @Override
    public void update(GameEntity entity) throws SQLException{
        throw new UnsupportedOperationException("Games are pre-defined and cannot be updated");
    }

    // DELETE (you can't delete the games)
    @Override
    public void deleteById(Integer id) throws SQLException{
        throw new UnsupportedOperationException("Games are pre-defined and cannot be deleted");
    }
}
