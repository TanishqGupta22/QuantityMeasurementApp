package com.app.exception;

import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(Exception ex) {
        return new ErrorResponse(ex.getMessage());
    }
}
