package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.io.IOException;
import java.util.List;

//В классе Order (заказ) должна быть информация, относящаяся к списку выбранных пользователем блюд.
//Т.е. где-то должен быть список всех блюд, и должен быть список выбранных блюд в классе Order.
public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws Exception {
        this.tablet = tablet;
        initDishes();
    }

    public int getTotalCookingTime() {
        int totalCookingTime = 0;
        for (Dish dish : dishes) {
            totalCookingTime += dish.getDuration();
        }
        return totalCookingTime;
//        return dishes.stream().mapToInt((s)->s.getDuration()).sum();
    }

    protected void initDishes() throws Exception {
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Tablet getTablet() {
        return tablet;
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    @Override
    public String toString() {
        return dishes.isEmpty() ? "" : String.format("Your order: %s of %s", dishes, tablet);
    }
}
