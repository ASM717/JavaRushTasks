package com.javarush.task.task29.task2909.car;

public class Truck extends Car {

    private static final int MAX_TRUCK_SPEED = 80;

    protected Truck(int type, int numberOfPassengers) {
        super(type, numberOfPassengers);
    }

    public Truck(int numberOfPassengers) {
        super(numberOfPassengers);
    }

    public int getMaxSpeed() {

        return MAX_TRUCK_SPEED;
    }
}
