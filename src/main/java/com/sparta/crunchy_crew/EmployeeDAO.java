package com.sparta.crunchy_crew;

import com.sparta.crunchy_crew.data.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Logger;

public class EmployeeDAO {

    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());

    SQLCommunication sqlCommunication = new SQLCommunication();

    public void createEmployee(Employee employee) {
        sqlCommunication.createRecord(employee);
    }

    public ArrayList<Employee> getEmployee(String flag, String value) {
        logger.info("Entered get employee method in DAO");
        try {
            switch (flag.toLowerCase()) {
                case "prefix":
                    logger.info("Returned all available employees with prefix: " + value);
                    return packagesMultipleEmployeeObjects(sqlCommunication.getEmployeesByPrefix(value));
                case "first name":
                    logger.info("Returned all available employees with first name: " + value);
                    return packagesMultipleEmployeeObjects(sqlCommunication.getEmployeesByFirstName(value));
                case "middle initial":
                    logger.info("Returned all available employees with middle initial: " + value);
                    return packagesMultipleEmployeeObjects(sqlCommunication.getEmployeesByMiddleInitial(value));
                case "last name":
                    logger.info("Returned all available employees with last name: " + value);
                    return packagesMultipleEmployeeObjects(sqlCommunication.getEmployeesByLastName(value));
                case "gender":
                    logger.info("Returned all available employees with gender: " + value);
                    return packagesMultipleEmployeeObjects(sqlCommunication.getEmployeesByGender(value));
                case "email":
                    logger.info("Returned all available employees with email: " + value);
                    return packagesMultipleEmployeeObjects(sqlCommunication.getEmployeesByEmail(value));
                case "dob":
                    logger.info("Returned all available employees with date of birth: " + value);
                    return packagesMultipleEmployeeObjects(sqlCommunication.getEmployeesByDob(value));
                case "doj":
                    logger.info("Returned all available employees with date of joining: " + value);
                    return packagesMultipleEmployeeObjects(sqlCommunication.getEmployeesByDoj(value));
                case "salary":
                    logger.info("Returned all available employees with salary: " + value);
                    return packagesMultipleEmployeeObjects(sqlCommunication.getEmployeesBySalary(value));
                default:
                    logger.info("Returned all available employees with employee ID: " + value);
                    return packagesMultipleEmployeeObjects(sqlCommunication.getEmployeeByID(value));
            }
        } catch (SQLException e) {
            logger.severe("SQLException encountered when attempting to get employee");
            return null;
        }

    }

    public void updateEmployee(String employeeID, String flag, String newValue) {
        logger.info("Entered update employee method in DAO");
        Employee employee = null;
        try {
            ArrayList<Employee> employeeList = packagesMultipleEmployeeObjects(sqlCommunication.getEmployeeByID(employeeID));
            employee = employeeList.getFirst();
            logger.info("Successfully obtained employee for updating");
            Employee newEmployee = null;
            switch (flag) {
                case "prefix":
                    logger.info("Updated prefix of employee with ID: " + employeeID + " from: " + employee.prefix() + " to: " + newValue);
                    newEmployee = new Employee(employee.empId(),
                            newValue,
                            employee.firstName(),
                            employee.middleInitial(),
                            employee.lastName(),
                            employee.gender(),
                            employee.email(),
                            employee.dob(),
                            employee.dateOfJoining(),
                            employee.salary());
                    sqlCommunication.updateRecord(newEmployee);
                    break;
                case "first name":
                    logger.info("Updated first name of employee with ID: " + employeeID + " from: " + employee.firstName() + " to: " + newValue);
                    newEmployee = new Employee(employee.empId(),
                            employee.prefix(),
                            newValue,
                            employee.middleInitial(),
                            employee.lastName(),
                            employee.gender(),
                            employee.email(),
                            employee.dob(),
                            employee.dateOfJoining(),
                            employee.salary());
                    sqlCommunication.updateRecord(newEmployee);
                    break;
                case "middle name":
                    logger.info("Updated middle name of employee with ID: " + employeeID + " from: " + employee.middleInitial() + " to: " + newValue);
                    newEmployee = new Employee(employee.empId(),
                            employee.prefix(),
                            employee.firstName(),
                            newValue,
                            employee.lastName(),
                            employee.gender(),
                            employee.email(),
                            employee.dob(),
                            employee.dateOfJoining(),
                            employee.salary());
                    sqlCommunication.updateRecord(newEmployee);
                    break;
                case "last name":
                    logger.info("Updated last name of employee with ID: " + employeeID + " from: " + employee.lastName() + " to: " + newValue);
                    newEmployee = new Employee(employee.empId(),
                            employee.prefix(),
                            employee.firstName(),
                            employee.middleInitial(),
                            newValue,
                            employee.gender(),
                            employee.email(),
                            employee.dob(),
                            employee.dateOfJoining(),
                            employee.salary());
                    sqlCommunication.updateRecord(newEmployee);
                    break;
                case "gender":
                    logger.info("Updated gender of employee with ID: " + employeeID + " from: " + employee.gender() + " to: " + newValue);
                    newEmployee = new Employee(employee.empId(),
                            employee.prefix(),
                            employee.firstName(),
                            employee.middleInitial(),
                            employee.lastName(),
                            newValue,
                            employee.email(),
                            employee.dob(),
                            employee.dateOfJoining(),
                            employee.salary());
                    sqlCommunication.updateRecord(newEmployee);
                    break;
                case "email":
                    logger.info("Updated email of employee with ID: " + employeeID + " from: " + employee.email() + " to: " + newValue);
                    newEmployee = new Employee(employee.empId(),
                            employee.prefix(),
                            employee.firstName(),
                            employee.middleInitial(),
                            employee.lastName(),
                            employee.gender(),
                            newValue,
                            employee.dob(),
                            employee.dateOfJoining(),
                            employee.salary());
                    sqlCommunication.updateRecord(newEmployee);
                    break;
                case "dob":
                    logger.info("Updated date of birth of employee with ID: " + employeeID + " from: " + employee.dob() + " to: " + newValue);
                    newEmployee = new Employee(employee.empId(),
                            employee.prefix(),
                            employee.firstName(),
                            employee.middleInitial(),
                            employee.lastName(),
                            employee.gender(),
                            employee.email(),
                            LocalDate.parse(newValue, DateTimeFormatter.ofPattern("[MM/dd/yyyy][M/d/yyyy][M/dd/yyyy][M/d/yyyy]")),
                            employee.dateOfJoining(),
                            employee.salary());
                    sqlCommunication.updateRecord(newEmployee);
                    break;
                case "doj":
                    logger.info("Updated date of joining of employee with ID: " + employeeID + " from: " + employee.dateOfJoining() + " to: " + newValue);
                    newEmployee = new Employee(employee.empId(),
                            employee.prefix(),
                            employee.firstName(),
                            employee.middleInitial(),
                            employee.lastName(),
                            employee.gender(),
                            employee.email(),
                            employee.dob(),
                            LocalDate.parse(newValue, DateTimeFormatter.ofPattern("[MM/dd/yyyy][M/d/yyyy][M/dd/yyyy][M/d/yyyy]")),
                            employee.salary());
                    sqlCommunication.updateRecord(newEmployee);
                    break;
                case "salary":
                    logger.info("Updated salary of employee with ID: " + employeeID + " from: " + employee.salary() + " to: " + newValue);
                    newEmployee = new Employee(employee.empId(),
                            employee.prefix(),
                            employee.firstName(),
                            employee.middleInitial(),
                            employee.lastName(),
                            employee.gender(),
                            employee.email(),
                            employee.dob(),
                            employee.dateOfJoining(),
                            newValue);
                    sqlCommunication.updateRecord(newEmployee);
                    break;
            }
        } catch (SQLException e) {
            logger.severe("SQLException encountered when attempting to update employee");
            e.printStackTrace();
        }

    }

    public void deleteEmployee(String employeeID) {
        logger.info("Entered delete employee method in DAO");
        Employee employee = null;
        try {
            ArrayList<Employee> list = packagesMultipleEmployeeObjects(sqlCommunication.getEmployeeByID(employeeID));
            sqlCommunication.deleteRecord(list.getFirst());
            logger.info("Successfully deleted employee record with employee ID: " + employeeID + " from database");
        } catch (SQLException e) {
            logger.severe("SQLException encountered when attempting to delete an employee from the database");
            e.printStackTrace();
        }
    }

    private ArrayList<Employee> packagesMultipleEmployeeObjects(ResultSet set) throws SQLException {
        logger.info("Entered package multiple employee objects method in DAO");

        ArrayList<Employee> employeeList = new ArrayList<>();
        while (set.next()) {
            Object[] array = new Object[10];
            Employee employee = new Employee(
                    set.getString(1),
                    set.getString(2),
                    set.getString(3),
                    set.getString(4),
                    set.getString(5),
                    set.getString(6),
                    set.getString(7),
                    LocalDate.parse(set.getDate(8).toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    LocalDate.parse(set.getDate(9).toString(),DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    set.getString(10)
            );
            employeeList.add(employee);
        }
        System.out.println(employeeList);
        return employeeList;
    }
}
