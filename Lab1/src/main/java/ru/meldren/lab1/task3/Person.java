package ru.meldren.lab1.task3;

public class Person {

    private final String name;
    private Location location;
    private PhysicalState physicalState;
    private EmotionalState emotionalState;
    private int size;

    public Person(String name, Location location, PhysicalState physicalState, EmotionalState emotionalState, int size) {
        checkSize(size);
        this.name = name;
        this.location = location;
        this.physicalState = physicalState;
        this.emotionalState = emotionalState;
        this.size = size;
    }

    public Person(String name, Location location, String physicalState, String emotionalState, int size) {
        checkSize(size);
        this.name = name;
        this.location = location;
        this.physicalState = PhysicalState.valueOf(physicalState);
        this.emotionalState = EmotionalState.valueOf(emotionalState);
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public PhysicalState getPhysicalState() {
        return physicalState;
    }

    public void setPhysicalState(PhysicalState physicalState) {
        this.physicalState = physicalState;
    }

    public EmotionalState getEmotionalState() {
        return emotionalState;
    }

    public void setEmotionalState(EmotionalState emotionalState) {
        this.emotionalState = emotionalState;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        checkSize(size);
        this.size = size;
    }

    private void checkSize(int size) {
        if (size <= 0) {
            throw new IllegalStateException("Person size must be greater than zero.");
        }
    }

    public enum PhysicalState {

        SITTING,
        JUMPING
    }

    public enum EmotionalState {

        HAPPINESS,
        SADNESS,
        ANGER,
        FEAR,
        DISGUST,
        ANXIETY
    }
}
