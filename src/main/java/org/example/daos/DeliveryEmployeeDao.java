package org.example.daos;

import org.example.models.DeliveryEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeliveryEmployeeDao {

    public List<DeliveryEmployee> getAllDeliveryEmployees()
            throws SQLException {
        List<DeliveryEmployee> deliveryEmployees = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT employeeId, firstName, lastName, salary, bankAccountNumber, nationalInsuranceNumber FROM Employee WHERE employeeId IN (SELECT employeeId FROM DeliveryEmployee);"
            );

            while (resultSet.next()) {
                DeliveryEmployee deliveryEmployee = new DeliveryEmployee(
                        resultSet.getInt("deliveryEmployeeID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("bankAccountNumber"),
                        resultSet.getString("nationalInsuranceNumber")
                );

                deliveryEmployees.add(deliveryEmployee);
            }

        }
        System.out.println(deliveryEmployees);
        return deliveryEmployees;
    }

    public DeliveryEmployee getDeliveryEmployeeById(final int id)
            throws SQLException {

        DeliveryEmployee deliveryEmployee = null;
        
        try (Connection connection = DatabaseConnector.getConnection()) {

            String query =
                    "SELECT employeeId, firstName, lastName, salary, bankAccountNumber, nationalInsuranceNumber FROM Employee WHERE employeeId IN (SELECT employeeId FROM DeliveryEmployee WHERE employeeId = (?));";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                deliveryEmployee = new DeliveryEmployee(
                        resultSet.getInt("employeeId"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("bankAccountNumber"),
                        resultSet.getString("nationalInsuranceNumber")
                );
            }
        }

        return deliveryEmployee;
    }
}
