package ru.meldren.lab1.task3;

import java.util.ArrayList;
import java.util.List;

public class Horse {

    private Location destination;
    private boolean wild;
    private final List<Item> cargo = new ArrayList<>();

    public Horse(Location destination, boolean wild) {
        this.destination = destination;
        this.wild = wild;
    }

    public Location getDestination() {
        return destination;
    }

    public void setDestination(Location destination) {
        this.destination = destination;
    }

    public boolean isWild() {
        return wild;
    }

    public void setWild(boolean wild) {
        this.wild = wild;
    }

    public List<Item> getCargo() {
        return cargo;
    }

    public void addCargo(Item item) {
        this.cargo.add(item);
    }

    public void takeCargo(Item item) {
        this.cargo.remove(item);
    }
}
