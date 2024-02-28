package ru.meldren.lab1.task3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestInstance;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        boolean huge = random.nextBoolean();
        Person person = new Person(
                name,
                location,
                physicalState,
                emotionalState,
                huge
        );
        assertEquals(person.getName(), name);
        assertEquals(person.getLocation(), location);
        assertEquals(person.getPhysicalState(), physicalState);
        assertEquals(person.getEmotionalState(), emotionalState);
        assertEquals(person.isHuge(), huge);
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
