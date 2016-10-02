package com.clara.lending.exception;

public class ClaraBusinessRuntimeException extends RuntimeException {

    private ValidationError validationError;

    public ClaraBusinessRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClaraBusinessRuntimeException(String message) {
        super(message);
    }

    public ClaraBusinessRuntimeException(Throwable cause) {
        super(cause);
    }

    public ClaraBusinessRuntimeException(ValidationError validationError) {
        super(validationError != null ? validationError.toStringDefault() : null);
        this.validationError = validationError;
    }

    public ClaraBusinessRuntimeException(ValidationError validationError, Throwable cause) {
        super(validationError != null ? validationError.toStringDefault() : null, cause);
        this.validationError = validationError;
    }

    public ValidationError getValidationError() {
        return validationError;
    }
}
