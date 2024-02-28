package ru.meldren.lab1.task1;

public class Arcsin {

    private static final double EPSILON = 1e-10;

    public static double calculate(double x) {
        if (Math.abs(x) > 1) {
            return Double.NaN;
        }
        double result = x, term = x;
        int n = 1;
        while (Math.abs(term) > EPSILON) {
            term *= x * x * (2 * n - 1) * (2 * n - 1) / (2 * n) / (2 * n + 1);
            result += term;
            n++;
        }
        return result;
    }
}
