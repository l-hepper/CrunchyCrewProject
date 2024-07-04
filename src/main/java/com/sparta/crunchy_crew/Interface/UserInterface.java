package com.sparta.crunchy_crew.Interface;

import com.sparta.crunchy_crew.Employee;
import com.sparta.crunchy_crew.EmployeeDAO;
import com.sparta.crunchy_crew.data_parsing.CsvReader;
import com.sparta.crunchy_crew.data_parsing.EmployeeParser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private final EmployeeDAO employeeDAO = new EmployeeDAO();
    private final Scanner SCAN = new Scanner(System.in);

    public void start() {
        System.out.println("\nWelcome to the CrunchyCrew CRM.\n");

        System.out.println(ConsoleColours.GREEN + CsvReader.getSanitisedEntryCounter() + " ENTRIES SUCCESSFULLY WRITTEN TO DATABASE" + ConsoleColours.RESET);
        System.out.println(ConsoleColours.RED + CsvReader.getCorruptEntryCount() + " CORRUPTED ENTRIES FOUND. " + ConsoleColours.RESET + "See log files for details.");

        boolean exit = false;
        do {
            System.out.println(ConsoleColours.UNDERLINE + "\nMAIN MENU\n" + ConsoleColours.RESET);
            System.out.println("1: CREATE New Employee record");
            System.out.println("2: SEARCH Records");
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
                case ("Q"), ("q") -> {
                    System.out.println("Shutting down CrunchyCrew CRM...Goodbye.");
                    exit = true;
                }
                default -> System.out.println("Invalid command. Please try again.");
            }
        } while (!exit);
    }

    private void createEmployeeMenu() {
        System.out.println("\n" + ConsoleColours.UNDERLINE + "EMPLOYEE CREATION" + ConsoleColours.RESET);

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

            System.out.print("Date Of Birth (MM/DD/YYYY): ");
            String birthday = SCAN.nextLine().trim();
            try {
                EmployeeParser.parseBirthday(birthday);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Entry - candidate must be 18 and not dead.");
                continue;
            }
//
            System.out.print("Date Of Joining (MM/DD/YYYY): ");
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

            System.out.println();
            printRecordHeader();
            System.out.println(newEmployee.printNicely() + "\n");

            System.out.print("See details above. Submit to database? (Y/N): ");
            String submit = SCAN.nextLine().trim().toLowerCase();
            if (submit.equals("y")) {

                employeeDAO.addToBatchStatement(newEmployee);
                employeeDAO.createEmployee();
                System.out.print("\n" + ConsoleColours.GREEN_BOLD + "Employee successfully submitted to database." + ConsoleColours.RESET);

            } else {

                System.out.print("Employee not submitted to database.");

            }

            System.out.print("\nAdd another? (Y/N):");
            String another = SCAN.nextLine();
            if (!another.equals("y".toLowerCase())) {
                break;
            }
        }
    }

    private void searchEmployeeMenu() {
        System.out.println(ConsoleColours.UNDERLINE + "\nEMPLOYEE SEARCH\n" + ConsoleColours.RESET);

        ArrayList<Employee> employeeList = null;

        outer:
        while (true) {
            System.out.println("0: ID");
            System.out.println("1: Title");
            System.out.println("2: First Name");
            System.out.println("3: Middle Initial");
            System.out.println("4: Last Name");
            System.out.println("5: Gender");
            System.out.println("6: Email");
            System.out.println("7: Birthday");
            System.out.println("8: Join Date");
            System.out.println("9: Salary");

            System.out.print("\nWhat would you like to search by? (Q: MAIN MENU): ");
            String userInput = SCAN.nextLine();

            switch (userInput) {
                case "0" -> employeeList = employeeDAO.getEmployee("id", enterSearchValue("ID"));
                case "1" -> employeeList = employeeDAO.getEmployee("prefix", enterSearchValue("Title"));
                case "2" -> employeeList = employeeDAO.getEmployee("first name", enterSearchValue("First Name"));
                case "3" -> employeeList = employeeDAO.getEmployee("middle initial", enterSearchValue("Middle Initial"));
                case "4" -> employeeList = employeeDAO.getEmployee("last name", enterSearchValue("Last Name"));
                case "5" -> employeeList = employeeDAO.getEmployee("gender", enterSearchValue("Gender"));
                case "6" -> employeeList = employeeDAO.getEmployee("email", enterSearchValue("Email"));
                case "7" -> employeeList = employeeDAO.getEmployee("dob", enterSearchValue("Date of Birth"));
                case "8" -> employeeList = employeeDAO.getEmployee("doj", enterSearchValue("Date of Joining"));
                case "9" -> employeeList = employeeDAO.getEmployeesBySalaryRange(
                        enterSearchValue("Salary Lower Bound"),
                        enterSearchValue("Salary Higher Bound"));
                case "q", "Q" -> {
                    break outer;
                }
                default -> {
                    System.out.println(ConsoleColours.RED_BOLD + "INVALID SELECTION" + ConsoleColours.RESET);
                    continue outer;
                }
            }


            if (!employeeList.isEmpty()) {
                System.out.println("\n" + ConsoleColours.GREEN_BOLD + "FOUND " + employeeList.size() + " RECORD(S)" + ConsoleColours.RESET + "\n");
                printRecordHeader();
                for (Employee emp : employeeList) {
                    System.out.println(emp.printNicely());
                }

                System.out.print("\n" + "Search again? (Y/N):");
                String another = SCAN.nextLine();
                if (!another.equalsIgnoreCase("y")) {
                    break;
                }

            } else {
                System.out.println(ConsoleColours.RED + "NO RECORDS FOUND\n" + ConsoleColours.RESET);
            }
        }
    }

    public String enterSearchValue(String fieldName) {
        System.out.print("Enter " + fieldName + ": ");
        return SCAN.nextLine();
    }

    private void updateEmployeeMenu() {
        System.out.println("\n" + ConsoleColours.UNDERLINE + "EMPLOYEE UPDATE" + ConsoleColours.RESET);

        while (true) {
            System.out.print("\n" + "Enter ID (Q: MAIN MENU): ");

            String id = SCAN.nextLine();
            if (id.equalsIgnoreCase("Q")) {
                break;
            }

            ArrayList<Employee> searchedEmployee = employeeDAO.getEmployee("id", id);
            if (!searchedEmployee.isEmpty()) {
                System.out.println(ConsoleColours.GREEN + "FOUND" + ConsoleColours.RESET + "\n");
                printRecordHeader();
                System.out.println(searchedEmployee.getFirst().printNicely());
                updateEmployeeSubMenu(searchedEmployee.getFirst().empId());
            } else {
                System.out.println(ConsoleColours.RED + "NO RECORDS FOUND\n" + ConsoleColours.RESET);
            }
        }
    }

    public void updateEmployeeSubMenu(String employeeID) {
        System.out.println("\n1: Title");
        System.out.println("2: First Name");
        System.out.println("3: Middle Initial");
        System.out.println("4: Last Name");
        System.out.println("5: Gender");
        System.out.println("6: Email");
        System.out.println("7: Birthday");
        System.out.println("8: Join Date");
        System.out.println("9: Salary");

        outer:
        while (true) {
            System.out.print("\nChoose field to update (Q: Return to ENTER ID): ");
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
                case "Q", "q" -> {
                    break outer;
                }
                default -> {
                    System.out.println(ConsoleColours.RED_BOLD + "INVALID SELECTION" + ConsoleColours.RESET);
                    continue outer;
                }
            }
            System.out.println("\n" + ConsoleColours.GREEN + "Employee successfully updated" + ConsoleColours.RESET);
            ArrayList<Employee> updated = employeeDAO.getEmployee("id", employeeID);

            printRecordHeader();
            for (Employee emp : updated) {
                System.out.println(emp.printNicely());
            }
        }
    }

    public String enterUpdateValue(String fieldName) {
        System.out.print("Enter new " + fieldName + ": ");
        return SCAN.nextLine();
    }

    private void deleteEmployeeMenu() {
        System.out.println("\n" + ConsoleColours.UNDERLINE + "EMPLOYEE DELETION" + ConsoleColours.RESET);

        while (true) {
            System.out.print("\n" + "Enter ID ('Q' for MAIN MENU): ");

            String id = SCAN.nextLine();
            if (id.equalsIgnoreCase("q")) {
                break;
            }

            ArrayList<Employee> searchedEmployee = employeeDAO.getEmployee("id", id);
            if (!searchedEmployee.isEmpty()) {

                System.out.println(ConsoleColours.GREEN + "FOUND" + ConsoleColours.RESET + "\n");
                printRecordHeader();
                System.out.println(searchedEmployee.getFirst().printNicely());
                System.out.print("\nAre you sure you would like to delete this employee? (Y/N): ");
                String userInput = SCAN.nextLine();
                if (userInput.equalsIgnoreCase("y")) {
                    employeeDAO.deleteEmployee(id);
                    System.out.println(ConsoleColours.GREEN + "Employee deleted." + ConsoleColours.RESET);
                }
            } else {
                System.out.println(ConsoleColours.RED + "NO RECORDS FOUND" + ConsoleColours.RESET);
            }
        }
    }

    public void printRecordHeader() {
        String id = "ID";
        String prefix = "TITLE";
        String name = "NAME";
        String gender = "GENDER";
        String email = "EMAIL";
        String birthday = "DOB";
        String joinDate = "JOIN DATE";
        String salary = "SALARY";
        //// String.format("%-10s "  + "%-25s " + "%-10s" + "%-30s" + "%-11s" + "%-11s" + "%-12s" + salary, empId, fullName, gender, email, dob, dateOfJoining, salary);
        System.out.println(String.format(ConsoleColours.CYAN_BOLD + "%-10s " + "%-10s" + "%-25s" + "%-12s" + "%-35s" + "%-20s" + "%-20s" + "%-10s" + ConsoleColours.RESET, id, name, prefix, gender, email, birthday, joinDate, salary));
    }
}
