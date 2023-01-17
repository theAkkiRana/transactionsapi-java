package com.rabo.innovation.transactions.exceptionhadler;

import java.util.*;
import java.util.stream.Collectors;
import com.rabo.innovation.transactions.constant.ErrorKeys;
import com.rabo.innovation.transactions.constant.Constants;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;

import org.springframework.web.bind.annotation.RestController;
import com.rabo.innovation.transactions.model.Error;
import com.rabo.innovation.transactions.model.Errors;
import org.springframework.validation.ObjectError;

/**
 * To Handle Springboot validation errors
 */
@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
             HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Error> errorList = new ArrayList<Error>();
        Errors errors = new Errors();
        String traceId = request.getHeader(Constants.TRACE_ID);
        for(ObjectError err : ex.getBindingResult().getAllErrors()) {
            Error error = new Error();
            error.setCode(ErrorKeys.valueOf(err.getDefaultMessage()).getErrorCode());
            error.setMessage(ErrorKeys.valueOf(err.getDefaultMessage()).getErrorMessage());
            error.setStatus(ErrorKeys.valueOf(err.getDefaultMessage()).getErrorStatus());
            error.setTraceId(traceId);
            errorList.add(error);
        }
        errors.setErrors(errorList);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
 
}