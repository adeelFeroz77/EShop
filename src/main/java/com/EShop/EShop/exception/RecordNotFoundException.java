package com.EShop.EShop.exception;

public class RecordNotFoundException extends RuntimeException{
    public RecordNotFoundException(String message){
        super(message);
    }
}
