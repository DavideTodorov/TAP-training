package com.tap.algos;

import java.util.Random;

public class Utils {
    public static int[] generateArray(int n) {
        int[] generatedArray = new int[n];

        for (int i = 0; i < n; i++) {
            int newRandomInt = new Random().nextInt(10000);
            generatedArray[i] = newRandomInt;
        }
        return generatedArray;
    }

    public static String[] generateStringArray(int n) {
        String[] generatedArray = new String[n];

        for (int i = 0; i < n; i++) {
            int leftLimit = 97;
            int rightLimit = 122;
            int targetStringLength = 10;
            Random random = new Random();

            String generatedString = random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            generatedArray[i] = generatedString;
        }
        return generatedArray;
    }
}
