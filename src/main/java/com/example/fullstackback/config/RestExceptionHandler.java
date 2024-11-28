package com.example.fullstackback.config;


import com.example.fullstackback.dtos.ErrorDto;
import com.example.fullstackback.exceptions.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value={AppException.class})
    @ResponseBody
    public ResponseEntity<ErrorDto> handleException (AppException ex){
        return ResponseEntity.status(ex.getHttpStatus()).body(new ErrorDto(ex.getMessage()));
    }
}
