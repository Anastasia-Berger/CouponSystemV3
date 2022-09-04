package com.jb.csv3.advice;

import com.jb.csv3.exeptions.CouponSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = {CouponSystemException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrDetails errorHelper(Exception e) {
        return new ErrDetails("An error occurred", e.getMessage());
    }

    @ExceptionHandler(value = CouponSystemException.class)
    public ResponseEntity<?> handleSecException(CouponSystemException e) {
        ErrDetails errorDetails = new ErrDetails("An error occurred", e.getMessage());
        return new ResponseEntity<>(errorDetails, e.getErrMsg().getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}