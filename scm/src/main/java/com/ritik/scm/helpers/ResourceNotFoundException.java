package com.ritik.scm.helpers;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("Resource not found");
    }

    public ResourceNotFoundException(String e) {
        super(e);
    }
}
