package com.p0_arcade.database;

import java.io.IOException;
import java.io.InputStream;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

import java.util.Properties;


import com.p0_arcade.Main;

public class DatabaseConfig {

    private static String url;
    private static String pass;
    private static String user;

    static {
        Properties props = new Properties();

        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("db.properties")){
            if(input == null){
                System.err.println("db.properties not found!");
            }else{
                props.load(input);
                url = props.getProperty("url");
                user = props.getProperty("username");
                pass = props.getProperty("password");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}
