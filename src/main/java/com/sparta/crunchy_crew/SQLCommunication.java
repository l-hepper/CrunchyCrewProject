package com.sparta.crunchy_crew;

import com.sparta.crunchy_crew.data.DatabaseConnection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;

public class SQLCommunication {

    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());

    public static DatabaseConnection connector = DatabaseConnection.getInstance();
    public static Connection connection = connector.getConnection();
    public static Statement statement = connector.getStatement();

    private PreparedStatement preparedStatement = connector.getPreparedStatement();

    public void addToBatchStatement(Employee employee) {
        try {
            preparedStatement.setString(1, employee.empId());
            preparedStatement.setString(2, employee.prefix());
            preparedStatement.setString(3, employee.firstName());
            preparedStatement.setString(4, employee.middleInitial());
            preparedStatement.setString(5, employee.lastName());
            preparedStatement.setString(6, employee.gender());
            preparedStatement.setString(7, employee.email());
            preparedStatement.setString(8, String.valueOf(employee.dob()));
            preparedStatement.setString(9, String.valueOf(employee.dateOfJoining()));
            preparedStatement.setString(10, employee.salary());
            preparedStatement.addBatch();
        } catch (SQLException e) {
            logger.severe("SQLException encountered when attempting to add a batch entry to employee database");
            e.printStackTrace();
        }
    }

    public void createRecord() {
        logger.info("Entered create record in SQLCommunication");

        try {
            preparedStatement.executeBatch();
            preparedStatement.clearParameters();
            logger.info("Successfully executed and cleared batch statement");
        } catch (SQLException e) {
            logger.severe("SQLException encountered when attempting to add a batch entry to employee database");
            e.printStackTrace();
        }
    }

    public void updateRecord(Employee employee) {
        logger.info("Entered update record method in SQLCommunication");
        try {
            statement.executeUpdate("UPDATE employees SET prefix = '" +
            employee.prefix() + "', first_name = '" +
            employee.firstName() + "', middle_initial = '" +
            employee.middleInitial() + "', last_name = '" +
            employee.lastName() + "', gender = '" +
            employee.gender() + "', email = '" +
            employee.email() + "', date_of_birth = '" +
            employee.dob() + "', date_of_joining = '" +
            employee.dateOfJoining() + "', salary = '" +
            employee.salary() + "' WHERE id = " +
            employee.empId());
            logger.info("Successfully updated employee record for employee with employee ID: " + employee.empId());
        } catch (SQLException e) {
            logger.severe("SQLException encountered when attempting to update employee entry");
            e.printStackTrace();
        }
    }

    public void deleteRecord(Employee employee) {
        try {
            statement.executeUpdate("DELETE FROM employees WHERE id = '" + employee.empId() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getEmployeeByID(String employeeID) {
        logger.info("Entered get employee by ID method in SQLCommunication");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE id = ?");
            preparedStatement.setString(1, employeeID);
            logger.info("Successfully returned available employee");
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.severe("SQLException encountered when attempting to get employee by ID");
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getEmployeesByPrefix(String prefix) {
        logger.info("Entered get employee by prefix method in SQLCommunication");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE prefix = ?");
            preparedStatement.setString(1, prefix);
            logger.info("Successfully returned available employees");
            return preparedStatement.executeQuery();
        } catch(SQLException e) {
            logger.severe("SQLException encountered when attempting to get employee");
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getEmployeesByFirstName(String firstName) {
        logger.info("Entered get employee by first name method in SQLCommunication");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE first_name LIKE ?");
            if (firstName.startsWith("*")) {
                firstName = firstName.replace("*", "");
                preparedStatement.setString(1, "%" + firstName + "%");
            } else {
                preparedStatement.setString(1, firstName);
            }
            logger.info("Successfully returned available employees");
            return preparedStatement.executeQuery();
        } catch(SQLException e) {
            logger.severe("SQLException encountered when attempting to get employee");
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getEmployeesByMiddleInitial(String middleInitial) {
        logger.info("Entered get employee by middle initial method in SQLCommunication");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE middle_initial = ?");
            preparedStatement.setString(1, middleInitial);
            logger.info("Successfully returned available employees");
            return preparedStatement.executeQuery();
        } catch(SQLException e) {
            logger.severe("SQLException encountered when attempting to get employee");
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getEmployeesByLastName(String lastName) {
        logger.info("Entered get employee by last name method in SQLCommunication");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE last_name LIKE ?");
            if (lastName.startsWith("*")) {
                lastName = lastName.replace("*", "");
                preparedStatement.setString(1, "%" + lastName + "%");
            } else {
                preparedStatement.setString(1, lastName);
            }
            logger.info("Successfully returned available employees");
            return preparedStatement.executeQuery();
        } catch(SQLException e) {
            logger.severe("SQLException encountered when attempting to get employee");
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getEmployeesByGender(String gender) {
        logger.info("Entered get employee by gender method in SQLCommunication");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE gender = ?");
            preparedStatement.setString(1, gender);
            logger.info("Successfully returned available employees");
            return preparedStatement.executeQuery();
        } catch(SQLException e) {
            logger.severe("SQLException encountered when attempting to get employee");
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getEmployeesByEmail(String email) {
        logger.info("Entered get employee by email method in SQLCommunication");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE email = ?");
            preparedStatement.setString(1, email);
            logger.info("Successfully returned available employees");
            return preparedStatement.executeQuery();
        } catch(SQLException e) {
            logger.severe("SQLException encountered when attempting to get employee");
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getEmployeesByDob(String dob) {
        logger.info("Entered get employee by date of birth method in SQLCommunication");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE date_of_birth = ?");
            preparedStatement.setString(1, dob);
            logger.info("Successfully returned available employees");
            return preparedStatement.executeQuery();
        } catch(SQLException e) {
            logger.severe("SQLException encountered when attempting to get employee");
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getEmployeesByDoj(String doj) {
        logger.info("Entered get employee by date of joining method in SQLCommunication");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE date_of_joining = ?");
            preparedStatement.setString(1, doj);
            logger.info("Successfully returned available employees");
            return preparedStatement.executeQuery();
        } catch(SQLException e) {
            logger.severe("SQLException encountered when attempting to get employee");
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getEmployeesBySalary(String salary) {
        logger.info("Entered get employee by salary method in SQLCommunication");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE salary = ?");
            preparedStatement.setString(1, salary);
            logger.info("Successfully returned available employees");
            return preparedStatement.executeQuery();
        } catch(SQLException e) {
            logger.severe("SQLException encountered when attempting to get employee");
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getEmployeesBySalaryRange(String salaryLowEnd, String salaryHighEnd) {
        logger.info("Entered getEmployeeBySalaryRange method in SQLCommunication");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE salary BETWEEN ? AND ?");
            preparedStatement.setInt(1, Integer.parseInt(salaryLowEnd));
            preparedStatement.setInt(2, Integer.parseInt(salaryHighEnd));
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            logger.severe("SQLException encountered when attempting to query employees by salary range.");
            e.printStackTrace();
        }

        return null;
    }
}
