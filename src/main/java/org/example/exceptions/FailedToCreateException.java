package org.example.exceptions;

public class FailedToCreateException extends Exception {

    public FailedToCreateException(final Entity entity) {
        super(entity.getEntity() + " failed to create");
    }
}
