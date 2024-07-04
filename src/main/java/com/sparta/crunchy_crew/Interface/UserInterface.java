package com.sparta.crunchy_crew.Interface;

import com.sparta.crunchy_crew.Employee;
import com.sparta.crunchy_crew.EmployeeDAO;
import com.sparta.crunchy_crew.data_parsing.CsvReader;
import com.sparta.crunchy_crew.data_parsing.EmployeeParser;

import java.io.Console;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UserInterface {

    private EmployeeDAO employeeDAO = new EmployeeDAO();
    private final Scanner SCAN = new Scanner(System.in);

    public void start() {
        System.out.println("\nWelcome to the CrunchyCrew CRM.\n");

        System.out.println(ConsoleColours.RED + CsvReader.getCorruptEntryCount() + " CORRUPTED ENTRIES FOUND. " + ConsoleColours.RESET + "See log files for details.");

        boolean exit = false;
        do {
            System.out.println(ConsoleColours.UNDERLINE + "\nMAIN MENU\n" + ConsoleColours.RESET);
            System.out.println("1: CREATE New Employee record");
            System.out.println("2: SEARCH Records By Employee ID");
            System.out.println("3: UPDATE Employee Record");
            System.out.println("4: DELETE Employee Record ");
            System.out.println("Q: QUIT\n");

            System.out.print("> ");

            String userInput = SCAN.nextLine().trim();
            switch (userInput) {
                case ("1") -> createEmployeeMenu();
                case ("2") -> searchEmployeeMenu();
                case ("3") -> updateEmployeeMenu();
                case ("4") -> deleteEmployeeMenu();
                case ("Q") -> {
                    System.out.println("Shutting down CrunchyCrew CRM...Goodbye.");
                    exit = true;
                }
                default -> System.out.println("Invalid command. Please try again.");
            }
        } while (!exit);
    }

    private void createEmployeeMenu() {
        System.out.println(ConsoleColours.UNDERLINE + "EMPLOYEE CREATION" + ConsoleColours.RESET);

        while (true) {
            System.out.println("Please provide the new employee's details: \n");

            System.out.print("ID: ");
            String id = SCAN.nextLine().trim();
            try {
                EmployeeParser.parseEmpId(id);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Entry - ID must be 6 digits only.");
                continue;
            }

            System.out.print("Title: ");
            String title = SCAN.nextLine().trim();
            try {
                EmployeeParser.parsePrefix(title);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Entry - title must be (Mr./Mrs./Miss./Ms./Drs.");
                continue;
            }

            System.out.print("First Name: ");
            String firstName = SCAN.nextLine().trim();
            try {
                EmployeeParser.parseFirstName(firstName);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Entry - cannot be null or empty.");
                continue;
            }

            System.out.print("Middle Initial (leave blank if no middle name): ");
            String middleInitial = SCAN.nextLine().trim();
            try {
                EmployeeParser.parseMiddleInitial(middleInitial);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Entry - length must be one.");
                continue;
            }

            System.out.print("Last Name: ");
            String lastName = SCAN.nextLine().trim();
            try {
                EmployeeParser.parseLastName(lastName);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Entry - cannot be null or empty.");
                continue;
            }

            System.out.print("Gender (M/F): ");
            String gender = SCAN.nextLine().trim();
            try {
                EmployeeParser.parseMiddleInitial(gender);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Entry -gender must be M or F.");
                continue;
            }

            System.out.print("Email (xxxxxx@xxxx.xxx): ");
            String email = SCAN.nextLine().trim();
            try {
                EmployeeParser.parseEmail(email);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Entry - email format invalid.");
                continue;
            }

            System.out.print("Date Of Birth (MM-DD-YYYY): ");
            String birthday = SCAN.nextLine().trim();
            try {
                EmployeeParser.parseBirthday(birthday);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Entry - candidate must be 18 and not dead.");
                continue;
            }
//
            System.out.print("Date Of Joining (MM-DD-YYYY): ");
            String joinDate = SCAN.nextLine().trim();
            try {
                EmployeeParser.parseStartDate(joinDate, LocalDate.parse(birthday, DateTimeFormatter.ofPattern("[MM/dd/yyyy][M/d/yyyy][M/dd/yyyy][M/d/yyyy]")));
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Entry - invalid entry.");
                continue;
            }

            System.out.print("Salary: ");
            String salary = SCAN.nextLine().trim();
            try {
                EmployeeParser.parseSalary(salary);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Entry - salary invalid.");
                continue;
            }

            LocalDate birthdayLocalDate = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("[MM/dd/yyyy][M/d/yyyy][M/dd/yyyy][M/d/yyyy]"));
            LocalDate joinDateLocalDate = LocalDate.parse(joinDate, DateTimeFormatter.ofPattern("[MM/dd/yyyy][M/d/yyyy][M/dd/yyyy][M/d/yyyy]"));

            Employee newEmployee = new Employee(id, title, firstName, middleInitial, lastName, gender, email, birthdayLocalDate, joinDateLocalDate, salary);

            System.out.println(newEmployee);

            System.out.print("See details above. Submit to database? (Y/N): ");
            String submit = SCAN.nextLine().trim().toLowerCase();
            if (submit.equals("y")) {

                employeeDAO.createEmployee(newEmployee);
                System.out.print("\nEmployee successfully submitted to database.");

            } else {

                System.out.print("Employee not submitted to database.");

            }

            System.out.print("Add another? (Y/N):");
            String another = SCAN.nextLine();
            if (!another.equals("y".toLowerCase())) {
                break;
            }
        }
    }

    private void searchEmployeeMenu() {
        System.out.println(ConsoleColours.UNDERLINE + "\nEMPLOYEE SEARCH\n" + ConsoleColours.RESET);

        while (true) {
            System.out.print("Enter ID ('M' for MAIN MENU): ");

            String id = SCAN.nextLine();
            if (id.toLowerCase().equals("m")) {
                break;
            }

            Employee searchedEmployee = employeeDAO.getEmployee("id", id);
            if (searchedEmployee != null) {
                System.out.println(ConsoleColours.GREEN + "FOUND" + ConsoleColours.RESET);
                System.out.println("\n" + searchedEmployee + "\n");
            } else {
                System.out.println(ConsoleColours.RED + "NO RECORDS FOUND\n" + ConsoleColours.RESET);
            }
        }
    }

    private void updateEmployeeMenu() {
        System.out.println("\n" + ConsoleColours.UNDERLINE + "EMPLOYEE UPDATE" + ConsoleColours.RESET);

        boolean exit = false;
        while (true) {
            System.out.print("\n" + "Enter ID ('M' for MAIN MENU): ");

            String id = SCAN.nextLine();
            if (id.toLowerCase().equals("m")) {
                break;
            }

            Employee searchedEmployee = employeeDAO.getEmployee("id", id);
            if (searchedEmployee != null) {
                System.out.println(ConsoleColours.GREEN + "FOUND" + ConsoleColours.RESET + "\n");
                System.out.println(searchedEmployee);
                updateEmployeeSubMenu(searchedEmployee.empId());
            } else {
                System.out.println(ConsoleColours.RED + "NO RECORDS FOUND\n" + ConsoleColours.RESET);
            }
        }
    }

    public void updateEmployeeSubMenu(String employeeID) {
        System.out.println("1: Title");
        System.out.println("2: First Name");
        System.out.println("3: Middle Initial");
        System.out.println("4: Last Name");
        System.out.println("5: Gender");
        System.out.println("6: Email");
        System.out.println("7: Birthday");
        System.out.println("8: Join Date");
        System.out.println("9: Salary");

        System.out.print("\nChoose field to update: ");
        String userInput = SCAN.nextLine();

        switch (userInput) {
            case "1" -> employeeDAO.updateEmployee(employeeID, "prefix", enterUpdateValue("title"));
            case "2" -> employeeDAO.updateEmployee(employeeID, "first name", enterUpdateValue("first name"));
            case "3" -> employeeDAO.updateEmployee(employeeID, "middle name", enterUpdateValue("middle name"));
            case "4" -> employeeDAO.updateEmployee(employeeID, "last name", enterUpdateValue("last name"));
            case "5" -> employeeDAO.updateEmployee(employeeID, "gender", enterUpdateValue("gender"));
            case "6" -> employeeDAO.updateEmployee(employeeID, "email", enterUpdateValue("email"));
            case "7" -> employeeDAO.updateEmployee(employeeID, "dob", enterUpdateValue("date of birth"));
            case "8" -> employeeDAO.updateEmployee(employeeID, "doj", enterUpdateValue("date of joining"));
            case "9" -> employeeDAO.updateEmployee(employeeID, "salary", enterUpdateValue("salary"));
        }
        System.out.println("\n" + ConsoleColours.GREEN + "Employee successfully updated" + ConsoleColours.RESET);
        System.out.println(employeeDAO.getEmployee("id", employeeID));

    }

    public String enterUpdateValue(String fieldName) {
        System.out.print("Enter new " + fieldName + ": ");
        String userInput = SCAN.nextLine();
        return userInput;
    }

    private void deleteEmployeeMenu() {
        System.out.println("\n" + ConsoleColours.UNDERLINE + "EMPLOYEE DELETION" + ConsoleColours.RESET);

        while (true) {
            System.out.print("\n" + "Enter ID ('M' for MAIN MENU): ");

            String id = SCAN.nextLine();
            if (id.toLowerCase().equals("m")) {
                break;
            }

            Employee searchedEmployee = employeeDAO.getEmployee("id", id);
            if (searchedEmployee != null) {

                System.out.println(ConsoleColours.GREEN + "FOUND" + ConsoleColours.RESET);
                System.out.println("\n" + searchedEmployee + "\n");
                System.out.print("Are you sure you would like to delete this employee? (Y/N): ");
                String userInput = SCAN.nextLine();
                if (userInput.toLowerCase().equals("y")) {
                    employeeDAO.deleteEmployee(id);
                    System.out.println(ConsoleColours.GREEN + "Employee deleted."  + ConsoleColours.RESET);
                }
            } else {
                System.out.println(ConsoleColours.RED + "NO RECORDS FOUND" + ConsoleColours.RESET);
            }
        }
    }
}
