package com.javarush.task.task25.task2512;

/* 
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        //t.interrupt();
        if (e != null) {
            uncaughtException(t, e.getCause());
            t.interrupt();
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new Solution().uncaughtException(Thread.currentThread(),
                new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI"))));
    }
}
