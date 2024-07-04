package com.sparta.crunchy_crew;

import com.sparta.crunchy_crew.data.DatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class EmployeeDAO {

    private static final Logger logger = Logger.getLogger(DatabaseConnection.class.getName());

    SQLCommunication sqlCommunication = new SQLCommunication();

    public void createEmployee(Employee employee) {
        sqlCommunication.createRecord(employee);
    }

    public Employee getEmployee(String flag, String value) {
        try {
            switch (flag.toLowerCase()) {
                case "prefix":
                    return packageEmployeeObject(sqlCommunication.getEmployeesByPrefix(value));
                case "first name":
                    return packageEmployeeObject(sqlCommunication.getEmployeesByFirstName(value));
                case "middle initial":
                    return packageEmployeeObject(sqlCommunication.getEmployeesByMiddleInitial(value));
                case "last name":
                    return packageEmployeeObject(sqlCommunication.getEmployeesByLastName(value));
                case "gender":
                    return packageEmployeeObject(sqlCommunication.getEmployeesByGender(value));
                case "email":
                    return packageEmployeeObject(sqlCommunication.getEmployeesByEmail(value));
                case "dob":
                    return packageEmployeeObject(sqlCommunication.getEmployeesByDob(value));
                case "doj":
                    return packageEmployeeObject(sqlCommunication.getEmployeesByDoj(value));
                case "salary":
                    return packageEmployeeObject(sqlCommunication.getEmployeesBySalary(value));
                default:
                    return packageEmployeeObject(sqlCommunication.getEmployeeByID(value));
            }
        } catch (SQLException e) {
            return null;
        }

    }

    public void updateEmployee(String employeeID, String flag, String newValue) {
        Employee employee = null;
        try {
            employee = packageEmployeeObject(sqlCommunication.getEmployeeByID(employeeID));
            System.out.println("updateEmployeeMethod" + employee);
            Employee newEmployee = null;
            switch (flag) {
                case "prefix":
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
            e.printStackTrace();
        }

    }

    public void deleteEmployee(String employeeID) {
        Employee employee = null;
        try {
            employee = packageEmployeeObject(sqlCommunication.getEmployeeByID(employeeID));
            sqlCommunication.deleteRecord(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                LocalDate.parse(set.getDate(9).toString(),DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                set.getString(10)
        );

        return employee;
    }
}
