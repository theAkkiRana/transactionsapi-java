package com.rabo.innovation.transactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.rabo.innovation.transactions.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;

@EnableMongoRepositories
@SpringBootApplication
public class TransactionsApplication {

	@Autowired
    AccountRepo accountRepo;

	public static void main(String[] args) {
		SpringApplication.run(TransactionsApplication.class, args);
	}

}
