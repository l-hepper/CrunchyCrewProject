package com.sparta.CrunchyCrew.Data;

import com.sparta.CrunchyCrew.App;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class DatabaseConnection {

    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());

    private static DatabaseConnection instance = new DatabaseConnection();
    private Connection connection;

    private DatabaseConnection() {

        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/main/resources/mysql.properties"));
            logger.fine("Properties file loaded successfully");
        } catch (IOException e) {
            logger.severe("Properties file failed to load successfuly");
            throw new RuntimeException(e);
        }

        try {
            this.connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password"));
            logger.info("Database connection successfully established");
        } catch (SQLException e) {
            e.printStackTrace();
            logger.severe("Database connection unsuccessful");
        }
    }

    public static DatabaseConnection getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}