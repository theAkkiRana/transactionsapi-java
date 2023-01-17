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
public class CreateAccountRequest {

    /**
     * name of the account owner
     */
    @NotBlank(message = "FIRST_NAME_MISSING")
    @NotNull(message = "FIRST_NAME_MISSING")
    @Pattern(regexp="^[A-Za-z]*$",message = "FIRST_NAME_INVALID")
    private String firstName;

    /**
     * name of the account owner
     */
    @Pattern(regexp="^[A-Za-z]*$",message = "MIDDLE_NAME_INVALID")
    private String middleName;

    /**
     * name of the account owner
     */
    @NotBlank(message = "LAST_NAME_MISSING")
    @NotNull(message = "LAST_NAME_MISSING")
    @Pattern(regexp="^[A-Za-z]*$",message = "LAST_NAME_INVALID")
    private String lastName;


    /**
     * email id of the user
     * should be unique - 1 email id per account
     */
    @NotNull(message = "EMAIL_ID_MISSING")
    @Pattern(regexp="^[A-Za-z0-9+_.-]+@(.+)$",message = "EMAIL_ID_INVALID")
    private String email;

    /**
     * initial opening balance given to the account
     */
    @DecimalMax(value = "999999.0", message = "INITIAL_BALANCE_TOO_HIGH") 
    @DecimalMin(value = "50.0", message = "INITIAL_BALANCE_TOO_LOW")
    private double balance;
    
}
