package com.rabo.innovation.transactions.constant;

/**
 * Error keys Enumeration
 * To be used while throwing exceptions for the users
 */
public enum ErrorKeys {

  INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "Internal error occurred", 500),
  TECHNICAL_ERROR("TECHNICAL_EXCEPTION", "Technical error",400),
  ACCOUNT_BALANCE_LOW("ACCOUNT_BALANCE_LOW", "Not enough balance for the transaction", 400),
  FIRST_NAME_MISSING("FIRST_NAME_MISSING", "First name of account holder missing", 400),
  FIRST_NAME_INVALID("FIRST_NAME_INVALID", "First name of account holder is not a valid name", 400),
  MIDDLE_NAME_INVALID("MIDDLE_NAME_INVALID", "Middle name of account holder is not a valid name", 400),
  LAST_NAME_MISSING("LAST_NAME_MISSING", "Last name of account holder missing", 400),
  LAST_NAME_INVALID("LAST_NAME_INVALID", "Last name of account holder is not a valid name", 400),
  EMAIL_ID_MISSING("EMAIL_ID_MISSING", "Email id is missing", 400),
  INITIAL_BALANCE_TOO_HIGH("INITIAL_BALANCE_TOO_HIGH", "Opening balance should be less than 1,000,000", 400),
  EMAIL_ID_INVALID("EMAIL_ID_INVALID", "Email id is invalid", 400),
  INITIAL_BALANCE_MISSING("INITIAL_BALANCE_MISSING", "Opening balance is missing", 400),
  INITIATOR_ACCOUNT_NUMBER_MISSING("INITIATOR_ACCOUNT_NUMBER_MISSING", "Transaction initiator account missing", 400),
  INITIATOR_ACCOUNT_NUMBER_INVALID("INITIATOR_ACCOUNT_NUMBER_INVALID", "Transaction initiator account is invalid", 400),
  RECIEVER_ACCOUNT_NUMBER_MISSING("RECIEVER_ACCOUNT_NUMBER_MISSING", "Reciever initiator account missing", 400),
  RECIEVER_ACCOUNT_NUMBER_INVALID("RECIEVER_ACCOUNT_NUMBER_INVALID", "Reciever initiator account is invalid", 400),
  AMOUNT_TOO_HIGH("AMOUNT_TOO_HIGH", "Transaction amount should be less than 1,000,000", 400),
  AMOUNT_TOO_LOW("AMOUNT_TOO_LOW", "Transaction amount should be a valid value", 400),
  AMOUNT_MISSING("AMOUNT_MISSING", "No transfer amount given", 400),
  INITIAL_BALANCE_TOO_LOW("INITIAL_BALANCE_TOO_LOW", "Minimum balance should be 50 Euros", 400 ),
  TRACE_ID_MISSING("TRACE_ID_MISSING", "header value trace-id missing", 400);

  
  /**
   * The developers error message to be given
   * in case of any error scenarios 
   */
  private final String errorMessage;

  /**
   * The error code that will be used by the consuming
   * Front ends to display error message as per
   * translation files
   */
  private final String errorCode;

  /**
   * The HTTP error status of the request
   */
  private final int errorStatus;

  /**
   * Constructor to assign the field value of the ErrorKeys enum values.
   *
   * @param errorMessage It carries the more detailed and understandable description of the error.
   * @param errorCode
   * @param errorStatus
   */
  ErrorKeys(String errorCode, String errorMessage,  int errorStatus) {
    this.errorMessage = errorMessage;
    this.errorCode = errorCode;
    this.errorStatus = errorStatus;
  }

  /**
   * @return the message
   */
  public String getErrorMessage() {
    return errorMessage;
  }

  /**
   * @return the error code
   */
  public String getErrorCode() {
    return errorCode;
  }

  /**
   * @return the error status
   */
  public int getErrorStatus() {
    return errorStatus;
  }
}


