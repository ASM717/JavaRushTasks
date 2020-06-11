package com.javarush.task.task32.task3203;

import java.io.PrintWriter;
import java.io.StringWriter;

/*
Пишем стек-трейс
*/
public class Solution {
    public static void main(String[] args) {
        String text = getStackTrace(new IndexOutOfBoundsException("fff"));
        System.out.println(text);
    }

    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();//create a StringWriter
        PrintWriter pw = new PrintWriter(sw);//create a PrintWriter using this string writer instance
        throwable.printStackTrace(pw);//print the stack trace to the print writer(it wraps the string writer sw)
        return sw.toString(); // we can now have the stack trace as a string
    }
}