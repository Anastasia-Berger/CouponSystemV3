package com.jb.csv3.advice;

import com.jb.csv3.exeptions.CouponSystemException;
import com.jb.csv3.exeptions.ErrMsg;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = {MissingRequestHeaderException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrDto handleMissingHeaders(MissingRequestHeaderException e){
        return new ErrDto("Coupon System ",e.getMessage());
    }
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrDto handleMissingHeaders(MethodArgumentTypeMismatchException e){
        return new ErrDto("Coupon System ",e.getMessage());
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ErrDto handle404() {
        return new ErrDto("Coupon System ","Page Not Found 4-0-4");
    }


    @ExceptionHandler(value = {CouponSystemException.class})
    public ResponseEntity<?> handleErrors(CouponSystemException e) {
        ErrMsg errMsg = e.getErrMsg();
        HttpStatus status = errMsg.getStatus();
        ErrDto errDto = new ErrDto("Coupon System ", e.getMessage());
        return new ResponseEntity<>(errDto, status);

    }
}