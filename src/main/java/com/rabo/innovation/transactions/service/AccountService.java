package com.rabo.innovation.transactions.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.rabo.innovation.transactions.model.CreateAccountRequest;
import com.rabo.innovation.transactions.model.Account;
import com.rabo.innovation.transactions.utils.InputMapper;
import com.rabo.innovation.transactions.repo.AccountRepo;
import java.util.List;

@Service
public class AccountService {
    
    //utils mapper used to map input request object to mongo db document object
    private InputMapper mapper;

    //mongo repository 
    private AccountRepo accountRepo;

    @Autowired
    public AccountService(InputMapper mapper, AccountRepo accountRepo) {
        this.mapper = mapper;
        this.accountRepo = accountRepo;
    }

    //method to add accounts to the mongo db
    public Account addAccount(CreateAccountRequest createAccountRequest){
        Account newAccount = this.mapper.createAccount(createAccountRequest);
        return this.accountRepo.save(newAccount);
    }

    //method to fetch all accounts from db, to be used by admin
    public List<Account> fetchAccounts(){
        return this.accountRepo.findAll();
    }

    //method to fetch individual account details, to be used by individual users
    public Account fetchAccount(String accountId){
        return this.accountRepo.findById(accountId).orElse(null);
    }

    //method to check if individual debit is allowed
    public boolean isDebitAllowed(String accountId, double amount){
        Account debitAccount = fetchAccount(accountId);
        return (debitAccount.getCurrentBalance() - amount > debitAccount.getMinBalance());
    }
    
}
