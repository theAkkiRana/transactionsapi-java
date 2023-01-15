package com.rabo.innovation.transactions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.rabo.innovation.transactions.repo.AccountRepo;
import com.rabo.innovation.transactions.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@EnableMongoRepositories
@SpringBootApplication
public class TransactionsApplication extends SpringBootServletInitializer {

	@Autowired
    AccountRepo accountRepo;

	@Autowired
    TransactionRepo transactionRepo;

	public static void main(String[] args) {
		SpringApplication.run(TransactionsApplication.class, args);
	}

	/**
	 * Method to disable to auto configuration
	 *
	 * @param applicationBuilder
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
		return applicationBuilder.sources(TransactionsApplication.class);
	}

}
