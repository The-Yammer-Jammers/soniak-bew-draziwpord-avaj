package org.example.exceptions;

public class DoesNotExistException extends RuntimeException {
    public DoesNotExistException(final Entity entity, final String reason) {
        super(entity.getName() + " does not exist because, " + reason);
    }
}
