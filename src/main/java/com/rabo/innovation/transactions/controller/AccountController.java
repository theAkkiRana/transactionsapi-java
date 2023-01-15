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

import com.rabo.innovation.transactions.model.CreateAccountRequest;
import com.rabo.innovation.transactions.model.Account;
import com.rabo.innovation.transactions.service.AccountService;

@RestController
@Validated
@RequestMapping("/v1/bankaccount")
public class AccountController {
    
    @Autowired
    private AccountService accountService;

    @PostMapping("/")
	@ResponseBody
	public ResponseEntity addAccount(
        @RequestBody @Valid CreateAccountRequest createAccountRequest,
		@RequestHeader(value = "Trace-Id", required = true) String traceId){
		Account account = accountService.addAccount(createAccountRequest);
			return ResponseEntity.status(HttpStatus.OK)
		   .body(account.getNumber());
	}

    @GetMapping("/")
	@ResponseBody
	public ResponseEntity fetchAccounts(@RequestHeader(value = "Trace-Id", required = true) String traceId) {
		return ResponseEntity.status(HttpStatus.OK)
		   .body(accountService.fetchAccounts());
	}

	@GetMapping("/{accountNumber}")
	@ResponseBody
	public ResponseEntity fetchAccount(@PathVariable String accountNumber,
			@RequestHeader(value = "Trace-Id", required = true) String traceId) {
		return ResponseEntity.status(HttpStatus.OK)
		   .body(accountService.fetchAccount(accountNumber));
	}
}
