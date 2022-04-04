package com.devmountain.training.test.sample.service;

import com.devmountain.training.test.sample.util.MiscUtils;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParameterizedTestExample {

    @ParameterizedTest
    @ValueSource(strings = { "racecar", "radar", "level", "refer", "deified", "civic" })
    void isPalindrome_ShouldReturnTrueForPalindromeString(String word) {

        assertTrue(MiscUtils.isPalindrome(word));
    }

    @ParameterizedTest
    @ValueSource(strings = { "test", "Runner", "Execute", "From", "People", "VALUE" })
    void isPalindrome_ShouldReturnFalseForNonPalindromeString(String word) {

        assertFalse(MiscUtils.isPalindrome(word));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE}) // six numbers
    void isOdd_ShouldReturnTrueForOddNumbers(int number) {
        assertTrue(MiscUtils.isOdd(number));
    }
}
