package com.devmountain.training.test.sample.util;

public class MiscUtils {

    public static boolean isPalindrome(String text) {

        var cleaned = text.replaceAll("\\s+", "").toLowerCase();
        var plain = new StringBuilder(cleaned);

        var reversed = plain.reverse().toString();

        return reversed.equals(cleaned);
    }

    public static boolean isOdd(int number) {
        return number % 2 != 0;
    }
}