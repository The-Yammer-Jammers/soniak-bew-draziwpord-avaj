package org.example.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterRequest {
    String username;
    String password;
    int roleId;

    @JsonCreator
    public RegisterRequest(
            @JsonProperty("username") final String username,
            @JsonProperty("password") final String password,
            @JsonProperty("roleName") final int roleId
    ) {
        this.username = username;
        this.password = password;
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(final int roleId) {
        this.roleId = roleId;
    }
}
