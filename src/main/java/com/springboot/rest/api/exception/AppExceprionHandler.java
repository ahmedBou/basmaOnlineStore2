package com.springboot.rest.api.exception;

import com.springboot.rest.api.response.ErrorMessageResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AppExceprionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handlerAllExceptions(Exception ex, WebRequest request) {

        ErrorMessageResponse errorResponse = new ErrorMessageResponse(new Date(), ex.getLocalizedMessage());

        return new ResponseEntity<>( errorResponse, new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<Object> handlerUserException(UserException ex, WebRequest request) {

        ErrorMessageResponse errorResponse = new ErrorMessageResponse(new Date(), ex.getLocalizedMessage());

        return new ResponseEntity<>( errorResponse, new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public ResponseEntity<Object> HandleMethodArgumentNotValid(MethodArgumentNotValidException ex, WebRequest request)
    {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
