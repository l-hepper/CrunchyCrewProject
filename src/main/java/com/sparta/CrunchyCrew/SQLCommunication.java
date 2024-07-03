package com.sparta.CrunchyCrew;

import com.sparta.CrunchyCrew.Data.DatabaseConnection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class SQLCommunication {

    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());

    public void connect() {

        Properties properties = new Properties();
        try {
            properties.load(new FileReader("src/main/resources/mysql.properties"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        try (
                Connection connection = DriverManager.getConnection(
                        properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM northwind.customers");
        ) {
            while (resultSet.next()) {
                System.out.println(resultSet.getInt(1));
                System.out.println(resultSet.getString(2));
                System.out.println(resultSet.getString(3));
            }

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM northwind.customers WHERE CustomerName = ?");
            preparedStatement.setString(1, "Manish");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static DatabaseConnection connector = DatabaseConnection.getInstance();
    public static Connection connection = connector.getConnection();
    public static Statement statement = connector.getStatement();

    public ResultSet queryDatabase(Employee employee, String flag) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE ?");

        switch(flag.toLowerCase()) {
            case "create":
                return statement.executeQuery("INSERT INTO employees VALUES " +
                        "('" + employee.empId() + "', '" +
                        employee.prefix() + "', '" +
                        employee.firstName() + "', '" +
                        employee.middleInitial() + "', '" +
                        employee.lastName() + "', '" +
                        employee.gender() + "', '" +
                        employee.email() + "', '" +
                        employee.dob() + "', '" +
                        employee.dateOfJoining() + "', '" +
                        employee.salary() + "')");
            case "update":
                return statement.executeQuery("UPDATE employees SET prefix = '" +
                        employee.prefix() + "', first_name = '" +
                        employee.firstName() + "', middle_initial = '" +
                        employee.middleInitial() + "', last_name = '" +
                        employee.lastName() + "', gender = '" +
                        employee.gender() + "', email = '" +
                        employee.email() + "', dob = '" +
                        employee.dob() + "', doj = '" +
                        employee.dateOfJoining() + "', salary = '" +
                        employee.salary() + "')");
            case "delete":
                return statement.executeQuery("DELETE FROM employees WHERE empID = '" + employee.empId() + "'");
            case "prefix":
                preparedStatement.setString(1, "prefix = '" + employee.prefix() + "'");
                return preparedStatement.executeQuery();
            case "first name":
                preparedStatement.setString(1, "first_name = '" + employee.firstName() + "'");
                return preparedStatement.executeQuery();
            case "middle initial":
                preparedStatement.setString(1, "middle_initial = '" + employee.middleInitial() + "'");
                return preparedStatement.executeQuery();
            case "last name":
                preparedStatement.setString(1, "last_name = '" + employee.lastName() + "'");
                return preparedStatement.executeQuery();
            case "gender":
                preparedStatement.setString(1, "gender = '" + employee.gender() + "'");
                return preparedStatement.executeQuery();
            case "email":
                preparedStatement.setString(1, "email = '" + employee.email() + "'");
                return preparedStatement.executeQuery();
            case "dob":
                preparedStatement.setString(1, "dob = '" + employee.dob() + "'");
                return preparedStatement.executeQuery();
            case "doj":
                preparedStatement.setString(1, "doj = '" + employee.dateOfJoining() + "'");
                return preparedStatement.executeQuery();
            case "salary":
                preparedStatement.setString(1, "salary = '" + employee.salary() + "'");
                return preparedStatement.executeQuery();
            default:
                preparedStatement.setString(1, "empID = '" + employee.empId() + "'");
                return preparedStatement.executeQuery();
        }
    }
}
