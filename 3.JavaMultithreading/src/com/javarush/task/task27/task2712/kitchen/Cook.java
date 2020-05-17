package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.LinkedBlockingQueue;

//Создадим класс Cook(Повар) в пакете kitchen, он будет готовить.
public class Cook extends Observable {
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    // готовим

    public void startCookingOrder(Order order) {
        StatisticManager statisticManager = StatisticManager.getInstance();
        CookedOrderEventDataRow cookedOrderEventDataRow = new CookedOrderEventDataRow(order.getTablet().toString(), name, order.getTotalCookingTime(), order.getDishes());
        statisticManager.register(cookedOrderEventDataRow);
        statisticManager.register(this);
        ConsoleHelper.writeMessage("Start cooking - " + order);


        try {
            Thread.sleep(order.getTotalCookingTime());
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        setChanged();
        notifyObservers(order);
    }

    @Override
    public String toString() {
        return name;
    }



}


