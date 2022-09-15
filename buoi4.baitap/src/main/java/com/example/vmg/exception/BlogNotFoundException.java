package com.example.vmg.exception;

public class BlogNotFoundException extends RuntimeException {

    public BlogNotFoundException(Long id) {
        super(String.format("Blog with Id %d not found", id));
    }
}
