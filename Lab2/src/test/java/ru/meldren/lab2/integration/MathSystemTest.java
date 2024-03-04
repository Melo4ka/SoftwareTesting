package ru.meldren.lab2.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.meldren.lab2.math.MathSystem;
import ru.meldren.lab2.math.function.logariphmic.Ln;
import ru.meldren.lab2.math.function.logariphmic.Log;
import ru.meldren.lab2.math.function.trigonometric.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;
import static ru.meldren.lab2.ReflectionUtil.setField;

@ExtendWith(MockitoExtension.class)
public class MathSystemTest {

    static final double EPSILON = 1e-4;
    @Mock
    Sin sinMock;
    @Mock
    Cos cosMock;
    @Mock
    Csc cscMock;
    @Mock
    Sec secMock;
    @Mock
    Tan tanMock;
    @Mock
    Cot cotMock;
    @Mock
    Ln lnMock;
    @Mock
    Log log2Mock;
    @Mock
    Log log3Mock;
    @Mock
    Log log5Mock;
    @Spy
    Sin sinSpy;
    @Spy
    Cos cosSpy;
    @Spy
    Csc cscSpy;
    @Spy
    Sec secSpy;
    @Spy
    Tan tanSpy;
    @Spy
    Cot cotSpy;
    @Spy
    Ln lnSpy;
    Log log2Spy = spy(new Log(2));
    Log log3Spy = spy(new Log(3));
    Log log5Spy = spy(new Log(5));

    @Test
    void testMathSystemCalculationTrigonometricFunctionsCalled() {
        MathSystem mathSystem = new MathSystem();
        setField(mathSystem, "sin", sinSpy);
        setField(mathSystem, "cos", cosSpy);
        setField(mathSystem, "csc", cscSpy);
        setField(mathSystem, "sec", secSpy);
        setField(mathSystem, "tan", tanSpy);
        setField(mathSystem, "cot", cotSpy);
        mathSystem.calculate(-1.5);
        verify(sinSpy, atLeastOnce()).calculate(anyDouble());
        verify(cosSpy, atLeastOnce()).calculate(anyDouble());
        verify(cscSpy, atLeastOnce()).calculate(anyDouble());
        verify(secSpy, atLeastOnce()).calculate(anyDouble());
        verify(tanSpy, atLeastOnce()).calculate(anyDouble());
        verify(cotSpy, atLeastOnce()).calculate(anyDouble());
    }

    @Test
    void testMathSystemCalculationWithMockedTrigonometricFunctionValues() {
        MathSystem mathSystem = new MathSystem();
        setField(mathSystem, "sin", sinMock);
        setField(mathSystem, "cos", cosMock);
        setField(mathSystem, "csc", cscMock);
        setField(mathSystem, "sec", secMock);
        setField(mathSystem, "tan", tanMock);
        setField(mathSystem, "cot", cotMock);
        double x = -Math.PI / 4;
        when(sinMock.calculate(x)).thenReturn(Math.sqrt(2) / 2);
        when(cosMock.calculate(x)).thenReturn(Math.sqrt(2) / 2);
        when(cscMock.calculate(x)).thenReturn(Math.sqrt(2));
        when(secMock.calculate(x)).thenReturn(Math.sqrt(2));
        when(tanMock.calculate(x)).thenReturn(1.0);
        when(cotMock.calculate(x)).thenReturn(1.0);
        assertEquals(3.8284, mathSystem.calculate(x), EPSILON);
    }

    @Test
    void testMathSystemCalculationLogarithmicFunctionsCalled() {
        MathSystem mathSystem = new MathSystem();
        setField(mathSystem, "ln", lnSpy);
        setField(mathSystem, "log2", log2Spy);
        setField(mathSystem, "log3", log3Spy);
        setField(mathSystem, "log5", log5Spy);
        mathSystem.calculate(1.5);
        verify(lnSpy, atLeastOnce()).calculate(anyDouble());
        verify(log2Spy, atLeastOnce()).calculate(anyDouble());
        verify(log3Spy, atLeastOnce()).calculate(anyDouble());
        verify(log5Spy, atLeastOnce()).calculate(anyDouble());
    }

    @Test
    void testMathSystemCalculationWithMockedLogarithmicFunctionValues() {
        MathSystem mathSystem = new MathSystem();
        setField(mathSystem, "ln", lnMock);
        setField(mathSystem, "log2", log2Mock);
        setField(mathSystem, "log3", log3Mock);
        setField(mathSystem, "log5", log5Mock);
        double x = 2.25;
        when(lnMock.calculate(x)).thenReturn(0.81093);
        when(log2Mock.calculate(x)).thenReturn(1.16992);
        when(log3Mock.calculate(x)).thenReturn(0.73814);
        when(log5Mock.calculate(x)).thenReturn(0.50385);
        assertEquals(-0.02539, mathSystem.calculate(x), EPSILON);
    }
}
