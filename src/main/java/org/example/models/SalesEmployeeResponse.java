package org.example.models;

public class SalesEmployeeResponse extends EmployeeResponse {
    double commissionRate;
    public SalesEmployeeResponse(final int employeeId, final String firstName,
                            final String lastName, final double salary,
                            final String bankAccountNumber,
                            final String nationalInsuranceNumber,
                            final double commissionRate) {
        super(employeeId, firstName, lastName,
                salary, bankAccountNumber, nationalInsuranceNumber);
        this.commissionRate = commissionRate;
    }

    public double getCommissionRate() {
        return commissionRate;
    }
}
