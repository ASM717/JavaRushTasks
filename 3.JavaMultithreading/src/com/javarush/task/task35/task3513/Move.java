package com.javarush.task.task35.task3513;

@FunctionalInterface //аннотация, которая будет сигнализировать о том,
                     // что в этом интерфейсе будет только один абстрактный метод.
public interface Move {
    void move();
}
