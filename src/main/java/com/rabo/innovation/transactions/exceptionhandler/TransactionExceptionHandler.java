package com.rabo.innovation.transactions.exceptionhadler;

import com.rabo.innovation.transactions.constant.ErrorKeys;
import com.rabo.innovation.transactions.constant.Constants;
import java.util.ArrayList;
import com.rabo.innovation.transactions.model.Error;
import com.rabo.innovation.transactions.model.Errors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import com.rabo.innovation.transactions.exception.TransactionException;

/**
 * Controller advice which catches and process custom TransactionException.
 */
@ControllerAdvice
public class TransactionExceptionHandler extends Throwable {

    @ExceptionHandler({TransactionException.class})
    public ResponseEntity<Errors> handleTransactionException(WebRequest request, Exception exception) {

        String traceId = request.getHeader(Constants.TRACE_ID);
        Errors errors = new Errors();
        ArrayList<Error> errorMessageList = new ArrayList<>();
        Error error = new Error();

        String errorMessage = exception.getMessage();
        
        error.setCode(ErrorKeys.valueOf(exception.getMessage()).getErrorCode());
        error.setMessage(ErrorKeys.valueOf(exception.getMessage()).getErrorMessage());
        error.setStatus(ErrorKeys.valueOf(exception.getMessage()).getErrorStatus());
        error.setTraceId(traceId);

        errorMessageList.add(error);
        errors.setErrors(errorMessageList);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
    }
}
