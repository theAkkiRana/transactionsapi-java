package com.rabo.innovation.transactions.constant;

/**
 * Error keys Enumeration
 * To be used while throwing exceptions for the users
 */
public enum ErrorKeys {

  INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "Internal error occurred", 500),
  TECHNICAL_ERROR("TECHNICAL_EXCEPTION", "Technical error",400),
  ACCOUNT_BALANCE_LOW("ACCOUNT_BALANCE_LOW", "Not enough balance for the transaction", 400);

  
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


