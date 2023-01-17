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
class CreateAccountRequestTest {
    
    private static final String FIRST_NAME = "akshat";
    private static final String MIDDLE_NAME = "singh";
    private static final String LAST_NAME = "rana";
    private static final String EMAIL_ID = "akki.rana87@gmail.com";
    private static final double BALANCE = 100; 

    /**
     * success scenario test case for model class
     */
    @Test
    public void givenValidDto_whenValidated_thenNoValidationError() {
        CreateAccountRequest createAccountRequest = getValidCreateAccountRequestDto();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        Set<ConstraintViolation<CreateAccountRequest>> constraintViolations =
                validator.validate(createAccountRequest);

        assertThat(constraintViolations.size()).isZero();

    }

    static Stream<Arguments> provideFieldAndInvalidValue() {
        return Stream.of(
                Arguments.of("firstName", "165Rte65625hub"),
                Arguments.of("middleName", "TEST1"),
                Arguments.of("lastName", "1245.0"),
                Arguments.of("email", "TREW"),
                Arguments.of("balance", 1999999)
        );
    }

    /**
     * error scenario test case for model class
     */
    @SneakyThrows
    @ParameterizedTest
    @MethodSource("provideFieldAndInvalidValue")
    public void testInvalidDto(String fieldName, Object invalidValue) {
        CreateAccountRequest createAccountRequest = getValidCreateAccountRequestDto();

        Field field = CreateAccountRequest.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(createAccountRequest, invalidValue);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        final Validator validator = factory.getValidator();

        Set<ConstraintViolation<CreateAccountRequest>> constraintViolations =
                validator.validate(createAccountRequest);

        assertThat(constraintViolations.size()).isOne();
    }

    /**
     * method to return valid model class
     */
    private CreateAccountRequest getValidCreateAccountRequestDto() {
        return new CreateAccountRequest(FIRST_NAME, MIDDLE_NAME, LAST_NAME, EMAIL_ID, BALANCE);
    }
}