package com.p0_arcade.repo.DAO;

import java.sql.*;
import java.util.*;

import com.p0_arcade.database.DatabaseConfig;
import com.p0_arcade.repo.entities.PlayerEntity;


public class PlayerRepository implements DAOInterface<PlayerEntity>{

    private Connection conn = DatabaseConfig.getConnection();

    //Inserting Players into DB
    @Override
    public Integer insert(PlayerEntity entity) throws SQLException{
        String sql = "INSERT INTO players (name, points) VALUES (?, ?) RETURNING id";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getPoints());

            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return rs.getInt("id");
                }
            }
        }
        
        return null;
    }

    //Finding Players in DB by ID
    @Override
    public Optional<PlayerEntity> findById(Integer id) throws SQLException{
        String sql = "SELECT * FROM players WHERE id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)){
            
            ps.setInt(1, id);

            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    Integer playerId = rs.getInt("id");
                    String name = rs.getString("name");
                    Integer points = rs.getInt("points");

                    return Optional.of(new PlayerEntity(playerId, name, points));
                }
            }
        }

        return Optional.empty(); //return null is no player is found with provided ID
    }

    //Return whole Player Table
    @Override
    public List<PlayerEntity> findAll() throws SQLException{
        String sql = "SELECT * FROM players ORDER BY id";
        List<PlayerEntity> players = new ArrayList<>();

        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while(rs.next()){
                Integer playerId = rs.getInt("id");
                String name = rs.getString("name");
                Integer points = rs.getInt("points");

                players.add(new PlayerEntity(playerId, name, points));
            }
            
        }

        return players;
    }

    //Update Player's points in DB
    @Override
    public void update(PlayerEntity entity) throws SQLException{
        String sql = "UPDATE players SET points = ? WHERE id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, entity.getPoints());
            ps.setInt(2, entity.getId());

            Integer rows = ps.executeUpdate();

            if (rows == 0){
                throw new IllegalArgumentException("No player found with id " + entity.getId());
            }
        }
    }

    // DELETE
    @Override
    public void deleteById(Integer id) throws SQLException{
        String sql = "DELETE FROM players WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, id);

            Integer rows = ps.executeUpdate();

            if (rows == 0){
                throw new IllegalArgumentException("No player found with id " + id);
            }
        }
    }


}
