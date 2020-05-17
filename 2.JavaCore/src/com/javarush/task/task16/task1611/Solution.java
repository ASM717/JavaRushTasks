package com.javarush.task.task16.task1611;

/* 
Часы
*/

import javax.swing.*;

import static java.lang.Thread.*;

public class Solution {
    public static volatile boolean isStopped = false;

    public static void main(String[] args) throws InterruptedException {
        Clock clock = new Clock();
        sleep(2000);
        isStopped = true;
        System.out.println("The clock has to be stopped");
        sleep(2000);
        System.out.println("Double-check");
    }

    public static class Clock extends Thread {
        public Clock() {
            setPriority(MAX_PRIORITY);
            start();
        }

        public void run() {
            try {
                while (!isStopped) {
                    printTikTak();
                }
            } catch (InterruptedException e) {
            }
        }

        private void printTikTak() throws InterruptedException {
            //add your code here - добавь код тут
            Thread.sleep(500);{
                System.out.println("Tik");
            }
            Thread.sleep(500);{
                System.out.println("Tak");
            }
        }
    }
}
