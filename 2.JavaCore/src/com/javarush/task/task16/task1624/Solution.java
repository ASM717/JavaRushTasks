package com.javarush.task.task16.task1624;

/* 
Последовательные выполнения нитей
*/

public class Solution {
    public static MyThread t = new MyThread();


    public static void main(String a[]) throws Exception {

        t.start();
        t.join();
        for (int i = 0; i < 10; i++) {
            String message = "inside main ";
            System.out.println(message + i);
            //t.start();
            //t.join();

            sleep();
        }
    }

    public static void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }

    static class MyThread extends Thread {
        String message = "inside MyThread ";

        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println(message + i);
                Solution.sleep();
            }
        }
    }
}