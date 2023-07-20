package com.example.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTests {
    // 덧셈 기능
    @Test
    public void additionTest() {
        Calculator calculator = new Calculator();

        assertEquals(5, calculator.add(2, 3));
        assertNotEquals(5, calculator.add(3, 3));
    }

    // 뺄셈 기능
    @Test
    public void subtractionTest() {
        Calculator calculator = new Calculator();

        assertEquals(3, calculator.sub(5, 2));
    }

    // 곱셈 기능
    @Test
    public void multipleTest() {
        Calculator calculator = new Calculator();

        assertEquals(6, calculator.mul(2, 3));
    }

    // 나눗셈 기능
    // 기본은 반환값이 정수
    // 0으로 나눌 경우, IllegalArgumentException 발생
    @Test
    public void divisionTest() {
        Calculator calculator = new Calculator();

        assertEquals(2, calculator.div(6, 3));
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.div(6, 0);
        });
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

        public int div(int a, int b) {
            if (b == 0) throw new IllegalArgumentException();
            return a / b;
        }
    }
}
