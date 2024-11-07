package org.example.exceptions;

public enum Entity {
    USER("User"),
    CLIENT("Client"),
    PROJECT("Project"),
    DELIVERY_EMPLOYEE("Delivery Employee"),
    SALES_EMPLOYEE("Sales Employee");

    private final String entity;

    Entity(final String entity) {
        this.entity = entity;
    }

    public String getEntity() {
        return this.entity;
    }
}
