package ru.meldren.lab2.writer;

import ru.meldren.lab2.math.MathFunction;

import java.io.IOException;

public interface IWriter {

    void write(
            MathFunction function,
            double from,
            double to,
            double step
    ) throws IOException;
}
