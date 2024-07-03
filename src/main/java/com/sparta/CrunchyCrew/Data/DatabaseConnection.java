package com.sparta.CrunchyCrew.Data;

import com.sparta.CrunchyCrew.App;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DatabaseConnection {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() {
        
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees", "root", "ratter123");
            this.connection.getCatalog();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }






}