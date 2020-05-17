package com.javarush.task.task27.task2712;


import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class DirectorTablet {

    public void printAdvertisementProfit() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        double totalAmount = 0;
        for (Map.Entry<Date, Double> profitOfDay : StatisticManager.getInstance().getAdvertisementProfit().entrySet()) {
            Date date = profitOfDay.getKey();
            Double amount = profitOfDay.getValue();
            totalAmount += amount;
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %.2f", sdf.format(date), amount));
        }
        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f", totalAmount));

    }

    public void printCookWorkloading() {
        Map<Date, TreeMap<String, Integer>> map = StatisticManager.getInstance().getCookWorkloading();
        List<Date> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        Collections.reverse(list);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        for (Date date : list) {
            ConsoleHelper.writeMessage(dateFormat.format(date));
            Map<String, Integer> mapSI = map.get(date);
            List<String> stringList = new ArrayList<>(mapSI.keySet());
            Collections.sort(stringList);
            for (String name : stringList) {
                ConsoleHelper.writeMessage(name + " - " + (int) Math.ceil(mapSI.get(name)) + " min");
            }
            ConsoleHelper.writeMessage("");
        }

    }

    public void printActiveVideoSet() {

        List<Advertisement> advertisementList = StatisticAdvertisementManager.getInstance().getAvailableVideos();

        Collections.sort(advertisementList, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
            }
        });

        for (Advertisement ad : advertisementList) {
            ConsoleHelper.writeMessage(ad.getName() + " - " + ad.getHits());
        }
    }

    public void printArchivedVideoSet() {
        List<Advertisement> advertisementList = StatisticAdvertisementManager.getInstance().getNotAvailableVideos();
        Collections.sort(advertisementList, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
            }
        });


        for (Advertisement ad : advertisementList) {
            ConsoleHelper.writeMessage(ad.getName());
        }
    }
}
