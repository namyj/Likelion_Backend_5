package com.example.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CalculatorTests {
    @Test
    public void additionTest() {
        Calculator calculator = new Calculator();

        assertEquals(5, calculator.add(2, 3));
        assertNotEquals(5, calculator.add(3, 3));
    }

    @Test
    public void subtractionTest() {
        Calculator calculator = new Calculator();

        assertEquals(3, calculator.sub(5, 2));
    }

    @Test
    public void multipleTest() {
        Calculator calculator = new Calculator();

        assertEquals(6, calculator.mul(2, 3));
    }


    private class Calculator {
        public int add(int a, int b) {
            return a + b;
        }

        public int sub(int a, int b) {
            return a - b;
        }

        public int mul(int a, int b) {
            return a * b;
        }
    }
}
