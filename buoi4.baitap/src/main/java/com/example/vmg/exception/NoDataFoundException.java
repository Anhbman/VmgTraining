package com.example.vmg.exception;

public class NoDataFoundException extends RuntimeException {

    public NoDataFoundException() {
        super("No blogs found");
    }

    public NoDataFoundException(String message) {
        super(String.format("Blog not found with %s", message));
    }
}
