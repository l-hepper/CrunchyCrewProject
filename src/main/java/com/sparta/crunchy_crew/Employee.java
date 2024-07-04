package com.sparta.crunchy_crew;

import java.time.LocalDate;
import java.util.Objects;

public record Employee(String empId,
                       String prefix,
                       String firstName,
                       String middleInitial,
                       String lastName,
                       String gender,
                       String email,
                       LocalDate dob,
                       LocalDate dateOfJoining,
                       String salary) {

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "empId=" + empId +
                ", prefix='" + prefix + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleInitial='" + middleInitial + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", dateOfJoining=" + dateOfJoining +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Employee employee
                && empId.equals(employee.empId)
                && prefix.equals(employee.prefix)
                && firstName.equals(employee.firstName)
                && middleInitial.equals(employee.middleInitial)
                && lastName.equals(employee.lastName)
                && gender.equals(employee.gender)
                && email.equals(employee.email)
                && dob.equals(employee.dob)
                && dateOfJoining.equals(employee.dateOfJoining)
                && salary.equals(employee.salary)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, prefix, firstName, middleInitial, lastName, gender, email, dob, dateOfJoining, salary);
    }
    // String.format("%-10s "  + "%-25s " + "%-10s" + "%-30s" + "%-11s" + "%-11s" + "%-12s" + salary, empId, fullName, gender, email, dob, dateOfJoining, salary);
    public String printNicely() {
        String fullName = firstName + " " + middleInitial + " " + lastName;
        return String.format("%-10s " + "%-25s" + "%-10s" + "%-30s" + "%-20s" + "%-20s", empId, fullName, gender, email, dob, dateOfJoining, salary);
    }
}
