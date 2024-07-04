package com.sparta.crunchy_crew;

import com.sparta.crunchy_crew.data.DatabaseConnection;
import com.sparta.crunchy_crew.Interface.UserInterface;
import com.sparta.crunchy_crew.logger.CrunchyLogger;
import com.sparta.crunchy_crew.data_parsing.CsvReader;

import java.util.logging.Logger;

public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        CrunchyLogger.configure();
        DatabaseConnection.getInstance().getConnection();

        EmployeeDAO employeeDAO = new EmployeeDAO();

//        uncomment to load in data from CSV
//        CsvReader.readCsvFile(employeeDAO);

        UserInterface userInterface = new UserInterface();
        userInterface.start();
    }
}
