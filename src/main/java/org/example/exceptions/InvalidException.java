package org.example.exceptions;

public class InvalidException extends RuntimeException {
    public InvalidException(final Entity entity, final String reason) {
        super(entity.getName() + " could not be created because, " + reason);
    }
}
