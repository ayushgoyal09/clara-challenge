package com.clara.lending.exception;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;

import java.util.List;

@ControllerAdvice
public class ControllerErrorHandler {

    private Logger logger = LoggerFactory.getLogger(ControllerErrorHandler.class);

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @Order(value = 1)
    public ErrorResponse validationErrors(MethodArgumentNotValidException manv) {
        BindingResult bindingResult = manv.getBindingResult();
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        return processFieldErrors(fieldErrorList);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler({ HttpMessageNotReadableException.class })
    @Order(value = 2)
    public ErrorResponse httpMessageNotReadableException(HttpMessageNotReadableException hmnrExcep) {
        Error error = new Error(String.valueOf(HttpStatus.BAD_REQUEST.value()), "Invalid payload."+hmnrExcep.getMessage());
        error.setRecoverability("Recoverable");
        return new ErrorResponse(error);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler({ ClaraBusinessRuntimeException.class})
    @Order(value = 3)
    public ErrorResponse businessRunTimeException(ClaraBusinessRuntimeException clientError) {
        ValidationError validationError = clientError.getValidationError();
        Error error = new Error(validationError.getCode(), validationError.getMsg());
        setRecoverability(validationError, error);
        return new ErrorResponse(error);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler({ RestClientException.class})
    @Order(value = 4)
    public ErrorResponse restClientException(RestClientException restException) {
        String errorMessage = restException.getMessage();
        Error error = new Error(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                "Giphy API issue", errorMessage, "Recoverable");
        return new ErrorResponse(error);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler({ IllegalArgumentException.class})
    @Order(value = 5)
    public ErrorResponse illegalArgumentException(IllegalArgumentException ex) {
        String errorMessage = ex.getMessage();
        if(StringUtils.isBlank(errorMessage)){
            errorMessage = "Illegal Argument was passed.";
        }
        Error error = new Error(String.valueOf(HttpStatus.BAD_REQUEST.value()),
                "Illegal Arguments Passed", errorMessage, "Recoverable");
        return new ErrorResponse(error);
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler({ Exception.class})
    @Order(value = 6)
    public ErrorResponse processRuntimeException(Exception ex) {
        logger.error("Unknown error occoured ", ex);
        String errorMessage = ex.getMessage();
        if(StringUtils.isBlank(errorMessage)){
            errorMessage = "Error occurred while processing the request.";
        }
        Error error = new Error(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                "Internal Error", errorMessage, "Recoverable");
        return new ErrorResponse(error);
    }

    private ErrorResponse processFieldErrors(List<FieldError> fieldErrors) {
        ErrorResponse validationErrorModel = new ErrorResponse();
        for (FieldError fieldError : fieldErrors) {
            validationErrorModel.add(new Error(fieldError.getField(), fieldError.getCode(), fieldError.getDefaultMessage()));
        }
        return validationErrorModel;
    }
    
    private void setRecoverability(ValidationError validationError, Error error) {
        String recoverable = "Not Recoverable";
        error.setRecoverability(recoverable);
    }
}
