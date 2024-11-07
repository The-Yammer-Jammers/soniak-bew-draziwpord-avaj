package org.example.validators;

import org.example.exceptions.InvalidException;
import org.example.models.DeliveryEmployeeCreateRequest;
import org.example.exceptions.Entity;

public class DeliveryEmployeeCreateValidator {

    public void validateDeliveryEmployee(
            final DeliveryEmployeeCreateRequest deliveryEmployeeRequest
    ) throws InvalidException {
        final int maxNameCharacterLength = 25;
        final int bankAccountNumberLength = 8;
        final int nationalInsuranceNumberLength = 9;

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
        if (deliveryEmployeeRequest.getNationalInsuranceNumber().length()
                != nationalInsuranceNumberLength
        ) {
            throw new InvalidException(Entity.DELIVERY_EMPLOYEE,
                    "national insurance number is not 9 characters."
            );
        }
    }
}
