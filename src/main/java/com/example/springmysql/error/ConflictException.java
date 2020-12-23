package com.example.springmysql.error;

import org.springframework.http.HttpStatus;

public class ConflictException extends ApiBaseException{

    public ConflictException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public HttpStatus getStatusCode(){
        return HttpStatus.CONFLICT;
    }
    
}
