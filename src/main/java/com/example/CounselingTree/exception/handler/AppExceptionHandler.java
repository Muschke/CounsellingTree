package com.example.CounselingTree.exception.handler;

import com.example.CounselingTree.exception.ExistsAlreadyInDatabaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ExistsAlreadyInDatabaseException.class)
    public ResponseEntity<Object> handleExistsAlreadyInDatabaseException(ExistsAlreadyInDatabaseException ex, WebRequest request){
        return new ResponseEntity<Object>(new ApiError(ex.getMessage(), HttpStatus.CONFLICT, LocalDateTime.now()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request){
        return new ResponseEntity<Object>(new ApiError(ex.getMessage(), HttpStatus.NO_CONTENT, LocalDateTime.now()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex, WebRequest request){
        return new ResponseEntity<Object>(new ApiError(ex.getMessage(), HttpStatus.NO_CONTENT, LocalDateTime.now()), HttpStatus.ACCEPTED);
    }
}
