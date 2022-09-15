package com.example.vmg.exception;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(Long id) {
        super(String.format("Category with %d not found", id));
    }
}
