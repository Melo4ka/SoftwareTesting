package ru.meldren.lab1.task3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LocationTest {

    @Test
    void locationInstantiationTest() {
        String name = "Неизведанные Области";
        assertEquals(new Location(name).getName(), name);
    }

    @Test
    void locationsEqualityCheck() {
        String name = "Изведанные Области";
        Location first = new Location(name);
        Location second = new Location(name);
        assertEquals(first, second);
    }
}
