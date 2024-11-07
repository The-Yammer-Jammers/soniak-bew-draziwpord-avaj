package org.example.daos;

import org.example.models.LoginRequest;
import org.example.models.RegisterRequest;
import org.example.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDao {
    public User getUser(final LoginRequest loginRequest) throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "SELECT Username, Password, RoleID "
                    + "FROM `User` WHERE BINARY Username = ? "
                    + "AND BINARY Password = ?;";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, loginRequest.getUsername());
            statement.setString(2, loginRequest.getPassword());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new User(
                        resultSet.getString("Username"),
                        resultSet.getString("Password"),
                        resultSet.getInt("RoleID")
                );
            }
        }

        return null;
    }

    public boolean registerUser(final RegisterRequest registerRequest)
            throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO `User` (Username, Password, RoleID) "
                    + "VALUES (?, ?, 2);";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, registerRequest.getUsername());
            statement.setString(2, registerRequest.getPassword());

            int result = statement.executeUpdate();
            return result > 0;
        }
    }
}
