package ru.meldren.lab2.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.io.TempDir;
import ru.meldren.lab2.math.MathFunction;
import ru.meldren.lab2.writer.CsvWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CsvWriterTest {

    @TempDir
    File csvDirectory;

    @Test
    void testWritIntoFile() throws IOException {
        File file = new File(csvDirectory, "output.csv");
        CsvWriter writer = new CsvWriter(file, ',');
        writer.write(new IncrementFunction(), 0, 3, 1);
        assertEquals(
                Arrays.asList(
                        "0.000000,1.000000",
                        "1.000000,2.000000",
                        "2.000000,3.000000",
                        "3.000000,4.000000"
                ),
                Files.readAllLines(file.toPath())
        );
    }

    private static class IncrementFunction extends MathFunction {

        @Override
        public double calculate(double x) {
            return x + 1;
        }
    }
}
