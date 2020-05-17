package com.javarush.task.task16.task1626;

public class Solution {
    public static int number = 1;

    public static void main(String[] args) {
        new Thread(new CountUpRunnable(), "Увеличиваем").start();
    }

    public static class CountUpRunnable implements Runnable {
        //Add your code here - добавь код тут
        private int countIndexUp = Solution.number;

        public void run() {
            try {
                while (!Thread.interrupted()) {
                    System.out.println(toString());
                    countIndexUp++;
                    Thread.sleep(500);
                    if (countIndexUp == 6) return;


                }
            } catch (InterruptedException e) {
            }
        }

        public String toString() {
            return Thread.currentThread().getName() + ": " + countIndexUp;
        }
    }
}
