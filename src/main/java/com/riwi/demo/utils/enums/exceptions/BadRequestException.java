package com.riwi.demo.utils.enums.exceptions;

public class BadRequestException extends RuntimeException{
    
    public BadRequestException(String message){
        super(message);
    }
}
