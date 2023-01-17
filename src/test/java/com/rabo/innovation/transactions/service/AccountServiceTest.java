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

import com.rabo.innovation.transactions.service.AccountService;
import com.rabo.innovation.transactions.repo.AccountRepo;
import com.rabo.innovation.transactions.utils.InputMapper;
import com.rabo.innovation.transactions.model.TransactionRef;
import com.rabo.innovation.transactions.model.Account;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AccountServiceTest {

	@Mock
	private MongoTemplate mongoTemplate;
	@Mock
	private AccountRepo repo;
    @Spy
    private InputMapper mapper;

	@InjectMocks
	private AccountService service;


    @BeforeEach
    public void setUp() {  
        Account testAccount = new Account(); 
        testAccount.setNumber("RABO1234");
        testAccount.setSalutation("mr");
        testAccount.setFirstName("akshat");
        testAccount.setMiddleName("");
        testAccount.setLastName("rana");
        testAccount.setEmailId("akki.rana87@gmail.com");
        testAccount.setAccountType("saving");
        testAccount.setOpeningBalance(100);
        testAccount.setCurrentBalance(150);
        testAccount.setOpeningTimestamp("10-10-1987");
        
        Mockito.when(repo.findById("RABO1234")).thenReturn(Optional.of(testAccount));
        List<Account> accountList = new ArrayList<Account>();
        accountList.add(testAccount);
        Mockito.when(repo.findAll()).thenReturn(accountList);
    }
    
    @Test
    public void shouldBeNotEmpty() {
        List<Account> fullAccountList = service.fetchAccounts();
        assertThat(fullAccountList.size()).isEqualTo(1);
        assertThat(fullAccountList.get(0).getFirstName()).isEqualTo("akshat");
		assertThat(fullAccountList.get(0).getEmailId()).isNotBlank();
		assertThat(fullAccountList.get(0).getAccountType()).isNotNull();
    }

    @Test
    public void checkGetById() {
        Account myAccount = service.fetchAccount("RABO1234");
        assertThat(myAccount.getNumber()).isEqualTo("RABO1234");
        assertThat(myAccount.getEmailId()).isEqualTo("akki.rana87@gmail.com");
        assertThat(myAccount.getAccountType()).isEqualTo("saving");
        assertThat(myAccount.getFirstName()).isEqualTo("akshat");
    }



}
