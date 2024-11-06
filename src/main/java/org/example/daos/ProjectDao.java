package org.example.daos;

import org.example.models.Project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao {

    public List<Project> getAllProjects() throws SQLException {
        List<Project> projects = new ArrayList<>();

        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(
                    "SELECT projectId, name, projectValue, clientId, techLeadId, salesEmployeeId, completed "
                            + "FROM Project"
            );


            while (resultSet.next()) {
                Project project = new Project(
                        resultSet.getInt("projectId"),
                        resultSet.getString("name"),
                        resultSet.getDouble("projectValue"),
                        resultSet.getInt("clientId"),
                        resultSet.getInt("techLeadId"),
                        resultSet.getInt("salesEmployeeId"),
                        resultSet.getBoolean("completed"));
                projects.add(project);
            }
        }

        return projects;
    }
}
