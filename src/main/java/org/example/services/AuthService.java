package org.example.services;

import io.jsonwebtoken.Jwts;
import org.example.daos.AuthDao;
import org.example.exceptions.Entity;
import org.example.exceptions.FailedToCreateException;
import org.example.exceptions.InvalidException;
import org.example.models.LoginRequest;
import org.example.models.RegisterRequest;
import org.example.models.User;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;

public class AuthService {
    final AuthDao authDao;
    final Key key;

    final int tokenLifetime = 28800000;
    static final int SALT_LENGTH = 16;
    static final String HASH_ALGORITHM = "PBKDF2WithHmacSHA1";
    static final int HASH_LENGTH = 128;
    static final int HASH_ITERATIONS = 65536;

    public AuthService(final AuthDao authDao, final Key key) {
        this.authDao = authDao;
        this.key = key;
    }

    public String login(final LoginRequest loginRequest)
            throws SQLException, InvalidException, NoSuchAlgorithmException,
            InvalidKeySpecException {
        User user = authDao.getUser(loginRequest);

        if (user == null) {
            throw new InvalidException(Entity.USER, "Invalid Credentials");
        }

        if (!verifyPassword(loginRequest.getPassword(),
                user.getSalt(),
                user.getPassword())) {
            throw new InvalidException(Entity.USER, "Invalid Credentials");
        }

        return generateJwtToken(user);
    }

    public void registerUser(final RegisterRequest registerRequest)
            throws FailedToCreateException, SQLException, InvalidException,
            NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] salt = generateSalt();
        byte[] hashedPassword = hashPassword(
                registerRequest.getPassword(), salt);
        boolean success = authDao.registerUser(
                registerRequest, hashedPassword, salt);

        if (!success) {
            throw new FailedToCreateException(Entity.USER);
        }
    }

    boolean verifyPassword(final String password,
                           final byte[] salt,
                           final byte[] hashedPassword)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        return Arrays.equals(
                hashPassword(password, salt),
                hashedPassword);
    }

    byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);

        return salt;
    }

    byte[] hashPassword(final String password, final byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec spec = new PBEKeySpec(
                password.toCharArray(), salt, HASH_ITERATIONS, HASH_LENGTH);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(HASH_ALGORITHM);

        return factory.generateSecret(spec).getEncoded();
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
