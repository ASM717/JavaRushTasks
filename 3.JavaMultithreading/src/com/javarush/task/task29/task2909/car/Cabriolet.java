package com.javarush.task.task29.task2909.car;

public class Cabriolet extends Car {

    private static final int MAX_CABRIOLET_SPEED = 90;

    protected Cabriolet(int type, int numberOfPassengers) {
        super(type, numberOfPassengers);
    }

    public Cabriolet(int numberOfPassengers) {
        super(numberOfPassengers);
    }

    public int getMaxSpeed() {

        return MAX_CABRIOLET_SPEED;
    }
}
