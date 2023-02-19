package com.example.incrementalkata;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @ParameterizedTest
    @ValueSource(strings = {"8", "1,7"})
    void should_return_sum(String input) {
        int result = calculator.add(input);
        assertThat(result).isEqualTo(8);
    }

    @Test
    void should_return_sum_when_negative_number() {
        int result = calculator.add("-1,7");
        assertThat(result).isEqualTo(6);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void should_return_zero_when_numbers_is_null_or_empty(String input) {
        int result = calculator.add(input);
        assertThat(result).isZero();
    }

    @Test
    void should_throw_illegal_argument_exception_when_more_than_2_numbers() {
        assertThrows(IllegalArgumentException.class, () -> calculator.add("1,2,3"));
    }

    @Test
    void should_throw_illegal_argument_exception_when_not_only_numbers() {
        assertThrows(IllegalArgumentException.class, () -> calculator.add("1,a3"));
    }
}
