package com.sparta.crunchy_crew.data_parsing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

public class CsvReader {
    private static final Logger LOGGER = Logger.getLogger(EmployeeParser.class.getName());

    public static void readCsvFile() {
        int corruptEntryCounter = 0;
        String employeeEntry;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("src/main/resources/employees.csv"));
            while ((employeeEntry = fileReader.readLine()) != null) {
                corruptEntryCounter += EmployeeParser.parseEmployeeData(employeeEntry);
            }
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        LOGGER.info("Number of corrupt entries in csv file: " + corruptEntryCounter);
        System.out.println("Number of corrupt entries in csv file: " + corruptEntryCounter);
    }
}
