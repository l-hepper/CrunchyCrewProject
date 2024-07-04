package com.sparta.CrunchyCrew;

import java.sql.SQLException;

public class EmployeeDAO {

    SQLCommunication sqlCommunication = new SQLCommunication();

    public void createEmployee(Employee employee) {
        sqlCommunication.createRecord(employee);
    }

    public Employee getEmployee(String employeeID) {
        Employee employee = sqlCommunication.getEmployeeByID(employeeID);
        return employee;
    }

    public void updateEmployee() {

    }

    public void deleteEmployee() {

    }
}
