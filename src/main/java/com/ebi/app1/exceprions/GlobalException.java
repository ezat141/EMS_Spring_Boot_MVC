package com.ebi.app1.exceprions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalException {

//    @ExceptionHandler
//    ResponseEntity<?> CustomExceptionHandler(CustomException customException){
//        ErrorResponse response = new ErrorResponse(customException.getErrorCode(), customException.getErrorMessage(), customException.getErrorDescription());
//        return new  ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(CustomException.class)
    ResponseEntity<ErrorResponse> handleCustomException(CustomException customException, HttpServletRequest request){
        ErrorResponse response = new ErrorResponse(customException.getErrorCode(), customException.getErrorMessage(), customException.getErrorDescription(), LocalDateTime.now(), request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException e, HttpServletRequest request){
        ErrorResponse response = new ErrorResponse("400", "Invalid Input", "Malformed request body", LocalDateTime.now(), request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAnyException(Exception ex, HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse("500", "Internal Server Error", ex.getMessage(), LocalDateTime.now(), request.getRequestURI());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
