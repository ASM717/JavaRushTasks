package com.javarush.task.task27.task2711;

import java.util.concurrent.CountDownLatch;

/* 
CountDownLatch
*/
public class Solution {
    //private final Object lock = new Object();
    private volatile boolean isWaitingForValue = true;

    CountDownLatch latch = new CountDownLatch(1);
    //(1) количество потоков занесенных
    //Средство синхронизации, позволяющее одному или нескольким потокам
    // дождаться завершения набора операций, выполняемых в других потоках.

    public void someMethod() throws InterruptedException {
        latch.await();
        /*
        Заставляет текущий поток ждать, пока защелка не обратится к нулю, если поток не прерывается .
        Если текущий счетчик равен нулю, этот метод возвращается немедленно.

        Если текущий счетчик больше нуля, текущий поток отключается для целей планирования потоков и остается неактивным, пока не произойдет одно из двух:

        Счет достигает нуля из-за вызовов countDown()метода; или же
        Какой-то другой поток прерывает текущий поток.
        Если текущий поток:

        имеет прерванный статус при входе в этот метод; или же
        будет прерван во время ожидания,
        затем InterruptedExceptionгенерируется и очищается статус прерывания текущего потока.
         */
        retrieveValue();
        latch.countDown();
    }

    void retrieveValue() {
        System.out.println("Value retrieved.");
    }

    public static void main(String[] args) {

    }
}
