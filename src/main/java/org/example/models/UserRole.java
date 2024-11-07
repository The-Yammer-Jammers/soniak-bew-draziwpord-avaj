package org.example.models;

import java.util.Map;

public class UserRole {
    public static final String ADMIN = "Admin";
    public static final String USER = "User";

    private final int roleId;

    private static final Map<Integer, String> ROLES_MAP = Map.of(
            1, ADMIN,
            2, USER
    );

    public UserRole(final int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return ROLES_MAP.get(this.roleId);
    }
}
