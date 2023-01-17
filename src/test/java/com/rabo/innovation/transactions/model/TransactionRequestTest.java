package com.rabo.innovation.transactions.model;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.lang.reflect.Field;
import java.util.Set;
import java.util.stream.Stream;

import com.rabo.innovation.transactions.model.CreateAccountRequest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test case for Instrument model class
 */
class TransactionRequestTest {
    
    private static final String INITIATOR_ACCOUNT_NUM = "RABOf895927543084cf8a5473b449b87c7a2";
    private static final String RECIEVER_ACCOUNT_NUM = "RABOf895927543084cf8a5473b449b87c7a3";
    private static final String MESSAGE = "test";
    private static final String REFERENCE = "test";
    private static final double AMOUNT = 100; 

    /**
     * success scenario test case for model class
     */
    @Test
    public void givenValidDto_whenValidated_thenNoValidationError() {
        TransactionRequest transactionRequest = getValidTransactionRequestDto();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        Set<ConstraintViolation<TransactionRequest>> constraintViolations =
                validator.validate(transactionRequest);

        assertThat(constraintViolations.size()).isZero();

    }

    static Stream<Arguments> provideFieldAndInvalidValue() {
        return Stream.of(
                Arguments.of("initiatorAccountNumber", "165Rte65625hub"),
                Arguments.of("initiatorAccountNumber", "test"),
                Arguments.of("recieverAccountNumber", "null"),
                Arguments.of("recieverAccountNumber", "TREW"),
                Arguments.of("amount", 1999999)
        );
    }

    /**
     * error scenario test case for model class
     */
    @SneakyThrows
    @ParameterizedTest
    @MethodSource("provideFieldAndInvalidValue")
    public void testInvalidDto(String fieldName, Object invalidValue) {
        TransactionRequest transactionRequest = getValidTransactionRequestDto();

        Field field = TransactionRequest.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(transactionRequest, invalidValue);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        Set<ConstraintViolation<TransactionRequest>> constraintViolations =
                validator.validate(transactionRequest);

        assertThat(constraintViolations.size()).isOne();
    }

    /**
     * method to return valid model class
     */
    private TransactionRequest getValidTransactionRequestDto() {
        return new TransactionRequest(INITIATOR_ACCOUNT_NUM, RECIEVER_ACCOUNT_NUM, AMOUNT, MESSAGE, REFERENCE);
    }
}