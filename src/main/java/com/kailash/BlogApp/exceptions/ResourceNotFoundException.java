package com.kailash.BlogApp.exceptions;

import org.springframework.http.HttpStatus;


import java.util.Map;

public class ResourceNotFoundException extends CustomException{

    public ResourceNotFoundException(HttpStatus httpStatus) {
        super(httpStatus);
    }

    public ResourceNotFoundException(HttpStatus httpStatus, String errorMessage, Map<String, Object> params) {
        super(httpStatus, errorMessage, params);
    }

    public ResourceNotFoundException(HttpStatus httpStatus, String errorMessage) {
        super(httpStatus, errorMessage);
    }
}
