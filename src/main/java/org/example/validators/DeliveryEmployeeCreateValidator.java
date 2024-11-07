package org.example.validators;

import org.example.exceptions.InvalidException;
import org.example.models.DeliveryEmployeeCreateRequest;
import org.example.exceptions.Entity;

public class DeliveryEmployeeCreateValidator {
    private static final int MAX_NAME_CHARACTER_LENGTH = 25;
    private static final int BANK_ACCOUNT_NUMBER_LENGTH = 8;
    private static final int NATIONAL_INSURANCE_NUMBER_LENGTH = 9;

    public void validateDeliveryEmployee(
            final DeliveryEmployeeCreateRequest deliveryEmployeeRequest
    ) throws InvalidException {


        if (deliveryEmployeeRequest.getFirstName().length()
                > MAX_NAME_CHARACTER_LENGTH
                ||
                deliveryEmployeeRequest.getLastName().length()
                        > MAX_NAME_CHARACTER_LENGTH
        ) {
            throw new InvalidException(Entity.DELIVERY_EMPLOYEE,
                    "name is greater than 25 characters."
            );
        }

        if (deliveryEmployeeRequest.getBankAccountNumber().length()
                != BANK_ACCOUNT_NUMBER_LENGTH
        ) {
            throw new InvalidException(Entity.DELIVERY_EMPLOYEE,
                    "bank account number is not 8 numbers."
            );
        }
        if (deliveryEmployeeRequest.getNationalInsuranceNumber().length()
                != NATIONAL_INSURANCE_NUMBER_LENGTH
        ) {
            throw new InvalidException(Entity.DELIVERY_EMPLOYEE,
                    "national insurance number is not 9 characters."
            );
        }
    }
}
