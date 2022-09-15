package com.example.vmg.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BlogNotFoundException.class)
    public ResponseEntity<Object> handleBlogNotFoundException (
            BlogNotFoundException exception,
            WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamps", LocalDateTime.now());
        body.put("Message", "Blog not found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Object> handleCategoryNotFound (
            CategoryNotFoundException ex,
            WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamps", LocalDateTime.now());
        body.put("Message", "Category not found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<Object> handleDataNotFound(
            NoDataFoundException ex,
            Model model) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamps", LocalDateTime.now());
        body.put("Message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
