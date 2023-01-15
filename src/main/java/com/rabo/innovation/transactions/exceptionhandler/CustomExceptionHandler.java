package com.rabo.innovation.transactions.exceptionhadler;

import java.util.*;
import java.util.stream.Collectors;
 
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;

import com.rabo.innovation.transactions.model.Error;
import com.rabo.innovation.transactions.model.Errors;
import org.springframework.validation.ObjectError;

/**
 * To Handle Springboot validation errors
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
 
    // @Override
    // protected ResponseEntity<Object> handleMethodArgumentNotValid(
    //         MethodArgumentNotValidException ex,
    //         HttpHeaders headers, HttpStatusCode status, WebRequest request) {
             
    //     // Map<String, Object> responseBody = new LinkedHashMap<>();
    //     // responseBody.put("timestamp", new Date());
    //     // responseBody.put("status", status.value());
         
    //     // List<String> errors = ex.getBindingResult().getFieldErrors()
    //     //     .stream()
    //     //     .map(x -> x.getDefaultMessage())
    //     //     .collect(Collectors.toList());
         
    //     // responseBody.put("errors", errors);
         
    //     // return new ResponseEntity<>(responseBody, headers, status);

    //     Map<String, Object> response = new HashMap<>();
    //     response.put("message", "Required request body is missing");

    //     return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    // }

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(
        RuntimeException ex, WebRequest request, HttpStatusCode status) {
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("status", "400");
         
        System.out.println(ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(responseBody, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
             HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        System.out.println("----------");
        System.out.println(ex);
        System.out.println("----------");
        List<Error> errorList = new ArrayList<Error>();
        Errors errors = new Errors();
        for(ObjectError objerror : ex.getBindingResult().getAllErrors()) {
            Error error = new Error();
            error.setMessage(objerror.getDefaultMessage());
            errorList.add(error);
        }
        errors.setErrors(errorList);
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
 
}