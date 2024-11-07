package org.example.mappers;

import org.example.models.SalesEmployee;
import org.example.models.SalesEmployeeResponse;

import java.util.List;
import java.util.stream.Collectors;

public final class SalesEmployeeMapper {
    private SalesEmployeeMapper() { }
    public static List<SalesEmployeeResponse> toResponseList(final List<SalesEmployee> salesEmployees) {
        return salesEmployees
                .stream().map(SalesEmployeeMapper::toResponse)
                .collect(Collectors.toList());
    }

    public static SalesEmployeeResponse toResponse(final SalesEmployee salesEmployee) {
        return new SalesEmployeeResponse(
                salesEmployee.getEmployeeId(),
                salesEmployee.getFirstName(),
                salesEmployee.getLastName(),
                salesEmployee.getSalary(),
                salesEmployee.getBankAccountNumber(),
                salesEmployee.getNationalInsuranceNumber(),
                salesEmployee.getCommissionRate()
        );
    }
}
