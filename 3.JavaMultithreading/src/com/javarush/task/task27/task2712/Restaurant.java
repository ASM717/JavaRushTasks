package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;


public class Restaurant {
    private static final int ORDER_CREATING_INTERVAL = 100;
    private static final LinkedBlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) throws IOException {
        Locale.setDefault(Locale.ENGLISH);

        Waiter waiter = new Waiter();

        List<Cook> cookList = new LinkedList<>();
        for (int i = 0; i < 2; i++) {
            Cook cook = new Cook("Поваренок" + i);
            StatisticManager.getInstance().register(cook);
            cook.addObserver(waiter);
            cookList.add(cook);
        }

        List<Tablet> tabletList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            Tablet tablet = new Tablet(i);
            tabletList.add(tablet);

        }

        DirectorTablet directorTablet = new DirectorTablet();

        Thread thread = new Thread(new RandomOrderGeneratorTask(tabletList, ORDER_CREATING_INTERVAL));
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            return;
        }
        thread.interrupt();

        directorTablet.printActiveVideoSet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printArchivedVideoSet();
        directorTablet.printCookWorkloading();
    }
}
