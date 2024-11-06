package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryEmployeeRequest {
    private final String firstName;
    private final String lastName;
    private final Double salary;
    private final String bankAccountNumber;

    @JsonCreator
    public DeliveryEmployeeRequest(
            final @JsonProperty("firstName") String firstName,
            final @JsonProperty("lastName") String lastName,
            final @JsonProperty("salary") Double salary,
            final @JsonProperty("bankAccountNumber") String bankAccountNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.bankAccountNumber = bankAccountNumber;
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
}
