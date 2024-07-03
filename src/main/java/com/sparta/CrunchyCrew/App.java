package com.sparta.CrunchyCrew;

import com.sparta.CrunchyCrew.Data.DatabaseConnection;
import com.sparta.CrunchyCrew.logger.CrunchyLogger;
import java.sql.Connection;

import javax.xml.crypto.Data;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) throws SQLException {
        CrunchyLogger.configure();
        Connection conn = DatabaseConnection.getInstance().getConnection();
        System.out.println(conn.getCatalog());
    }
}
