package org.example.services;

import org.example.daos.DeliveryEmployeeDao;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.Entity;
import org.example.exceptions.FailedToCreateException;
import org.example.models.DeliveryEmployee;
import org.example.models.DeliveryEmployeeCreateRequest;
import org.example.models.DeliveryEmployeeRequest;
import org.example.validators.DeliveryEmployeeCreateValidator;
import org.example.validators.DeliveryEmployeeValidator;

import java.sql.SQLException;
import java.util.List;

public class DeliveryEmployeeService {
    private final DeliveryEmployeeDao deliveryEmployeeDao;
    private final DeliveryEmployeeCreateValidator createValidator;
    private final DeliveryEmployeeValidator validator;

    public DeliveryEmployeeService(
            final DeliveryEmployeeDao deliveryEmployeeDao,
            final DeliveryEmployeeCreateValidator createValidator,
            final DeliveryEmployeeValidator validator
    ) {
        this.deliveryEmployeeDao = deliveryEmployeeDao;
        this.createValidator = createValidator;
        this.validator = validator;
    }

    public List<DeliveryEmployee> getAllDeliveryEmployees()
            throws SQLException {
        return deliveryEmployeeDao.getAllDeliveryEmployees();
    }

    public DeliveryEmployee getDeliveryEmployeeById(final int id)
            throws SQLException {

        DeliveryEmployee deliveryEmployee = deliveryEmployeeDao
                .getDeliveryEmployeeById(id);

        if (deliveryEmployee == null) {
            throw new DoesNotExistException(
                    Entity.DELIVERY_EMPLOYEE,
                    "delivery employee does not exist."
            );
        }

        return deliveryEmployeeDao.getDeliveryEmployeeById(id);
    }

    public void deleteDeliveryEmployee(final int id) throws SQLException,
            DoesNotExistException {
        DeliveryEmployee deliveryEmployee =
                deliveryEmployeeDao.getDeliveryEmployeeById(id);
        if (deliveryEmployee == null) {
            throw new DoesNotExistException(
                    Entity.DELIVERY_EMPLOYEE,
                    "delivery employee does not exist"
            );
        }
        deliveryEmployeeDao.deleteDeliveryEmployee(id);
    }

    public int createDeliveryEmployee(
            final DeliveryEmployeeCreateRequest deliveryEmployeeCreateRequest
    )
            throws SQLException {
        createValidator.validateDeliveryEmployee(deliveryEmployeeCreateRequest);

        int id = deliveryEmployeeDao.createDeliveryEmployee(
                deliveryEmployeeCreateRequest
        );

        if (id == -1) {
            throw new FailedToCreateException(
                    Entity.DELIVERY_EMPLOYEE, "something went wrong."
            );
        }

        return id;
    }

    public void updateDeliveryEmployee(
            final int id, final DeliveryEmployeeRequest deliveryEmployeeRequest
    )
            throws SQLException {
        validator.validateDeliveryEmployee(deliveryEmployeeRequest);

        DeliveryEmployee deliveryEmployeeToUpdate = deliveryEmployeeDao
                .getDeliveryEmployeeById(id);

        if (deliveryEmployeeToUpdate == null) {
            throw new DoesNotExistException(
                    Entity.DELIVERY_EMPLOYEE,
                    "delivery employee does not exist."
            );
        }

        deliveryEmployeeDao.updateDeliveryEmployee(id, deliveryEmployeeRequest);
    }
}
