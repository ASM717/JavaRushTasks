package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.kitchen.Dish;
import com.javarush.task.task27.task2712.kitchen.Order;

import java.util.ArrayList;
import java.util.List;

//Класс для хранилища рекламных роликов.
public class AdvertisementStorage {

    private static AdvertisementStorage instance;
    private final List<Advertisement> videos = new ArrayList<>();

    public static AdvertisementStorage getInstance() {
        if (instance == null) instance = new AdvertisementStorage();
        return instance;
    }

    private AdvertisementStorage() {
        Object someContent = new Object();
        videos.add(new Advertisement(someContent, "First Video", 5000, 100, 3*60));
        videos.add(new Advertisement(someContent, "Second Video", 100, 10, 15*60));
        videos.add(new Advertisement(someContent, "Third Video", 400, 2, 10*60));
    }

    public List<Advertisement> list() {
        return videos;
    }

    public void add(Advertisement advertisement) {
        videos.add(advertisement);
    }
}
