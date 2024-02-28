package ru.meldren.lab1.task3;

public class Person {

    private final String name;
    private Location location;
    private PhysicalState physicalState;
    private EmotionalState emotionalState;
    private boolean huge;

    public Person(String name, Location location, PhysicalState physicalState, EmotionalState emotionalState, boolean huge) {
        this.name = name;
        this.location = location;
        this.physicalState = physicalState;
        this.emotionalState = emotionalState;
        this.huge = huge;
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

    public boolean isHuge() {
        return huge;
    }

    public void setHuge(boolean huge) {
        this.huge = huge;
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
