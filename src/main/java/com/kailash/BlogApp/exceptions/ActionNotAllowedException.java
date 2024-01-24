package com.kailash.BlogApp.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class ActionNotAllowedException extends CustomException{
    public ActionNotAllowedException(HttpStatus httpStatus) {
        super(httpStatus);
    }

    public ActionNotAllowedException(HttpStatus httpStatus, String errorMessage, Map<String, Object> params) {
        super(httpStatus, errorMessage, params);
    }

    public ActionNotAllowedException(HttpStatus httpStatus, String errorMessage) {
        super(httpStatus, errorMessage);
    }
}
