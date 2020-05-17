package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

//1. Создадим пакет statistic, в котором создадим класс StatisticManager.
// С его помощью будем регистрировать события в хранилище.
//2. У нас должно быть одно хранилище с одной точкой входа. Поэтому сделаем StatisticManager синглтоном.
public class StatisticManager {
    private static StatisticManager ourInstance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();
    private Set<Cook> cooks = new LinkedHashSet<>();

    private StatisticManager() {
    }

    public static StatisticManager getInstance() {
        return ourInstance;
    }

    public void register(EventDataRow data) {
        statisticStorage.put(data);
    }

    public void register(Cook cook) {
        cooks.add(cook);
    }

    public Map<Date, TreeMap<String, Integer>> getCookWorkloading() {
        List<EventDataRow> displays = statisticStorage.getStorage().get(EventType.COOKED_ORDER);
        Map<Date, TreeMap<String, Integer>> cookingTimeOfDays = new TreeMap<>(Collections.reverseOrder());
        for (EventDataRow edr : displays) {
            Calendar c = Calendar.getInstance();
            c.setTime(edr.getDate());
            GregorianCalendar gc = new GregorianCalendar(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
            Date date = gc.getTime();
            String name = ((CookedOrderEventDataRow) edr).getCookName();

            int cookingTime = (int) Math.ceil(edr.getTime());
            if (!cookingTimeOfDays.containsKey(date)) {
                TreeMap<String, Integer> cooksAndTimes = new TreeMap<>();
                cooksAndTimes.put(name, cookingTime);
                cookingTimeOfDays.put(date, cooksAndTimes);
            } else {
                TreeMap<String, Integer> cooksAndTimes = cookingTimeOfDays.get(date);
                if (!cooksAndTimes.containsKey(name)) {
                    cooksAndTimes.put(name, cookingTime);
                } else {
                    cooksAndTimes.put(name, cooksAndTimes.get(name) + cookingTime);
                }
            }
        }
        return cookingTimeOfDays;
    }

    public Map<Date, Double> getAdvertisementProfit() {
        List<EventDataRow> eventDataRowList = statisticStorage.getStorage().get(EventType.SELECTED_VIDEOS);


        Map<Date, Double> result = new TreeMap<>(Collections.reverseOrder());
        Date dateWithOutTime;
        Calendar calendar;
        double amount;

        for (EventDataRow eventDataRow : eventDataRowList) {

            VideoSelectedEventDataRow videoSelectedEventDataRow = (VideoSelectedEventDataRow) eventDataRow;
            amount = videoSelectedEventDataRow.getAmount() / 100.0;


            calendar = Calendar.getInstance();
            calendar.setTime(videoSelectedEventDataRow.getDate());
            GregorianCalendar gc = new GregorianCalendar(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            dateWithOutTime = gc.getTime();

            if (result.containsKey(dateWithOutTime)) amount += result.get(dateWithOutTime);

            result.put(dateWithOutTime, amount);
        }

        return result;
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage() {
            for (EventType eventType : EventType.values()) {
                storage.put(eventType, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data) {

            storage.get(data.getType()).add(data);
        }

        public Map<EventType, List<EventDataRow>> getStorage() {
            return storage;
        }
    }
}
