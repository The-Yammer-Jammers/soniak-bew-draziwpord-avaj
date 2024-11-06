package org.soniakbew.exceptions;

public class InvalidException extends RuntimeException {
    public InvalidException(final Entity entity) {
        super(entity.getName() + " could not be created.");
    }
}
