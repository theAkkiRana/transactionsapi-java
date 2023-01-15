package com.abnamro.trv.order.controller;

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

import com.rabo.innovation.transactions.model.CreateAccountRequest;
import com.rabo.innovation.transactions.model.Account;
import com.rabo.innovation.transactions.service.AccountService;
import com.rabo.innovation.transactions.controller.AccountController;

/**
 * Testing of the endpoint resource for OrderingService
 */
@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

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
  }

  /**
   * implements the test case for prevalidation endpoint
   *
   * @throws Exception in case of error
   */
  @Test
  public void preValidateOrderTest() throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders
        .post("/v1/precheck").header("Trace-Id", TRACE_ID)
        .header("Content-Type", "application/json")
        .content("{\"firstName\": \"ritika\",\"middleName\": \"singh\",\"lastName\": \"rana\",\"balance\": 100,\"email\": \"ritika.tutu@gmail.com\"}"))
        .andExpect(status().isBadRequest());
    
  }

}
