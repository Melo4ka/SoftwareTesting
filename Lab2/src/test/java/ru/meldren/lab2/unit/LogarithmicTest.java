package ru.meldren.lab2.unit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.meldren.lab2.math.function.logariphmic.Ln;
import ru.meldren.lab2.math.function.logariphmic.Log;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LogarithmicTest {

    static final double EPSILON = 1e-4;
    Ln ln;
    Log log2;
    Log log3;
    Log log5;

    @BeforeAll
    void init() {
        ln = new Ln();
        log2 = new Log(2);
        log3 = new Log(3);
        log5 = new Log(5);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.001, 0.5, 1.0, 2.0})
    void testLnCalculation(double x) {
        assertEquals(Math.log(x), ln.calculate(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, 0.0})
    void testInvalidLnCalculation(double x) {
        assertThrows(ArithmeticException.class, () -> ln.calculate(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.001, 0.5, 1.0, 2.0})
    void testLogCalculation(double x) {
        assertAll(
                () -> assertEquals(Math.log(x) / Math.log(2), log2.calculate(x), EPSILON),
                () -> assertEquals(Math.log(x) / Math.log(3), log3.calculate(x), EPSILON),
                () -> assertEquals(Math.log(x) / Math.log(5), log5.calculate(x), EPSILON)
        );
    }

    @Test
    void testInvalidLogCalculation() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new Log(0)),
                () -> assertThrows(ArithmeticException.class, () -> log2.calculate(-1))
        );
    }
}