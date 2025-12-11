package com.p0_arcade.repo.DAO;

import java.sql.*;
import java.util.*;

import com.p0_arcade.repo.entities.WagerEntity;
import com.p0_arcade.util.DatabaseConfig;

public class WagerRepository implements DAOInterface<WagerEntity>{

    private Connection conn = DatabaseConfig.getConnection();

    //CREATE
    @Override
    public Integer insert(WagerEntity entity) throws SQLException{
        String sql = "INSERT INTO wagers (player_id, game_id, points) VALUES (?, ?, ?) RETURNING id";

        try(PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, entity.getPlayerId());
            ps.setInt(2, entity.getGameId());
            ps.setInt(3, entity.getPoints());

            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return rs.getInt("id");
                }
            }
        }

        return null;
    }

    //READ BY ID
    @Override
    public Optional<WagerEntity> findById(Integer id) throws SQLException{
        String sql = "SELECT * FROM wagers WHERE id = ?";

        try(PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, id);

            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    Integer wagerId = rs.getInt("id");
                    Integer playerId = rs.getInt("player_id");
                    Integer gameId = rs.getInt("game_id");
                    Integer points = rs.getInt("points");

                    return Optional.of(new WagerEntity(wagerId, playerId, gameId, points));
                }
            }
        }

        return Optional.empty();
    }

    //READ ALL
    @Override
    public List<WagerEntity> findAll() throws SQLException{
        String sql = "SELECT * FROM wagers ORDER BY id";
        List<WagerEntity> wagers = new ArrayList<>();

        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while(rs.next()){
                Integer wagerId = rs.getInt("id");
                Integer playerId = rs.getInt("player_id");
                Integer gameId = rs.getInt("game_id");
                Integer points = rs.getInt("points");

                wagers.add(new WagerEntity(wagerId, playerId, gameId, points));
            }
        }

        return wagers;
    }

    //UPDATE
    @Override
    public void update(WagerEntity entity) throws SQLException{
        throw new UnsupportedOperationException("Wagers are pre-defined and cannot be updated");
    }

    //DELETE
    @Override
    public void deleteById(Integer id) throws SQLException{
        throw new UnsupportedOperationException("Wagers are pre-defined and cannot be deleted");
    }
}
