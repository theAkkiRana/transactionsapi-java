package com.rabo.innovation.transactions.repo;

import com.rabo.innovation.transactions.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepo extends MongoRepository<Transaction, String> {
   
}
