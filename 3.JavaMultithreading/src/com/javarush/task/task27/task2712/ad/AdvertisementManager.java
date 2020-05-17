package com.javarush.task.task27.task2712.ad;
//у каждого планшета будет свой объект менеджера, который будет подбирать оптимальный набор роликов
// и их последовательность для каждого заказа.
//Он также будет взаимодействовать с плеером и отображать ролики.

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.NoAvailableVideoEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;


/**AdvertisementManager выполняет только одно единственное действие - обрабатывает рекламное видео.*/
public class AdvertisementManager {

    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() {

        List<Advertisement> advertisements = new ArrayList<>();
        for (Advertisement ad : storage.list())
        {
            if (ad.getHits() > 0)
            {
                advertisements.add(ad);
            }
        }
        if (advertisements.isEmpty()) throw new NoVideoAvailableException();

        Collections.sort(advertisements, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                int result = Long.compare(o1.getAmountPerOneDisplaying(), o2.getAmountPerOneDisplaying());
                if (result != 0) {
                    return -result;
                }

                long oneSecondCost1 = o1.getAmountPerOneDisplaying() * 1000 / o1.getDuration();
                long oneSecondCost2 = o2.getAmountPerOneDisplaying() * 1000 / o2.getDuration();

                return Long.compare(oneSecondCost1, oneSecondCost2);
            }
        });

        int totalDuration = 0;
        long totalAmount = 0;
        List<Advertisement> adsForShow = new ArrayList<>();

        for (Advertisement ad : advertisements) {
            totalAmount += ad.getAmountPerOneDisplaying();
            totalDuration += ad.getDuration();
            if (totalDuration <= timeSeconds && ad.getDuration() <= timeSeconds) adsForShow.add(ad);
            else {
                totalAmount -= ad.getAmountPerOneDisplaying();
                totalDuration -= ad.getDuration();
            }
        }

        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(adsForShow, totalAmount, totalDuration));

        int timeLeft = timeSeconds;
        for (Advertisement advertisement : adsForShow) {
            if (timeLeft < advertisement.getDuration()) continue;

            ConsoleHelper.writeMessage(advertisement.getName() + " is displaying... "
                    + advertisement.getAmountPerOneDisplaying() + ", "
                    + advertisement.getAmountPerOneDisplaying() * 1000 / advertisement.getDuration());

            timeLeft -= advertisement.getDuration();
            advertisement.revalidate();
        }
    }
}