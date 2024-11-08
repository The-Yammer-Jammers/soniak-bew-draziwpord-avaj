package org.example.daos;

import org.example.models.DeliveryEmployee;
import org.example.models.DeliveryEmployeeCreateRequest;
import org.example.models.DeliveryEmployeeRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeliveryEmployeeDao {

    private DeliveryEmployee createAndReturnDeliveryEmployee(
            final ResultSet resultSet
    )
            throws SQLException {
        return new DeliveryEmployee(
                resultSet.getInt("employeeId"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getDouble("salary"),
                resultSet.getString("bankAccountNumber"),
                resultSet.getString("nationalInsuranceNumber"));
    }


    public List<DeliveryEmployee> getAllDeliveryEmployees()
            throws SQLException {
        List<DeliveryEmployee> deliveryEmployees = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT emp.employeeId, emp.firstName, "
                            + "emp.lastName, emp.salary,"
                            + " emp.bankAccountNumber, "
                            + "emp.nationalInsuranceNumber "
                            + "FROM Employee emp JOIN DeliveryEmployee "
                            + "delEmp ON emp.employeeId "
                            + "= delEmp.employeeId;");

            while (resultSet.next()) {
                DeliveryEmployee deliveryEmployee =
                        createAndReturnDeliveryEmployee(resultSet);

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
                    "SELECT emp.employeeId, emp.firstName, "
                            + "emp.lastName, emp.salary, "
                            + "emp.bankAccountNumber, "
                            + "emp.nationalInsuranceNumber "
                            + "FROM Employee emp JOIN DeliveryEmployee "
                            + "delEmp ON emp.employeeId = delEmp.employeeId "
                            + "WHERE delEmp.employeeId = (?);";



            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                deliveryEmployee = createAndReturnDeliveryEmployee(resultSet);
            }
        }

        return deliveryEmployee;
    }

    public void updateDeliveryEmployee(
            final int id, final DeliveryEmployeeRequest deliveryEmployee
    )
            throws SQLException {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String statement = "UPDATE Employee SET firstName=(?), "
                    + "lastName=(?), salary=(?), bankAccountNumber=(?) "
                    + "WHERE employeeId IN (SELECT "
                    + "employeeId FROM DeliveryEmployee "
                    + "WHERE employeeId=(?));";
            PreparedStatement pst = conn.prepareStatement(statement);

            final int firstNameIndex = 1;
            final int lastNameIndex = 2;
            final int salaryIndex = 3;
            final int bankAccountNumberIndex = 4;
            final int lastInt = 5;

            pst.setString(firstNameIndex, deliveryEmployee.getFirstName());
            pst.setString(lastNameIndex, deliveryEmployee.getLastName());
            pst.setDouble(salaryIndex, deliveryEmployee.getSalary());
            pst.setString(bankAccountNumberIndex,
                    deliveryEmployee.getBankAccountNumber()
            );
            pst.setInt(lastInt, id);

            pst.executeUpdate();
        }
    }

    public int createDeliveryEmployee(
            final DeliveryEmployeeCreateRequest deliveryEmployee
    ) throws SQLException {
        try (Connection conn = DatabaseConnector.getConnection()) {

            String insertStatement = "INSERT INTO Employee (firstName, "
                    + "lastName, salary, bankAccountNumber, "
                    + "nationalInsuranceNumber) VALUES (?,?,?,?,?);";

            PreparedStatement pst = conn.prepareStatement(
                    insertStatement, Statement.RETURN_GENERATED_KEYS
            );

            final int firstNameIndex = 1;
            final int lastNameIndex = 2;
            final int salaryIndex = 3;
            final int bankAccountNumberIndex = 4;
            final int nationalInsuranceNumberIndex = 5;

            pst.setString(firstNameIndex, deliveryEmployee.getFirstName());
            pst.setString(lastNameIndex, deliveryEmployee.getLastName());
            pst.setDouble(salaryIndex, deliveryEmployee.getSalary());
            pst.setString(
                    bankAccountNumberIndex,
                    deliveryEmployee.getBankAccountNumber()
            );
            pst.setString(nationalInsuranceNumberIndex,
                    deliveryEmployee.getNationalInsuranceNumber()
            );

            pst.executeUpdate();

            ResultSet res = pst.getGeneratedKeys();

            if (res.next()) {
                String insertStatementDeliveryEmployee = "INSERT INTO "
                        + "DeliveryEmployee"
                        + " (employeeId) VALUES (?);";

                PreparedStatement pstDeliveryEmployee = conn.prepareStatement(
                        insertStatementDeliveryEmployee,
                        Statement.RETURN_GENERATED_KEYS
                );

                final int idIndex = 1;

                pstDeliveryEmployee.setInt(idIndex, res.getInt(1));

                return res.getInt(1);
            }

            return -1;
        }
    }

    public void deleteDeliveryEmployee(final int id) throws SQLException {
        Connection conn = DatabaseConnector.getConnection();

        String statementDeliveryEmployee = "DELETE FROM DeliveryEmployee "
                + "WHERE employeeId=(?);";
        PreparedStatement pstDeliveryEmployee = conn
                .prepareStatement(statementDeliveryEmployee);
        pstDeliveryEmployee.setInt(1, id);
        pstDeliveryEmployee.executeUpdate();

        String statement = "DELETE FROM Employee WHERE employeeId = (?)";
        PreparedStatement pst = conn.prepareStatement(statement);
        pst.setInt(1, id);
        pst.executeUpdate();
    }
}
