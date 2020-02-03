package com.gridstudentpractice.chatservice.message.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    private HttpStatus status;
    private String message;
    private String debugMessage;
    private List<FieldValidationError> fieldValidationErrors;

    public ApiError() {

    }

    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiError(HttpStatus status, Throwable e) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.setDebugMessage(e.getLocalizedMessage());
    }

    public ApiError(HttpStatus status, String message, Throwable e) {
        this();
        this.status = status;
        this.message = message;
        this.setDebugMessage(e.getLocalizedMessage());
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public List<FieldValidationError> getFieldValidationErrors() {
        return fieldValidationErrors;
    }

    public void setFieldValidationErrors(List<FieldValidationError> apiValidationErrors) {
        this.fieldValidationErrors = apiValidationErrors;
    }

    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(error -> {
            FieldValidationError subError = new FieldValidationError();
            subError.setField(error.getField());
            subError.setMessage(error.getDefaultMessage());
            subError.setRejectedValue(error.getRejectedValue());
            subError.setObject(error.getObjectName());
            this.addSubError(subError);
        });
    }

    private void addSubError(FieldValidationError subError) {
        if(fieldValidationErrors == null) {
            fieldValidationErrors = new ArrayList<>();
        }
        fieldValidationErrors.add(subError);
    }
}
