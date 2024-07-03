package com.sparta.crunchy_crew.data_parsing;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.logging.Logger;

public class EmployeeParser {
    private static final Logger LOGGER = Logger.getLogger(EmployeeParser.class.getName());
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final HashSet<String> employeeIds = new HashSet<>();

    // Return Codes:
    // 0: valid data
    // 1: invalid data
    public int parseEmployeeData(String employeeEntry) {
        if (employeeEntry == null) return 1;

        String[] csvValues = employeeEntry.split(",");
        try {
            String empId = parseEmpId(csvValues[0]);
            String prefix = parsePrefix(csvValues[1]);
            String firstName = parseFirstName(csvValues[2]);
            Character midInitial = parseMiddleInitial(csvValues[3]);
            String lastName = parseLastName(csvValues[4]);
            Character gender = parseGender(csvValues[5]);
            String email = parseEmail(csvValues[6]);
            LocalDate birthday = parseBirthday(csvValues[7]);
            LocalDate joinDate = parseJoiningDate(csvValues[8]);
            int salary = parseSalary(csvValues[9]);
        }catch (IllegalArgumentException e){
            LOGGER.warning("Invalid employeeEntry: " + employeeEntry);
            return 1;
        }

        // TODO: Call DAO to send parsed data to database
        return 0;
    }

    // TODO: Make private once tested
    public static String parseEmpId(String employeeId) throws IllegalArgumentException {
        if (employeeId.matches("\\d+") && employeeId.length() == 6 && !employeeIds.contains(employeeId)) {
            employeeIds.add(employeeId);
            return employeeId;
        } else {
            throw new IllegalArgumentException("IllegalArgumentException: employeeId: " + employeeId);
        }
    }

    // TODO: Make private once tested
    public static String parsePrefix(String prefix) throws IllegalArgumentException {
        if (!prefix.endsWith(".") || prefix.length() < 3 || prefix.length() > 5) {
            throw new IllegalArgumentException("IllegalArgumentException: prefix: " + prefix);
        } else {
            return prefix;
        }
    }

    // TODO: Make private once tested
    public static String parseFirstName(String firstName) throws IllegalArgumentException {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("IllegalArgumentException: firstName: " + firstName);
        }
        return firstName;
    }

    // TODO: Make private once tested
    public static Character parseMiddleInitial(String midInitial) throws IllegalArgumentException {
        if (midInitial == null || midInitial.length() != 1) {
            throw new IllegalArgumentException("IllegalArgumentException: midInitial: " + midInitial);
        }
        return midInitial.charAt(0);
    }

    // TODO: Make private once tested
    public static String parseLastName(String lastName) throws IllegalArgumentException {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("IllegalArgumentException: lastName: " + lastName);
        }
        return lastName;
    }

    // TODO: Make private once tested
    public static char parseGender(String gender) throws IllegalArgumentException {
        if (gender.equals("M") || gender.equals("F")) {
            return gender.charAt(0);
        } else {
            throw new IllegalArgumentException("IllegalArgumentException: gender: " + gender);
        }
    }

    // TODO: Make private once tested
    public static String parseEmail(String email) throws IllegalArgumentException {
        int firstAtSignIndex = email.indexOf('@');
        int lastAtSignIndex = email.lastIndexOf('@');
        int periodIndex = email.lastIndexOf('.');
        if (lastAtSignIndex < periodIndex && firstAtSignIndex == lastAtSignIndex) {
            return email;
        } else {
            throw new IllegalArgumentException("IllegalArgumentException: email: " + email);
        }
    }

    // TODO: Make private once tested
    public static LocalDate parseBirthday(String birthday) throws IllegalArgumentException {
        LocalDate parsedDate = null;
        try {
            parsedDate = LocalDate.parse(birthday, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            LOGGER.warning("Invalid Date of Birth format: " + birthday);
            throw new IllegalArgumentException("IllegalArgumentException: birthday: " + birthday);
        }
        return parsedDate;
    }

    // TODO: Make private once tested
    public static LocalDate parseJoiningDate(String joinDate) throws IllegalArgumentException {
        LocalDate parsedDate = null;
        try {
            parsedDate = LocalDate.parse(joinDate, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            LOGGER.warning("Invalid Date of Joining format: " + joinDate);
            throw new IllegalArgumentException("IllegalArgumentException: joinDate: " + joinDate);
        }
        return parsedDate;
    }

    // TODO: Make private once tested
    public static int parseSalary(String salary) throws IllegalArgumentException {
        if (salary.matches("\\d+") && (!salary.isEmpty() || Integer.parseInt(salary) > 0)) {
            return Integer.parseInt(salary);
        } else {
            throw new IllegalArgumentException("IllegalArgumentException: prefix: " + salary);
        }
    }
}
