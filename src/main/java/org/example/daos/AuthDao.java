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
            String query = "SELECT Username, Password, Salt, RoleID "
                    + "FROM `User` WHERE BINARY Username = ?;";

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(1, loginRequest.getUsername());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new User(
                        resultSet.getString("Username"),
                        resultSet.getBytes("Password"),
                        resultSet.getBytes("Salt"),
                        resultSet.getInt("RoleID")
                );
            }
        }

        return null;
    }

    public boolean registerUser(
            final RegisterRequest registerRequest,
            final byte[] hashedPass,
            final byte[] salt
    )
            throws SQLException {
        try (Connection connection = DatabaseConnector.getConnection()) {
            String query = "INSERT INTO `User` "
                    + "(Username, Password, Salt, RoleID) "
                    + "VALUES (?, ?, ?, ?);";

            final int usernameParam = 1;
            final int passwordParam = 2;
            final int saltParam = 3;
            final int roleIdParam = 4;

            PreparedStatement statement = connection.prepareStatement(query);

            statement.setString(usernameParam, registerRequest.getUsername());
            statement.setBytes(passwordParam, hashedPass);
            statement.setBytes(saltParam, salt);
            statement.setInt(roleIdParam, registerRequest.getRoleId());

            int result = statement.executeUpdate();
            return result > 0;
        }
    }
}
