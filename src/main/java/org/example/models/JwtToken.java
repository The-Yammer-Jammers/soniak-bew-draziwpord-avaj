package org.example.models;

import javax.security.auth.Subject;
import java.security.Principal;

public class JwtToken implements Principal {
    String username;
    UserRole userRole;

    public JwtToken(final String username,
                    final UserRole userRole) {
        this.username = username;
        this.userRole = userRole;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(final UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String getName() {
        return username;
    }

    @Override
    public boolean implies(final Subject subject) {
        return Principal.super.implies(subject);
    }
}
