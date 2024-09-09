package com.employee.exception;

public class RepositoryAccessException extends RuntimeException {
    public RepositoryAccessException(String message) {
        super(message);
    }

    public RepositoryAccessException(Exception ex) {
        super(ex);
    }
}
