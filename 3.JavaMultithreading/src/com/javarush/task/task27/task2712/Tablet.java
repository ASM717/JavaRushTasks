package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;


import java.io.IOException;
import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet extends Observable {
    //номер планшета, чтобы можно было однозначно установить, откуда поступил заказ.
    // Номер планшета должен инициализироваться в конструкторе переданным параметром.
    //private final int number;
    //private static Logger logger = Logger.getLogger(Tablet.class.getName());
    final int number;
    static Logger logger = Logger.getLogger(Tablet.class.getName());
    private LinkedBlockingQueue<Order> queue;

    public Tablet(int number) {
        this.number = number;
    }
    // создадим метод, который будет создавать заказ из тех блюд, которые выберет пользователь


    public Order createOrder() throws Exception {

        Order order = null;
        try {
            order = new Order(this);
            ConsoleHelper.writeMessage(order.toString());
            setChanged();
            notifyObservers(order);
            AdvertisementManager advertisementManager = new AdvertisementManager(order.getTotalCookingTime() * 60); //секунды блять
            advertisementManager.processVideos();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        } catch (NoVideoAvailableException e) {
            logger.log(Level.INFO, "No video is available for the order " + order);
        }
        return order;
    }

    @Override
    public String toString() {
        return "Tablet{number=" + number + "}";
    }

    public void createTestOrder() throws Exception {
        try {
            TestOrder order = new TestOrder(this);
            printOrderAndShowAds(order);
        }
        catch (IOException e) {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }

    private void printOrderAndShowAds(Order order) {
        if (!order.isEmpty()) {
            ConsoleHelper.writeMessage(order.toString());
            AdvertisementManager advertisementManager = new AdvertisementManager(order.getTotalCookingTime()*60);
            try {
                advertisementManager.processVideos();
            } catch (NoVideoAvailableException e) {
                logger.log(Level.INFO, "No video is available for the order " + order.toString());
            }
        }
        queue.add(order);
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }
}
