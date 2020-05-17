package com.javarush.task.task19.task1911;

/* 
Ридер обертка
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        //запоминаем настоящий PrintStream в специальную переменную
        PrintStream consoleStream = System.out;

        //Создаем динамический массив
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //создаем адаптер к классу PrintStream
        PrintStream stream = new PrintStream(outputStream);
        //Устанавливаем его как текущий System.out
        System.setOut(stream);



        //Преобразовываем записанные в наш ByteArray данные в строку


        testString.printSomething();
        //Возвращаем все как было
        System.setOut(consoleStream);
        System.out.println(outputStream.toString().toUpperCase());
    }


    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}
