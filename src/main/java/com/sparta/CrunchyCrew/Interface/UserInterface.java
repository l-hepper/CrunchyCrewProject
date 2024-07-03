package com.sparta.CrunchyCrew.Interface;

import com.sparta.CrunchyCrew.Employee;
import com.sparta.CrunchyCrew.EmployeeDAO;

import java.io.Console;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UserInterface {

    private EmployeeDAO employeeDAO = new EmployeeDAO();
    private final Scanner SCAN = new Scanner(System.in);

    public void start() {
        System.out.println("\nWelcome to the CrunchyCrew CRM.");

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
                case("2") -> searchEmployeeMenu();
                case("3") -> updateEmployeeMenu();
                case("4") -> deleteEmployeeMenu();
                case("Q") -> {System.out.println("Shutting down CrunchyCrew CRM...Goodbye."); exit = true;}
                default -> System.out.println("Invalid command. Please try again.");
            }
        } while (!exit);
    }

    private void createEmployeeMenu() {
        System.out.println(ConsoleColours.UNDERLINE + "EMPLOYEE CREATION" + ConsoleColours.RESET);

        while (true) {
            System.out.println("Please provide the new employee's details: \n");

            System.out.print("ID (6 digits only): ");
            String id = SCAN.nextLine().trim();

            System.out.print("Title (Mr/Mrs/Miss/Ms/Drs): ");
            String title = SCAN.nextLine().trim();

            System.out.print("First Name: ");
            String firstName = SCAN.nextLine().trim();

            System.out.print("Middle Initial (leave blank if no middle name): ");
            String middleInitial = SCAN.nextLine().trim();

            System.out.print("Last Name: " );
            String lastName = SCAN.nextLine().trim();

            System.out.print("Gender (M/F): ");
            String gender = SCAN.nextLine().trim();

            System.out.print("Email (xxxxxx@xxxx.xxx): ");
            String email = SCAN.nextLine().trim();

            System.out.print("Date Of Birth (MM-DD-YYYY): ");
            LocalDate dateOfBirth = LocalDate.parse(SCAN.nextLine().trim(), DateTimeFormatter.ofPattern("[MM/dd/yyyy][M/d/yyyy][M/dd/yyyy][M/d/yyyy]"));
//
            System.out.print("Date Of Joining (MM-DD-YYYY): ");
            LocalDate dateOfJoining = LocalDate.parse(SCAN.nextLine().trim(), DateTimeFormatter.ofPattern("[MM/dd/yyyy][M/d/yyyy][M/dd/yyyy][M/d/yyyy]"));

            System.out.print("Salary: ");
            String salary = SCAN.nextLine().trim();

            Employee newEmployee = new Employee(id, title, firstName, middleInitial, lastName, gender, email, dateOfBirth, dateOfJoining, salary);
            System.out.println(newEmployee);

            System.out.print("See details above. Submit to database? (Y/N): ");
            String submit = SCAN.nextLine().trim().toLowerCase();
            if (submit.equals("y")) {
                employeeDAO.createEmployee();
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
        System.out.println(ConsoleColours.UNDERLINE + "\nEMPLOYEE SEARCH\n"  + ConsoleColours.RESET);

        boolean exit = false;
        while (true) {
            System.out.print("Enter ID ('M' for MAIN MENU): ");

            String id = SCAN.nextLine();
            if (id.toLowerCase().equals("m")) {
                break;
            }

            Employee searchedEmployee = null; // replace with EmployeeDAO.getEmployee(id);
            if (searchedEmployee != null) {
                System.out.println(ConsoleColours.GREEN + "FOUND" + ConsoleColours.RESET);
                // System.out.println(employee)
            } else {
                System.out.println(ConsoleColours.RED + "NOT FOUND\n" + ConsoleColours.RESET);
            }
        }
    }

    private void updateEmployeeMenu() {
        System.out.println(ConsoleColours.UNDERLINE + "EMPLOYEE UPDATE" + ConsoleColours.RESET);

        boolean exit = false;
        while (true) {
            System.out.print("Enter ID ('M' for MAIN MENU): ");

            String id = SCAN.nextLine();
            if (id.toLowerCase().equals("m")) {
                break;
            }

            Employee searchedEmployee = null; // replace with EmployeeDAO.getEmployee(id);
            if (searchedEmployee != null) {
                System.out.println("FOUND\n");
                // System.out.println(employee);
                // Enter update employee sub menu

            } else {
                System.out.println("\nNOT FOUND\n");
            }
        }
    }

    private void deleteEmployeeMenu() {
        System.out.println(ConsoleColours.UNDERLINE + "EMPLOYEE DELETION" + ConsoleColours.RESET);

        boolean exit = false;
        while (true) {
            System.out.print("Enter ID ('M' for MAIN MENU): ");

            String id = SCAN.nextLine();
            if (id.toLowerCase().equals("m")) {
                break;
            }

            Employee searchedEmployee = null; // replace with EmployeeDAO.getEmployee(id);
            if (searchedEmployee != null) {
                System.out.println("FOUND\n");
                // System.out.println(employee);
                // confirm deletion of employee? if yes EmployeeDAO.deleteEmployee(employee); if no continue loop
            } else {
                System.out.println("NOT FOUND\n");
            }
        }
    }
}


// DateTimeFormatter.ofPattern("[MM/dd/yyyy][M/d/yyyy][M/dd/yyyy][M/d/yyyy]");
