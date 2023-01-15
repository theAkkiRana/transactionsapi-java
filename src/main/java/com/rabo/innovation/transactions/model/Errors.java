package com.rabo.innovation.transactions.model;

import java.util.List;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import lombok.Data;

/**
 * This is the wrapper class for exception according to rest error message new standard
 */
@AllArgsConstructor
@NoArgsConstructor
@Data public class Errors {

  /**
   * This attribute hold the value errors which is thrown when exception occurs
   */
  private List<Error> errors;

}
