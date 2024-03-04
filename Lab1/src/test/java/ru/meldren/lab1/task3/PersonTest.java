package ru.meldren.lab1.task3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PersonTest {

    private static Random random;

    @BeforeAll
    static void init() {
        random = new Random();
    }

    @RepeatedTest(500)
    void personInstantiationTest() {
        String name = getRandomString(10);
        Location location = new Location(getRandomString(20));
        Person.PhysicalState physicalState = getRandomEnum(Person.PhysicalState.class);
        Person.EmotionalState emotionalState = getRandomEnum(Person.EmotionalState.class);
        int size = random.nextInt(1, Integer.MAX_VALUE);
        Person person = new Person(
                name,
                location,
                physicalState,
                emotionalState,
                size
        );
        assertEquals(person.getName(), name);
        assertEquals(person.getLocation(), location);
        assertEquals(person.getPhysicalState(), physicalState);
        assertEquals(person.getEmotionalState(), emotionalState);
        assertEquals(person.getSize(), size);
    }

    @Test
    void validEmotionalAndPhysicalStatesAsStringTest() {
        assertDoesNotThrow(() -> new Person(
                "Том",
                new Location("Дом"),
                "SITTING",
                "HAPPINESS",
                1
        ));
    }

    @Test
    void invalidEmotionalStateAsStringTest() {
        assertThrows(IllegalArgumentException.class, () -> new Person(
                "Том",
                new Location("Дом"),
                "SITTING",
                "12345",
                1
        ));
    }

    @Test
    void invalidPhysicalStateAsStringTest() {
        assertThrows(IllegalArgumentException.class, () -> new Person(
                "Том",
                new Location("Дом"),
                "ABCDE",
                "HAPPINESS",
                1
        ));
    }

    @RepeatedTest(10)
    void invalidSizeTest() {
        assertThrows(IllegalStateException.class, () -> new Person(
                "Том",
                new Location("Дом"),
                "SITTING",
                "HAPPINESS",
                random.nextInt(Integer.MIN_VALUE, 1)
        ));
    }

    private static String getRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        random.ints('0', 'z')
                .filter(num -> num >= '0' && num <= '9' || num >= 'a' && num <= 'z' || num >= 'A' && num <= 'Z')
                .limit(length)
                .forEach(sb::appendCodePoint);
        return sb.toString();
    }

    private static <T extends Enum<?>> T getRandomEnum(Class<T> enumClass) {
        T[] values = enumClass.getEnumConstants();
        return values[random.nextInt(values.length)];
    }
}
