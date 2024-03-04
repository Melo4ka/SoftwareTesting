package ru.meldren.lab2.math;

import ru.meldren.lab2.math.function.logariphmic.Ln;
import ru.meldren.lab2.math.function.logariphmic.Log;
import ru.meldren.lab2.math.function.trigonometric.*;

public class MathSystem extends MathFunction {

    private final Sin sin;
    private final Cos cos;
    private final Csc csc;
    private final Sec sec;
    private final Tan tan;
    private final Cot cot;
    private final Ln ln;
    private final Log log2;
    private final Log log3;
    private final Log log5;

    public MathSystem() {
        this.sin = new Sin();
        this.cos = new Cos();
        this.csc = new Csc();
        this.sec = new Sec();
        this.tan = new Tan();
        this.cot = new Cot();
        this.ln = new Ln();
        this.log2 = new Log(2);
        this.log3 = new Log(3);
        this.log5 = new Log(5);
    }

    public MathSystem(double precision, int maxIterations) {
        super(precision, maxIterations);
        this.sin = new Sin(precision, maxIterations);
        this.cos = new Cos(precision, maxIterations);
        this.csc = new Csc(precision, maxIterations);
        this.sec = new Sec(precision, maxIterations);
        this.tan = new Tan(precision, maxIterations);
        this.cot = new Cot(precision, maxIterations);
        this.ln = new Ln(precision, maxIterations);
        this.log2 = new Log(2, precision, maxIterations);
        this.log3 = new Log(3, precision, maxIterations);
        this.log5 = new Log(5, precision, maxIterations);
    }

    @Override
    public double calculate(double x) {
        double result;
        if (x <= 0) {
            double sinValue = sin.calculate(x);
            double cosValue = cos.calculate(x);
            double cscValue = csc.calculate(x);
            double secValue = sec.calculate(x);
            double tanValue = tan.calculate(x);
            double cotValue = cot.calculate(x);

            double term1 = sinValue / cosValue;
            double term2 = 2.0 * cscValue / secValue;
            double term3 = Math.pow(secValue, 3) / tanValue;

            result = term1 * (term2 + term3 - cotValue);
        } else {
            double lnValue = ln.calculate(x);
            double log2Value = log2.calculate(x);
            double log3Value = log3.calculate(x);
            double log5Value = log5.calculate(x);

            double term1 = lnValue * log5Value / (lnValue * log2Value);
            double term2 = lnValue - (lnValue + log2Value);
            double term3 = log3Value / log2Value;
            double term4 = Math.pow(log5Value, 2);

            result = Math.pow(term1, 2) / term2 * term3 * term4;
        }
        if (!Double.isFinite(result)) {
            throw new ArithmeticException(String.format("Function value for argument %f doesn't exist.", x));
        }
        return result;
    }
}
