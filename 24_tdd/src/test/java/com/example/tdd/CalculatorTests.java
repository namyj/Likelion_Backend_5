package com.example.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTests {
    private Calculator calculator;

    @BeforeEach
    public void init() {
        calculator = new Calculator();
    }

    // 덧셈 기능
    // 여러 개 숫자를 더하는 기능
    @Test
    public void additionTest() {

        assertEquals(5, calculator.add(2, 3));
        assertEquals(6, calculator.add(1, 2, 3));
        assertNotEquals(5, calculator.add(3, 3));
    }

    // 뺄셈 기능
    @Test
    public void subtractionTest() {
        assertEquals(3, calculator.sub(5, 2));
    }

    // 곱셈 기능
    @Test
    public void multipleTest() {
        assertEquals(6, calculator.mul(2, 3));
    }

    // 나눗셈 기능
    // 기본은 반환값이 정수
    // 0으로 나눌 경우, IllegalArgumentException 발생
    @Test
    public void divisionTest() {
        assertEquals(2, calculator.div(6, 3));

        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.div(6, 0);
        });
        assertEquals("division by zero", exception.getMessage());
    }


    private class Calculator {
        public int add(int ...numbers) {
            int sum = 0;
            for (int number : numbers) {
                sum += number;
            }

            return sum;
        }


        public int sub(int op1, int op2) {
            return op1 - op2;
        }

        public int mul(int op1, int op2) {
            return op1 * op2;
        }

        public int div(int op1, int op2) {
            if (op2 == 0) throw new IllegalArgumentException("division by zero");
            return op1 / op2;
        }
    }
}
