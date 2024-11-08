package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SalesEmployeeRequest extends EmployeeRequest {
    private double commissionRate;
    @JsonCreator
    public SalesEmployeeRequest(
            final @JsonProperty("firstName") String firstName,
            final @JsonProperty("lastName") String lastName,
            final @JsonProperty("salary")Double salary,
            final @JsonProperty("bankAccountNumber")String bankAccountNumber,
            final @JsonProperty("nationalInsuranceNumber")
            String nationalInsuranceNumber,
            final @JsonProperty("commissionRate")Double commissionRate
            ) {
            super(firstName, lastName, salary,
                    bankAccountNumber, nationalInsuranceNumber);
            this.commissionRate = commissionRate;
        }


    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(final double commissionRate) {
        this.commissionRate = commissionRate;
    }
}
