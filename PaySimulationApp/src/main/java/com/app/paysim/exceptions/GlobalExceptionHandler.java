package com.app.dvm.exceptions;

import com.app.paysim.exceptions.EntityNotFoundException;
import com.app.paysim.exceptions.InvalidInputException;
import com.app.paysim.exceptions.InvalidTokenException;
import com.app.paysim.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static com.app.paysim.constant.Constants.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle EntityNotFoundException
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ApiResponse errorResponse = new ApiResponse(ex.getMessage(), request.getDescription(false),INT_400, null);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle InvalidInputException
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ApiResponse> handleInvalidInputException(InvalidInputException ex, WebRequest request) {
        ApiResponse errorResponse = new ApiResponse(ex.getMessage(), request.getDescription(false), INT_400, null);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Handle Generic Exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGlobalException(Exception ex, WebRequest request) {
        ApiResponse errorResponse = new ApiResponse("An unexpected error occurred", request.getDescription(false), INT_500, null);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ApiResponse> handleInvalidTokenException(InvalidTokenException ex, WebRequest request) {
        ApiResponse errorResponse = new ApiResponse(ex.getMessage(), request.getDescription(false), INT_401, null);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }
}