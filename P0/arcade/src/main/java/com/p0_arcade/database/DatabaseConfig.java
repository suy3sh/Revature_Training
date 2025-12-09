package com.p0_arcade.database;

import java.io.IOException;
import java.io.InputStream;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

import java.util.Properties;

import com.p0_arcade.Main;

public class DatabaseConfig {

    private static Connection connection;

    static {
        Properties props = new Properties();

        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("db.properties")){
            if(input == null){
                System.err.println("db.properties not found!");
            }else{
                props.load(input);

                connection = DriverManager.getConnection(
                    props.getProperty("url"),
                    props.getProperty("username"),
                    props.getProperty("password")
                );
            }
        }catch(IOException e){
            throw new RuntimeException("failed to load db connection", e);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null){
            throw new RuntimeException("Connection failed to setup correctly");
        }
        return connection;
    }
}
