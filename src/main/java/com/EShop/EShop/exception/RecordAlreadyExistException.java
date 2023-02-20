package com.EShop.EShop.exception;

public class RecordAlreadyExistException extends RuntimeException{
    public RecordAlreadyExistException(String message){
        super(message);
    }
}
