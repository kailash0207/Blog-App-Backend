package com.kailash.BlogApp.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class ResourceAlreadyExistsException extends CustomException{
    public ResourceAlreadyExistsException(HttpStatus httpStatus) {
        super(httpStatus);
    }

    public ResourceAlreadyExistsException(HttpStatus httpStatus, String errorMessage, Map<String, Object> params) {
        super(httpStatus, errorMessage, params);
    }

    public ResourceAlreadyExistsException(HttpStatus httpStatus, String errorMessage) {
        super(httpStatus, errorMessage);
    }
}
