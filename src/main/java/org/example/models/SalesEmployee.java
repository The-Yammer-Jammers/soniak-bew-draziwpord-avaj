package org.example.models;
public class SalesEmployee extends Employee {
    private double commissionRate;
    public  SalesEmployee(final int employeeId, final String firstName,
                          final String lastName, final double salary,
                          final String bankAccountNumber,
                          final String nationalInsuranceNumber,
                          final double commissionRate) {
        super(employeeId, firstName, lastName, salary,
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
