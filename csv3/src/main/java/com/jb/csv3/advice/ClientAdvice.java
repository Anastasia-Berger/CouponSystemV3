package com.jb.csv3.advice;

import com.jb.csv3.exeptions.CouponSystemException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClientAdvice {
    @ExceptionHandler(value = {CouponSystemException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrDetails adminError(Exception err) {
        return new ErrDetails( "error : ", err.getMessage());
    }
}