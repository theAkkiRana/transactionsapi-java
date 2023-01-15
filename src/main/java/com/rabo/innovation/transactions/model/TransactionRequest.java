package com.rabo.innovation.transactions.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * Request object to create new account
 * user can create new account with 
 * username, emailId, initial balance 
 */
@AllArgsConstructor
@NoArgsConstructor
@Data 
public class TransactionRequest {

    @NotBlank(message = "ACCOUNT_NUMBER_MISSING")
    @NotNull(message = "ACCOUNT_NUMBER_MISSING")
    @Pattern(regexp="^RABO[a-z0-9{32}]*$",message = "ACCOUNT_NUMBER_INVALID")
    private String initiatorAccountNumber;

    @NotBlank(message = "ACCOUNT_NUMBER_MISSING")
    @NotNull(message = "ACCOUNT_NUMBER_MISSING")
    @Pattern(regexp="^RABO[a-z0-9{32}]*$",message = "ACCOUNT_NUMBER_INVALID")
    private String recieverAccountNumber;
    
    @DecimalMax(value = "999999.0", message = "AMOUNT_TOO_HIGH") 
    @DecimalMin(value = "0.0", message = "AMOUNT_TOO_LOW")
    @NotNull(message = "AMOUNT_MISSING")
    private double amount;

    private String message;
    private String reference;
    
    
}
