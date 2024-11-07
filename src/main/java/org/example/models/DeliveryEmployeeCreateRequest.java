package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DeliveryEmployeeCreateRequest {
    private String firstName;
    private String lastName;
    private Double salary;
    private String bankAccountNumber;
    private String nationalInsuranceNumber;

    @JsonCreator
    public DeliveryEmployeeCreateRequest(
            final @JsonProperty("firstName") String firstName,
            final @JsonProperty("lastName") String lastName,
            final @JsonProperty("salary") Double salary,
            final @JsonProperty("bankAccountNumber") String bankAccountNumber,
            final @JsonProperty("nationalInsuranceNumber")
            String nationalInsuranceNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.bankAccountNumber = bankAccountNumber;
        this.nationalInsuranceNumber = nationalInsuranceNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(final Double salary) {
        this.salary = salary;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(final String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getNationalInsuranceNumber() {
        return nationalInsuranceNumber;
    }

    public void setNationalInsuranceNumber(
            final String nationalInsuranceNumber
    ) {
        this.nationalInsuranceNumber = nationalInsuranceNumber;
    }
}
