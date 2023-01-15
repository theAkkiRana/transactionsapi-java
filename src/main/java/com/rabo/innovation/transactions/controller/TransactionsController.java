package com.rabo.innovation.transactions.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import javax.validation.ConstraintViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.rabo.innovation.transactions.model.TransactionRequest;
import com.rabo.innovation.transactions.model.Transaction;
import com.rabo.innovation.transactions.service.TransactionsService;

@RestController
@Validated
@RequestMapping("/v1/transaction")
public class TransactionsController {
    
    @Autowired
    private TransactionsService transactionsService;

    @PostMapping("/")
	@ResponseBody
	public ResponseEntity addTransaction(
        @Valid @RequestBody TransactionRequest transactionRequest,
		@RequestHeader(value = "Trace-Id", required = true) String traceId){
			Transaction transaction = transactionsService.addTransaction(transactionRequest);
			return ResponseEntity.status(HttpStatus.OK)
		   .body(transaction.getId());
	}

    @GetMapping("/")
	@ResponseBody
	public ResponseEntity fetchTransaction(	@RequestHeader(value = "Trace-Id", required = true) String traceId) {
		return ResponseEntity.status(HttpStatus.OK)
		   .body(transactionsService.fetchTransactions());
	}

	@GetMapping("/{transactionId}")
	@ResponseBody
	public ResponseEntity fetchAccount(@PathVariable String transactionId,
			@RequestHeader(value = "Trace-Id", required = true) String traceId) {
		return ResponseEntity.status(HttpStatus.OK)
		   .body(transactionsService.fetchTransaction(transactionId));
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
	  return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
