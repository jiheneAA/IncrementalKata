package com.example.incrementalkata;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class Calculator {

    public static final String COMMA_SEPARATOR = ",";

    public int add(String numbers) {
        if (StringUtils.isBlank(numbers)) {
            return 0;
        }
        String[] split = numbers.split(COMMA_SEPARATOR);
        try {
            return Arrays.stream(split)
                    .map(Integer::parseInt)
                    .reduce(0, Integer::sum);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("input should contain only numbers with comma separator!");
        }
    }
}
