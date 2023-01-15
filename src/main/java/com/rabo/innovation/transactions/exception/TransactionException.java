package com.rabo.innovation.transactions.exception;

import java.lang.RuntimeException;
import java.lang.Exception;

public class TransactionException extends RuntimeException {

	public TransactionException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

	/**
	 * Constructor that takes an existing Exception. This will move any
	 * messages into the new exception. <br>
	 * 
	 * @param e :accepts type of Exception
	 */
	public TransactionException(Exception e) {
		super(e);
	}

}
