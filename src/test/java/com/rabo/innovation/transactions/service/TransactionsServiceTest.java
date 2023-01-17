package com.rabo.innovation.transactions.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.mockito.Spy;
import org.junit.jupiter.api.BeforeEach;

import com.rabo.innovation.transactions.service.TransactionsService;
import com.rabo.innovation.transactions.repo.TransactionRepo;
import com.rabo.innovation.transactions.utils.InputMapper;
import com.rabo.innovation.transactions.model.TransactionRef;
import com.rabo.innovation.transactions.model.Transaction;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TransactionsServiceTest {

	@Mock
	private MongoTemplate mongoTemplate;
	@Mock
	private TransactionRepo repo;
    @Spy
    private InputMapper mapper;

	@InjectMocks
	private TransactionsService service;


    @BeforeEach
    public void setUp() {  
        Transaction testTransaction = new Transaction(); 
        testTransaction.setId("testid123"); 
        testTransaction.setDebitedfrom("RABO1234");
        testTransaction.setCreditedto("RABO12345");
        testTransaction.setTimestamp("10-10-1987");
        testTransaction.setReference("testRef");
        testTransaction.setMessage("testMsg");
        testTransaction.setAmount(100);
        
        Mockito.when(repo.findById("testid123")).thenReturn(Optional.of(testTransaction));
        List<Transaction> transactionList = new ArrayList<Transaction>();
        transactionList.add(testTransaction);
        Mockito.when(repo.findAll()).thenReturn(transactionList);
    }
    
    /**
     * test case for fetch all transaction list to return Transaction object
     */
    @Test
    public void shouldBeNotEmpty() {
        List<Transaction> tranactionList = service.fetchTransactions();
        assertThat(tranactionList.size()).isEqualTo(1);
        assertThat(tranactionList.get(0).getId()).isEqualTo("testid123");
		assertThat(tranactionList.get(0).getDebitedfrom()).isNotBlank();
		assertThat(tranactionList.get(0).getReference()).isNotNull();
    }

    /**
     * test case for fetch transaction by id to return transaction object
     */
    @Test
    public void checkGetById() {
        Transaction myTransaction =  service.fetchTransaction("testid123");
        assertThat(myTransaction.getId()).isEqualTo("testid123");
        assertThat(myTransaction.getTimestamp()).isEqualTo("10-10-1987");
        assertThat(myTransaction.getMessage()).isEqualTo("testMsg");
        assertThat(myTransaction.getAmount()).isEqualTo(100);
    }



}
