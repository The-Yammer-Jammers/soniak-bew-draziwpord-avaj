package org.example.validators;
import org.example.exceptions.Entity;
import org.example.exceptions.InvalidException;
import org.example.models.SalesEmployeeRequest;

public class SalesEmployeeValidator {
    static final int MAXIMUM_COMMISSION_RATE = 1;
    static final int MAXIMUM_NAME_LENGTH = 50;
    static final int MAXIMUM_BANK_ACCOUNT_NUMBER = 34;
    static final int MAXIMUM_NATIONAL_INSURANCE_NUMBER_LENGTH = 9;
    static final int MAXIMUM_SALARY = 15;

    public void validateSalesEmployee(
            final SalesEmployeeRequest salesEmployeeRequest)
        throws InvalidException {

        if (salesEmployeeRequest.getCommissionRate()
                >= MAXIMUM_COMMISSION_RATE) {
            throw new InvalidException(
                    Entity.SALES_EMPLOYEE,
                    "Commission must not be greater than one"
            );
        }
        if (salesEmployeeRequest.getFirstName()
                .length() > MAXIMUM_NAME_LENGTH) {
            throw new InvalidException(
                    Entity.SALES_EMPLOYEE,
                    "First name cannot be greater"
                    + " than 50 characters"
            );
        }
        if (salesEmployeeRequest.getLastName()
                .length() > MAXIMUM_NAME_LENGTH) {
            throw new InvalidException(
                    Entity.SALES_EMPLOYEE,
                    "Last name cannot be greater"
                    + " than 50 characters");
        }
        if (salesEmployeeRequest.getBankAccountNumber()
                .length()
                > MAXIMUM_BANK_ACCOUNT_NUMBER) {
            throw new InvalidException(
                    Entity.SALES_EMPLOYEE,
                    "Bank Account Number cannot be greater"
                    + " than 34 characters");
        }
        if (salesEmployeeRequest.getNationalInsuranceNumber()
                .length()
                > MAXIMUM_NATIONAL_INSURANCE_NUMBER_LENGTH) {
            throw new InvalidException(
                    Entity.SALES_EMPLOYEE,
                    "National Insurance Number"
                    + " cannot be greater"
                    + " than 9 characters");
        }
        if (Double.toString(salesEmployeeRequest.getSalary())
                .length() > MAXIMUM_SALARY) {
                throw new InvalidException(
                        Entity.SALES_EMPLOYEE,
                        "Salary cannot be greater than 15 digits long"
                );
        }
    }
}
