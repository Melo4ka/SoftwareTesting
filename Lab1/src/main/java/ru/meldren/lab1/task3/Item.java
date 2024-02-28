package ru.meldren.lab1.task3;

import java.time.LocalDate;
import java.util.Objects;

public class Item {

    public static final int MINIMUM_MANUFACTURING_YEARS = 10;
    private final String name;
    private final LocalDate manufacturingDate;

    public Item(String name, LocalDate manufacturingDate) {
        if (manufacturingDate.isBefore(LocalDate.now().minusYears(MINIMUM_MANUFACTURING_YEARS))) {
            throw new IllegalArgumentException(String.format("Manufacturing date cannot be more than %d years ago.", MINIMUM_MANUFACTURING_YEARS));
        }
        this.name = name;
        this.manufacturingDate = manufacturingDate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getManufacturingDate() {
        return manufacturingDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return Objects.equals(name, item.name) && Objects.equals(manufacturingDate, item.manufacturingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, manufacturingDate);
    }
}
