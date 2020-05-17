package com.javarush.task.task16.task1603;

import java.util.ArrayList;
import java.util.List;

/* 
Список и нити
*/

public class Solution {
    public static volatile List<Thread> list = new ArrayList<Thread>(5);

    public static void main(String[] args) {
        //Add your code here - добавьте свой код тут
        Thread thread1 = new Thread(new SpecialThread());
        list.add(thread1);
        Thread thread2 = new Thread(new SpecialThread());
        list.add(thread2);
        Thread thread3 = new Thread(new SpecialThread());
        list.add(thread3);
        Thread thread4 = new Thread(new SpecialThread());
        list.add(thread4);
        Thread thread5 = new Thread(new SpecialThread());
        list.add(thread5);

        //for (Thread thread : list)
          //  thread.start();
    }

    public static class SpecialThread implements Runnable {
        public void run() {
            System.out.println("it's a run method inside SpecialThread");
        }
    }
}

