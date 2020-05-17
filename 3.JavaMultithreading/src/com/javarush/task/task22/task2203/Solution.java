package com.javarush.task.task22.task2203;

/* 
Между табуляциями
*/
public class Solution {
    public static String getPartOfString(String string) throws TooShortStringException {

        if (string == null)
            throw new TooShortStringException();
        if (string.equals(""))
            throw new TooShortStringException();
        int firstTabulation = string.indexOf("\t");
        if (firstTabulation == -1)
            throw new TooShortStringException();
        int secondTabulation = string.indexOf("\t", firstTabulation+1);
        if (secondTabulation == -1)
            throw new TooShortStringException();

        return string.substring(firstTabulation+1, secondTabulation);
    }

    public static class TooShortStringException extends Exception {
    }

    public static void main(String[] args) throws TooShortStringException {
        System.out.println(getPartOfString("\tJavaRush - лучший сервис \tобучения Java\t."));
    }
}
