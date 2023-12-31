package com.rezende.commerce.dto.exceptionsDTO;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError{

    private final List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Instant timeStamp, Integer status, String error, String path) {
        super(timeStamp, status, error, path);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        errors.removeIf(error -> error.equals(fieldName));
        errors.add(new FieldMessage(fieldName, message));
    }
}
