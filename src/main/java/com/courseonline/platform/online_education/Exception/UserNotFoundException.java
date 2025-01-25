package com.courseonline.platform.online_education.Exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String id, Class<?> entityType) {
        super(entityType.getSimpleName() + " with ID " + id + " not found.");
    }
}
