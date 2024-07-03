package com.sparta.crunchy_crew.data_parsing;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class EmployeeParser {
    private static final Logger LOGGER = Logger.getLogger(EmployeeParser.class.getName());
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final HashSet<String> employeeIds = new HashSet<>();

    // Return Codes:
    // 0: valid data
    // 1: invalid data
    public static int parseEmployeeData(String employeeEntry) {
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
            LocalDate startDate = parseStartDate(csvValues[8], birthday);
            int salary = parseSalary(csvValues[9]);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
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
            LOGGER.fine("IllegalArgumentException: Invalid employeeId: " + employeeId);
            throw new IllegalArgumentException("IllegalArgumentException: Invalid employeeId: " + employeeId);
        }
    }

    // TODO: Make private once tested
    public static String parsePrefix(String prefix) throws IllegalArgumentException {
        if (!prefix.endsWith(".") || prefix.length() < 3 || prefix.length() > 5) {
            LOGGER.fine("IllegalArgumentException: Invalid prefix: " + prefix);
            throw new IllegalArgumentException("IllegalArgumentException: Invalid prefix: " + prefix);
        } else {
            return prefix;
        }
    }

    // TODO: Make private once tested
    public static String parseFirstName(String firstName) throws IllegalArgumentException {
        if (firstName == null || firstName.isEmpty()) {
            LOGGER.fine("IllegalArgumentException: Invalid firstName: " + firstName);
            throw new IllegalArgumentException("IllegalArgumentException: Invalid firstName: " + firstName);
        }
        return firstName;
    }

    // TODO: Make private once tested
    public static Character parseMiddleInitial(String midInitial) throws IllegalArgumentException {
        if (midInitial == null || midInitial.length() != 1) {
            LOGGER.fine("IllegalArgumentException: Invalid midInitial: " + midInitial);
            throw new IllegalArgumentException("IllegalArgumentException: Invalid midInitial: " + midInitial);
        }
        return midInitial.charAt(0);
    }

    // TODO: Make private once tested
    public static String parseLastName(String lastName) throws IllegalArgumentException {
        if (lastName == null || lastName.isEmpty()) {
            LOGGER.fine("IllegalArgumentException: Invalid lastName: " + lastName);
            throw new IllegalArgumentException("IllegalArgumentException: Invalid lastName: " + lastName);
        }
        return lastName;
    }

    // TODO: Make private once tested
    public static char parseGender(String gender) throws IllegalArgumentException {
        if (gender == null) {
            LOGGER.fine("IllegalArgumentException: Invalid gender: null");
            throw new IllegalArgumentException("IllegalArgumentException: Invalid gender: null");
        } else if (!gender.equals("M") && !gender.equals("F")) {
            LOGGER.fine("IllegalArgumentException: Invalid gender: " + gender);
            throw new IllegalArgumentException("IllegalArgumentException: Invalid gender: " + gender);
        } else {
            return gender.charAt(0);
        }
    }

    // TODO: Make private once tested
    public static String parseEmail(String email) throws IllegalArgumentException {
        if (Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$").matcher(email).matches()) {
            return email;
        } else {
            LOGGER.fine("IllegalArgumentException: Invalid email: " + email);
            throw new IllegalArgumentException("IllegalArgumentException: Invalid email: " + email);
        }
    }

    // TODO: Make private once tested
    public static LocalDate parseBirthday(String birthday) throws IllegalArgumentException {
        LocalDate parsedDate = null;

        try {
            isDateInValidFormat(birthday);
            parsedDate = LocalDate.parse(birthday, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            LOGGER.fine("Invalid Date of Joining format: " + birthday);
            throw new IllegalArgumentException("IllegalArgumentException: Invalid birthday: " + birthday);
        }

        boolean isAtLeastEighteen = LocalDate.now().minusYears(18).isAfter(parsedDate);
        boolean isNotDead = parsedDate.plusYears(100).isAfter(LocalDate.now());
        if (isAtLeastEighteen && isNotDead) {
            return parsedDate;
        } else {
            LOGGER.fine("IllegalArgumentException: birthday: " + birthday);
            throw new IllegalArgumentException("IllegalArgumentException: Invalid birthday: " + birthday);
        }
    }

    // TODO: Make private once tested
    public static LocalDate parseStartDate(String startDate, LocalDate birthday) throws IllegalArgumentException {
        LocalDate parsedDate = null;
        try {
            isDateInValidFormat(startDate);
            parsedDate = LocalDate.parse(startDate, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            LOGGER.fine("Invalid Date of Joining format: " + startDate);
            throw new IllegalArgumentException("IllegalArgumentException: Invalid startDate: " + startDate);
        }

        boolean startedDayOfTurningEighteen = birthday.plusYears(18).isEqual(parsedDate);
        boolean startedAfterTurningEighteen = birthday.plusYears(18).isBefore(parsedDate);
        if (startedDayOfTurningEighteen || startedAfterTurningEighteen) {
            return parsedDate;
        } else {
            LOGGER.fine("IllegalArgumentException: startDate: " + startDate);
            throw new IllegalArgumentException("IllegalArgumentException: Invalid startDate: " + startDate);
        }
    }

    // TODO: Make private once tested
    public static void isDateInValidFormat(String date) throws IllegalArgumentException {
        String[] dateArray = date.split("/");
        boolean dateHasValidCharacters = (dateArray.length == 3) && (dateArray[0].length() == 2 && dateArray[1].length() == 2 && dateArray[2].length() == 4);
        if (!dateHasValidCharacters) {
            LOGGER.fine("IllegalArgumentException: Invalid date: " + date);
            throw new IllegalArgumentException("IllegalArgumentException: Invalid date: " + date);
        }
    }

    // TODO: Make private once tested
    public static int parseSalary(String salary) throws IllegalArgumentException {
        if (salary.matches("\\d+") && (!salary.isEmpty() || Integer.parseInt(salary) > 0)) {
            return Integer.parseInt(salary);
        } else {
            LOGGER.fine("IllegalArgumentException: Invalid salary: " + salary);
            throw new IllegalArgumentException("IllegalArgumentException: Invalid salary: " + salary);
        }
    }
}
