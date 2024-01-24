package com.kailash.BlogApp.exceptions;

import com.kailash.BlogApp.models.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;



@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse> CustomExceptionHandler(CustomException e){
        return new ResponseEntity<>(new ApiResponse(e.getErrorMessage(), e.getHttpStatus(), false, e.getParams()), HttpStatusCode.valueOf(e.getHttpStatus().value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        Map<String, Object>map = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> map.put(((FieldError)error).getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(new ApiResponse(e.getBody().getDetail(), HttpStatus.valueOf(e.getStatusCode().value()),false, map), e.getStatusCode());
    }
}
