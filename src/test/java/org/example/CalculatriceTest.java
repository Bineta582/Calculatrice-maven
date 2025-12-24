package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatriceTest {
    Calculatrice calc = new Calculatrice();

    @Test
    void testAddition() {
        assertEquals(5, calc.addition(2, 3));
    }

    @Test
    void testSoustraction() {
        assertEquals(1, calc.soustraction(3, 2));
    }

    @Test
    void testMultiplication() {
        assertEquals(6, calc.multiplication(2, 3));
    }

    @Test
    void testDivision() {
        assertEquals(2, calc.division(6, 3));
    }

    @Test
    void testDivisionParZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            calc.division(5, 0);
        });
    }
}
