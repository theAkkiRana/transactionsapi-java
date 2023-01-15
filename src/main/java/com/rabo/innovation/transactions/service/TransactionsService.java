package com.rabo.innovation.transactions.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;

import com.rabo.innovation.transactions.service.AccountService;
import com.rabo.innovation.transactions.exception.TransactionException;
import com.rabo.innovation.transactions.model.TransactionRequest;
import com.rabo.innovation.transactions.model.TransactionRef;
import com.rabo.innovation.transactions.model.Transaction;
import com.rabo.innovation.transactions.model.Account;
import com.rabo.innovation.transactions.utils.InputMapper;
import com.rabo.innovation.transactions.repo.TransactionRepo;
import com.rabo.innovation.transactions.constant.Constants;
import java.util.List;

@Service
public class TransactionsService {
    
    //utils mapper used to map input request object to mongo db document object
    private InputMapper mapper;

    //mongo repository 
    private TransactionRepo transactionRepo;

    @Autowired
    private AccountService accountService;

    @Autowired
    protected MongoTemplate mongoTemplate;

    @Autowired
    public TransactionsService(InputMapper mapper, TransactionRepo transactionRepo, MongoTemplate mongoTemplate, AccountService accountService) {
        this.mapper = mapper;
        this.transactionRepo = transactionRepo;
        this.mongoTemplate = mongoTemplate;
        this.accountService = accountService;
    }

    //method to add accounts to the mongo db
    public Transaction addTransaction(TransactionRequest transactionRequest){
        Transaction transaction = this.mapper.createTransaction(transactionRequest);
        if(this.accountService.isDebitAllowed(transactionRequest.getInitiatorAccountNumber(), transactionRequest.getAmount())){
            transaction = this.transactionRepo.save(transaction);
            if(null!=transaction && null != transaction.getId()){
                updateAccount(transactionRequest.getInitiatorAccountNumber(), transactionRequest.getAmount(), 
                    Constants.DEBIT, transaction.getId());
                updateAccount(transactionRequest.getRecieverAccountNumber(), transactionRequest.getAmount(), 
                    Constants.CREDIT, transaction.getId());
            }
            return transaction;
        }else{
            throw new TransactionException("ACCOUNT_BALANCE_LOW", new Throwable());
        }
    
        
    }

    //method to fetch all accounts from db, to be used by admin
    public List<Transaction> fetchTransactions(){
        return this.transactionRepo.findAll();
    }

    //method to fetch individual account details, to be used by individual users
    public Transaction fetchTransaction(String transactionId){
        return this.transactionRepo.findById(transactionId).orElse(null);
    }

    private void updateAccount(String accountNum, double amount, String transactionType, String transactionId){
        this.mongoTemplate.updateFirst(
            Query.query(Criteria.where("number").is(accountNum)), 
            new Update().push("transactions", new TransactionRef(transactionId, transactionType)), Account.class);
        double currentAmount = transactionType.equals(Constants.DEBIT) ? this.accountService.fetchAccount(accountNum).getCurrentBalance() - amount :
        this.accountService.fetchAccount(accountNum).getCurrentBalance() + amount;
        this.mongoTemplate.updateFirst(
            Query.query(Criteria.where("number").is(accountNum)), 
            new Update().set("currentBalance", currentAmount), Account.class);
    }
    
}
