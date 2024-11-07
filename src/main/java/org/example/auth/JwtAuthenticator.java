package org.example.auth;

import io.dropwizard.auth.Authenticator;
import io.jsonwebtoken.Jwts;
import org.example.models.JwtToken;
import org.example.models.UserRole;

import javax.crypto.SecretKey;
import java.util.Optional;

public class JwtAuthenticator implements Authenticator<String, JwtToken> {
    SecretKey key;

    public JwtAuthenticator(final SecretKey key) {
        this.key = key;
    }

    @Override
    public Optional<JwtToken> authenticate(final String token) {
        try {
            Integer roleId = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .get("Role", Integer.class);

            JwtToken jwtToken = new JwtToken(new UserRole(roleId));
            return Optional.of(jwtToken);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
