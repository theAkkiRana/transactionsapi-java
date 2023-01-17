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

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.rabo.innovation.transactions.controller.TransactionsController;
import com.rabo.innovation.transactions.model.TransactionRequest;
import com.rabo.innovation.transactions.model.Transaction;
import com.rabo.innovation.transactions.service.TransactionsService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Testing of the endpoint resource for OrderingService
 */
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@WebMvcTest(TransactionsController.class)
public class TransactionsControllerTests {

  private final String TRACE_ID = "1234567890";

  private final String initiatorAccountNumber = "RABOf776ec7633dc42748413ba9e76c92fc6";
  private final String recieverAccountNumber = "RABOf895927543084cf8a5473b449b87c7a2";
  private final double amount = 20;
  private final String message = "message for first transaction 20 euro";
  private final String reference = "reference for first transaction 20 euro";

  /**
   * creating a mock object of OrderingController class
   */
  @InjectMocks
  private TransactionsController controller;

  /**
   * mocking of the service layer
   */
  @MockBean
  TransactionsService transactionService;

  @Autowired
	private MockMvc mockMvc;

  /**
   * mocking the controller
   */
  @BeforeEach
  public void setup() throws Exception {
    MockitoAnnotations.initMocks(this);
    this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    TransactionRequest req = new TransactionRequest();
    req.setInitiatorAccountNumber("test123");
    req.setRecieverAccountNumber("test234");
    req.setAmount(100);
    req.setMessage("my msg");
    req.setReference("my ref");
    Transaction transaction = new Transaction();
    transaction.setId("12345"); 
    transaction.setDebitedfrom("test123");
    transaction.setCreditedto("test234");
    transaction.setTimestamp("10-10-1987");
    transaction.setReference("my ref");
    transaction.setMessage("my msg");
    transaction.setAmount(100);
    Mockito.when(transactionService.addTransaction(req)).thenReturn(transaction);
  }

  /**
   * implements the test case for prevalidation endpoint
   *
   * @throws Exception in case of error
   */
  @Test
  public void transactionApiInvalidData() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders
        .post("/v1/transaction/").header("Trace-Id", TRACE_ID)
        .header("Content-Type", "application/json")
        .content("{\"initiatorAccountNumber\": \"\",\"recieverAccountNumber\" : \"RABOf895927543084cf8a5473b449b87c7a2\",\"amount\" : 0,\"message\": \"message for first transaction 20 euro\",\"reference\" : \"reference for first transaction 20 euro\"}"))
        .andExpect(status().isBadRequest());
  }


}
