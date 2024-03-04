package ru.meldren.lab2.unit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import ru.meldren.lab2.math.MathSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MathSystemTest {

    static final double EPSILON = 1e-4;
    MathSystem mathSystem;

    @BeforeAll
    void init() {
        mathSystem = new MathSystem();
    }

    @ParameterizedTest
    @CsvSource({
            "0.0721, 0.1",
            "0.0003, 0.99",
            "0.2163, 0.001",
            "-0.0217, 2.0",
            "2.7787, -0.6",
            "-0.9447, -2.5",
            "-0.3866, -3.6",
    })
    void testMathSystemCalculation(double expected, double x) {
        assertEquals(expected, mathSystem.calculate(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, 1.0, Double.NaN, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY})
    void testInvalidCosCalculation(double x) {
        assertThrows(ArithmeticException.class, () -> mathSystem.calculate(x));
    }
}
