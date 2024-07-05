# CrunchyCrew CRM System

This project was developed to fulfil the requirements of an employee management system that would connect to a database, perform CRUD operations, and validate data. The app was developed in Java using MySQL JDBC and JUnit for testing.

## Features

- [ ] CSV parsing: The application will read a CSV file and populate a local MySQL database with the parsed data.
- [ ] Data Validation: While reading the CSV data, the application will ensure that any entry from the file matches a specified format, labelling the data that does not match as corrupt. 
- [ ] Console Interface: The application has a console interface that allows the user to perform CRUD operations on the database through prepared SQL queries.
- [ ] SQL Interaction: The SQL queries are handled in a way that prevents SQL injection through prepared statements.

## Usage

1. Clone the repo:
   ```sh
   git clone git@github.com:l-hepper/CrunchyCrewProject.git
   ```
2. Navigate to the project directory:
   ```sh
   cd your-repo
   ```
3. Ensure MySQL is installed and create a database called employees that contains a table called employees:
   ```sql
   CREATE DATABASE IF NOT EXISTS employees;

   USE employees;

   CREATE TABLE `employees` (
    `id` VARCHAR(6) PRIMARY KEY,
    `prefix` VARCHAR(5) NOT NULL,
    `first_name` VARCHAR(50) NOT NULL,
    `middle_initial` CHAR NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
    `gender` CHAR NOT NULL,
    `email` VARCHAR(256) NOT NULL,
    `dob` DATE NOT NULL,
    `doj` DATE NOT NULL,
    `salary` INT NOT NULL
   );
   ```   
5. Add a mysql.properties file:
   ```
   url=jdbc:mysql://localhost:3306/employees
   username=your_username
   password=your_password
   ```
