package com.sparta.crunchy_crew.data_parsing;

import com.sparta.crunchy_crew.Employee;
import com.sparta.crunchy_crew.EmployeeDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

public class CsvReader {
    private static final Logger LOGGER = Logger.getLogger(EmployeeParser.class.getName());
    private static int corruptEntryCounter = 0;

    public static void readCsvFile(EmployeeDAO dataAccessObject) {
        corruptEntryCounter = 0;
        String employeeEntry;
        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("src/main/resources/employees.csv"));
            while ((employeeEntry = fileReader.readLine()) != null) {
                Optional<Employee> entry = EmployeeParser.parseEmployeeData(employeeEntry);
                if (entry.isPresent()) {
                    dataAccessObject.createEmployee(entry.get());
                } else {
                    corruptEntryCounter++;
                }
            }
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        LOGGER.info("Number of corrupt entries in csv file: " + corruptEntryCounter);
    }

    public static int getCorruptEntryCount() {
        return corruptEntryCounter;
    }
}
