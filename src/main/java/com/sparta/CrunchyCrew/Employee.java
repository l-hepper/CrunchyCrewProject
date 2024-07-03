package com.sparta.CrunchyCrew;

import java.time.LocalDate;

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
}