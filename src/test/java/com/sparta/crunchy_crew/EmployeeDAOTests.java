package com.sparta.crunchy_crew;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EmployeeDAOTests {

    EmployeeDAO employeeDAO = new EmployeeDAO();

    @Test
    @DisplayName("Check if get employee returns employee record when given a valid ID")
    void checkIfGetEmployeesReturnsEmployeeRecordWhenGivenAValidID() {
        String id = "133611";
        Employee expected = new Employee("133611", "Dr.", "Felix", "W", "Imhoff", "M", "felix.imhoff@charter.net", LocalDate.of(1962, 1, 9), LocalDate.of(1985, 6, 3), "64560");
        Employee actual = employeeDAO.getEmployee("id", id);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check if get employee returns null employee record when given an ivalid ID")
    void checkIfGetEmployeesReturnsNullEmployeeRecordWhenGivenAnInvalidID() {
        String id = "112345";
        Employee expected = null;
        Employee actual = employeeDAO.getEmployee("id", id);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check if get employee returns employee record when given an ivalid ID")
    void checkIfGetEmployeesReturnsEmployeeRecordsWhenGivenAValidPrefix() {
        String id = "112345";
        Employee expected = new Employee("133611", "Dr.", "Felix", "W", "Imhoff", "M", "felix.imhoff@charter.net", LocalDate.of(1962, 1, 9), LocalDate.of(1985, 6, 3), "64560");
        Employee actual = employeeDAO.getEmployee("id", id);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check if get employee returns employee record when given an ivalid ID")
    void checkIfGetEmployeesReturnsEmployeeRecordWhenGivenAnInvalidID() {
        String id = "112345";
        Employee expected = null;
        Employee actual = employeeDAO.getEmployee("id", id);
        Assertions.assertEquals(expected, actual);
    }







}
