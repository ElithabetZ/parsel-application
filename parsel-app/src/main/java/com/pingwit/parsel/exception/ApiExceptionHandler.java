package com.pingwit.parsel.exception;

import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.net.ConnectException;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(exception.getMessage()));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<ApiError>> handleConstraintViolationException(ConstraintViolationException exception) {
        List<ApiError> errorsMessages = exception.getConstraintViolations().stream()
                .map(c-> new ApiError(c.getMessage())).collect(Collectors.toList());
        return new ResponseEntity<>(errorsMessages, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PSQLException.class)
    public ResponseEntity<ApiError> handlePSQLException(PSQLException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiError(exception.getMessage()));
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiError(exception.getMessage()));
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<ApiError> handleConnectException() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ApiError("Sorry, something goes wrong, please try again later"));
    }
}
