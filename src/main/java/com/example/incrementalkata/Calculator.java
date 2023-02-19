package com.example.incrementalkata;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class Calculator {

    public static final String DELIMITERS = "[,\n]";

    public int add(String input) {
        boolean delimiterChange = isDelimiterChange(input);
        String delimiter = getDelimiter(input, delimiterChange);
        if (delimiterChange && delimiter.length() != 1) {
            throw new IllegalArgumentException("should have only one delimiter change!");
        }
        if (delimiterChange) {
            input = StringUtils.substring(input, input.indexOf("\n")+1);
        }
        if (StringUtils.isBlank(input)) {
            return 0;
        }
        String[] split = input.split(delimiter, -1);
        try {
            return Arrays.stream(split)
                    .map(Integer::parseInt)
                    .reduce(0, Integer::sum);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("input should contain only numbers with comma and new line delimiters!");
        }
    }

    private String getDelimiter(String input, boolean delimiterChange) {
        if (delimiterChange) {
            String delimiter = StringUtils.substringBetween(input, "//", "\n");
            return delimiter != null ? delimiter : DELIMITERS;
        }
        return DELIMITERS;
    }

    private boolean isDelimiterChange(String input) {
        return StringUtils.startsWith(input,"//");
    }
}
