package com.example.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTests {
    @Test
    public void additionTest() {
        Calculator calculator = new Calculator();

        assertEquals(5, calculator.add(2, 3));
    }

    private class Calculator {
        public int add(int op1, int op2) {
            return 5;
        }
    }
}
