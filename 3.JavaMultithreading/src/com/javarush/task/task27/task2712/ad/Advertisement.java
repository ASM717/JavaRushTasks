package com.javarush.task.task27.task2712.ad;
/*Заказ создается, потом готовится поваром, а после этого относится посетителю. К тому же считается время выполнения заказа.
        Будем считать, что первая часть задания выполнена.
        Перейдем ко второй - пока заказ готовится, на планшете должна показываться реклама.
        Определим, что такое реклама.

        Реклама - это видео определенной продолжительности. Также известно, что кто-то оплатил количество показов.
        Будем считать, что у нас известно количество оплаченных показов, общая стоимость всех показов и сам рекламный ролик.*/


public class Advertisement {

    private Object content; //видео
    private String name; //название
    private long initialAmount; //начальная сумма, стоимость рекламы в копейках
    private int hits; //количество оплаченных показов
    private int duration; //продолжительность в секундах
    private long amountPerOneDisplaying;

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        this.amountPerOneDisplaying = initialAmount / hits;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public int getHits() {
        return hits;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public void revalidate() {
        if (hits <= 0) {
            throw new NoVideoAvailableException();
        }
        hits--;
    }
}
