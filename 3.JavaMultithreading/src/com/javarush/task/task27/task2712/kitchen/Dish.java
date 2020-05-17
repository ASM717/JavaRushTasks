package com.javarush.task.task27.task2712.kitchen;

import java.util.Arrays;

public enum Dish {
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);
    //Предположим, что нам известно время приготовления каждого блюда в минутах.
    private int duration;

    Dish(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    //Чтобы пользователь мог выбрать себе блюда, нужно их все ему отобразить
    public static String allDishesToString() {
        return Arrays.toString(Dish.values());
    }
}
