package com.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entityName, Object id) {
        super(entityName + " not found with ID: " + id);
    }
}
