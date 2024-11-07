package org.example.models;

public class DeliveryEmployee {
    private final int employeeId;
    private final String firstName;
    private final String lastName;
    private final Double salary;
    private final String bankAccountNumber;
    private final String nationalInsuranceNumber;

    public DeliveryEmployee(final int employeeId,
                            final String firstName,
                            final String lastName,
                            final Double salary,
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

    public Double getSalary() {
        return salary;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public String getNationalInsuranceNumber() {
        return nationalInsuranceNumber;
    }
}
