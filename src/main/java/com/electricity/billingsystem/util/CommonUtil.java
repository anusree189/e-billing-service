package com.electricity.billingsystem.util;

import java.util.Random;

public class CommonUtil {
    public static String convertToCamelCase(String inputString) {
        StringBuilder camelCase = new StringBuilder();
        boolean capitalizeNext = true;
        for (char c : inputString.trim().toCharArray()) {
            if (Character.isLetter(c) || c == '&' || c == ' ' || c == ',') {
                if (capitalizeNext && c != ' ') {
                    camelCase.append(Character.toUpperCase(c));
                    capitalizeNext = false;
                } else {
                    if (c == '&' || c == ',' || c == ' ') {
                        capitalizeNext = true;
                    }
                    camelCase.append(Character.toLowerCase(c));
                }
            } else {
                capitalizeNext = true;
            }
        }
        return camelCase.toString();
    }

    public static int generateUniqueNumber() {
        Random random = new Random();
        return random.nextInt(9000000) + 1000000;
    }
}
