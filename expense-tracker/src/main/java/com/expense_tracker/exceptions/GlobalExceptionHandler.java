package com.expense_tracker.exceptions;

import com.expense_tracker.dtos.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<?>> handleAllUnknownException(Exception ex){
        Response<?> response = Response.builder()
                .success(false)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response<?>> handleNotFoundException(Exception ex){
        Response<?> response = Response.builder()
                .success(false)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<Response<?>> handleAlreadyExistException(Exception ex){
        Response<?> response = Response.builder()
                .success(false)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidCredentialException.class)
    public ResponseEntity<Response<?>> handleInvalidCredentialException(Exception ex){
        Response<?> response = Response.builder()
                .success(false)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccountLockedException.class)
    public ResponseEntity<Response<?>> handleLockedException(Exception ex){
        Response<?> response = Response.builder()
                .success(false)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.LOCKED);
    }

}
