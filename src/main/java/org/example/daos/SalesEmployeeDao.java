package org.example.daos;

import org.example.models.SalesEmployee;
import org.example.models.SalesEmployeeRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SalesEmployeeDao {
    static final int STATEMENT_PARAMETER_ONE = 1;
    static final int STATEMENT_PARAMETER_TWO = 2;
    static final int STATEMENT_PARAMETER_THREE = 3;
    static final int STATEMENT_PARAMETER_FOUR = 4;
    static final int STATEMENT_PARAMETER_FIVE = 5;
    static final int STATEMENT_PARAMETER_SIX = 6;

    public List<SalesEmployee> getSalesEmployees() throws SQLException {
        List<SalesEmployee> salesEmployees = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT Employee.employeeId, firstName, lastName, salary, "
                            + "bankAccountNumber, nationalInsuranceNumber, "
                            + "commissionRate FROM Employee INNER JOIN "
                            + "SalesEmployee ON Employee.employeeId = "
                            + "SalesEmployee.employeeId");

            while (resultSet.next()) {
                SalesEmployee salesEmployee =
                        getSalesEmployeeResultSet(resultSet);

                salesEmployees.add(salesEmployee);
            }
        }
        return salesEmployees;
    }
    public SalesEmployee getSalesEmployeeById(final int id)
            throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            final String query =
                    ("SELECT Employee.employeeId, firstName, lastName, salary, "
                    + "bankAccountNumber, nationalInsuranceNumber, "
                    + "commissionRate FROM Employee INNER JOIN "
                    + "SalesEmployee ON Employee.employeeId = "
                    + "SalesEmployee.employeeId "
                    + "WHERE SalesEmployee.employeeId = ?");
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return getSalesEmployeeResultSet(resultSet);

            }
        }
        return null;
    }
    public int createSalesEmployee(final SalesEmployeeRequest
                                           salesEmployee) throws SQLException {
        try (Connection c = DatabaseConnector.getConnection();) {
            String insertStatement = "INSERT INTO Employee(firstName, lastName,"
                    + " salary, bankAccountNumber, nationalInsuranceNumber)"
                    + " VALUES(?,?,?,?,?);";
            PreparedStatement st = c.prepareStatement(insertStatement,
                    Statement.RETURN_GENERATED_KEYS);
            st.setString(STATEMENT_PARAMETER_ONE, salesEmployee.getFirstName());
            st.setString(STATEMENT_PARAMETER_TWO, salesEmployee.getLastName());
            st.setDouble(STATEMENT_PARAMETER_THREE, salesEmployee.getSalary());
            st.setString(STATEMENT_PARAMETER_FOUR,
                    salesEmployee.getBankAccountNumber());
            st.setString(STATEMENT_PARAMETER_FIVE,
                    salesEmployee.getNationalInsuranceNumber());
            st.executeUpdate();

            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                int employeeId = rs.getInt(1);

                insertStatement = "INSERT INTO SalesEmployee(employeeId, "
                        + "CommissionRate) VALUES(?,?)";

                PreparedStatement st2 = c.prepareStatement(insertStatement);
                st2.setInt(STATEMENT_PARAMETER_ONE, employeeId);
                st2.setDouble(STATEMENT_PARAMETER_TWO,
                        salesEmployee.getCommissionRate());
                int rowsUpdated = st2.executeUpdate();

                if (rowsUpdated == 1) {
                    return employeeId;
                }
            }

            return -1;
        }


    }
    public void updateSalesEmployee(final int id,
                                    final SalesEmployeeRequest salesEmployee)
            throws SQLException {
        try (Connection c = DatabaseConnector.getConnection();) {
            String updateStatement = "UPDATE Employee SET firstName=?,"
                    + "lastName=?,salary=?,"
                    + "bankAccountNumber=?,nationalInsuranceNumber=?"
                    + "WHERE employeeId = ?;";
            PreparedStatement st = c.prepareStatement(updateStatement);
            st.setString(STATEMENT_PARAMETER_ONE, salesEmployee.getFirstName());
            st.setString(STATEMENT_PARAMETER_TWO, salesEmployee.getLastName());
            st.setDouble(STATEMENT_PARAMETER_THREE, salesEmployee.getSalary());
            st.setString(STATEMENT_PARAMETER_FOUR,
                    salesEmployee.getBankAccountNumber());
            st.setString(STATEMENT_PARAMETER_FIVE,
                    salesEmployee.getNationalInsuranceNumber());
            st.setInt(STATEMENT_PARAMETER_SIX, id);
            st.executeUpdate();
            updateStatement = "UPDATE SalesEmployee"
                    + " SET commissionRate=?"
                    + " WHERE employeeId = ?;";
            PreparedStatement st2 = c.prepareStatement(updateStatement);
            st2.setDouble(STATEMENT_PARAMETER_ONE,
                    salesEmployee.getCommissionRate());
            st2.setInt(STATEMENT_PARAMETER_TWO, id);
            st2.executeUpdate();
        }
    }
    public void deleteSalesEmployee(final int id) throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        String deleteStatement = "DELETE FROM "
                + "SalesEmployee WHERE EmployeeId = ?";
        PreparedStatement st = c.prepareStatement(deleteStatement);
        st.setInt(STATEMENT_PARAMETER_ONE, id);
        st.executeUpdate();
        deleteStatement = "DELETE FROM "
                + "Employee WHERE EmployeeId = ?";
        PreparedStatement st2 = c.prepareStatement(deleteStatement);
        st2.setInt(STATEMENT_PARAMETER_ONE, id);
        st2.executeUpdate();
    }

    public SalesEmployee getSalesEmployeeResultSet(
            final ResultSet resultSet) throws SQLException {
        return new SalesEmployee(
                resultSet.getInt("EmployeeId"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getDouble("salary"),
                resultSet.getString("bankAccountNumber"),
                resultSet.getString("nationalInsuranceNumber"),
                resultSet.getDouble("commissionRate")
        );

    }

}
