package com.github.stanislav.kuzmin.yasp.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Pattern;

@UtilityClass
public class DateUtil {

    private static final String DATE_PATTERN_FORMATTER = "MMMM d, yyyy";
    private static final String REGEX = "^(?:January|February|March|April|May|June|July|August|September|October|November|December) \\d{1,2}, \\d{4}$";

    public static LocalDate convert(String s) {
        return Pattern.matches(REGEX, s) ? LocalDate.parse(s, DateTimeFormatter.ofPattern(DATE_PATTERN_FORMATTER, Locale.ENGLISH)) : null;
    }
}
