package com.kkpae.todayon.exception;


import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TodayOnException.class)
    public ResponseEntity<TodayOnExceptionResponse> handleTodayOnException(TodayOnException ex) {
        TodayOnExceptionResponse response = new TodayOnExceptionResponse(ex.getMessage(), ex.getResponse().httpStatus());
        return ResponseEntity.status(ex.getResponse().httpStatus()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ObjectError error = ex.getBindingResult().getAllErrors().getFirst();
        String fieldName = ((FieldError) error).getField();
        String errorMessage = error.getDefaultMessage();
        errors.put(fieldName, errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}

