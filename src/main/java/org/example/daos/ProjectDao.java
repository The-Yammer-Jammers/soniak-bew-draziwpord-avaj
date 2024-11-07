package org.example.daos;

import org.example.models.Project;
import org.example.models.ProjectRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao {

    public List<Project> getAllProjects() throws SQLException {
        List<Project> projects = new ArrayList<>();
        List<Integer> deliveryEmployees = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            String selectQuery =
                    "SELECT deliveryEmployeeId FROM DeliveryEmployee_Project "
                            + "INNER JOIN Project ON "
                            + "Project.projectId = "
                            + "DeliveryEmployee_Project.projectId "
                            + "WHERE Project.projectId = ?;";

            PreparedStatement preparedStatement =
                    connection.prepareStatement(selectQuery);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT projectId,"
                            + "name,"
                            + "projectValue,"
                            + "clientId,"
                            + "techLeadId,"
                            + "salesEmployeeId,"
                            + "completed "
                            + "FROM Project"
            );

            while (resultSet.next()) {
                final int projectId = resultSet.getInt("projectId");
                preparedStatement.setInt(1, projectId);
                ResultSet deliveryResultSet =  preparedStatement.executeQuery();

                while (deliveryResultSet.next()) {
                    final int deliveryEmployeeId =
                            deliveryResultSet.getInt("deliveryEmployeeId");
                    deliveryEmployees.add(deliveryEmployeeId);
                }

                Project project = new Project(
                        projectId,
                        resultSet.getString("name"),
                        resultSet.getDouble("projectValue"),
                        resultSet.getInt("clientId"),
                        deliveryEmployees,
                        resultSet.getInt("techLeadId"),
                        resultSet.getBoolean("completed"));
                projects.add(project);
            }
        }

        return projects;
    }

    public Project getProjectById(final int projectId) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            List<Integer> deliveryEmployees = new ArrayList<>();
            String deliveryIdQuery =
                    "SELECT deliveryEmployeeId FROM DeliveryEmployee_Project "
                            + "INNER JOIN Project ON "
                            + "Project.projectId = "
                            + "DeliveryEmployee_Project.projectId "
                            + "WHERE Project.projectId = ?;";

            String projectIdQuery =
                    "SELECT projectId,"
                            + "name,"
                            + "projectValue,"
                            + "clientId,"
                            + "techLeadId,"
                            + "salesEmployeeId,"
                            + "completed "
                            + "FROM Project WHERE projectId = ?";
            // Get Delivery employees where projectId = projectId

            PreparedStatement deliveryStatement =
                    connection.prepareStatement(deliveryIdQuery);

            // Get selected projectId
            PreparedStatement projectIdStatement =
                    connection.prepareStatement(projectIdQuery);
            projectIdStatement.setInt(1, projectId);

            ResultSet projectIdResultSet = projectIdStatement.executeQuery();

            if (projectIdResultSet.next()) {
                deliveryStatement.setInt(1, projectId);
                ResultSet deliveryResultSet = deliveryStatement.executeQuery();

                while (deliveryResultSet.next()) {
                    final int deliveryEmployeeId =
                            deliveryResultSet.getInt("deliveryEmployeeId");
                    deliveryEmployees.add(deliveryEmployeeId);
                }
                return new Project(
                        projectId,
                        projectIdResultSet.getString("name"),
                        projectIdResultSet.getDouble("projectValue"),
                        projectIdResultSet.getInt("clientId"),
                        deliveryEmployees,
                        projectIdResultSet.getInt("techLeadId"),
                        projectIdResultSet.getBoolean("completed"));
            }
        }
        return null;
    }

    public void updateProject(final int projectId,
                              final ProjectRequest projectRequest)
            throws SQLException {
        Connection connection = DatabaseConnector.getConnection();

        String updateStatement =
                "UPDATE Project SET completed = ? WHERE projectId = ?";

        PreparedStatement statement =
                connection.prepareStatement(updateStatement);
        statement.setBoolean(1, projectRequest.getCompleted());
        statement.setInt(2, projectId);

        statement.executeUpdate();
    }

    public int createProject(final ProjectRequest projectRequest)
            throws SQLException {
        Connection c = DatabaseConnector.getConnection();
        String selectStatement =
                "INSERT INTO Project(name,"
                        + "projectValue,"
                        + "clientId,"
                        + "techLeadId,"
                        + "salesEmployeeId) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement st = c.prepareStatement(selectStatement,
                Statement.RETURN_GENERATED_KEYS);

        final int firstValue = 1;
        final int secondValue = 2;
        final int thirdValue = 3;
        final int fourthValue = 4;
        final int fifthValue = 5;

        st.setString(firstValue, projectRequest.getName());
        st.setDouble(secondValue, projectRequest.getProjectValue());
        st.setInt(thirdValue, projectRequest.getClientId());
        st.setInt(fourthValue, projectRequest.getTechLeadId());
        st.setInt(fifthValue, projectRequest.getSalesEmployeeId());

        st.executeUpdate();

        ResultSet rs = st.getGeneratedKeys();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return -1;
    }
}
