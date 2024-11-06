package org.example.exceptions;

public class FailedToCreateException extends RuntimeException {
    public FailedToCreateException(final Entity entity, final String reason) {
        super(entity.getName() + " could not be created because, " + reason);
    }
}
