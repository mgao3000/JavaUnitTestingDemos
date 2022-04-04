package com.devmountain.training.test.sample.service;

import com.devmountain.training.test.sample.exception.NameNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionUnitTestExample {

    @Test
    void test_exception_custom() {
        Exception exception = assertThrows(
                NameNotFoundException.class,
                () -> findByName("mike"));

        assertTrue(exception.getMessage().contains("not found"));
    }

    private String findByName(String name) throws NameNotFoundException{
        throw new NameNotFoundException( name + " not found!");
    }

    @Test
    void testExpectedException() {

        NumberFormatException thrown = Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("One");
        }, "NumberFormatException was expected");

        Assertions.assertEquals("For input string: \"One\"", thrown.getMessage());
    }

}
