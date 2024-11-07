package org.example.auth;

import io.dropwizard.auth.Authenticator;
import io.jsonwebtoken.Claims;
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
            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            String username = claims.get("Username", String.class);
            int roleId = claims.get("Role", Integer.class);

            JwtToken jwtToken = new JwtToken(username, new UserRole(roleId));
            return Optional.of(jwtToken);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
