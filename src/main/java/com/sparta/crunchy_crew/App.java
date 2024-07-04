package com.sparta.CrunchyCrew;

import com.sparta.CrunchyCrew.Data.DatabaseConnection;
import com.sparta.CrunchyCrew.Interface.UserInterface;
import com.sparta.CrunchyCrew.logger.CrunchyLogger;
import java.sql.Connection;
import com.sparta.crunchy_crew.data_parsing.CsvReader;
import com.sparta.crunchy_crew.logger.CrunchyLogger;

import javax.xml.crypto.Data;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        CrunchyLogger.configure();
        CsvReader.readCsvFile();
        DatabaseConnection.getInstance().getConnection();

        UserInterface userInterface = new UserInterface();
        userInterface.start();
    }
}
