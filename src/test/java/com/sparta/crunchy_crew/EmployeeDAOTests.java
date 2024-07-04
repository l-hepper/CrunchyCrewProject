package com.sparta.crunchy_crew;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeDAOTests {

    EmployeeDAO employeeDAO = new EmployeeDAO();
    Employee employeeExample = new Employee("178566", "Mrs.", "Juliette", "M", "Rojo", "F", "juliette.rojo@yahoo.co.uk", LocalDate.of(1967, 5, 8), LocalDate.of(2011, 6, 4), "193912");
    Employee employeeExample2 = new Employee("198429", "Mrs.", "Serafina", "I", "Bumgarner", "F", "serafina.bumgarner@exxonmobil.com", LocalDate.of(1982, 9, 21), LocalDate.of(2008, 2, 1), "69294");
    Employee employeeExample3 = new Employee("260736", "Ms.", "Zelda", "P", "Forest", "F", "zelda.forest@ibm.com", LocalDate.of(1959, 11, 27), LocalDate.of(2014, 1, 28), "176642");

    @Test
    @DisplayName("Check if get employee returns employee record when given a valid ID")
    void checkIfGetEmployeesReturnsEmployeeRecordWhenGivenAValidID() {
        String id = "178566";
        ArrayList<Employee> expected = new ArrayList<>(Arrays.asList(employeeExample));
        ArrayList<Employee> actual = employeeDAO.getEmployee("id", id);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check if get employee returns null employee record when given an invalid ID")
    void checkIfGetEmployeesReturnsNullEmployeeRecordWhenGivenAnInvalidID() {
        String id = "178560";
        ArrayList<Employee> expected = new ArrayList<>(List.of());
        ArrayList<Employee> actual = employeeDAO.getEmployee("id", id);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check if get employee returns employee record when given a valid prefix")
    void checkIfGetEmployeesReturnsEmployeeRecordsWhenGivenAValidPrefix() {
        String prefix = "Mrs.";
        ArrayList<Employee> expected = new ArrayList<>(Arrays.asList(employeeExample, employeeExample2));
        ArrayList<Employee> actual = employeeDAO.getEmployee("prefix", prefix);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check if get employee returns employee record when given an invalid prefix")
    void checkIfGetEmployeesReturnsEmployeeRecordWhenGivenAnInvalidID() {
        String prefix = "x";
        ArrayList<Employee> expected = new ArrayList<>(List.of());
        ArrayList<Employee> actual = employeeDAO.getEmployee("prefix", prefix);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check if get employee returns employee record when given a valid first name")
    void checkIfGetEmployeesReturnsEmployeeRecordsWhenGivenAValidFirstName() {
        String first_name = "Juliette";
        ArrayList<Employee> expected = new ArrayList<>(Arrays.asList(employeeExample));
        ArrayList<Employee> actual = employeeDAO.getEmployee("first name", first_name);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check if get employee returns employee record when given an invalid first name")
    void checkIfGetEmployeesReturnsEmployeeRecordWhenGivenAnInvalidFirstName() {
        String first_name = "x";
        ArrayList<Employee> expected = new ArrayList<>(List.of());
        ArrayList<Employee> actual = employeeDAO.getEmployee("first name", first_name);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check if get employee returns employee record when given a valid middle name")
    void checkIfGetEmployeesReturnsEmployeeRecordsWhenGivenAValidMiddleName() {
        String middle_name = "M";
        ArrayList<Employee> expected = new ArrayList<>(Arrays.asList(employeeExample));
        ArrayList<Employee> actual = employeeDAO.getEmployee("middle initial", middle_name);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check if get employee returns employee record when given an invalid middle name")
    void checkIfGetEmployeesReturnsEmployeeRecordWhenGivenAnInvalidMiddleName() {
        String middle_name = "x";
        ArrayList<Employee> expected = new ArrayList<>(List.of());
        ArrayList<Employee> actual = employeeDAO.getEmployee("middle initial", middle_name);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check if get employee returns employee record when given a valid last name")
    void checkIfGetEmployeesReturnsEmployeeRecordsWhenGivenAValidLastName() {
        String last_name = "Rojo";
        ArrayList<Employee> expected = new ArrayList<>(Arrays.asList(employeeExample));
        ArrayList<Employee> actual = employeeDAO.getEmployee("last name", last_name);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check if get employee returns employee record when given an invalid last name")
    void checkIfGetEmployeesReturnsEmployeeRecordWhenGivenAnInvalidLastName() {
        String last_name = "x";
        ArrayList<Employee> expected = new ArrayList<>(List.of());
        ArrayList<Employee> actual = employeeDAO.getEmployee("last name", last_name);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check if get employee returns employee record when given a valid gender")
    void checkIfGetEmployeesReturnsEmployeeRecordsWhenGivenAValidGender() {
        String gender = "F";
        ArrayList<Employee> expected = new ArrayList<>(Arrays.asList(employeeExample, employeeExample2, employeeExample3));
        ArrayList<Employee> actual = employeeDAO.getEmployee("gender", gender);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check if get employee returns employee record when given an invalid gender")
    void checkIfGetEmployeesReturnsEmployeeRecordWhenGivenAnInvalidGender() {
        String gender = "x";
        ArrayList<Employee> expected = new ArrayList<>(List.of());
        ArrayList<Employee> actual = employeeDAO.getEmployee("gender", gender);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check if get employee returns employee record when given a valid email")
    void checkIfGetEmployeesReturnsEmployeeRecordsWhenGivenAValidEmail() {
        String email = "juliette.rojo@yahoo.co.uk";
        ArrayList<Employee> expected = new ArrayList<>(Arrays.asList(employeeExample));
        ArrayList<Employee> actual = employeeDAO.getEmployee("email", email);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check if get employee returns employee record when given an invalid email")
    void checkIfGetEmployeesReturnsEmployeeRecordWhenGivenAnInvalidEmail() {
        String email = "juliette.rojo@yahoo.co.uk...";
        ArrayList<Employee> expected = new ArrayList<>(List.of());
        ArrayList<Employee> actual = employeeDAO.getEmployee("email", email);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check if get employee returns employee record when given a valid date of birth")
    void checkIfGetEmployeesReturnsEmployeeRecordsWhenGivenAValidDOB() {
        String dob = "1967/05/08";
        ArrayList<Employee> expected = new ArrayList<>(Arrays.asList(employeeExample));
        ArrayList<Employee> actual = employeeDAO.getEmployee("dob", dob);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check if get employee returns employee record when given an invalid date of birth")
    void checkIfGetEmployeesReturnsEmployeeRecordWhenGivenAnInvalidDOB() {
        String dob = "1/1/1";
        ArrayList<Employee> expected = new ArrayList<>(List.of());
        ArrayList<Employee> actual = employeeDAO.getEmployee("dob", dob);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check if get employee returns employee record when given a valid date of joining")
    void checkIfGetEmployeesReturnsEmployeeRecordsWhenGivenAValidDOJ() {
        String doj = "2011/06/04";
        ArrayList<Employee> expected = new ArrayList<>(Arrays.asList(employeeExample));
        ArrayList<Employee> actual = employeeDAO.getEmployee("doj", doj);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check if get employee returns employee record when given an invalid date of birth")
    void checkIfGetEmployeesReturnsEmployeeRecordWhenGivenAnInvalidDOJ() {
        String doj = "1/1/1";
        ArrayList<Employee> expected = new ArrayList<>(List.of());
        ArrayList<Employee> actual = employeeDAO.getEmployee("doj", doj);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check if get employee returns employee record when given a valid salary")
    void checkIfGetEmployeesReturnsEmployeeRecordsWhenGivenAValidSalary() {
        String salary = "193912";
        ArrayList<Employee> expected = new ArrayList<>(Arrays.asList(employeeExample));
        ArrayList<Employee> actual = employeeDAO.getEmployee("salary", salary);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Check if get employee returns employee record when given an invalid salary")
    void checkIfGetEmployeesReturnsEmployeeRecordWhenGivenAnInvalidSalary() {
        String salary = "-10000";
        ArrayList<Employee> expected = new ArrayList<>(List.of());
        ArrayList<Employee> actual = employeeDAO.getEmployee("salary", salary);
        Assertions.assertEquals(expected, actual);
    }

}
