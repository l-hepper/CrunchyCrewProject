package com.sparta.crunchy_crew;

import com.sparta.crunchy_crew.data.DatabaseConnection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.logging.Logger;

public class SQLCommunication {

    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());

    public static DatabaseConnection connector = DatabaseConnection.getInstance();
    public static Connection connection = connector.getConnection();
    public static Statement statement = connector.getStatement();

    public void createRecord(Employee employee) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employees VALUES " +
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

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateRecord(Employee employee) {
        try {
            statement.executeQuery("UPDATE employees SET prefix = '" +
            employee.prefix() + "', first_name = '" +
            employee.firstName() + "', middle_initial = '" +
            employee.middleInitial() + "', last_name = '" +
            employee.lastName() + "', gender = '" +
            employee.gender() + "', email = '" +
            employee.email() + "', dob = '" +
            employee.dob() + "', doj = '" +
            employee.dateOfJoining() + "', salary = '" +
            employee.salary() + "' WHERE empID = " +
            employee.empId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRecord(Employee employee) {
        try {
            statement.executeUpdate("DELETE FROM employees WHERE ID = '" + employee.empId() + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getEmployeeByID(String employeeID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE ID = ?");
            preparedStatement.setString(1, employeeID);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getEmployeesByPrefix(String prefix) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE prefix = ?");
            preparedStatement.setString(1, "'" + prefix + "'");
            return preparedStatement.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getEmployeesByFirstName(String firstName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE firstName = ?");
            preparedStatement.setString(1, "'" + firstName + "'");
            return preparedStatement.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getEmployeesByMiddleInitial(String middleInitial) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE middleInitial = ?");
            preparedStatement.setString(1, "'" + middleInitial + "'");
            return preparedStatement.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getEmployeesByLastName(String lastName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE lastName = ?");
            preparedStatement.setString(1, "'" + lastName + "'");
            return preparedStatement.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getEmployeesByGender(String gender) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE gender = ?");
            preparedStatement.setString(1, "'" + gender + "'");
            return preparedStatement.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getEmployeesByEmail(String email) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE email = ?");
            preparedStatement.setString(1, "'" + email + "'");
            return preparedStatement.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getEmployeesByDob(String dob) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE dob = ?");
            preparedStatement.setString(1, "'" + dob + "'");
            return preparedStatement.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getEmployeesByDoj(String doj) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE doj = ?");
            preparedStatement.setString(1, "'" + doj + "'");
            return preparedStatement.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getEmployeesBySalary(String salary) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE salary = ?");
            preparedStatement.setString(1, "'" + salary + "'");
            return preparedStatement.executeQuery();
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Employee packageEmployeeObject(ResultSet set) throws SQLException {
        Object[] array = new Object[10];
        set.next();
        Employee employee = new Employee(
                set.getString(1),
                set.getString(2),
                set.getString(3),
                set.getString(4),
                set.getString(5),
                set.getString(6),
                set.getString(7),
                LocalDate.parse(set.getDate(8).toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse(set.getDate(9).toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                set.getString(10)
        );

        return employee;
    }
}
