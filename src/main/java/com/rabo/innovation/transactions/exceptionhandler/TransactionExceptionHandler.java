package com.rabo.innovation.transactions.exceptionhandler;

import com.rabo.innovation.transactions.constant.Constants;
import com.rabo.innovation.transactions.constant.ErrorKeys;
import com.rabo.innovation.transactions.model.Error;
import com.rabo.innovation.transactions.model.Errors;
import com.rabo.innovation.transactions.exception.TransactionException;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Controller advice which catches and process custom TransactionException.
 */
@ControllerAdvice
public class TransactionExceptionHandler extends Throwable {

    /**
     * handleTransactionException : processes the TransactionException.
     *
     * @param request   WebRequest object to get header details
     * @param exception TransactionException
     * @return Errors List of error codes.
     */
    @ExceptionHandler({TransactionException.class})
    public ResponseEntity<Errors> handleRuntimeException(WebRequest request, Exception exception) {

        String traceId = request.getHeader(Constants.TRACE_ID);
        Errors errors = new Errors();
        ArrayList<Error> errorMessageList = new ArrayList<>();
        Error error = new Error();

        error.setCode(ErrorKeys.valueOf(exception.getMessage()).getErrorCode());
        error.setMessage(ErrorKeys.valueOf(exception.getMessage()).getErrorMessage());
        error.setStatus(ErrorKeys.valueOf(exception.getMessage()).getErrorStatus());
        error.setTraceId(traceId);

        errorMessageList.add(error);
        errors.setErrors(errorMessageList);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
    }
}
