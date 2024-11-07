package org.example.validators;

import org.example.exceptions.Entity;
import org.example.exceptions.InvalidException;
import org.example.models.DeliveryEmployeeRequest;

public class DeliveryEmployeeValidator {

    public void validateDeliveryEmployee(
            final DeliveryEmployeeRequest deliveryEmployeeRequest
    ) throws InvalidException {
        final int maxNameCharacterLength = 25;
        final int bankAccountNumberLength = 8;

        if (deliveryEmployeeRequest.getFirstName().length()
                > maxNameCharacterLength
                ||
                deliveryEmployeeRequest.getLastName().length()
                        > maxNameCharacterLength
        ) {
            throw new InvalidException(Entity.DELIVERY_EMPLOYEE,
                    "name is greater than 25 characters."
            );
        }

        if (deliveryEmployeeRequest.getBankAccountNumber().length()
                != bankAccountNumberLength
        ) {
            throw new InvalidException(Entity.DELIVERY_EMPLOYEE,
                    "bank account number is not 8 numbers."
            );
        }
    }
}
