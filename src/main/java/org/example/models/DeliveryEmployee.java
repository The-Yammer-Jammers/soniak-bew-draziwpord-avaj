package org.example.models;

public class DeliveryEmployee {
    private int deliveryEmployeeId;
    private String firstName;
    private String lastName;
    private Double salary;
    private String bankAccountNumber;
    private String nationalInsuranceNumber;

    public DeliveryEmployee(int deliveryEmployeeId,
                            String firstName,
                            String lastName, Double salary,
                            String bankAccountNumber,
                            String nationalInsuranceNumber) {
        this.deliveryEmployeeId = deliveryEmployeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.bankAccountNumber = bankAccountNumber;
        this.nationalInsuranceNumber = nationalInsuranceNumber;
    }

    public int getDeliveryEmployeeId() {
        return deliveryEmployeeId;
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
