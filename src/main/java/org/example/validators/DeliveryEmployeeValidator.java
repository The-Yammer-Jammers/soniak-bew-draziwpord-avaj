package org.example.validators;

import org.example.exceptions.Entity;
import org.example.exceptions.InvalidException;
import org.example.models.DeliveryEmployeeRequest;

public class DeliveryEmployeeValidator {

    private static final int MAX_NAME_CHARACTER_LENGTH = 25;
    private static final int BANK_ACCOUNT_NUMBER_LENGTH = 8;

    public void validateDeliveryEmployee(
            final DeliveryEmployeeRequest deliveryEmployeeRequest
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
    }
}
