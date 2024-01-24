package com.kailash.BlogApp.exceptions;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;


import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class CustomException extends RuntimeException{
    private HttpStatus httpStatus;
    private String errorMessage;
    private Map<String, Object> params;

    public CustomException(HttpStatus httpStatus){
        this.httpStatus = httpStatus;
        this.params = new HashMap<>();
    }

    public CustomException(HttpStatus httpStatus, String errorMessage, Map<String, Object>params){
        this.params = params;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    public CustomException(HttpStatus httpStatus, String errorMessage){
        this.params = new HashMap<>();
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

}
