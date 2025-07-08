package com.ecommerce.project.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GloberException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> myResourceNotFoundException(ResourceNotFoundException e) {
        String message = e.getMessage();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }




    public class ResourceNotFoundException extends RuntimeException {
        String resourceName;
        String field;
        String fieldName;
        Long fieldId;


        public ResourceNotFoundException() {

        }

        public ResourceNotFoundException(String resourceName, String field, String fieldName) {
            super(String.format("%s not found with %s: %s", resourceName, field, fieldName));
            this.resourceName = resourceName;
            this.field = field;
            this.fieldName = fieldName;
        }

        public ResourceNotFoundException(String resourceName, String field, Long fieldId) {
            super(String.format("%s not found with %s: %d", resourceName, field, fieldId));
            this.resourceName = resourceName;
            this.field = field;
            this.fieldId = fieldId;
        }

    }

}
