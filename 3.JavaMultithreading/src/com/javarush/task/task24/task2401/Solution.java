package com.javarush.task.task24.task2401;

/* 
Создание своего интерфейса-маркера
*/
public class Solution {
    public static <UnsupportedInterfaceMarkerException extends Throwable> void main(String[] args) throws Exception{
        SelfInterfaceMarkerImpl obj = new SelfInterfaceMarkerImpl();
        Util.testClass(obj);
    }

}
