package com.javarush.task.task33.task3310;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Helper {
    public static String generateRandomString() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, new SecureRandom()).toString(36);
    }

    static void printMessage(String message) {
        System.out.println(message);
    }
}
