package com.javarush.task.task32.task3209;

public class ExceptionHandler extends Throwable {
    public static void log(Exception e){
        System.out.println(e);
    }
}
