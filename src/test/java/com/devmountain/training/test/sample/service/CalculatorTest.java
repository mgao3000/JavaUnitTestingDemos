package com.devmountain.training.test.sample.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    public void initEach() {
        calculator = new Calculator();
    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void testSum() {
        // Given
//        Calculator calculator = new Calculator();
        // When
        int result = calculator.sum(2, 2);
        // Then
        if (result != 4) {   // if 2 + 2 != 4
            fail();
        }
    }

    @Test
    public void testMinus() {
        assertEquals(0, calculator.minus(2, 2));
    }

    @Test
    public void testDivide() {
        assertEquals(2, calculator.divide(6, 3));
    }

    @Test
    public void testDivideWillThrowExceptionWhenDivideOnZero() {
        Calculator calculator = new Calculator();
        Exception exception = assertThrows(ArithmeticException.class, () -> {
            calculator.divide(6, 0);
        });
//        System.out.println("exception.getMessage() = " + exception.getMessage());

        String expectedMessage = "/ by zero";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}

