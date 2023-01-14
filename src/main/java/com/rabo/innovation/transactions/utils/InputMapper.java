package com.rabo.innovation.transactions.utils;

import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;

import com.rabo.innovation.transactions.model.CreateAccountRequest;
import com.rabo.innovation.transactions.model.Account;
import com.rabo.innovation.transactions.model.TransactionRef;

@Service
public class InputMapper {

    //method to map create account user inputs to db account object
    public Account createAccount(CreateAccountRequest createAccountRequest){
        Account account = new Account();
        account.setNumber("RABO" + UUID.randomUUID().toString().replace("-",""));
        account.setFirstName(createAccountRequest.getFirstName());
        account.setMiddleName(createAccountRequest.getMiddleName());
        account.setLastName(createAccountRequest.getLastName());
        account.setEmailId(createAccountRequest.getEmail());
        account.setAccountType("savings");
        account.setOpeningBalance(createAccountRequest.getBalance());
        account.setCurrentBalance(createAccountRequest.getBalance());
        account.setOpeningTimestamp(new Timestamp(System.currentTimeMillis()).toString());
        List<TransactionRef> tr = new ArrayList<TransactionRef>();
        account.setTransactions(tr);

        return account;
    }
}
