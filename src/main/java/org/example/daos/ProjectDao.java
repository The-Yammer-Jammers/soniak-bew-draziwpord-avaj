package org.example.daos;

import org.example.models.Project;

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
                    "SELECT projectId, name, projectValue, clientId, techLeadId, salesEmployeeId, completed "
                            + "FROM Project"
            );
            // Get deliveryEmployeeId


            while (resultSet.next()) {
                final int projectId = resultSet.getInt("projectId");
                preparedStatement.setInt(1, projectId);
                ResultSet deliveryResultSet =  preparedStatement.executeQuery();

                if (!deliveryResultSet.next()) {
                    continue;
                }

                final int deliveryEmployeeId = deliveryResultSet.getInt("deliveryEmployeeId");
                Project project = new Project(
                        projectId,
                        resultSet.getString("name"),
                        resultSet.getDouble("projectValue"),
                        resultSet.getInt("clientId"),
                        resultSet.getInt("techLeadId"),
                        deliveryEmployeeId,
                        resultSet.getBoolean("completed"));
                projects.add(project);
            }
        }

        return projects;
    }
}
