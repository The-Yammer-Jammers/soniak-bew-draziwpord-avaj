package org.example.models;

import java.util.Map;

public class UserRole {
    public static final String HR = "HR";
    public static final String SALES = "Sales";
    public static final String MANAGEMENT = "Management";

    private final int roleId;

    private static final Map<Integer, String> ROLES_MAP = Map.of(
            1, HR,
            2, SALES,
            3, MANAGEMENT
    );

    public UserRole(final int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        if (ROLES_MAP.containsKey(this.roleId)) {
            return "";
        }

        return ROLES_MAP.get(this.roleId);
    }
}
