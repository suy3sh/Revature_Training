package com.postgres_example;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class Main {
   
    //JDBC URL Format: jdbc:postgresql://host:port/database
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";

    //FIND A WAY TO AVOID HARDCODING THESE
    private static final String USER = "postgres";
    private static final String PASS = "secret";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static void main(String[] args) {
        try(Connection conn = getConnection()){
            System.out.println("Connected to Postgres!");
            System.out.println("Database: " + conn.getMetaData().getDatabaseProductName());
            System.out.println("Version: " + conn.getMetaData().getDatabaseProductVersion());

        }catch(SQLException e){
            System.err.println("Connection Failed!");
            e.printStackTrace();
        }
        
    }
}