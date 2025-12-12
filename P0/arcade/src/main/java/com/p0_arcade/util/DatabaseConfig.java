package com.p0_arcade.util;

import java.io.IOException;
import java.io.InputStream;

import java.sql.DriverManager;
import java.sql.Connection;

import java.util.Properties;

import com.p0_arcade.Main;

public class DatabaseConfig {

    private static Connection connection;

    static {

        if (connection == null){
            Properties props = new Properties();

            try (InputStream input = Main.class.getClassLoader().getResourceAsStream("db.properties")){
                if(input == null){
                    throw new Exception("Unable to find database.properties");
                }else{
                    props.load(input);
                }

                Class.forName(props.getProperty("driver"));

                connection = DriverManager.getConnection(
                        props.getProperty("url"),
                        props.getProperty("username"),
                        props.getProperty("password")
                );

            }catch(IOException e){
                throw new RuntimeException("failed to load db connection", e);
            }catch(Exception e){
                throw new RuntimeException(e);
            }
        }
    }

    public static Connection getConnection() throws RuntimeException {
        if (connection == null){
            throw new RuntimeException("Connection failed to setup correctly");
        }
        return connection;
    }
}