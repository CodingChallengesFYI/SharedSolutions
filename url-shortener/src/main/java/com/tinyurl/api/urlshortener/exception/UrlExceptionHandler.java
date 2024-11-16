package com.tinyurl.api.urlshortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class UrlExceptionHandler {

        // Handle custom UrlException
        @ExceptionHandler(UrlException.class)
        public ResponseEntity<ErrorResponse> handleUrlException(UrlException ex, WebRequest request) {
            ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getStatus());
            return new ResponseEntity<>(errorResponse, ex.getStatus());
        }

        @ResponseStatus(HttpStatus.BAD_REQUEST)
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {

            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return ResponseEntity.badRequest().body(errors);
        }

        // Handle general exceptions
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
            ErrorResponse errorResponse = new ErrorResponse("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Custom response object for error handling
        static class ErrorResponse {
            private String message;
            private HttpStatus status;

            public ErrorResponse(String message, HttpStatus status) {
                this.message = message;
                this.status = status;
            }

            // Getters and setters
            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public HttpStatus getStatus() {
                return status;
            }

            public void setStatus(HttpStatus status) {
                this.status = status;
            }
        }


}
