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
    @ValueSource(strings = {"8", "1,7", "1,2,5"})
    void should_return_sum_when_comma_delimiter(String input) {
        int result = calculator.add(input);
        assertThat(result).isEqualTo(8);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void should_return_zero_when_numbers_is_null_or_empty(String input) {
        int result = calculator.add(input);
        assertThat(result).isZero();
    }

    @Test
    void should_throw_illegal_argument_exception_when_not_only_numbers() {
        assertThrows(IllegalArgumentException.class, () -> calculator.add("1,a3"));
    }

    @Test
    void should_return_sum_when_new_line_and_comma_delimiters() {
        int result = calculator.add("1\n2,3");
        assertThat(result).isEqualTo(6);
    }

    @Test
    void should_return_sum_when_change_delimiters() {
        int result = calculator.add("//;\n1;2");
        assertThat(result).isEqualTo(3);
    }

    @Test
    void should_throw_illegal_argument_exception_when_not_only_one_delimiter_change() {
        assertThrows(IllegalArgumentException.class, () -> calculator.add("//1;2//;\n"));
    }

    @Test
    void should_throw_exception_when_negative_numbers() {
        assertThrows(IllegalArgumentException.class, () -> calculator.add("-1,7,-2,-1,-7"));
    }
}
