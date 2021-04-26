package com.springboot.rest.api.exception;

public class UserException extends RuntimeException {

    private static final long serialVersionUID = -2150781967352316016L;

    public UserException(String message) {
        super(message);
    }

}
