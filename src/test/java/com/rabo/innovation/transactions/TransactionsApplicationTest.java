package com.rabo.innovation.transactions;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.rabo.innovation.transactions.repo.AccountRepo;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("test")
@SpringBootTest
@RunWith(SpringRunner.class)
class TransactionsApplicationTest {
    @Mock
	private MongoTemplate mongoTemplate;
	@Mock
	private AccountRepo repo;

}