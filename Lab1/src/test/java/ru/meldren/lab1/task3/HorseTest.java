package ru.meldren.lab1.task3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HorseTest {

    @Test
    void horseInstantiationTest() {
        Location destination = new Location("Сад");
        Horse horse = new Horse(destination, false);
        assertEquals(horse.getDestination(), destination);
        assertFalse(horse.isWild());
        assertEquals(horse.getCargo().size(), 0);
    }

    @Test
    void addCargoTest() {
        Horse horse = new Horse(new Location("Дом"), true);
        Item cargo = new Item("Армированные изгороди", LocalDate.now());
        horse.addCargo(cargo);
        assertEquals(cargo, horse.getCargo().get(0));
    }
}
