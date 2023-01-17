package com.rabo.innovation.transactions.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.rabo.innovation.transactions.model.CreateAccountRequest;
import com.rabo.innovation.transactions.model.Account;
import com.rabo.innovation.transactions.service.AccountService;
import com.rabo.innovation.transactions.controller.AccountController;
import com.rabo.innovation.transactions.repo.AccountRepo;

/**
 * Testing of the endpoint resource for OrderingService
 */
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTests {

  private final String TRACE_ID = "1234567890";

  /**
   * creating a mock object of OrderingController class
   */
  @InjectMocks
  private AccountController controller;

  /**
   * mocking of the service layer
   */
  @MockBean
  AccountService accountService;

  @Autowired
	private MockMvc mockMvc;
  
  /**
   * mocking the controller
   */
  @BeforeEach
  public void setup() throws Exception {
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    CreateAccountRequest req = new CreateAccountRequest();
    req.setFirstName("akshat");
    req.setMiddleName("");
    req.setLastName("rana");
    req.setEmail("akki.rana87@gmail.com");
    req.setBalance(100);
    Account testAccount = new Account(); 
    testAccount.setNumber("RABO1234");
    testAccount.setSalutation("mr");
    testAccount.setFirstName("akshat");
    testAccount.setMiddleName("");
    testAccount.setLastName("rana");
    testAccount.setEmailId("akki.rana87@gmail.com");
    testAccount.setAccountType("saving");
    testAccount.setOpeningBalance(100);
    testAccount.setCurrentBalance(100);
    testAccount.setOpeningTimestamp("10-10-1987");;
    Mockito.when(accountService.addAccount(req)).thenReturn(testAccount);
  }

  /**
   * implements the test case for prevalidation endpoint
   *
   * @throws Exception in case of error
   */
  @Test
  public void preValidateOrderTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders
        .post("/v1/bankaccount/").header("Trace-Id", TRACE_ID)
        .header("Content-Type", "application/json")
        .content("{\"firstName\": \"\",\"middleName\": \"singh\",\"lastName\": \"rana\",\"balance\": 100,\"email\": \"ritika.tutu@gmail.com\"}"))
        .andExpect(status().isBadRequest());
    
  }

}
