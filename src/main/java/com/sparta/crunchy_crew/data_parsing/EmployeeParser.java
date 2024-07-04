package com.sparta.crunchy_crew.data_parsing;

import com.sparta.crunchy_crew.Employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class EmployeeParser {
    private static final Logger LOGGER = Logger.getLogger(EmployeeParser.class.getName());
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("M/d/yyyy");
    private static final HashSet<String> employeeIds = new HashSet<>();

    public static Optional<Employee> parseEmployeeData(String employeeEntry) {
        Employee employeeRecord;
        if (employeeEntry == null){
            LOGGER.warning("Invalid employeeEntry: null");
            return Optional.empty();
        }

        String[] csvValues = employeeEntry.split(",");
        if (csvValues.length != 10){
            LOGGER.warning("Invalid employeeEntry: " + employeeEntry);
            return Optional.empty();
        }

        try {
            String employeeId = parseEmpId(csvValues[0]);
            String prefix = parsePrefix(csvValues[1]);
            String firstName = parseFirstName(csvValues[2]);
            char midInitial = parseMiddleInitial(csvValues[3]);
            String lastName = parseLastName(csvValues[4]);
            char gender = parseGender(csvValues[5]);
            String email = parseEmail(csvValues[6]);
            LocalDate birthday = parseBirthday(csvValues[7]);
            LocalDate startDate = parseStartDate(csvValues[8], birthday);
            int salary = parseSalary(csvValues[9]);

            employeeIds.add(employeeId);
            employeeRecord = new Employee(
                    employeeId,
                    prefix,
                    firstName,
                    Character.toString(midInitial),
                    lastName,
                    Character.toString(gender),
                    email,
                    birthday,
                    startDate,
                    Integer.toString(salary));

        } catch (IllegalArgumentException e) {
            LOGGER.warning("Invalid employeeEntry: " + employeeEntry);
            LOGGER.info("Reason: " + e.getMessage());
            return Optional.empty();
        }
        return Optional.of(employeeRecord);
    }

    public static String parseEmpId(String employeeId) throws IllegalArgumentException {
        if (employeeId != null && employeeId.matches("\\d+") && employeeId.length() == 6 && !employeeIds.contains(employeeId)) {
            return employeeId;
        } else {
            throw new IllegalArgumentException("IllegalArgumentException: Invalid employeeId: " + employeeId);
        }
    }

    public static String parsePrefix(String prefix) throws IllegalArgumentException {
        if (prefix == null || !prefix.endsWith(".") || prefix.length() < 3 || prefix.length() > 5) {
            throw new IllegalArgumentException("IllegalArgumentException: Invalid prefix: " + prefix);
        } else {
            return prefix;
        }
    }

    public static String parseFirstName(String firstName) throws IllegalArgumentException {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("IllegalArgumentException: Invalid firstName: " + firstName);
        }
        return firstName;
    }

    public static Character parseMiddleInitial(String midInitial) throws IllegalArgumentException {
        if (midInitial == null || midInitial.length() != 1) {
            throw new IllegalArgumentException("IllegalArgumentException: Invalid midInitial: " + midInitial);
        }
        return midInitial.charAt(0);
    }

    public static String parseLastName(String lastName) throws IllegalArgumentException {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("IllegalArgumentException: Invalid lastName: " + lastName);
        }
        return lastName;
    }

    public static char parseGender(String gender) throws IllegalArgumentException {
        if (gender == null) {
            throw new IllegalArgumentException("IllegalArgumentException: Invalid gender: null");
        } else if (!gender.equals("M") && !gender.equals("F")) {
            throw new IllegalArgumentException("IllegalArgumentException: Invalid gender: " + gender);
        } else {
            return gender.charAt(0);
        }
    }

    public static String parseEmail(String email) throws IllegalArgumentException {
        if (Pattern.compile("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$").matcher(email).matches()) {
            return email;
        } else {
            throw new IllegalArgumentException("IllegalArgumentException: Invalid email: " + email);
        }
    }

    public static LocalDate parseBirthday(String birthday) throws IllegalArgumentException {
        LocalDate parsedDate = null;

        try {
            parsedDate = LocalDate.parse(birthday, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("IllegalArgumentException: Invalid birthday format: " + birthday);
        }

        boolean isAtLeastEighteen = LocalDate.now().minusYears(18).isAfter(parsedDate);
        boolean isNotDead = parsedDate.plusYears(100).isAfter(LocalDate.now());
        if (isAtLeastEighteen && isNotDead) {
            return parsedDate;
        } else {
            throw new IllegalArgumentException("IllegalArgumentException: Invalid birthday: " + birthday);
        }
    }

    public static LocalDate parseStartDate(String startDate, LocalDate birthday) throws IllegalArgumentException {
        LocalDate parsedDate = null;

        try {
            parsedDate = LocalDate.parse(startDate, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("IllegalArgumentException: Invalid startDate format: " + startDate);
        }

        boolean startedDayOfTurningEighteen = birthday.plusYears(18).isEqual(parsedDate);
        boolean startedAfterTurningEighteen = birthday.plusYears(18).isBefore(parsedDate);
        if (startedDayOfTurningEighteen || startedAfterTurningEighteen) {
            return parsedDate;
        } else {
            throw new IllegalArgumentException("IllegalArgumentException: Invalid startDate: " + startDate);
        }
    }

    public static int parseSalary(String salary) throws IllegalArgumentException {
        if (salary.matches("\\d+") && (!salary.isEmpty() || Integer.parseInt(salary) > 0)) {
            return Integer.parseInt(salary);
        } else {
            throw new IllegalArgumentException("IllegalArgumentException: Invalid salary: " + salary);
        }
    }
}
