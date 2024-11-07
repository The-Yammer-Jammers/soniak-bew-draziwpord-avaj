package org.example.services;

import io.jsonwebtoken.Jwts;
import org.example.daos.AuthDao;
import org.example.exceptions.Entity;
import org.example.exceptions.FailedToCreateException;
import org.example.exceptions.InvalidException;
import org.example.models.LoginRequest;
import org.example.models.RegisterRequest;
import org.example.models.User;

import java.security.Key;
import java.sql.Date;
import java.sql.SQLException;

public class AuthService {
    final AuthDao authDao;
    final Key key;

    final int tokenLifetime = 28800000;

    public AuthService(final AuthDao authDao, final Key key) {
        this.authDao = authDao;
        this.key = key;
    }

    public String login(final LoginRequest loginRequest)
            throws SQLException, InvalidException {
        User user = authDao.getUser(loginRequest);

        if (user == null) {
            throw new InvalidException(Entity.USER, "Invalid Credentials");
        }

        return generateJwtToken(user);
    }

    public void registerUser(final RegisterRequest registerRequest)
            throws FailedToCreateException, SQLException, InvalidException {
        boolean success = authDao.registerUser(registerRequest);

        if (!success) {
            throw new FailedToCreateException(Entity.USER);
        }
    }

    String generateJwtToken(final User user) {
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(
                        new Date(System.currentTimeMillis()
                                + tokenLifetime
                        ))
                .claim("Role", user.getRoleId())
                .claim("Username", user.getUsername())
                .subject(user.getUsername())
                .issuer("DropwizardDemo")
                .signWith(key)
                .compact();
    }
}
