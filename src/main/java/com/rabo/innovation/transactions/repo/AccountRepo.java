package com.rabo.innovation.transactions.repo;

import com.rabo.innovation.transactions.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends MongoRepository<Account, String> {
   
}
