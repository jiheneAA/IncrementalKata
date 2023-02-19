package com.example.incrementalkata;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Calculator {

    public static final String DELIMITERS = "[,\n]";

    public int add(String input) {
        List<Integer> numbers = getNumbers(input);
        List<Integer> negativeNumbers = numbers.stream().filter(x -> x < 0).toList();
        if (!CollectionUtils.isEmpty(negativeNumbers)) {
            throw new IllegalArgumentException("negatives not allowed :" + negativeNumbers);
        }
            return numbers.stream().reduce(0, Integer::sum);
    }

    private List<Integer> getNumbers(String input) {
        boolean delimiterChange = isDelimiterChange(input);
        String delimiter = getDelimiter(input, delimiterChange);
        if (delimiterChange && delimiter.length() != 1) {
            throw new IllegalArgumentException("should have only one delimiter change!");
        }
        if (delimiterChange) {
            input = StringUtils.substring(input, input.indexOf("\n")+1);
        }
        if (StringUtils.isBlank(input)) {
            return Collections.emptyList();
        }
        try {
            return Arrays.stream(input.split(delimiter, -1))
                    .map(Integer::parseInt)
                    .toList();
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
