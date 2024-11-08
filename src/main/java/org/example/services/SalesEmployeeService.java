package org.example.services;

import org.example.daos.SalesEmployeeDao;
import org.example.exceptions.DoesNotExistException;
import org.example.exceptions.Entity;
import org.example.exceptions.FailedToCreateException;
import org.example.exceptions.InvalidException;
import org.example.mappers.SalesEmployeeMapper;
import org.example.models.SalesEmployee;
import org.example.models.SalesEmployeeRequest;
import org.example.models.SalesEmployeeResponse;
import org.example.validators.SalesEmployeeValidator;

import java.sql.SQLException;
import java.util.List;


public class SalesEmployeeService {
    SalesEmployeeDao salesEmployeeDao;
    SalesEmployeeValidator salesEmployeeValidator =
            new SalesEmployeeValidator();

    public SalesEmployeeService(final SalesEmployeeDao salesEmployeeDao) {
        this.salesEmployeeDao = salesEmployeeDao;
    }

    public List<SalesEmployeeResponse> getSalesEmployees() throws
            SQLException {
        return SalesEmployeeMapper.toResponseList(
                salesEmployeeDao.getSalesEmployees());
    }

    public SalesEmployee getSalesEmployeeByID(final int id) throws SQLException,
            DoesNotExistException {
        SalesEmployee salesEmployee = salesEmployeeDao.getSalesEmployeeById(id);
        checkForNull(salesEmployee);
        return salesEmployee;
    }
    public int createSalesEmployee(
            final SalesEmployeeRequest salesEmployeeRequest) throws
            FailedToCreateException, SQLException, InvalidException {
        salesEmployeeValidator.validateSalesEmployee(salesEmployeeRequest);
        int id = salesEmployeeDao.createSalesEmployee(salesEmployeeRequest);

        if (id == -1) {
            throw new FailedToCreateException(Entity.SALES_EMPLOYEE);
        }

        return  id;
    }
    public void updateSalesEmployee(final int id,
                 final SalesEmployeeRequest salesEmployeeRequest)
            throws InvalidException, DoesNotExistException, SQLException {
        salesEmployeeValidator.validateSalesEmployee(
                salesEmployeeRequest);
        SalesEmployee salesEmployeeToUpdate =
                salesEmployeeDao.getSalesEmployeeById(id);
        checkForNull(salesEmployeeToUpdate);

        salesEmployeeDao.updateSalesEmployee(id, salesEmployeeRequest);
    }

    public void deleteSalesEmployee(
            final int id) throws SQLException, DoesNotExistException {
        SalesEmployee salesEmployeeToUpdate =
                salesEmployeeDao.getSalesEmployeeById(id);
        checkForNull(salesEmployeeToUpdate);
        salesEmployeeDao.deleteSalesEmployee(id);
    }

    public void checkForNull(final SalesEmployee salesEmployee)
            throws DoesNotExistException {
        if (salesEmployee == null) {
            throw new DoesNotExistException(Entity.SALES_EMPLOYEE);
        }
    }
}
