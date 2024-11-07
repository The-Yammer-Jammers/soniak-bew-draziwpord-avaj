package org.example.exceptions;

public enum Entity {
    SALES_EMPLOYEE("SalesEmployee");
    private final String entity;
    Entity(final String entity) {
        this.entity = entity;
    }
    public String getEntity() {
        return this.entity;
    }
}
