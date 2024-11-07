package org.example.models;

public class EmployeeResponse {
    private int employeeId;
    private String firstName;
    private String lastName;
    private double salary;
    private String bankAccountNumber;
    private String nationalInsuranceNumber;
    public EmployeeResponse(final int employeeId, final String firstName,
                           final String lastName, final double salary,
                           final String bankAccountNumber,
                           final String nationalInsuranceNumber) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.bankAccountNumber = bankAccountNumber;
        this.nationalInsuranceNumber = nationalInsuranceNumber;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getSalary() {
        return salary;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public String getNationalInsuranceNumber() {
        return nationalInsuranceNumber;
    }
}
