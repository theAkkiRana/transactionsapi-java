package com.rabo.innovation.transactions.model;

import java.util.List;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * Error message for the exception
 */
@AllArgsConstructor
@NoArgsConstructor
@Data public class Error {

  /**
   * This attribute hold the code corresponds to the message of exception
   */
  private String code;

  /**
   * This attribute holds description of the message
   */
  private String message;

  /**
   * This attribute shows Unique trace for API
   */
  private String traceId;

  /**
   * This attribute shows the http status code possible values 400 (BAD request)
   */
  private int status;

  /**
   * This attribute holds the value of params array
   */
  private List<String> params;

  /**
   * Parameterised Constructor of the error class
   *
   * @param status http status code possible values 400 (BAD request)
   * @param code holds the code corresponds to the message of exception
   * @param message holds description of the message
   */
  public Error(int status, String code, String message, String traceId) {
    this.status = status;
    this.message = message;
    this.code = code;
    this.traceId = traceId;
  }

}
