package com.code.Kata;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class StringCalculatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"", "1", "1,2", "1\n2,3", "//;\n1;2", "//[|||]\n1|||2|||3", "//[|][%]\n1|2%3"})
    void testAdd_withMultipleDelimiters_shouldSucceed(String input) {
        Integer result = StringCalculator.add(input);
        Assertions.assertDoesNotThrow(()->RuntimeException.class);
        System.out.println(String.format("Input: %s, Result: %d", input, result));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1,2", "2,-4,3,-5"})
    void testAdd_withNegativeNumbers_shouldThrowException(String input) {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            StringCalculator.add(input);
        });
        String expectedMessage = "Negatives not allowed: ";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1001,1,2"})
    void testAdd_withNumbersGreaterThan1000_shouldIgnoreThem(String input) {
        Integer result = StringCalculator.add(input);
        Assertions.assertDoesNotThrow(()->RuntimeException.class);
        System.out.println(String.format("Input: %s, Result: %d", input, result));
    }

}