package org.soniakbew.exceptions;

public class FailedToCreateException extends RuntimeException {
    public FailedToCreateException(final Entity entity) {
        super(entity.getName() + " could not be created.");
    }
}
