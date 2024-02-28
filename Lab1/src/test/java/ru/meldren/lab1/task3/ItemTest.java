package ru.meldren.lab1.task3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.function.Executable;

import java.time.LocalDate;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ItemTest {

    private static Random random;

    @BeforeAll
    static void init() {
        random = new Random();
    }

    @Test
    void itemInstantiationTest() {
        String name = "Армированные изгороди";
        LocalDate manufacturingDate = LocalDate.now();
        Item item = new Item(name, manufacturingDate);
        assertEquals(item.getName(), name);
        assertEquals(item.getManufacturingDate(), manufacturingDate);
    }

    @RepeatedTest(100)
    void itemInstantiationWithRandomManufacturingDateTest() {
        String name = "Армированные изгороди";
        LocalDate randomDate = generateRandomDate();
        Executable executable = () -> new Item(name, randomDate);
        if (randomDate.isBefore(LocalDate.now().minusYears(Item.MINIMUM_MANUFACTURING_YEARS))) {
            assertThrows(IllegalArgumentException.class, executable);
        } else {
            assertDoesNotThrow(executable);
        }
    }

    private LocalDate generateRandomDate() {
        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.now().toEpochDay();
        long randomDay = random.nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }
}
