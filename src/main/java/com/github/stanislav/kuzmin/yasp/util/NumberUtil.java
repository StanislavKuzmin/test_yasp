package com.github.stanislav.kuzmin.yasp.util;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;


@UtilityClass
public class NumberUtil {

    private static final String REGEX = "^\\d+(\\.\\d+)?$";

    public static Integer convertPriceToCents(String s) {
        return Pattern.matches(REGEX, s) ? (int) (Double.parseDouble(s) * 100) : null;
    }

    public static Integer convertToInteger(String s) {
        return Pattern.matches(REGEX, s) ? (int) Double.parseDouble(s) : null;
    }

    public static Double convertRating(String s) {
        return Pattern.matches(REGEX, s) ? Double.parseDouble(s) : null;
    }
}
