package com.javarush.task.task30.task3003;

import java.util.ArrayDeque;
import java.util.concurrent.TransferQueue;

public class Producer implements Runnable{

    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i < 10; i++) {
                //реализуй метод run так, чтобы вызов метода interrupt прерывал работу consumer и producer трэдов.
                if (Thread.currentThread().isInterrupted()) break;

                System.out.format("Элемент 'ShareItem-%s' добавлен", i);
                this.queue.offer(new ShareItem("ShareItem-" + i, i));
                Thread.sleep(100);
                if (this.queue.hasWaitingConsumer()) System.out.format("Consumer в ожидании!%n");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
