package com.rabo.innovation.transactions.utils;

import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;

import com.rabo.innovation.transactions.model.CreateAccountRequest;
import com.rabo.innovation.transactions.model.TransactionRequest;
import com.rabo.innovation.transactions.model.Account;
import com.rabo.innovation.transactions.model.Transaction;
import com.rabo.innovation.transactions.model.TransactionRef;
import com.rabo.innovation.transactions.constant.Constants;

@Service
public class InputMapper {

    //method to map create account user inputs to db account object
    public Account createAccount(CreateAccountRequest createAccountRequest){
        Account account = new Account();
        account.setNumber(Constants.RABO_PREFIX + UUID.randomUUID().toString().replace("-",""));
        account.setFirstName(createAccountRequest.getFirstName());
        account.setMiddleName(createAccountRequest.getMiddleName());
        account.setLastName(createAccountRequest.getLastName());
        account.setEmailId(createAccountRequest.getEmail());
        account.setAccountType(Constants.SAVING_ACCOUNT);
        account.setOpeningBalance(createAccountRequest.getBalance());
        account.setCurrentBalance(createAccountRequest.getBalance());
        account.setOpeningTimestamp(new Timestamp(System.currentTimeMillis()).toString());
        List<TransactionRef> tr = new ArrayList<TransactionRef>();
        account.setTransactions(tr);

        return account;
    }

    //method to map create account user inputs to db account object
    public Transaction createTransaction(TransactionRequest transactionRequest){
        Transaction transaction = new Transaction();
        transaction.setId(UUID.randomUUID().toString().replace("-","")); 
        transaction.setDebitedfrom(transactionRequest.getInitiatorAccountNumber());
        transaction.setCreditedto(transactionRequest.getRecieverAccountNumber());
        transaction.setTimestamp(new Timestamp(System.currentTimeMillis()).toString());
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setReference(transactionRequest.getReference());
        transaction.setMessage(transactionRequest.getMessage());
        return transaction;
    }
}
