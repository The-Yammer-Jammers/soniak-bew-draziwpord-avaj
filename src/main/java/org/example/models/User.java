package org.example.models;

public class User {
    String username;
    byte[] password;
    byte[] salt;
    int roleId;

    public User(final String username,
                final byte[] password,
                final byte[] salt,
                final int roleId) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(final byte[] password) {
        this.password = password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(final byte[] salt) {
        this.salt = salt;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(final int roleId) {
        this.roleId = roleId;
    }
}
