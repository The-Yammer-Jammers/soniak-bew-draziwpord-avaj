package org.soniakbew.exceptions;

public class DoesNotExistException extends RuntimeException {
    public DoesNotExistException(final Entity entity) {
        super(entity.getName() + " does not exist");
    }
}
