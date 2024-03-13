package ru.meldren.lab1.task1;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ArcsinTest {

    private static final double EPSILON = 0.001;
    private static Random random;

    @BeforeAll
    static void init() {
        random = new Random();
    }

    @ParameterizedTest
    @CsvSource({
            "0.5, 0.52359877559",
            "-0.5, -0.52359877559",
            "0, 0",
            "1, 1.57079632679",
            "-1, -1.57079632679",
            "-0.95, -1.2532359",
            "0.95, 1.2532359"
    })
    void testArcsinWithinRange(double x, double expected) {
        assertEquals(expected, Arcsin.calculate(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {
            1.1,
            -1.1,
            Double.POSITIVE_INFINITY,
            Double.NEGATIVE_INFINITY
    })
    void testArcsinOutsideRange(double x) {
        assertTrue(Double.isNaN(Arcsin.calculate(x)));
    }

    @RepeatedTest(100)
    void testArcsinRandomValues() {
        double x = random.nextDouble() * 2 - 1;
        assertEquals(Math.asin(x), Arcsin.calculate(x), EPSILON);
    }
}
