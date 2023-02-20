package com.EShop.EShop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RecordNotFoundException.class)
    public ResponseEntity<ErrorMessage> recordNotFoundException(RecordNotFoundException recordNotFoundException){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .body(recordNotFoundException.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RecordAlreadyExistException.class)
    public ResponseEntity<ErrorMessage> recordAlreadyExistException(RecordAlreadyExistException recordAlreadyExistException){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .body(recordAlreadyExistException.getMessage())
                .localDateTime(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorMessage,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException){
        Map<String,String> errors = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getFieldErrors().forEach(
                error   -> errors.put(error.getField(),error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
